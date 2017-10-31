package com.dhha22.bindadaptersample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dhha22.bindadaptersample.Navigator;
import com.dhha22.bindadaptersample.R;

public class MainActivity extends AppCompatActivity {

    private Button goSimpleBtn;
    private Button goCustomBtn;
    private Button goEndlessScrollBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goSimpleBtn = (Button) findViewById(R.id.goSimpleBtn);
        goCustomBtn = (Button) findViewById(R.id.goCustomBtn);
        goEndlessScrollBtn = (Button) findViewById(R.id.goEndlessScrollBtn);

        goSimpleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigator.goSimpleMode(MainActivity.this);
            }
        });

        goCustomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigator.goCustomMode(MainActivity.this);
            }
        });

        goEndlessScrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigator.goEndlessScroll(MainActivity.this);
            }
        });
    }

}


