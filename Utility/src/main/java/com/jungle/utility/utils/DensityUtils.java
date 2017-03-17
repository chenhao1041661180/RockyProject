package com.jungle.utility.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * 功能描述：单位换算工具类
 * <p/>
 * 创 建 人：Rocky
 * 创建时间：2017-03-10
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2017-03-10）
 */
public class DensityUtils {

    /**
     * 构造函数
     */
    private DensityUtils() {
        throw new AssertionError();
    }

    /**
     * dp转px
     *
     * @param context 上下文
     * @param dpVal   大小
     * @return 转换后大小（int）
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context 上下文
     * @param spVal   大小
     * @return 转换后大小（int）
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context 上下文
     * @param pxVal   大小
     * @return 转换后大小（float）
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px2sp
     *
     * @param context 上下文
     * @param pxVal   大小
     * @return 转换后大小（float）
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }
}
