package com.sinothk.widget.loadingRecyclerView.demo;

import android.app.Application;

/**
 * Created by jianghejie on 16/12/1.
 */

public class ExampleApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
    }
}
