package com.dhha22.bindadaptersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dhha22.bindadapter.BindAdapter;
import com.dhha22.bindadaptersample.model.User;
import com.dhha22.bindadaptersample.view.HeaderItem1View;
import com.dhha22.bindadaptersample.view.HeaderItem2View;
import com.dhha22.bindadaptersample.view.SimpleListItemView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BindAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new BindAdapter(this)
                .addLayout(SimpleListItemView.class)
                .addHeaderView(HeaderItem1View.class)
                .addHeaderView(HeaderItem2View.class);

        recyclerView.setAdapter(adapter);

        addDummyData();
    }

    private void addDummyData(){
        adapter.addItem(new User("A", 0));
        adapter.addItem(new User("B", 1));
        adapter.addItem(new User("C", 2));
        adapter.addItem(new User("D", 3));
    }

}
