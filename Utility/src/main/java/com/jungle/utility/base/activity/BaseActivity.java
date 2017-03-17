package com.jungle.utility.base.activity;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.jungle.utility.R;
import com.jungle.utility.base.BaseApplication;

/**
 * 功能描述：Activity基类
 * <p>
 * 创 建 人：Rocky
 * 创建时间：2017-03-08
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2017-03-08）
 */
public class BaseActivity extends AppCompatActivity {

    // 默认布局id
    protected static final int DEFAULT_LAYOUT = -1;
    //默认statusBar颜色
//    protected static final int DEFAULT_STATUSBAR_COLOR = -1;
    //上下文
    protected Context mContext;

    /**
     * onCreate
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onBeforeLayout();
        mContext = BaseApplication.getInstance();
        setContent();
        init(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    /**
     * 初始化控件通用方法
     *
     * @param id 控件id
     * @param <T> 泛型
     * @return 当前控件
     */
    protected <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }

    /**
     * 初始化控件通用方法
     *
     * @param view 视图View
     * @param id 控件id
     * @param <T> 泛型
     * @return 当前控件
     */
    protected <T extends View> T getView(View view, int id) {
        return (T) view.findViewById(id);
    }

    /**
     * 在加载布局之前
     */
    protected void onBeforeLayout() {

    }/**
     * 在加载布局之前
     */
    protected void setContent() {

    }

    /**
     * 加载布局文件
     *
     * @return 布局文件
     */
    protected int getLayoutId() {
        return DEFAULT_LAYOUT;
    }

    /**
     * 初始化
     *
     * @param savedInstanceState Bundle
     */
    protected void init(Bundle savedInstanceState) {

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
     * 初始化事件
     */
    protected void initEvent() {
    }

    /**
     * 是否显示Toolbar
     *
     * @return
     */
    protected boolean hasToolbar() {
        return true;
    }
    /**
     * 是否显示StatusBar
     *
     * @return
     */
    protected boolean isShowStatusBar() {
        return true;
    }
    /**
     * 设置StatusBar颜色
     *
     * @return
     */
    protected int showStatusBarColor() {
        return R.color.colorPrimary;
    }

    /**
     * 是否有返回按钮
     *
     * @return boolean
     */
    protected boolean hasBackIcon() {
        return true;
    }
}
