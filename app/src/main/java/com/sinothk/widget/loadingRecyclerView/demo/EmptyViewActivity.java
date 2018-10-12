package com.sinothk.widget.loadingRecyclerView.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.sinothk.widget.loadingRecyclerView.LoadingRecyclerView;

import java.util.ArrayList;

public class EmptyViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emptyview);

        LoadingRecyclerView mRecyclerView = this.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

//        mRecyclerView.setPullRefreshEnabled(true);
        mRecyclerView
                .getDefaultRefreshHeaderView()
                .setRefreshTimeVisible(true);


        View mEmptyView = findViewById(R.id.text_empty);
        mRecyclerView.setEmptyView(mEmptyView);

        //没有数据，触发emptyView
        ArrayList<String> listData = new ArrayList<String>();
//        listData.add("SINNOTHK1");
//        listData.add("SINNOTHK2");
//        listData.add("SINNOTHK3");

        MyAdapter mAdapter = new MyAdapter(listData);
        mRecyclerView.setAdapter(mAdapter);




    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}