package com.dhha22.bindadaptersample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dhha22.bindadapter.BindAdapter;
import com.dhha22.bindadaptersample.R;
import com.dhha22.bindadaptersample.contract.SimpleModeContract;
import com.dhha22.bindadaptersample.presenter.SimpleModePresenter;
import com.dhha22.bindadaptersample.view.FooterItemView;
import com.dhha22.bindadaptersample.view.HeaderItem1View;
import com.dhha22.bindadaptersample.view.SimpleListItemView;

/**
 * Created by DavidHa on 2017. 11. 1..
 */

public class SimpleModeMVPFragment extends Fragment implements SimpleModeContract.View {
    private SimpleModePresenter presenter;
    private Button addHeaderBtn;
    private Button addFirstItemBtn;
    private Button addItemBtn;
    private Button addFooterBtn;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SimpleModePresenter();
        presenter.view = this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_mode, container, false);
        bindView(view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final BindAdapter adapter = new BindAdapter(getContext())
                .addHeaderView(HeaderItem1View.class)
                .addLayout(SimpleListItemView.class)
                .addFooterView(FooterItemView.class);

        recyclerView.setAdapter(adapter);
        presenter.adapterModel = adapter;
        presenter.adapterView = adapter;

        presenter.loadMoreData();   // add header, item, footer data

        addHeaderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addListHeaderItem();
            }
        });

        addFirstItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addFirstListItem();
            }
        });

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addListItem();
            }
        });

        addFooterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addListFooterItem();
            }
        });

        adapter.setOnItemClickListener(presenter);

        adapter.setOnItemLongClickListener(presenter);


        return view;
    }

    @Override
    public void toast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    private void bindView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        addHeaderBtn = view.findViewById(R.id.addHeaderBtn);
        addFirstItemBtn = view.findViewById(R.id.addFirstItemBtn);
        addItemBtn = view.findViewById(R.id.addItemBtn);
        addFooterBtn = view.findViewById(R.id.addFooterBtn);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
