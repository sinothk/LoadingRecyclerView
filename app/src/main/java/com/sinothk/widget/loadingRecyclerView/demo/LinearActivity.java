package com.sinothk.widget.loadingRecyclerView.demo;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sinothk.widget.loadingRecyclerView.ProgressStyle;
import com.sinothk.widget.loadingRecyclerView.LoadingRecyclerView;
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;

import java.util.ArrayList;

public class LinearActivity extends AppCompatActivity {
    private LoadingRecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private ArrayList<String> listData;
    private int refreshTime = 0;
    private int times = 0;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // release memory
        if(mRecyclerView != null){
            mRecyclerView.destroy();
            mRecyclerView = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
//        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (LoadingRecyclerView)this.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);



        mRecyclerView.addItemDecoration(mRecyclerView.getListViewLine(this,R.drawable.divider_sample));

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

        mRecyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);

        View header = LayoutInflater.from(this).inflate(R.layout.recyclerview_header, (ViewGroup)findViewById(android.R.id.content),false);
        mRecyclerView.addHeaderView(header);

        int c = mRecyclerView.getHeaderViewsCount();

        mRecyclerView.getDefaultFootView().setLoadingHint("自定义加载中提示");
        mRecyclerView.getDefaultFootView().setNoMoreHint("自定义加载完毕提示");

        // if you use setFooterView,the default footerView will unUseful
//        TextView tv = new TextView(this);
//        tv.setText("自定义 footer");
//        mRecyclerView.setFootView(tv, new CustomFooterViewCallBack() {
//            @Override
//            public void onLoadingMore(View yourFooterView) {
//
//            }
//
//            @Override
//            public void onLoadMoreComplete(View yourFooterView) {
//
//            }
//
//            @Override
//            public void onSetNoMore(View yourFooterView, boolean noMore) {
//
//            }
//        });

        final int itemLimit = 5;

        // When the item number of the screen number is list.size-2,we call the onLoadMore
        mRecyclerView.setLimitNumberToCallLoadMore(2);

        mRecyclerView.setLoadingListener(new LoadingRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime ++;
                times = 0;
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        listData.clear();
                        for(int i = 0; i < itemLimit ;i++){
                            listData.add("item" + i + "after " + refreshTime + " times of refresh");
                        }
                        mAdapter.notifyDataSetChanged();
                        if(mRecyclerView != null)
                            mRecyclerView.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                Log.e("aaaaa","call onLoadMore");
                if(times < 2){
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            for(int i = 0; i < itemLimit ;i++){
                                listData.add("item" + (1 + listData.size() ) );
                            }
                            if(mRecyclerView != null) {
                                mRecyclerView.loadMoreComplete();
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for(int i = 0; i < itemLimit ;i++){
                                listData.add("item" + (1 + listData.size() ) );
                            }
                            if(mRecyclerView != null) {
                                mRecyclerView.setNoMore(true);
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                    }, 1000);
                }
                times ++;
            }
        });

        listData = new  ArrayList<String>();
        mAdapter = new MyAdapter(listData);
        mAdapter.setClickCallBack(
                new ItemClickCallBack<String>() {
                    @Override
                    public void onItemClick(int pos, String o) {
                        listData.remove(pos);
                        mRecyclerView.notifyItemRemoved(listData, pos);
                    }
                }
        );
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.refresh();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}










