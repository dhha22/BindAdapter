package com.dhha22.bindadapter;

import com.dhha22.bindadapter.listener.OnItemClickListener;
import com.dhha22.bindadapter.listener.OnItemLongClickListener;

/**
 * Created by DavidHa on 2017. 10. 16..
 */

public interface BindAdapterContract {
    interface View {

        // Header

        BindAdapter addHeaderView(Class<? extends ItemView> layoutClass);

        BindAdapter addHeaderView(int position, Class<? extends ItemView> layoutClass);

        ItemView getHeaderView(int position);

        void removeHeaderView(int position);

        void clearHeaderView();

        int getHeaderViewSize();

        // Item

        BindAdapter addLayout(Class<? extends ItemView> layoutClass);

        void removeItemView(int position);

        void setOnItemClickListener(OnItemClickListener itemClickListener);

        void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener);


        // Footer

        BindAdapter addFooterView(Class<? extends ItemView> layoutClass);

        BindAdapter addFooterView(int position, Class<? extends ItemView> layoutClass);

        ItemView getFooterView(int position);

        void removeFooterView(int position);

        void clearFooterView();

        int getFooterViewSize();

    }

    interface Model {

        // Header

        void addHeaderItem(Item item);

        void setHeaderItem(int position, Item item);

        Item getHeaderItem(int position);

        void removeHeaderItem(int position);

        void clearHeaderItem();

        int getHeaderItemSize();

        // Item

        void addFirstItem(Item item);

        void addItem(Item item);

        void removeItem(int position);

        void setItem(int position, Item item);

        Item getItem(int position);

        void clearItem();

        int getItemSize();

        // Footer

        void addFooterItem(Item item);

        void setFooterItem(int position, Item item);

        Item getFooterItem(int position);

        void removeFooterItem(int position);

        void clearFooterItem();

        int getFooterItemSize();

        void notifyData();


        int getTotalItemSize();
    }

}
