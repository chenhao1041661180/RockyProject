package com.jungle.utility.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.jungle.utility.R;

/**
 * 功能描述：
 * <p>
 * 创 建 人：Rocky
 * 创建时间：2017-03-08
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2017-03-08）
 */
public class BaseFragment extends Fragment {

    // 默认布局id
    protected static final int DEFAULT_FMLAYOUT = -1;
    //上下文
    protected Context mContext;
    // fragment是否显示了
    protected boolean mIsVisible = false;
    /**
     * 初始化控件
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T getView(int id) {
        return (T) getView().findViewById(id);
    }

    /**
     * 初始化控件
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T getView(View view, int id) {
        return (T) view.findViewById(id);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
    }
    /**
     * 在加载布局之前
     */
    protected void onFmBeforeLayout() {

    }

    /**
     * 加载布局文件
     *
     * @return 布局文件
     */
    protected int getFmLayoutId() {
        return DEFAULT_FMLAYOUT;
    }

    /**
     * 初始化
     *
     * @param savedInstanceState Bundle
     */
    protected void initFm(Bundle savedInstanceState) {

    }

    /**
     * 初始化控件
     */
    protected void initFmView(View view) {
    }

    /**
     * 初始化数据
     */
    protected void initFmData(View view) {
    }

    /**
     * 初始化事件
     */
    protected void initFmEvent(View view) {
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
     * 在这里实现Fragment数据的缓加载.
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    /**
     * 显示时加载数据,需要这样的使用
     * 注意声明 isPrepared，先初始化
     * 生命周期会先执行 setUserVisibleHint 再执行onActivityCreated
     * 在 onActivityCreated 之后第一次显示加载数据，只加载一次
     */
    protected void loadData() {
    }

    /**
     * 可见
     */
    protected void onVisible() {
        loadData();
    }

    /**
     * 不可见
     */
    protected void onInvisible() {
    }

    /**
     * 解绑
     */
    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * 销毁
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
