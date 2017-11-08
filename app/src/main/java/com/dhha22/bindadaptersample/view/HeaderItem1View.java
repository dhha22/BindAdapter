package com.dhha22.bindadaptersample.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.dhha22.bindadapter.Item;
import com.dhha22.bindadapter.ItemView;
import com.dhha22.bindadaptersample.R;
import com.dhha22.bindadaptersample.model.Feed;

/**
 * Created by DavidHa on 2017. 10. 16..
 */

public class HeaderItem1View extends ItemView {
    private static final String TAG = "HeaderItem1View";
    private TextView titleTxt;

    public HeaderItem1View(@NonNull Context context) {
        super(context);
        setContentView(R.layout.header_item_1);
        setFullSpan();
        titleTxt = findViewById(R.id.titleTxt);
    }

    @Override
    public void setData(Item data) {
        super.setData(data);
        if(data instanceof Feed){
            Log.d(TAG, "setData: " + ((Feed) data).name);
            titleTxt.setText(((Feed) data).name);
        }
    }
}
