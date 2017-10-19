package com.dhha22.bindadaptersample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.dhha22.bindadapter.BindAdapter;
import com.dhha22.bindadaptersample.model.User;
import com.dhha22.bindadaptersample.view.HeaderItem1View;
import com.dhha22.bindadaptersample.view.HeaderItem2View;
import com.dhha22.bindadaptersample.view.SimpleListItemView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BindAdapter adapter;
    private GridAdapter innerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position < adapter.getHeaderViewSize()){
                    return 3;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);

        innerAdapter = new GridAdapter(this);
        adapter = new BindAdapter(this)
                .addHeaderView(HeaderItem1View.class)
                .addHeaderView(HeaderItem2View.class);

        recyclerView.setAdapter(adapter);
        adapter.setInnerAdapter(innerAdapter);

        addDummyData();
    }

    private void addDummyData(){
        innerAdapter.addItem(new User("A", 0));
        innerAdapter.addItem(new User("B", 1));
        innerAdapter.addItem(new User("C", 2));
        innerAdapter.addItem(new User("D", 3));
        adapter.addHeaderItem(new User("David Ha", 0));
        adapter.notifyDataSetChanged();
    }



}
 class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder>{
     private static final String TAG = "GridAdapter";
     private List<User> users = new ArrayList<>();
     private Context context;

     public GridAdapter(Context context) {
         this.context = context;
     }

     @Override
     public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         Log.v(TAG, "inner view holder");
         return new ViewHolder(new SimpleListItemView(context));
     }

     @Override
     public void onBindViewHolder(ViewHolder holder, int position) {
         holder.v.setData(users.get(position));
     }

     @Override
     public int getItemCount() {
         return users.size();
     }

     public void addItem(User user){
         users.add(user);
         notifyDataSetChanged();
     }

     public static class ViewHolder extends RecyclerView.ViewHolder{
         final SimpleListItemView v;
        public ViewHolder(View itemView) {
            super(itemView);
            v = (SimpleListItemView)itemView;
        }
    }

}