package com.jungle.utility.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jungle.utility.R;
import com.jungle.utility.utils.ScreenUtils;

/**
 * 功能描述：
 * <p>
 * 创 建 人：Rocky
 * 创建时间：2017-03-14
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2017-03-14）
 */
public class WaitDialog {

    private Dialog mDialog;
    private View mDialogView;
    private TextView mContent;
    private WaitDialog.Builder mBuilder;

    /**
     * 构造方法
     *
     * @param builder 内部类
     */
    public WaitDialog(WaitDialog.Builder builder) {
        this.mBuilder = builder;
        //获取屏幕匡高
        int screenHeight = ScreenUtils.getScreenHeight(builder.getContext());
        int screenWidth = ScreenUtils.getScreenWidth(builder.getContext());
        mDialog = new Dialog(builder.getContext(), R.style.MyDialogStyle);
        mDialogView = View.inflate(builder.getContext(), R.layout.wait_dialog, null);
        mContent = (TextView) mDialogView.findViewById(R.id.tv_message);
        mDialogView.setMinimumHeight((int) (screenWidth /4));
        mDialog.setContentView(mDialogView);

        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.width = (int) (screenWidth * builder.getWidth());
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.width =  (int) (screenWidth/4);
        lp.height = (int) (screenWidth/4);
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        initDialog();

    }

    private void initDialog() {
        mDialog.setCanceledOnTouchOutside(mBuilder.isTouchOutside());
        mContent.setText(mBuilder.getContentText());
        mContent.setTextColor(mBuilder.getContentTextColor());
        mContent.setTextSize(mBuilder.getContentTextSize());
    }
    public void show() {
        mDialog.show();
    }

    public void dismiss() {
        mDialog.dismiss();
    }
    public Dialog getDialog() {
        return mDialog;
    }

    public static class Builder {

        private String contentText;
        private int contentTextColor;
        private int contentTextSize;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private Context mContext;

        public Builder(Context context) {

            mContext = context;
            contentText = "";
            contentTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            isTouchOutside = true;
            height = 0.23f;
            width = 0.23f;
            contentTextSize = 14;
        }

        public Context getContext() {
            return mContext;
        }


        public String getContentText() {
            return contentText;
        }

        public WaitDialog.Builder setContentText(String contentText) {
            this.contentText = contentText;
            return this;
        }

        public int getContentTextColor() {
            return contentTextColor;
        }

        public WaitDialog.Builder setContentTextColor(@ColorRes int contentTextColor) {
            this.contentTextColor = ContextCompat.getColor(mContext, contentTextColor);
            return this;
        }

        public boolean isTouchOutside() {
            return isTouchOutside;
        }

        public WaitDialog.Builder setCanceledOnTouchOutside(boolean touchOutside) {
            isTouchOutside = touchOutside;
            return this;
        }

        public float getHeight() {
            return height;
        }

        public WaitDialog.Builder setHeight(float height) {
            this.height = height;
            return this;
        }

        public float getWidth() {
            return width;
        }

        public WaitDialog.Builder setWidth(float width) {
            this.width = width;
            return this;
        }

        public int getContentTextSize() {
            return contentTextSize;
        }

        public WaitDialog.Builder setContentTextSize(int contentTextSize) {
            this.contentTextSize = contentTextSize;
            return this;
        }

        public WaitDialog build() {
            return new WaitDialog(this);
        }
    }

}
