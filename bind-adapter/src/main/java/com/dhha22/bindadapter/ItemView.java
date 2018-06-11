package com.dhha22.bindadapter;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by DavidHa on 2017. 10. 12..
 */

public class ItemView extends FrameLayout {
    protected Item data;
    private Context context;
    public ItemView(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public ItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public ItemView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void setData(Item data){
        this.data = data;
    }

    public Item getData(){
        return data;
    }

    protected void setFullSpan() {
        setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    protected void setContentView(int resource) {
        LayoutInflater.from(context).inflate(resource, this, true);
    }

}
