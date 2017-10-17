package com.dhha22.bindadaptersample.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.dhha22.bindadapter.Item;
import com.dhha22.bindadapter.ItemView;
import com.dhha22.bindadaptersample.R;
import com.dhha22.bindadaptersample.model.User;

/**
 * Created by DavidHa on 2017. 10. 12..
 */

public class SimpleListItemView extends ItemView {
    private TextView nameTxt;

    public SimpleListItemView(@NonNull Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.simple_list_item, this, true);
        nameTxt = findViewById(R.id.nameTxt);
    }

    @Override
    public void setData(Item data) {
        super.setData(data);
        if(data instanceof User) {
            nameTxt.setText(((User)data).name);
        }
    }
}
