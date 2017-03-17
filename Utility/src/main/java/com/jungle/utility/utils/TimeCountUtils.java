package com.jungle.utility.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;

import com.jungle.utility.R;

/**
 * 功能描述：计时器
 * <p/>
 * 创  建  人：陈浩
 * 创建时间：2017-03-10
 * 版  本  号：V1.0
 * 修  改  人：陈浩（2017-03-10）
 */
public class TimeCountUtils extends CountDownTimer {
    private Context mActivity;
    private Button btn;//按钮

    // 在这个构造方法里需要传入三个参数，一个是Activity，一个是总的时间millisInFuture，一个是countDownInterval，然后就是你在哪个按钮上做这个是，就把这个按钮传过来就可以了
    public TimeCountUtils(Context mActivity, long millisInFuture, long countDownInterval, Button btn) {
        super(millisInFuture, countDownInterval);
        this.mActivity = mActivity;
        this.btn = btn;
    }

    /**
     * 计时过程中回调
     *
     * @param millisUntilFinished
     */
    @Override
    public void onTick(long millisUntilFinished) {
        btn.setClickable(false);//设置不能点击
        btn.setText(millisUntilFinished / 1000 + mActivity.getResources().getString(R.string.to_resend));//设置倒计时时间

        //设置按钮为灰色，这时是不能点击的
        btn.setBackgroundColor(mActivity.getResources().getColor(R.color.base_btn_check_color));//还原背景色
        Spannable span = new SpannableString(btn.getText().toString());//获取按钮的文字
        span.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//讲倒计时时间显示为红色
        btn.setText(span);

    }

    /**
     * 计时完成回调
     */
    @Override
    public void onFinish() {
        btn.setText(mActivity.getResources().getString(R.string.to_obtain_yzm));
        btn.setClickable(true);//重新获得点击
        btn.setBackgroundColor(mActivity.getResources().getColor(R.color.base_btn_color));//还原背景色
    }
}
