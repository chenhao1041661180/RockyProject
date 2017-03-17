package com.rocky.rockyproject.fragment;


import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.rocky.rockyproject.R;
import com.rocky.rockyproject.base.BaseActivity;
import com.rocky.rockyproject.base.BaseFragment;
import com.rocky.rockyproject.utils.StatusBarUtil;
import com.rocky.rockyproject.utils.statusbarutils.StatusBarCompat;

/**
 * 功能描述：主Fragment
 * <p>
 * 创 建 人：Rocky
 * 创建时间：2016-12-16
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2016-12-16）
 */
public class MainFragment extends BaseFragment {
    private static MainFragment mainFragment;

    @Override
    public int getFragmentLayout() {
        return R.layout.frag_mainn;
    }

    /**
     * 初始化
     *
     * @return
     */
    public static MainFragment newInstance() {
        if (mainFragment == null) {
            synchronized (MainFragment.class) {
                if (mainFragment == null) {
                    mainFragment = new MainFragment();
                }
            }
        }
        return mainFragment;
    }

    @Override
    protected void initFmView(View view) {
        super.initFmView(view);

    }

}
