package com.dhha22.bindadaptersample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhha22.bindadapter.BindAdapter;
import com.dhha22.bindadaptersample.model.Feed;
import com.dhha22.bindadaptersample.view.HeaderItem1View;
import com.dhha22.bindadaptersample.view.HeaderItem2View;
import com.dhha22.bindadaptersample.view.SimpleListItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DavidHa on 2017. 10. 25..
 */

public class CustomModeFragment extends Fragment {

    private RecyclerView recyclerView;
    private BindAdapter adapter;
    private GridAdapter innerAdapter;

    public static CustomModeFragment getInstance() {
        return new CustomModeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position < adapter.getHeaderViewSize()){
                    return 3;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);

        innerAdapter = new GridAdapter(getContext());
        adapter = new BindAdapter(getContext())
                .addHeaderView(HeaderItem1View.class)
                .addHeaderView(HeaderItem2View.class);

        recyclerView.setAdapter(adapter);
        adapter.setInnerAdapter(innerAdapter);
        adapter.addItem(new Feed("A"));

        addDummyData();

        return recyclerView;
    }

    private void addDummyData(){
        innerAdapter.addItem(new Feed("A"));
        innerAdapter.addItem(new Feed("B"));
        innerAdapter.addItem(new Feed("C"));
        innerAdapter.addItem(new Feed("D"));
        adapter.addHeaderItem(new Feed("David Ha"));
        adapter.notifyData();
    }
}

class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    private static final String TAG = "GridAdapter";
    private List<Feed> feeds = new ArrayList<>();
    private Context context;

    public GridAdapter(Context context) {
        this.context = context;
    }

    @Override
    public GridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v(TAG, "inner view holder");
        return new GridAdapter.ViewHolder(new SimpleListItemView(context));
    }

    @Override
    public void onBindViewHolder(GridAdapter.ViewHolder holder, int position) {
        holder.v.setData(feeds.get(position));
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    public void addItem(Feed feed) {
        feeds.add(feed);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final SimpleListItemView v;

        public ViewHolder(View itemView) {
            super(itemView);
            v = (SimpleListItemView) itemView;
        }
    }
}

