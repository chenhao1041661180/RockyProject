package com.jungle.utility.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 功能描述：SharePreference工具类
 * <p/>
 * 创 建 人：Rocky
 * 创建时间：2017-03-10
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2017-03-10）
 */
public class PreferenceUtils {

    // 数据保存名称
    public static String PREFERENCE_NAME = "share_data";

    /**
     * 构造函数
     */
    private PreferenceUtils() {
        throw new AssertionError();
    }

    /**
     * 初始化PREFERENCE名称
     *
     * @param name PREFERENCE名称
     */
    public static void init(String name) {
        PREFERENCE_NAME = name;
    }

    /**
     * 保存数据方法，根据具体类型调用不同的保存方法
     *
     * @param context 上下文
     * @param key     key键
     * @param value   value值
     */
    public static void put(Context context, String key, Object value) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else {
            editor.putString(key, value.toString());
        }
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 获取数据方法，根据具体类型调用不同的获取方法
     *
     * @param context      上下文
     * @param key          key键
     * @param defaultValue 默认值
     * @return key的value值
     */
    public static Object get(Context context, String key, Object defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        if (defaultValue instanceof String) {
            return sp.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return sp.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Float) {
            return sp.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Long) {
            return sp.getLong(key, (Long) defaultValue);
        }
        return null;
    }

    /**
     * 移除对应的key值
     *
     * @param context 上下文
     * @param key     key键
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清空所有数据
     *
     * @param context 上下文
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 判断key是否存在
     *
     * @param context 上下文
     * @param key     key键
     * @return 是否存在
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 获取所有键值对
     *
     * @param context 上下位
     * @return 所有键值对
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 创建SharedPreferencesCompat.apply方法兼容类
     */
    private static class SharedPreferencesCompat {
        private static final Method mApplyMethod = findApplyMethod();

        /**
         * 反射查找apply方法
         *
         * @return 方法名
         */
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 查找apply方法，否则执行commit
         *
         * @param editor Editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (mApplyMethod != null) {
                    mApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException ignored) {
            }
            editor.commit();
        }
    }
}
