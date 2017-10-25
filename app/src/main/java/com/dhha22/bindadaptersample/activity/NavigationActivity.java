package com.dhha22.bindadaptersample.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.dhha22.bindadaptersample.R;

/**
 * Created by DavidHa on 2017. 10. 25..
 */

public class NavigationActivity extends AppCompatActivity{

    private static Fragment fragment;

    public static void setFragment(Fragment fragment) {
        NavigationActivity.fragment = fragment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
        fragment = null;
    }
}
