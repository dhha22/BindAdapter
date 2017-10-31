package com.dhha22.bindadapter.listener;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.dhha22.bindadapter.BindAdapter;

/**
 * Created by DavidHa on 2017. 10. 30..
 */


public class EndlessScrollListener extends RecyclerView.OnScrollListener {
    private ScrollStartSubscriber scrollStartSubscriber;
    private ScrollEndSubscriber scrollEndSubscriber;
    private int firstVisibleItemPosition;
    private int lastVisibleItemPosition;

    public EndlessScrollListener(ScrollStartSubscriber scrollStartSubscriber) {
        this(scrollStartSubscriber, null);
    }

    public EndlessScrollListener(ScrollEndSubscriber scrollEndSubscriber) {
        this(null, scrollEndSubscriber);
    }


    public EndlessScrollListener(ScrollStartSubscriber scrollStartSubscriber, ScrollEndSubscriber scrollEndSubscriber) {
        this.scrollStartSubscriber = scrollStartSubscriber;
        this.scrollEndSubscriber = scrollEndSubscriber;
    }


    public int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }


    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        if (view.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) view.getLayoutManager()).findLastVisibleItemPositions(null);
            lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);
        } else if (view.getLayoutManager() instanceof LinearLayoutManager) {
            firstVisibleItemPosition = ((LinearLayoutManager) view.getLayoutManager()).findFirstVisibleItemPosition();
            lastVisibleItemPosition = ((LinearLayoutManager) view.getLayoutManager()).findLastVisibleItemPosition();
        } else if (view.getLayoutManager() instanceof GridLayoutManager) {
            firstVisibleItemPosition = ((GridLayoutManager) view.getLayoutManager()).findFirstVisibleItemPosition();
            lastVisibleItemPosition = ((GridLayoutManager) view.getLayoutManager()).findLastVisibleItemPosition();
        }

        if (firstVisibleItemPosition == 0 && scrollStartSubscriber != null) {
            scrollStartSubscriber.onScrollStart();
        } else if (lastVisibleItemPosition == view.getLayoutManager().getItemCount() - 1 && scrollEndSubscriber != null) {
            scrollEndSubscriber.onScrollEnd();
        }

    }

}
