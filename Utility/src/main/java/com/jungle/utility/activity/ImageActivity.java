package com.jungle.utility.activity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jungle.utility.R;
import com.jungle.utility.base.activity.BaseDetailActivity;
import com.jungle.utility.config.Contants;
import com.jungle.utility.databinding.ActBasicImageBinding;

/**
 * 功能描述：查看图片
 * <p/>
 * 创 建 人：吴水平
 * 创建时间：2016-11-01
 * 版 本 号：V1.0
 * 修 改 人：陈浩（2016-11-17）
 */
public class ImageActivity extends BaseDetailActivity<ActBasicImageBinding> {
    // 放大的view
    ImageView zoomView;
    // 图片路径
    String path;
    //加载中
    ProgressDialog dialog;

    /**
     * 初始化layout
     *
     * @return layout
     */
    @Override
    protected int getLayoutId() {
        return R.layout.act_basic_image;
    }

    @Override
    protected boolean hasToolbar() {
        return false;
    }

    @Override
    protected boolean isShowStatusBar() {
        return false;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
//        setTranslucentStatus(true);
//        SystemBarTintManager tintManager = new SystemBarTintManager(
//                this);
//        tintManager.setStatusBarTintResource(R.color.black);// 通知栏所需颜色
//        tintManager.setNavigationBarTintResource(R.color.black); //NavigationBar颜色
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        try {
            // 初始化控件
            zoomView = (ImageView) findViewById(R.id.zoom_image_view);
//			dialog=new ProgressDialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
//			dialog.setMessage("正在加载中...");
//			dialog.show();
            path = getIntent().getStringExtra(Contants.IMAGE_PATH);
            Glide.with(this).load(path)
                    .thumbnail(0.1f)
                    .dontTransform()
                    .override(800, 800)
//                    .placeholder(R.drawable.img_error)
//                    .error(R.drawable.ic_picture_loading)
                    .into(zoomView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置退出动画
     *
     * @param keyCode code
     * @param event   事件
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        finish();
        // 退出动画
        overridePendingTransition(0, 0);
        return super.onKeyDown(keyCode, event);
    }
}
