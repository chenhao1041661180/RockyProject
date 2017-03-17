package com.rocky.rockyproject;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationBar.OnTabSelectedListener;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.rocky.rockyproject.base.BaseActivity;
import com.rocky.rockyproject.fragment.MainFragment;
import com.rocky.rockyproject.fragment.MessageFragment;
import com.rocky.rockyproject.fragment.MoreFragment;
import com.rocky.rockyproject.fragment.SearchFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能描述：MainActivity
 * <p>
 * 创 建 人：Rocky
 * 创建时间：2016-12-14
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2016-12-14）
 */
public class MainActivity extends BaseActivity implements OnTabSelectedListener {
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    //首页Fragment
    private MainFragment mainFragment;
    //搜索Fragment
    private SearchFragment searchFragment;
    //消息Fragment
    private MessageFragment messageFragment;
    //更多Fragment
    private MoreFragment moreFragment;

    /**
     * 初始化布局
     *
     * @return layout
     */
    @Override
    protected int getLayoutId() {
        return R.layout.act_main;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initView() {
        super.initView();
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.main, "首页").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.search, "搜索").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.message, "消息").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.more, "更多").setActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mainFragment = MainFragment.newInstance();
        transaction.replace(R.id.layFrame, mainFragment);
        transaction.commit();
    }

    /**
     * 初始化监听
     */
    @Override
    protected void initEvent() {
        super.initEvent();
    }

    /**
     * 监听选中
     *
     * @param position
     */
    @Override
    public void onTabSelected(int position) {

        FragmentManager fm = this.getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0://首页
                if (mainFragment == null) {
                    mainFragment = MainFragment.newInstance();
                }
                transaction.replace(R.id.layFrame, mainFragment);
                break;
            case 1://搜索页
                if (searchFragment == null) {
                    searchFragment = SearchFragment.newInstance();
                }
                transaction.replace(R.id.layFrame, searchFragment);
                break;
            case 2://消息页
                if (messageFragment == null) {
                    messageFragment = MessageFragment.newInstance();
                }
                transaction.replace(R.id.layFrame, messageFragment);
                break;
            case 3://更多页
                if (moreFragment == null) {
                    moreFragment = MoreFragment.newInstance();
                }
                transaction.replace(R.id.layFrame, moreFragment);
                break;
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }

    /**
     * 监听刚刚未被选中的position
     *
     * @param position
     */
    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

}
