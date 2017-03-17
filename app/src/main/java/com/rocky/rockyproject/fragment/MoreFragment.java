package com.rocky.rockyproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rocky.rockyproject.R;
import com.rocky.rockyproject.base.BaseFragment;
import com.rocky.rockyproject.utils.statusbarutils.StatusBarCompat;

/**
 * 功能描述：搜索fragment
 * <p>
 * 创 建 人：Rocky
 * 创建时间：2016-12-22
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2016-12-22）
 */
public class MoreFragment extends BaseFragment {
    //当前类
    private static MoreFragment moreFragment;

    @Override
    public int getFragmentLayout() {
        return R.layout.frag_more;
    }

    /**
     * 初始化
     * @return
     */
    public static MoreFragment newInstance() {
        if (moreFragment == null) {
            synchronized (MoreFragment.class) {
                if (moreFragment == null) {
                    moreFragment = new MoreFragment();
                }
            }
        }
        return moreFragment;
    }

}
