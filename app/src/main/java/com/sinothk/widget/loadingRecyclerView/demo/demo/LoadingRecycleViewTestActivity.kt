package com.sinothk.widget.loadingRecyclerView.demo.demo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.sinothk.widget.loadingRecyclerView.LoadingRecyclerView
import com.sinothk.widget.loadingRecyclerView.ProgressStyle
import com.sinothk.widget.loadingRecyclerView.demo.LoadingRecycleViewDemoMainActivity
import com.sinothk.widget.loadingRecyclerView.demo.MyAdapter
import com.sinothk.widget.loadingRecyclerView.demo.R
import com.sinothk.widget.loadingRecyclerView.extend.LoadingRecycleViewHeader
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack
import kotlinx.android.synthetic.main.activity_loading_recycle_view_demo.*
import java.util.*
import com.sinothk.widget.loadingRecyclerView.demo.R.id.recyclerView



class LoadingRecycleViewTestActivity : AppCompatActivity() {

    private var mAdapter: MyAdapter? = null
    private var listData: ArrayList<String>? = null
//    private var refreshTime = 0

    private var times = 0
    val pageSize = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_recycle_view_demo)

        findViewById<View>(R.id.moreBtn).setOnClickListener { startActivity(Intent(this@LoadingRecycleViewTestActivity, LoadingRecycleViewDemoMainActivity::class.java)) }
        findViewById<View>(R.id.grid).setOnClickListener { startActivity(Intent(this@LoadingRecycleViewTestActivity, LoadingRecycleGridViewTestActivity::class.java)) }

        initRV()
        initData()
    }

    private fun initData() {
        listData = ArrayList<String>()

        mAdapter = MyAdapter(listData)

        mAdapter!!.setClickCallBack { pos, value ->

            Toast.makeText(this@LoadingRecycleViewTestActivity, value.toString(), Toast.LENGTH_SHORT).show()

//            listData!!.removeAt(pos)
//            recyclerView.notifyItemRemoved(listData, pos)
        }


        recyclerView.adapter = mAdapter

        // 获取数据
        recyclerView.refresh()
    }

    private fun initRV() {
        // 设置方向
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerView.addItemDecoration(recyclerView.getListViewLine(this,R.drawable.divider_sample))

        // 设置刷新样式
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallZigZag)
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallZigZag)
        // 设置刷新样式:图标
        recyclerView.setArrowImageView(R.drawable.iconfont_downgrey)
        // 设置需要时间
        recyclerView.defaultRefreshHeaderView!!.setRefreshTimeVisible(true)
        // 设置加载更多相关信息
//        recyclerView.defaultFootView!!.setLoadingHint("正在加载...")
//        recyclerView.defaultFootView!!.setNoMoreHint("已全部加载")
        // 设置头部
        val header: View = LoadingRecycleViewHeader.getViewByLayoutId(this@LoadingRecycleViewTestActivity, R.layout.recyclerview_header)
        recyclerView.addHeaderView(header)

        // When the item number of the screen number is list.size-2,we call the onLoadMore
        recyclerView.setLimitNumberToCallLoadMore(2)

        recyclerView.setLoadingListener(object : LoadingRecyclerView.LoadingListener {
            override fun onRefresh() {

                times = 0
                Handler().postDelayed({

                    listData!!.clear()

                    for (i in 0 until pageSize) {
                        listData!!.add("item_$i")
                    }

                    mAdapter!!.notifyDataSetChanged()

                    if (recyclerView != null)
                        recyclerView.refreshComplete()

                }, 1000)            //refresh data here
            }

            override fun onLoadMore() {

                if (times < 2) {

                    Handler().postDelayed({

                        for (i in 0 until pageSize) {

                            listData?.add("item" + (1 + listData!!.size))
                        }

                        recyclerView?.loadMoreComplete()
                        mAdapter?.notifyDataSetChanged()

                    }, 1000)

                } else {

                    Handler().postDelayed({
                        for (i in 0 until pageSize) {
                            listData?.add("item" + (1 + listData!!.size))
                        }

                        recyclerView?.setNoMore(true)
                        mAdapter?.notifyDataSetChanged()

                    }, 1000)

                }
                times++
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        // release memory
        if (recyclerView != null) {
            recyclerView.destroy()
        }
    }

}
