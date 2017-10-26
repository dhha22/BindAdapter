package com.dhha22.bindadapter;

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

    @Override
    public void addHeaderItem(Item item) {
        headerItems.add(item);
    }

    @Override
    public void setHeaderItem(int position, Item item) {
        headerItems.set(position, item);
    }

    @Override
    public void clearHeaderItem(Item item) {
        headerItems.clear();
    }

    @Override
    public int getHeaderItemSize() {
        return headerItems.size();
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public void setItem(int position, Item item) {
        items.set(position, item);
    }

    @Override
    public Item getItem(int position) {
        return items.get(position);
    }

    @Override
    public void clearItem() {
        items.clear();
    }

    @Override
    public int getItemSize() {
        return items.size();
    }

    @Override
    public void addFooterItem(Item item) {
        footerItems.add(item);
    }

    @Override
    public void setFooterItem(int position, Item item) {
        footerItems.set(position, item);
    }

    @Override
    public void clearFooterItem(Item item) {
        footerItems.clear();
    }

    @Override
    public int getFooterItemSize() {
        return footerItems.size();
    }

    @Override
    public void notifyData() {
        if(innerAdapter != null){
            innerAdapter.notifyDataSetChanged();
        }
        notifyDataSetChanged();
    }

}
