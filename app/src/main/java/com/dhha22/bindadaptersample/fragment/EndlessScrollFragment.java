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
import android.widget.Toast;

import com.dhha22.bindadapter.BindAdapter;
import com.dhha22.bindadapter.listener.EndlessScrollListener;
import com.dhha22.bindadapter.listener.ScrollEndSubscriber;
import com.dhha22.bindadapter.listener.ScrollStartSubscriber;
import com.dhha22.bindadaptersample.R;
import com.dhha22.bindadaptersample.model.Feed;
import com.dhha22.bindadaptersample.view.SimpleListItemView;

/**
 * Created by DavidHa on 2017. 10. 30..
 */

public class EndlessScrollFragment extends Fragment {
    private static final String TAG = "EndlessScrollFragment";
    private static final int DEFAULT_LENGTH = 20;
    private RecyclerView recyclerView;
    private BindAdapter adapter;

    public static EndlessScrollFragment getInstance() {
        return new EndlessScrollFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new BindAdapter(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_endless_scroll, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BindAdapter(getContext())
                .addLayout(SimpleListItemView.class);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndlessScrollListener(scrollStartSubscriber, scrollEndSubscriber));
        loadMoreData();
        return view;
    }

    private ScrollStartSubscriber scrollStartSubscriber = new ScrollStartSubscriber() {
        @Override
        public void onScrollStart() {
            Log.v(TAG, "scroll start");
        }
    };

    private ScrollEndSubscriber scrollEndSubscriber = new ScrollEndSubscriber() {
        @Override
        public void onScrollEnd() {
            Toast.makeText(getContext(), "load more data", Toast.LENGTH_SHORT).show();
            loadMoreData();
        }
    };


    private void loadMoreData() {
        for (int i = 0; i < DEFAULT_LENGTH; i++) {
            adapter.addItem(new Feed("Item " + adapter.getItemSize()));
        }
        adapter.notifyData();
    }
}
