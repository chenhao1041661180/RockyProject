package com.jungle.utility.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：Intent工具类
 * <p/>
 * 创 建 人：Rocky
 * 创建时间：2017-03-10
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2017-03-10）
 */
public class IntentUtils {

    /**
     * 构造函数
     */
    private IntentUtils() {
        throw new AssertionError();
    }

    /**
     * 开启Activity
     *
     * @param context 上下文
     * @param cls     调整界面
     */
    public static void startActivity(Context context, Class<?> cls) {
        Map<String, String> map = new HashMap<>();
        startActivity(context, cls, map);
    }

    /**
     * 开启Activity
     *
     * @param context 上下文
     * @param cls     调整界面
     * @param map     Map对象
     */
    public static void startActivity(Context context, Class<?> cls, Map<String, String> map) {
        Intent intent = new Intent(context, cls);
        intent.setClass(context, cls);
        // 判断Map对象是否为空
        if (!HashMapUtils.isEmpty(map)) {
            for (String key : map.keySet()) {
                intent.putExtra(key, map.get(key));
            }
        }
        // 查询是否有符合条件的Activity
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    /**
     * 开启Activity
     *
     * @param context 上下文
     * @param cls     调整界面
     * @param bundle  Bundle对象
     */
    public static void startActivity(Context context, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        intent.setClass(context, cls);
        // 判断Bundle是否为空
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        // 查询是否有符合条件的Activity
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }
}
