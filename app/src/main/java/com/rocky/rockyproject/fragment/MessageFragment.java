package com.rocky.rockyproject.fragment;

import android.view.View;
import android.widget.ViewFlipper;

import com.rocky.rockyproject.R;
import com.rocky.rockyproject.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能描述：搜索fragment
 * <p>
 * 创 建 人：Rocky
 * 创建时间：2016-12-22
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2016-12-22）
 */
public class MessageFragment extends BaseFragment {

    private static MessageFragment messageFragment;
    @BindView(R.id.viewflipper)
    ViewFlipper viewFlipper;

    /**
     * 初始化布局
     * @return
     */
    @Override
    public int getFragmentLayout() {
        return R.layout.frag_message;
    }

    /**
     * 初始化
     *
     * @return
     */
    public static MessageFragment newInstance() {
        if (messageFragment == null) {
            synchronized (MessageFragment.class) {
                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                }
            }
        }
        return messageFragment;
    }

    @Override
    protected void initFmView(View view) {
        super.initFmView(view);
        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewflipper);
        viewFlipper.addView(View.inflate(mContext, R.layout.view_viewflipper_one, null));
        viewFlipper.addView(View.inflate(mContext, R.layout.view_viewflipper_two, null));
    }
}
