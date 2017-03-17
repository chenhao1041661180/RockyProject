package com.jungle.utility.utils;

import android.content.Context;

import com.jungle.utility.view.dialog.DialogInterface;
import com.jungle.utility.view.dialog.MDAlertDialog;
import com.jungle.utility.view.dialog.MDSelectionDialog;
import com.jungle.utility.view.dialog.NormalAlertDialog;
import com.jungle.utility.view.dialog.NormalSelectionDialog;
import com.jungle.utility.view.dialog.WaitDialog;

import java.util.List;

/**
 * 功能描述：Dialog工具类
 * <p>
 * 创 建 人：Rocky
 * 创建时间：2017-03-14
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2017-03-14）
 */
public class DialogUtils {
    // 等待提示框
    private static WaitDialog mDialog;

    /**
     * 构造函数
     */
    private DialogUtils() {
        throw new AssertionError();
    }

    /**
     * 提示对话框
     *
     * @param context 上下文
     * @param title   标题
     * @param message 内容
     */
    public static void showNormalDialog(Context context, int title, int message) {
        showNormalDialog(context, context.getResources().getString(title), context.getResources().getString(message), null);
    }

    /**
     * 提示对话框
     *
     * @param context 上下文
     * @param title   标题
     * @param message 内容
     */
    public static void showNormalDialog(Context context, String title, String message) {
        showNormalDialog(context, title, message, null);
    }

    /**
     * MD风格提示对话框
     *
     * @param context 上下文
     * @param title   标题
     * @param message 内容
     */
    public static void showMDDialog(Context context, int title, int message) {
        showMDDialog(context, context.getResources().getString(title), context.getResources().getString(message), null);
    }

    /**
     * MD风格提示对话框
     *
     * @param context 上下文
     * @param title   标题
     * @param message 内容
     */
    public static void showMDDialog(Context context, String title, String message) {
        showMDDialog(context, title, message, null);
    }

    /**
     * 单键提示对话框
     *
     * @param context 上下文
     * @param title   标题
     * @param message 内容
     */
    public static void showSingleDialog(Context context, int title, int message) {
        showSingleDialog(context, context.getResources().getString(title), context.getResources().getString(message), null);
    }

    /**
     * 单键提示对话框
     *
     * @param context 上下文
     * @param title   标题
     * @param message 内容
     */
    public static void showSingleDialog(Context context, String title, String message) {
        showSingleDialog(context, title, message, null);
    }
    /**
     * 单键提示对话框
     *
     * @param context 上下文
     * @param title   标题
     * @param message 内容
     */
    public static void showSingleDialog(Context context, String title, String message, DialogInterface.OnSingleClickListener<NormalAlertDialog> listener) {
        try {
            new NormalAlertDialog.Builder(context)
                    .setTitleVisible(true).setTitleText(title)
                    .setContentText(message)
                    .setSingleMode(true)
                    .setSingleListener(listener)
                    .build()
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * MD样式提示对话框
     *
     * @param context 上下文
     * @param title   标题
     * @param message 内容
     */
    public static void showMDDialog(Context context, String title, String message,
                                    DialogInterface.OnLeftAndRightClickListener<MDAlertDialog> listener) {
        try {
            new MDAlertDialog.Builder(context)
                    .setTitleText(title)
                    .setContentText(message)
                    .setOnclickListener(listener).build().show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 普通风格提示对话框
     *
     * @param context 上下文
     * @param title   标题
     * @param message 内容
     */
    public static void showNormalDialog(Context context, String title, String message,
                                        DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog> listener) {
        try {
            new NormalAlertDialog.Builder(context)
                    .setTitleText(title)
                    .setContentText(message)
                    .setOnclickListener(listener)
                    .build()
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提示对话框
     *
     * @param context  上下文
     * @param datas    集合
     * @param listener 接口
     */
    public static void showMDSelectDialog(Context context, List<String> datas, DialogInterface.OnItemClickListener<MDSelectionDialog> listener) {
        try {
            new MDSelectionDialog.Builder(context)
                    .setCanceledOnTouchOutside(true)
                    .setOnItemListener(listener)
                    .build()
                    .setDatas(datas)
                    .show();
        } catch (Exception e) {

        }

    }

    /**
     * IOS样式底部弹出框
     *
     * @param context 上下文
     * @param title   标题
     * @param datas 数据集合
     * @param isTitleVisible 是否显示标题
     * @param listener  事件
     */
    public static void showBottomDialog(Context context, String title,List<String> datas,boolean isTitleVisible,
                                        DialogInterface.OnItemClickListener<NormalSelectionDialog> listener) {
        try {
            new NormalSelectionDialog.Builder(context)
                    .setlTitleVisible(isTitleVisible)
                    .setTitleText(title)  //设置标题提示文本
                    .setOnItemListener(listener)
                    .setCanceledOnTouchOutside(true)  //设置是否可点击其他地方取消dialog
                    .build()
                    .setDatas(datas)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }/**
     * IOS样式底部弹出框
     *
     * @param context 上下文
     * @param title   标题
     * @param datas 数据集合
     * @param listener  事件
     */
    public static void showBottomDialog(Context context, String title,List<String> datas,
                                        DialogInterface.OnItemClickListener<NormalSelectionDialog> listener) {
        try {
            new NormalSelectionDialog.Builder(context)
                    .setlTitleVisible(true)
                    .setTitleText(title)  //设置标题提示文本
                    .setOnItemListener(listener)
                    .setCanceledOnTouchOutside(true)  //设置是否可点击其他地方取消dialog
                    .build()
                    .setDatas(datas)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 提示用户登录对话框
     *
     * @param context 上下文
     */
    public static void showLoginStatus(final Context context, final Class<?> cls) {

    }

    /**
     * 显示等待提示框
     *
     * @param context 上下文
     * @param message 内容
     * @return WaitDialog
     */
    public static WaitDialog showWaitDialog(Context context, String message) {
        mDialog = new WaitDialog.Builder(context).setContentText(message).build();
        mDialog.show();
        return mDialog;
    }

    /**
     * 显示等待提示框
     *
     * @param context 上下文
     * @param resId   资源ID
     * @return WaitDialog
     */
    public static WaitDialog showWaitDialog(Context context, int resId) {
        mDialog = new WaitDialog.Builder(context).setContentText(context.getResources().getString(resId)).build();
        mDialog.show();
        return mDialog;
    }

    /**
     * 销毁等待提示框
     *
     * @param dialog WaitDialog
     */
    public static void dismissWaitDialog(WaitDialog dialog) {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    /**
     * 销毁等待提示框
     */
    public static void dismissWaitDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
}
