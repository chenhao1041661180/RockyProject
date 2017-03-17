package com.rocky.rockyproject.base;

import android.app.Application;

/**
 * 功能描述：Application类
 * <p>
 * 创 建 人：Rocky
 * 创建时间：2016-12-14
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2016-12-14）
 */
public class BaseApplication extends Application {
    public static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static  BaseApplication getInstance()
    {
        return instance;
    }
}
