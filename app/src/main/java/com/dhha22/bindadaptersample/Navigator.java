package com.dhha22.bindadaptersample;

import android.content.Context;
import android.content.Intent;

import com.dhha22.bindadaptersample.activity.NavigationActivity;
import com.dhha22.bindadaptersample.fragment.CustomModeFragment;
import com.dhha22.bindadaptersample.fragment.EndlessScrollFragment;
import com.dhha22.bindadaptersample.fragment.SimpleModeFragment;

/**
 * Created by DavidHa on 2017. 10. 25..
 */

public class Navigator {
    public static void goSimpleMode(Context context) {
        Intent intent = new Intent(context, NavigationActivity.class);
        NavigationActivity.setFragment(SimpleModeFragment.getInstance());
        context.startActivity(intent);
    }

    public static void goCustomMode(Context context) {
        Intent intent = new Intent(context, NavigationActivity.class);
        NavigationActivity.setFragment(CustomModeFragment.getInstance());
        context.startActivity(intent);
    }

    public static void goEndlessScroll(Context context) {
        Intent intent = new Intent(context, NavigationActivity.class);
        NavigationActivity.setFragment(EndlessScrollFragment.getInstance());
        context.startActivity(intent);
    }
}
