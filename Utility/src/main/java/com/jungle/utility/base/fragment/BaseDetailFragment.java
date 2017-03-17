package com.jungle.utility.base.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jungle.utility.R;

import rx.subscriptions.CompositeSubscription;

/**
 * 功能描述：
 * <p>
 * 创 建 人：Rocky
 * 创建时间：2017-03-09
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2017-03-09）
 */
public class BaseDetailFragment<V extends ViewDataBinding> extends BaseFragment {
    // 布局view
    protected V bindingView;
    // 内容布局
    protected RelativeLayout mContainer;
    //Subscription
    private CompositeSubscription mCompositeSubscription;

    /**
     * 创建View
     * @param inflater  布局填充器
     * @param container  父容器
     * @param savedInstanceState Bundle
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        onFmBeforeLayout();
        View view = inflater.inflate(R.layout.frag_base_list, container, false);
        bindingView = DataBindingUtil.inflate(inflater, getFmLayoutId(), null, false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(params);
        mContainer = (RelativeLayout) view.findViewById(R.id.container);
        mContainer.addView(bindingView.getRoot());

        initFm(savedInstanceState);
        initFmView(view);
        initFmData(view);
        initFmEvent(view);
        return view;
    }

    /**
     * Activity创建
     * @param savedInstanceState Bundle
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     *View销毁
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
