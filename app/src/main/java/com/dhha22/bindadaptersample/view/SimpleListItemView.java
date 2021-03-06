package com.dhha22.bindadaptersample.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.dhha22.bindadapter.Item;
import com.dhha22.bindadapter.ItemView;
import com.dhha22.bindadaptersample.R;
import com.dhha22.bindadaptersample.model.Feed;

/**
 * Created by DavidHa on 2017. 10. 12..
 */

public class SimpleListItemView extends ItemView {
    private TextView nameTxt;

    public SimpleListItemView(@NonNull Context context) {
        super(context);
        setContentView(R.layout.simple_list_item);
        nameTxt = findViewById(R.id.nameTxt);
    }

    @Override
    public void setData(Item data) {
        super.setData(data);
        if(data instanceof Feed) {
            nameTxt.setText(((Feed)data).name);
        }
    }
}
