package com.sinothk.widget.loadingRecyclerView.decorations;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


/**
 * Created by Peng on 2017/8/25.
 */
public class StaggeredGridDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = StaggeredGridDecoration.class.getName();
    private int space;
    private int columnCount;
    private int headerCount;

    public StaggeredGridDecoration(int space, int columnCount, int headerCount) {
        this.space = space;
        this.columnCount = columnCount;
        this.headerCount = headerCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);
        if (position < headerCount) { //mWrapAdapter.getHeadersCount()
            return;
        }

        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.top = space;
        }

        //瀑布流专属分割线
        StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
        /**
         * 根据params.getSpanIndex()来判断左右边确定分割线
         * 第一列设置左边距为space，右边距为space/2  （第二列反之）
         */
        if (params.getSpanIndex() % columnCount == 0) {
            outRect.left = space;
            outRect.right = space / 2;
        } else if (params.getSpanIndex() % columnCount == columnCount - 1) {
            outRect.left = space / 2;
            outRect.right = space;
        } else {
            outRect.left = space / 2;
            outRect.right = space / 2;
        }
    }
}
