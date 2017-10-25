package com.dhha22.bindadaptersample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dhha22.bindadapter.BindAdapter;
import com.dhha22.bindadaptersample.R;
import com.dhha22.bindadaptersample.model.Feed;
import com.dhha22.bindadaptersample.view.FooterItemView;
import com.dhha22.bindadaptersample.view.HeaderItem1View;
import com.dhha22.bindadaptersample.view.HeaderItem2View;
import com.dhha22.bindadaptersample.view.SimpleListItemView;

import java.util.logging.Logger;

/**
 * Created by DavidHa on 2017. 10. 25..
 */

public class SimpleModeFragment extends Fragment {
    private static final String TAG = "SimpleModeFragment";
    private Button addHeaderBtn;
    private Button addItemBtn;
    private Button addFooterBtn;
    private RecyclerView recyclerView;

    public static SimpleModeFragment getInstance() {
        return new SimpleModeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recyclerView = new RecyclerView(getContext());
        View view = inflater.inflate(R.layout.fragment_simple_mode, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        addHeaderBtn = view.findViewById(R.id.addHeaderBtn);
        addItemBtn = view.findViewById(R.id.addItemBtn);
        addFooterBtn = view.findViewById(R.id.addFooterBtn);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final BindAdapter adapter = new BindAdapter(getContext())
                .addHeaderView(HeaderItem1View.class)
                .addLayout(SimpleListItemView.class)
                .addFooterView(FooterItemView.class);

        recyclerView.setAdapter(adapter);
        adapter.addHeaderItem(new Feed("Header 0"));    // add header data
        adapter.addItem(new Feed("Item 0"));            // add item data
        adapter.addItem(new Feed("Item 1"));
        adapter.addItem(new Feed("Item 2"));
        adapter.addItem(new Feed("Item 3"));
        adapter.addItem(new Feed("Item 4"));
        adapter.addFooterItem(new Feed("Footer 0"));    // add footer data
        adapter.notifyData();

        addHeaderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getHeaderViewSize() % 2 == 0) {
                    adapter.addHeaderView(HeaderItem1View.class);
                } else {
                    adapter.addHeaderView(HeaderItem2View.class);
                }
                Log.d(TAG, "onClick: add header");
                adapter.addHeaderItem(new Feed("Header " + adapter.getHeaderItemSize()));
                adapter.notifyData();
            }
        });

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: add item");
                adapter.addItem(new Feed("Item " + adapter.getItemSize()));
                adapter.notifyData();
            }
        });

        addFooterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: add footer");
                adapter.addFooterView(FooterItemView.class);
                adapter.addFooterItem(new Feed("Footer " + adapter.getFooterItemSize()));
                adapter.notifyData();
            }
        });
        return view;
    }

}
