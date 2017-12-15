package com.dhha22.bindadapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DavidHa on 2017. 10. 12..
 */

public abstract class AbsAdapter extends RecyclerView.Adapter implements BindAdapterContract.Model {

    protected RecyclerView.Adapter innerAdapter;
    protected List<Item> headerItems = new ArrayList<>();
    protected List<Item> footerItems = new ArrayList<>();
    protected List<Item> items = new ArrayList<>();
    private final Handler handler = new Handler();
    private final Runnable notifyRunnable = new Runnable() {
        @Override
        public void run() {
            if (innerAdapter != null) {
                innerAdapter.notifyDataSetChanged();
            }
            notifyDataSetChanged();
        }
    };

    // Header

    @Override
    public void addHeaderItem(Item item) {
        headerItems.add(item);
    }

    @Override
    public void setHeaderItem(int position, Item item) {
        headerItems.set(position, item);
    }

    @Override
    public void removeHeaderItem(int position) {
        if (position >= 0 && position < headerItems.size()) {
            headerItems.remove(position);
        }
    }

    @Override
    public void clearHeaderItem() {
        headerItems.clear();
    }

    @Override
    public int getHeaderItemSize() {
        return headerItems.size();
    }

    // Item


    @Override
    public void addFirstItem(Item item) {
        items.add(0, item);
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }


    @Override
    public void removeItem(int position) {
        if (position >= 0 && position < items.size())
            items.remove(position);
    }

    @Override
    public void setItem(int position, Item item) {
        items.set(position, item);
    }

    @Override
    public Item getItem(int position) {
        if (headerItems.size() > 0 && headerItems.size() > position) {
            return headerItems.get(position);
        } else if (items.size() > position) {
            return items.get(headerItems.size() + position);
        } else {
            return footerItems.get(headerItems.size() + items.size() + position);
        }

    }

    @Override
    public void clearItem() {
        items.clear();
    }

    @Override
    public int getItemSize() {
        return items.size();
    }

    // Footer

    @Override
    public void addFooterItem(Item item) {
        footerItems.add(item);
    }

    @Override
    public void setFooterItem(int position, Item item) {
        footerItems.set(position, item);
    }

    @Override
    public void removeFooterItem(int position) {
        if (position >= 0 && position < footerItems.size()) {
            footerItems.remove(position);
        }
    }

    @Override
    public void clearFooterItem() {
        footerItems.clear();
    }

    @Override
    public int getFooterItemSize() {
        return footerItems.size();
    }


    @Override
    public void notifyData() {
        handler.post(notifyRunnable);
    }

    @Override
    public int getTotalItemSize() {
        return headerItems.size() + items.size() + footerItems.size();
    }
}
