package com.sinothk.widget.loadingRecyclerView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sinothk.widget.loadingRecyclerView.progressindicator.AVLoadingIndicatorView;
import com.sinothk.widget.loadingRecyclerView.utils.UnitUtil;


public class LoadingMoreFooter extends LinearLayout {

    private SimpleViewSwitcher progressCon;
    public final static int STATE_LOADING = 0;
    public final static int STATE_COMPLETE = 1;
    public final static int STATE_NOMORE = 2;

    private Context mContext;

    private TextView mText;
    private String loadingHint;
    private String noMoreHint;
    private String loadingDoneHint;

    private int loadingViewHeight = 50;// 容器高度
    private int loadingViewBackgroundColor = R.color.load_more_bg;// 容器背景


    private AVLoadingIndicatorView progressView;

    public LoadingMoreFooter(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    /**
     * @param context
     * @param attrs
     */
    public LoadingMoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public void destroy() {
        progressCon = null;
        if (progressView != null) {
            progressView.destroy();
            progressView = null;
        }
    }

    /**
     * 加载更多容器高度
     *
     * @param viewHeight 默认48dp
     */
    public void setLoadingViewHeight(int viewHeight) {
        loadingViewHeight = viewHeight;
        setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UnitUtil.dp2px(mContext, loadingViewHeight)));
    }

    public void setLoadingViewBackgroundColor(int backgroundColor) {
        if (loadingViewBackgroundColor != 0) {
            setBackgroundColor(mContext.getResources().getColor(backgroundColor));
        }
    }

    public void setLoadingHint(String hint) {
        loadingHint = hint;
    }

    public void setNoMoreHint(String hint) {
        noMoreHint = hint;
    }

    public void setLoadingDoneHint(String hint) {
        loadingDoneHint = hint;
    }

    public void initView() {
        // 根属性
        setGravity(Gravity.CENTER);
        setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UnitUtil.dp2px(mContext, loadingViewHeight)));//ViewGroup.LayoutParams.WRAP_CONTENT

        if (loadingViewBackgroundColor != 0) {
            setBackgroundColor(mContext.getResources().getColor(loadingViewBackgroundColor));
        }

        // 动画
        progressCon = new SimpleViewSwitcher(getContext());
        progressCon.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        progressView = new AVLoadingIndicatorView(this.getContext());
        progressView.setIndicatorColor(0xffB5B5B5);
        progressView.setIndicatorId(ProgressStyle.BallSpinFadeLoader);
        progressCon.setView(progressView);

        addView(progressCon);

        // 文字
        mText = new TextView(getContext());
        mText.setText(getContext().getString(R.string.listview_loading));

        if (loadingHint == null || loadingHint.equals("")) {
            loadingHint = (String) getContext().getText(R.string.listview_loading);
        }
        if (noMoreHint == null || noMoreHint.equals("")) {
            noMoreHint = (String) getContext().getText(R.string.nomore_loading);
        }
        if (loadingDoneHint == null || loadingDoneHint.equals("")) {
            loadingDoneHint = (String) getContext().getText(R.string.loading_done);
        }

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins((int) getResources().getDimension(R.dimen.textandiconmargin), 0, 0, 0);

        mText.setLayoutParams(layoutParams);
        mText.setTextColor(getContext().getResources().getColor(R.color.load_more_txt));

        addView(mText);
    }

    public void setProgressStyle(int style) {
        if (style == ProgressStyle.SysProgress) {
            progressCon.setView(new ProgressBar(getContext(), null, android.R.attr.progressBarStyle));
        } else {
            progressView = new AVLoadingIndicatorView(this.getContext());
            progressView.setIndicatorColor(0xffB5B5B5);
            progressView.setIndicatorId(style);
            progressCon.setView(progressView);
        }
    }

    public void setState(int state) {
        switch (state) {
            case STATE_LOADING:
                progressCon.setVisibility(View.VISIBLE);
                mText.setText(loadingHint);
                this.setVisibility(View.VISIBLE);
                break;
            case STATE_COMPLETE:
                mText.setText(loadingDoneHint);
                this.setVisibility(View.GONE);
                break;
            case STATE_NOMORE:
                mText.setText(noMoreHint);
                progressCon.setVisibility(View.GONE);
                this.setVisibility(View.VISIBLE);
                break;
        }
    }


}
