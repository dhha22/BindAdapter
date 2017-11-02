package com.dhha22.bindadaptersample.contract;

/**
 * Created by DavidHa on 2017. 11. 1..
 */

public interface SimpleModeContract {
    interface View {
        void toast(String message);
    }

    interface Presenter {
        void addListHeaderItem();
        void addListItem();
        void addListFooterItem();
        void loadMoreData();
        void onDestroy();
    }
}
