package com.jungle.utility.utils;

import java.util.Map;

/**
 * 功能描述：HashMap工具类
 * <p/>
 * 创 建 人：陈浩
 * 创建时间：2017-03-10
 * 版 本 号：V1.0
 * 修 改 人：陈浩（2017-03-10）
 */
public class HashMapUtils {

    /**
     * 构造函数
     */
    private HashMapUtils() {
        throw new AssertionError();
    }

    /**
     * 判断Map是否为空
     *
     * @param map Map对象
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 是否为空
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return (map == null || map.size() == 0);
    }
}
