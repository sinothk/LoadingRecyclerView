# LoadingRecyclerView
下拉刷新，滚动到底部加载更多以及添加header功能的的RecyclerView。使用方式和RecyclerView完全一致，不需要额外的layout，不需要写特殊的adater。 

# 引入

## Step 1. Add the JitPack repository to your build file
  Add it in your root build.gradle at the end of repositories:

    allprojects {
      repositories {
        ...
        maven { url 'https://jitpack.io' }
      }
    }
  
## Step 2. Add the dependency

    dependencies {
            implementation 'com.github.sinothk:LoadingRecyclerView:1.3.1013'
    }

# 使用
  ## XML
      <com.sinothk.widget.loadingRecyclerView.LoadingRecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#0f0" />
        
  ## Kotlin
    
         // ListView：设置方向
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        recyclerView.addItemDecoration(recyclerView.getListViewLine(this, R.drawable.divider_sample))

        // 网格
        recyclerView.layoutManager = GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(recyclerView.getGridViewLine(10))

        // 设置刷新样式
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallZigZag)
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallZigZag)
        // 设置刷新样式:图标
        recyclerView.setArrowImageView(R.drawable.iconfont_downgrey)
        // 设置需要时间
        recyclerView.defaultRefreshHeaderView!!.setRefreshTimeVisible(false)
        // 设置加载更多相关信息
        recyclerView.defaultFootView!!.setLoadingHint("正在加载...")
        recyclerView.defaultFootView!!.setNoMoreHint("已全部加载")
//        recyclerView.defaultFootView!!.setLoadingViewBackgroundColor(R.color.load_more_bg)
        // 设置头部
        val header: View = LoadingRecycleViewHeader.getViewByLayoutId(this@LoadingRecycleGridViewTestActivity, R.layout.recyclerview_header)
        recyclerView.addHeaderView(header)

        // When the item number of the screen number is list.size-2,we call the onLoadMore
        recyclerView.setLimitNumberToCallLoadMore(2)

        recyclerView.setLoadingListener(object : LoadingRecyclerView.LoadingListener {
            override fun onRefresh() {
                        recyclerView.refreshComplete()
            }

            override fun onLoadMore() {
                    recyclerView?.loadMoreComplete()
                    mAdapter?.notifyDataSetChanged()
            }
        })
    
