package com.jungle.utility.utils;

/**
 * Object工具类
 * <p/>
 * 创 建 人：陈浩
 * 创建时间：2017-03-10
 * 版 本 号：V1.0
 * 修 改 人：陈浩（2017-03-10）
 */
public class ObjectUtils {

    private ObjectUtils() {
        throw new AssertionError();
    }

    /**
     * 判断两个对象是否相等
     *
     * @param actual
     * @param expected
     * @return boolean
     */
    public static boolean isEquals(Object actual, Object expected) {
        return (actual == expected) || (actual == null ? expected == null : actual.equals(expected));
    }

    /**
     * 对象转换为字符
     *
     * @param str
     * @return String
     */
    public static String nullStrToEmpty(Object str) {
        return (str == null ? "" : (str instanceof String ? (String) str : str.toString()));
    }
}
