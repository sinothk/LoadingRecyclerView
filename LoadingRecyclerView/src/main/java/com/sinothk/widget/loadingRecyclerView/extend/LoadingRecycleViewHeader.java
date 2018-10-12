package com.sinothk.widget.loadingRecyclerView.extend;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ author LiangYT
 * @ create 2018/10/12 11:20
 * @ Describe
 */
public class LoadingRecycleViewHeader {

    public static View getViewByLayoutId(Activity activity, int layoutId) {
        return LayoutInflater.from(activity).inflate(layoutId, (ViewGroup) activity.findViewById(android.R.id.content), false);
    }
}
