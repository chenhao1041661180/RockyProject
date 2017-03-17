package com.jungle.utility.utils;

import android.content.Context;

/**
 * 功能描述：反射获取资源ID工具类
 * <p/>
 * 创  建  人：陈浩
 * 创建时间：2016-07-28
 * 版  本  号：V1.0
 * 修  改  人：陈浩（2016-07-28）
 */
public class ReflectUtils {
    /**
     * 得到layout Id
     *
     * @param context  上下文
     * @param layoutId 布局编号
     * @return Layout布局ID
     */
    public static int getLayoutId(Context context, String layoutId) {
        return context.getResources().getIdentifier(layoutId, "layout",
                context.getPackageName());
    }

    /**
     * 得到String Id
     *
     * @param context  上下文
     * @param stringId String编号
     * @return String资源ID
     */
    public static int getStringId(Context context, String stringId) {
        return context.getResources().getIdentifier(stringId, "string",
                context.getPackageName());
    }

    /**
     * 得到Drawable Id
     *
     * @param context    上下文
     * @param drawableId Drawable编号
     * @return Drawable资源ID
     */
    public static int getDrawableId(Context context, String drawableId) {
        return context.getResources().getIdentifier(drawableId,
                "drawable", context.getPackageName());
    }

    /**
     * 得到Style Id
     *
     * @param context 上下文
     * @param styleId Style编号
     * @return Style资源ID
     */
    public static int getStyleId(Context context, String styleId) {
        return context.getResources().getIdentifier(styleId, "style",
                context.getPackageName());
    }

    /**
     * 得到控件ID
     *
     * @param context   上下文
     * @param controlId 控件编号
     * @return 控件资源ID
     */
    public static int getControlId(Context context, String controlId) {
        return context.getResources().getIdentifier(controlId, "id",
                context.getPackageName());
    }

    /**
     * 得到颜色ID
     *
     * @param context 上下文
     * @param colorId 颜色编号
     * @return Color资源ID
     */
    public static int getColorId(Context context, String colorId) {
        return context.getResources().getIdentifier(colorId, "color",
                context.getPackageName());
    }

    /**
     * 得到Array数组Id
     *
     * @param context 上下文
     * @param arrayId Array编号
     * @return Array资源ID
     */
    public static int getArrayId(Context context, String arrayId) {
        return context.getResources().getIdentifier(arrayId, "array",
                context.getPackageName());
    }
}
