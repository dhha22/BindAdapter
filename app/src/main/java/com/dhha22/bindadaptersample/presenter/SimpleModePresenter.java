package com.dhha22.bindadaptersample.presenter;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dhha22.bindadapter.BindAdapter;
import com.dhha22.bindadapter.BindAdapterContract;
import com.dhha22.bindadapter.listener.OnItemClickListener;
import com.dhha22.bindadapter.listener.OnItemLongClickListener;
import com.dhha22.bindadaptersample.contract.SimpleModeContract;
import com.dhha22.bindadaptersample.model.Feed;
import com.dhha22.bindadaptersample.view.FooterItemView;
import com.dhha22.bindadaptersample.view.HeaderItem1View;
import com.dhha22.bindadaptersample.view.HeaderItem2View;

/**
 * Created by DavidHa on 2017. 10. 16..
 */

public class SimpleModePresenter implements SimpleModeContract.Presenter,
        OnItemLongClickListener, OnItemClickListener {
    private static final String TAG = "SimpleModePresenter";
    public SimpleModeContract.View view;
    public BindAdapterContract.Model adapterModel;
    public BindAdapterContract.View adapterView;

    @Override
    public void addListHeaderItem() {
        if (adapterView.getHeaderViewSize() % 2 == 0) {
            adapterView.addHeaderView(HeaderItem1View.class);
        } else {
            adapterView.addHeaderView(HeaderItem2View.class);
        }
        adapterModel.addHeaderItem(new Feed("Header " + adapterModel.getHeaderItemSize()));
        adapterModel.notifyData();
    }

    @Override
    public void addListItem() {
        adapterModel.addItem(new Feed("Item " + adapterModel.getItemSize()));
        adapterModel.notifyData();
    }

    @Override
    public void addListFooterItem() {
        adapterView.addFooterView(FooterItemView.class);
        adapterModel.addFooterItem(new Feed("Footer " + adapterModel.getFooterItemSize()));
        adapterModel.notifyData();
    }

    @Override
    public void loadMoreData() {
        adapterModel.addHeaderItem(new Feed("Header 0"));    // add header data
        adapterModel.addItem(new Feed("Item 0"));            // add item data
        adapterModel.addItem(new Feed("Item 1"));
        adapterModel.addItem(new Feed("Item 2"));
        adapterModel.addItem(new Feed("Item 3"));
        adapterModel.addItem(new Feed("Item 4"));
        adapterModel.addFooterItem(new Feed("Footer 0"));    // add footer data
        adapterModel.notifyData();
    }

    @Override
    public void onItemClick(View view, int position) {
        this.view.toast("click current position " + position);
    }

    @Override
    public void onItemLongClick(View view, int position) {
        this.view.toast("long click current position " + position);
    }

    @Override
    public void onDestroy() {
        view = null;
        adapterView = null;
        adapterModel = null;
    }
}
