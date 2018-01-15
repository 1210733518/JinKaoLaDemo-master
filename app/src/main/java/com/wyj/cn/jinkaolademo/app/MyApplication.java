package com.wyj.cn.jinkaolademo.app;

import android.app.Application;

import com.guaju.screenrecorderlibrary.ScreenRecorderHelper;

/**
 * Created by acer-pc on 2017/12/26.
 */

public class MyApplication extends Application {
    private ScreenRecorderHelper instance;
    public static MyApplication app;
    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        //init
        instance = ScreenRecorderHelper.getInstance(this);
    }
    public ScreenRecorderHelper getSRHelper(){
        return instance;
    }
    public static MyApplication getApp(){
        return app;
    }
}
