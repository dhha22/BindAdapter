package com.dhha22.bindadaptersample.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;

import com.dhha22.bindadapter.ItemView;
import com.dhha22.bindadaptersample.R;

/**
 * Created by DavidHa on 2017. 10. 16..
 */

public class HeaderItem1View extends ItemView {
    public HeaderItem1View(@NonNull Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.header_item_1, this, true);
    }
}
