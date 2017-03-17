package com.rocky.rockyproject.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 功能描述：基类
 * <p>
 * 创 建 人：Rocky
 * 创建时间：2016-12-14
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2016-12-14）
 */
public abstract class BaseActivity extends AppCompatActivity {
    public static int DEFAULT_LAYOUT = -1;
    //注解
    private Unbinder unbinder;
    public Context mContext;

    /**
     * onnCreate方法
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateViewBrfore();
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
        }
        //注解绑定
        unbinder = ButterKnife.bind(this);
        mContext = getApplicationContext();
        initView();
        initData();
        initEvent();
    }

    /**
     * 初始化Layout
     */
    protected abstract int getLayoutId() ;

    /**
     * 创建View之前调用
     */
    protected void onCreateViewBrfore() {
    }

    /**
     * 初始化控件
     */
    protected void initView() {
    }

    /**
     * 初始化数据
     */
    protected void initData() {
    }

    /**
     * 初始化监听
     */
    protected void initEvent() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注解解绑
        unbinder.unbind();
    }
}
