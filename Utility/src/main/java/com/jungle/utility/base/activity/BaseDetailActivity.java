package com.jungle.utility.base.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jaeger.library.StatusBarUtil;
import com.jungle.utility.R;
import com.jungle.utility.databinding.ViewDetailBaseBinding;
import com.jungle.utility.utils.ResourceUtils;

/**
 * 功能描述：
 * <p>
 * 创 建 人：Rocky
 * 创建时间：2017-03-08
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2017-03-08）
 */
public class BaseDetailActivity<SV extends ViewDataBinding> extends BaseActivity {
    //Toolbar
    private Toolbar mToolbar;
    // 布局view
    protected SV bindingView;
    //baseBinding
    private ViewDetailBaseBinding mBaseBinding;

    /**
     * 初始化Bundle
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        int layoutId = getLayoutId();
        if (layoutId != DEFAULT_LAYOUT) {
            mBaseBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_detail_base, null, false);
            bindingView = DataBindingUtil.inflate(getLayoutInflater(), layoutId, null, false);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            bindingView.getRoot().setLayoutParams(params);
            RelativeLayout mContainer = (RelativeLayout) mBaseBinding.getRoot().findViewById(R.id.container);
            mContainer.addView(bindingView.getRoot());
            getWindow().setContentView(mBaseBinding.getRoot());
            if (isShowStatusBar()) {
                // 设置透明状态栏
                StatusBarUtil.setColor(this, ResourceUtils.getColor(showStatusBarColor()), 0);
            }
        }
        //toolbar处理
        if (hasToolbar()) {
            initToolbar();
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
     * 是否显示ToolBar
     * @return
     */
    protected boolean hasToolbar() {
        return true;
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
     * 销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBaseBinding = null;
        bindingView = null;

    }
}
