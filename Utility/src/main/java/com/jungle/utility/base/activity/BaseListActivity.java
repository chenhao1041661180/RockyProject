package com.jungle.utility.base.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jaeger.library.StatusBarUtil;
import com.jungle.utility.R;
import com.jungle.utility.databinding.ActivityBaseBinding;
import com.jungle.utility.utils.ResourceUtils;
import com.weavey.loading.lib.LoadingLayout;

/**
 * 功能描述：
 * <p>
 * 创 建 人：Rocky
 * 创建时间：2017-03-08
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2017-03-08）
 */
public class BaseListActivity<V extends ViewDataBinding> extends BaseActivity {
    //Toolbar
    private Toolbar mToolbar;
    //初始化控件
//    private LinearLayout llProgressBar;
    //    private View error_refresh;
//    private View progressView;
    // 布局view
    protected V bindingView;
    //baseBinding
    private ActivityBaseBinding mBaseBinding;
    //动画
    private AnimationDrawable mAnimationDrawable;

    /**
     * 初始化bundle
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        int layoutId = getLayoutId();
        if (layoutId != DEFAULT_LAYOUT) {
            mBaseBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_base, null, false);
            bindingView = DataBindingUtil.inflate(getLayoutInflater(), layoutId, null, false);
            // content
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            bindingView.getRoot().setLayoutParams(params);
            RelativeLayout mContainer = (RelativeLayout) mBaseBinding.getRoot().findViewById(R.id.container);
            mContainer.addView(bindingView.getRoot());
            getWindow().setContentView(mBaseBinding.getRoot());
            //判断是否已经加载进入stubProgressBar
            ImageView imageView = getView(mBaseBinding.llLoadinglayout.getGlobalLoadingPage(), R.id.img_progress);
            // 加载动画
            mAnimationDrawable = (AnimationDrawable) imageView.getDrawable();
            // 默认进入页面就开启动画
            if (!mAnimationDrawable.isRunning()) {
                mAnimationDrawable.start();
            }
            mBaseBinding.llLoadinglayout.setOnReloadListener(new LoadingLayout.OnReloadListener() {
                @Override
                public void onReload(View v) {
                    showLoading();
                    onRefresh();
                }
            });
            //处理Toolbar
            if (hasToolbar()) {
                initToolbar();
            } else {
                mBaseBinding.toolBar.setVisibility(View.GONE);
            }

            if (isShowStatusBar()) {
                // 设置透明状态栏
                StatusBarUtil.setColor(this, ResourceUtils.getColor(showStatusBarColor()), 0);
            }
//            bindingView.getRoot().setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    protected void setTitle(String title) {
        if (hasToolbar())
            mToolbar.setTitle(title);
    }

    /**
     * 设置标题
     *
     * @param resId 资源id
     */
    public void setTitle(int resId) {
        if (resId > 0 && hasToolbar())
            mToolbar.setTitle(getString(resId));
    }

    /**
     * 设置左侧图标
     *
     * @param resId 资源ID
     */
    protected void setLeftIcon(int resId) {
        if (hasBackIcon() && hasToolbar())
            mToolbar.setNavigationIcon(resId);
    }

    /**
     * 初始化Toolbar
     */
    protected void initToolbar() {
        mToolbar = mBaseBinding.toolBar;
        setTitle(R.string.app_name);

        // 默认设置为返回图标
        setLeftIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        setSupportActionBar(mToolbar);
        // 不设置navigation的事件时， 默认finish();
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 显示加载中视图
     */
    protected void showLoading() {
        //加载中
        mBaseBinding.llLoadinglayout.setStatus(LoadingLayout.Loading);
        // 开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        //隐藏
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }

    }

    /**
     * 显示加载完成视图
     */
    protected void showContentView() {
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        //加载成功
        mBaseBinding.llLoadinglayout.setStatus(LoadingLayout.Success);
        //显示
        if (bindingView.getRoot().getVisibility() != View.VISIBLE) {
            bindingView.getRoot().setVisibility(View.VISIBLE);
        }

    }

    /**
     * 显示加载失败视图
     */
    protected void showError() {
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        mBaseBinding.llLoadinglayout.setStatus(LoadingLayout.Error);//加载失败
        //隐藏
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
    }

    /**
     * 无数据
     */
    protected void showNoData() {
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        mBaseBinding.llLoadinglayout.setStatus(LoadingLayout.Empty);//无数据
        //隐藏
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
    }

    /**
     * 无网络
     */
    protected void showNoNetwork() {
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        mBaseBinding.llLoadinglayout.setStatus(LoadingLayout.No_Network);//无网络
        //隐藏
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
    }
    /**
     * 显示加载失败的view
     */
//    private void showErrorView() {
//        if (error_refresh.getVisibility() != View.VISIBLE) {
//            error_refresh.setVisibility(View.VISIBLE);
//        } else {
//            error_refresh.setVisibility(View.GONE);
//        }
//    }

    /**
     * 失败后点击刷新
     */
    protected void onRefresh() {

    }

    /**
     * 销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBaseBinding = null;
        bindingView = null;

    }
}
