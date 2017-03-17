package com.jungle.utility.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 功能描述：Toast工具类
 * <p/>
 * 创 建 人：陈浩
 * 创建时间：2017-03-10
 * 版 本 号：V1.0
 * 修 改 人：陈浩（2017-03-10）
 */
public class ToastUtils {
    // 定义Toast对象
    private static Toast toast;

    /**
     * 私有构造函数
     */
    private ToastUtils() {
        throw new AssertionError();
    }

    /**
     * 显示Toast
     *
     * @param context  上下文
     * @param content  内容
     * @param duration 时长
     */
    public static void showToast(Context context,
                                 String content, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    duration);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * 显示Toast
     *
     * @param context  上下文
     * @param resId    资源编号
     * @param duration 时长
     */
    public static void showToast(Context context,
                                 int resId, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    resId,
                    duration);
        } else {
            toast.setText(resId);
        }
        toast.show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context 上下文
     * @param message 显示文字
     */
    public static void showShort(Context context, String message) {
        showToast(context, message, Toast.LENGTH_SHORT);
    }

    /**
     * 短时间显示Toast
     *
     * @param context 上下文
     * @param resId   资源ID
     */
    public static void showShort(Context context, int resId) {
        showToast(context, resId, Toast.LENGTH_SHORT);
    }

    /**
     * 长时间显示Toast
     *
     * @param context 上下文
     * @param message 显示文字
     */
    public static void showLong(Context context, String message) {
        showToast(context, message, Toast.LENGTH_LONG);
    }

    /**
     * 长时间显示Toast
     *
     * @param context 上下文
     * @param resId   资源ID
     */
    public static void showLong(Context context, int resId) {
        showToast(context, resId, Toast.LENGTH_LONG);
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context  上下文
     * @param message  显示文字
     * @param duration 显示时间
     */
    public static void showCustom(Context context, String message, int duration) {
        showToast(context, message, duration);
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context  上下文
     * @param resId    资源ID
     * @param duration 显示时间
     */
    public static void showCustom(Context context, int resId, int duration) {
        showToast(context, resId, duration);
    }

}
