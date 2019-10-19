package com.sinothk.widget.loadingRecyclerView.decorations;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Peng on 2017/8/25.
 */
public class GridDecoration extends RecyclerView.ItemDecoration {

    private int space;

    private int columnCount;
    private int headerCount;

    public GridDecoration(int space, int columnCount, int headerCount) {
        this.space = space;
        this.columnCount = columnCount;
        this.headerCount = headerCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int position = parent.getChildAdapterPosition(view);
        if (position < headerCount) { //mWrapAdapter.getHeadersCount()
            return;
        }

        if (parent.getLayoutManager() != null) {
            if (parent.getLayoutManager() instanceof LinearLayoutManager && !(parent.getLayoutManager() instanceof GridLayoutManager)) {

                if (((LinearLayoutManager) parent.getLayoutManager()).getOrientation() == LinearLayoutManager.HORIZONTAL) {
                    outRect.set(space, 0, space, 0);
                } else {
                    outRect.set(0, space, 0, space);
                }
            } else {
//                outRect.set(space, space, space, space);

                if (parent.getChildAdapterPosition(view) != 0) {
                    outRect.top = space;
                }

                GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) view.getLayoutParams();

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
    }
}
