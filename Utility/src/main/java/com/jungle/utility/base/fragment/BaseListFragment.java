package com.jungle.utility.base.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jungle.utility.R;
import com.weavey.loading.lib.LoadingLayout;

import rx.subscriptions.CompositeSubscription;

/**
 * 功能描述：
 * <p>
 * 创 建 人：Rocky
 * 创建时间：2017-03-09
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2017-03-09）
 */
public class BaseListFragment<V extends ViewDataBinding> extends BaseFragment {

    // 布局view
    protected V bindingView;
    // 加载中
//    private LinearLayout mLlProgressBar;
    // 加载失败
//    private LinearLayout mRefresh;
    // 内容布局
    protected RelativeLayout mContainer;
    // 动画
    private AnimationDrawable mAnimationDrawable;
    //Rx
    private CompositeSubscription mCompositeSubscription;
    private LoadingLayout loadingLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        onFmBeforeLayout();//c创建之前
        //急着布局
        View view = inflater.inflate(R.layout.frag_base_list, container, false);
        //databinding
        bindingView = DataBindingUtil.inflate(inflater, getFmLayoutId(), null, false);
        //设置LayoutParams
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(params);
        mContainer = (RelativeLayout) view.findViewById(R.id.container);
        loadingLayout = (LoadingLayout) view.findViewById(R.id.ll_loadinglayout);
        mContainer.addView(bindingView.getRoot());
        //加载失败及无网络时按钮点击事件
        loadingLayout.setOnReloadListener(new LoadingLayout.OnReloadListener() {
            @Override
            public void onReload(View v) {
                showLoading();
                onRefresh();
            }
        });
        initFm(savedInstanceState);
        initFmView(view);
        initFmData(view);
        initFmEvent(view);
        return view;
    }

    /**
     * ActivityCreated Activity创建回调
     *
     * @param savedInstanceState Bundle
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mLlProgressBar = getView(R.id.ll_progress_bar);
        ImageView img = getView(loadingLayout.getGlobalLoadingPage(), R.id.img_progress);

        // 加载动画
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();
//        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
//        mRefresh = getView(R.id.ll_error_refresh);
//        // 点击加载失败布局
//        mRefresh.setOnClickListener(new PerfectClickListener() {
//            @Override
//            protected void onNoDoubleClick(View v) {
//                showLoading();
//                onRefresh();
//            }
//        });
        bindingView.getRoot().setVisibility(View.GONE);

    }

    /**
     * 加载失败后点击后的操作
     */
    protected void onRefresh() {

    }

    /**
     * 显示加载中状态
     */
    protected void showLoading() {
//        if (mLlProgressBar.getVisibility() != View.VISIBLE) {
//            mLlProgressBar.setVisibility(View.VISIBLE);
//        }
        //        if (mRefresh.getVisibility() != View.GONE) {
//            mRefresh.setVisibility(View.GONE);
//        }
        // 开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        //加载中
        loadingLayout.setStatus(LoadingLayout.Loading);
        //隐藏
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
    }

    /**
     * 加载完成的状态
     */
    protected void showContentView() {
//        if (mLlProgressBar.getVisibility() != View.GONE) {
//            mLlProgressBar.setVisibility(View.GONE);
//        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        //加载成功
        loadingLayout.setStatus(LoadingLayout.Success);
//        if (mRefresh.getVisibility() != View.GONE) {
//            mRefresh.setVisibility(View.GONE);
//        }
        //显示
        if (bindingView.getRoot().getVisibility() != View.VISIBLE) {
            bindingView.getRoot().setVisibility(View.VISIBLE);
        }
    }

    /**
     * 加载失败点击重新加载的状态
     */
    protected void showError() {
//        if (mLlProgressBar.getVisibility() != View.GONE) {
//            mLlProgressBar.setVisibility(View.GONE);
//        }
        //        if (mRefresh.getVisibility() != View.VISIBLE) {
//            mRefresh.setVisibility(View.VISIBLE);
//        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        //加载失败
        loadingLayout.setStatus(LoadingLayout.Error);
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
        loadingLayout.setStatus(LoadingLayout.Empty);//无数据
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
        loadingLayout.setStatus(LoadingLayout.No_Network);//无网络
        //隐藏
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
    }

    /**
     * View销毁
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bindingView = null;
    }
//    public void addSubscription(Subscription s) {
//        if (this.mCompositeSubscription == null) {
//            this.mCompositeSubscription = new CompositeSubscription();
//        }
//        this.mCompositeSubscription.add(s);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
//            this.mCompositeSubscription.unsubscribe();
//        }
//    }
//
//    public void removeSubscription() {
//        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
//            this.mCompositeSubscription.unsubscribe();
//        }
//    }
}
