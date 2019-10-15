package com.sinothk.widget.loadingRecyclerView.lines;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.sinothk.widget.loadingRecyclerView.R;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/10 on 18:10
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class RecyclerLineView extends RecyclerView.ItemDecoration {

    private int dividerHeight;
    private Paint dividerPaint;

    public RecyclerLineView(Context context) {
        dividerPaint = new Paint();
        dividerPaint.setColor(context.getResources().getColor(R.color.load_more_bg));
        dividerHeight = 2;
    }

    public RecyclerLineView(Context context, int colorResId) {
        dividerPaint = new Paint();
        dividerPaint.setColor(context.getResources().getColor(colorResId));
        dividerHeight = 2;
    }

    public RecyclerLineView(Context context, int colorResId, int dimenResId) {
        dividerPaint = new Paint();
        dividerPaint.setColor(context.getResources().getColor(colorResId));
        dividerHeight = context.getResources().getDimensionPixelSize(dimenResId);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + dividerHeight;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }
    }
}

