package com.sinothk.widget.loadingRecyclerView.utils;

import android.content.Context;

/**
 * @ author LiangYT
 * @ create 2018/10/12 15:51
 * @ Describe
 */
public class UnitUtil {
    public static int dp2px(Context mContext, int dpValue) {
        return (int) mContext.getResources().getDisplayMetrics().density * dpValue;
    }
}
