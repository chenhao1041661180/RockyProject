package com.jungle.utility.utils;

import com.orhanobut.logger.Logger;

/**
 * 功能描述：日志工具类
 * <p/>
 * 创 建 人：陈浩
 * 创建时间：2017-03-10
 * 版 本 号：V1.0
 * 修 改 人：陈浩（2017-03-10）
 */
public class LogUtils {

    // 是否调试
    private static boolean IsDebug = true;

    /**
     * 构造函数
     */
    private LogUtils() {
        throw new AssertionError();
    }

    /**
     * 设置是否调试
     *
     * @param isDebug 是否调试
     */
    public static void init(boolean isDebug) {
        IsDebug = isDebug;
    }

    /**
     * 记录VERBOSE日志
     *
     * @param message 日志内容
     */
    public static void v(String message) {
        if (IsDebug) {
            Logger.v(message);
        }
    }

    /**
     * 记录VERBOSE日志
     *
     * @param tag     日志标识
     * @param message 日志内容
     */
    public static void v(String tag, String message) {
        if (IsDebug) {
            Logger.t(tag).v(message);
        }
    }

    /**
     * 记录DEBUG日志
     *
     * @param message 日志内容
     */
    public static void d(String message) {
        if (IsDebug) {
            Logger.d(message);
        }
    }

    /**
     * 记录DEBUG日志
     *
     * @param tag     日志标识
     * @param message 日志内容
     */
    public static void d(String tag, String message) {
        if (IsDebug) {
            Logger.t(tag).d(message);
        }
    }

    /**
     * 记录INFO日志
     *
     * @param message 日志内容
     */
    public static void i(String message) {
        if (IsDebug) {
            Logger.i(message);
        }
    }

    /**
     * 记录INFO日志
     *
     * @param tag     日志标识
     * @param message 日志内容
     */
    public static void i(String tag, String message) {
        if (IsDebug) {
            Logger.t(tag).i(message);
        }
    }

    /**
     * 记录WARN日志
     *
     * @param message 日志内容
     */
    public static void w(String message) {
        if (IsDebug) {
            Logger.w(message);
        }
    }

    /**
     * 记录WARN日志
     *
     * @param tag     日志标识
     * @param message 日志内容
     */
    public static void w(String tag, String message) {
        if (IsDebug) {
            Logger.t(tag).w(message);
        }
    }

    /**
     * 记录ERROR日志
     *
     * @param message 日志内容
     */
    public static void e(String message) {
        if (IsDebug) {
            Logger.e(message);
        }
    }

    /**
     * 记录ERROR日志
     *
     * @param tag     日志标识
     * @param message 日志内容
     */
    public static void e(String tag, String message) {
        if (IsDebug) {
            Logger.t(tag).e(message);
        }
    }

    /**
     * 记录JSON日志
     *
     * @param message 日志内容
     */
    public static void json(String message) {
        if (IsDebug) {
            Logger.json(message);
        }
    }

    /**
     * 记录JSON日志
     *
     * @param tag     日志标识
     * @param message 日志内容
     */
    public static void json(String tag, String message) {
        if (IsDebug) {
            Logger.t(tag).json(message);
        }
    }

    /**
     * 记录XML日志
     *
     * @param message 日志内容
     */
    public static void xml(String message) {
        if (IsDebug) {
            Logger.xml(message);
        }
    }

    /**
     * 记录XML日志
     *
     * @param tag     日志标识
     * @param message 日志内容
     */
    public static void xml(String tag, String message) {
        if (IsDebug) {
            Logger.t(tag).xml(message);
        }
    }
}
