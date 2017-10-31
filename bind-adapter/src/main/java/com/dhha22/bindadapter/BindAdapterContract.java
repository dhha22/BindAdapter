package com.dhha22.bindadapter;

import com.dhha22.bindadapter.listener.OnItemClickListener;
import com.dhha22.bindadapter.listener.OnItemLongClickListener;

/**
 * Created by DavidHa on 2017. 10. 16..
 */

public interface BindAdapterContract {
    interface View {
        BindAdapter addHeaderView(Class<? extends ItemView> layoutClass);

        ItemView getHeaderView(int position);

        void removeAllHeaderView();

        int getHeaderViewSize();

        BindAdapter addFooterView(Class<? extends ItemView> layoutClass);

        ItemView getFooterView(int position);

        void removeAllFooterView();

        int getFooterViewSize();

        BindAdapter addLayout(Class<? extends ItemView> layoutClass);

        ItemView getItemView();

        void setOnItemClickListener(OnItemClickListener itemClickListener);

        void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener);
    }

    interface Model {

        void addHeaderItem(Item item);

        void setHeaderItem(int position, Item item);

        void clearHeaderItem(Item item);

        int getHeaderItemSize();

        void addItem(Item item);

        void setItem(int position, Item item);

        Item getItem(int position);

        void clearItem();

        int getItemSize();

        void addFooterItem(Item item);

        void setFooterItem(int position, Item item);

        void clearFooterItem(Item item);

        int getFooterItemSize();

        void notifyData();

        int getTotalItemSize();
    }

}
