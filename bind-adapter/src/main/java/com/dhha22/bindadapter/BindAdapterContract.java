package com.dhha22.bindadapter;

import com.dhha22.bindadapter.listener.OnItemClickListener;
import com.dhha22.bindadapter.listener.OnItemLongClickListener;

/**
 * Created by DavidHa on 2017. 10. 16..
 */

public interface BindAdapterContract {
    interface View {
        BindAdapter addHeaderView(Class<? extends ItemView> layoutClass);

        void removeAllHeaderView();

        BindAdapter addFooterView(Class<? extends ItemView> layoutClass);

        void removeAllFooterView();

        BindAdapter addLayout(Class<? extends ItemView> layoutClass);

        void setOnItemClickListener(OnItemClickListener itemClickListener);

        void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener);
    }

    interface Model {
        void addItem(Item item);

        void setItem(int position, Item item);

        int getHeaderSize();

        void clear();

        Item getItem(int position);

        void notifyData();
    }

}
