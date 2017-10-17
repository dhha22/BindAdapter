package com.dhha22.bindadapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DavidHa on 2017. 10. 12..
 */

public abstract class AbsAdapter extends RecyclerView.Adapter implements BindAdapterContract.View, BindAdapterContract.Model {

    protected List<Item> list = new ArrayList<>();

    @Override
    public void addItem(Item item) {
        list.add(item);
    }

    @Override
    public void setItem(int position, Item item) {
        list.set(position, item);
    }

    @Override
    public Item getItem(int position) {
        return list.get(position);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void notifyData() {
        notifyDataSetChanged();
    }

}
