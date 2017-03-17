package com.rocky.rockyproject.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rocky.rockyproject.R;
import com.rocky.rockyproject.utils.statusbarutils.StatusBarCompat;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 功能描述：
 * <p>
 * 创 建 人：Rocky
 * 创建时间：2016-12-16
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2016-12-16）
 */
public abstract class BaseFragment extends Fragment {
    //注解
    private Unbinder unbinder;
    public Context mContext;

    /**
     * onCreate
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * onActivityCreated
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * onCreateView
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        if (getFragmentLayout() > 0) {
            view = inflater.inflate(getFragmentLayout(), container, false);
        }
        if (view != null) {
            mContext = getActivity().getApplicationContext();
            unbinder = ButterKnife.bind(this, view);
            initFmView(view);
            initFmData(view);
            initFmEvent(view);
            initToolBar(view);
        }
        return view;
    }

    /**
     * 初始化ToolBar
     */
    private void initToolBar(View view) {
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getContext(), R.color.colorPrimary));
    }

    /**
     * onViewCreated
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * onHiddenChanged
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    /**
     * 获取FRAGMENT布局
     *
     * @return FRAGMENT布局
     */
    public abstract int getFragmentLayout();

    /**
     * 初始化
     */
    protected void initFm() {

    }

    /**
     * 初始化Fragment View
     *
     * @param view Fragment布局
     */
    protected void initFmView(View view) {

    }

    /**
     * 初始化Fragment Data
     *
     * @param view Fragment布局
     */
    protected void initFmData(View view) {
    }

    /**
     * 初始化Fragment Event
     *
     * @param view Fragment布局
     */
    protected void initFmEvent(View view) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
