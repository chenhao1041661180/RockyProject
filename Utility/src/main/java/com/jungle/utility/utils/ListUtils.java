package com.jungle.utility.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * List工具类
 * <p/>
 * 创 建 人：Rocky
 * 创建时间：2017-03-10
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2017-03-10）
 */
public class ListUtils {

    /* 默认分隔符 */
    public static final String DEFAULT_JOIN_SEPARATOR = ",";

    private ListUtils() {
        throw new AssertionError();
    }

    /**
     * 获取List的数量
     *
     * @param list
     * @return 返回数量
     */
    public static <V> int getSize(List<V> list) {
        return list == null ? 0 : list.size();
    }

    /**
     * 判断List是否为空
     *
     * @param list
     * @return boolean
     */
    public static <V> boolean isEmpty(List<V> list) {
        return (list == null || list.size() == 0);
    }

    /**
     * 判断两个List是否相同
     *
     * @param actual
     * @param expected
     * @return boolean
     */
    public static <V> boolean isEquals(ArrayList<V> actual,
                                       ArrayList<V> expected) {
        if (actual == null) {
            return expected == null;
        }

        if (expected == null) {
            return false;
        }

        if (actual.size() != expected.size()) {
            return false;
        }
        int length = actual.size();
        for (int pos = 0; pos < length; pos++) {
            if (!ObjectUtils.isEquals(actual.get(pos), expected.get(pos))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 已默认分隔符隔开字符串
     *
     * @param list
     * @return字符串
     */
    public static String join(List<String> list) {
        return join(list, DEFAULT_JOIN_SEPARATOR);
    }

    /**
     * 分隔符隔开字符串
     *
     * @param list
     * @param separator
     * @return 字符串
     */
    public static String join(List<String> list, char separator) {
        return join(list, new String(new char[]{separator}));
    }

    /**
     * 分隔符隔开字符串
     *
     * @param list
     * @param separator
     * @return 字符串
     */
    public static String join(List<String> list, String separator) {
        return list == null ? "" : TextUtils.join(separator, list);
    }

    /**
     * List添加对象,已存在的对象返回false
     *
     * @param list
     * @param entry
     * @return boolean
     */
    public static <V> boolean addDistinctEntry(List<V> list, V entry) {
        return (list != null && !list.contains(entry)) ? list.add(entry)
                : false;
    }

    /**
     * 两个List相加，已经存在的不增加，返回添加的数量
     *
     * @param source
     * @param target
     * @return int
     */
    public static <V> int addDistinctList(List<V> source, List<V> target) {
        if (source == null || isEmpty(target)) {
            return 0;
        }

        int sourceCount = source.size();
        for (V entry : target) {
            if (!source.contains(entry)) {
                source.add(entry);
            }
        }
        return source.size() - sourceCount;
    }

    /**
     * List去除重复数据，返回重复数据数量
     *
     * @param source
     * @return int
     */
    public static <V> int distinctList(List<V> source) {
        if (isEmpty(source)) {
            return 0;
        }

        int sourceCount = source.size();
        int sourceListSize = source.size();
        for (int i = 0; i < sourceListSize; i++) {
            for (int j = (i + 1); j < sourceListSize; j++) {
                if (source.get(i).equals(source.get(j))) {
                    source.remove(j);
                    sourceListSize = source.size();
                    j--;
                }
            }
        }
        return sourceCount - source.size();
    }

}
