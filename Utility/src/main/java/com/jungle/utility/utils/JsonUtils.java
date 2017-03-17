package com.jungle.utility.utils;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * 功能描述：Json解析工具类
 * <p/>
 * 创 建 人：陈浩
 * 创建时间：2017-03-10
 * 版 本 号：V1.0
 * 修 改 人：陈浩（2017-03-10）
 */
public class JsonUtils {
    // 返回空字符串
    public static final String EMPTY_STRING = "";
    // 返回空json数据
    public static final String EMPTY_JSON = "{}";
    // 返回空json数组
    public static final String EMPTY_ARRAY = "[]";
    // 默认时间格式
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 构造函数
     */
    private JsonUtils() {
        throw new AssertionError();
    }

    /**
     * 从JsonObject中获取对应key的值，如果没有返回默认值，返回类型根据传入的默认值来判断
     *
     * @param jsonObject   JSONObject
     * @param key          键值
     * @param defaultValue 默认值
     * @return Object 返回对象
     */
    public static Object get(JSONObject jsonObject, String key, Object defaultValue) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            if (defaultValue instanceof String) {
                return jsonObject.getString(key);
            } else if (defaultValue instanceof Integer) {
                return jsonObject.getInt(key);
            } else if (defaultValue instanceof Boolean) {
                return jsonObject.getBoolean(key);
            } else if (defaultValue instanceof Float) {
                return jsonObject.getDouble(key);
            } else if (defaultValue instanceof Long) {
                return jsonObject.getLong(key);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * 从JsonObject中获取对应key的值，如果没有返回默认值，返回类型根据传入的默认值来判断
     *
     * @param jsonData     json数据
     * @param key          键值
     * @param defaultValue 默认值
     * @return Object 返回对象
     */
    public static Object get(String jsonData, String key, Object defaultValue) {
        if (StringUtils.isEmpty(jsonData) || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return get(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * 从JsonObject中获取对应key的JSONArray值，如果没有返回默认值
     *
     * @param jsonObject   JSONObject
     * @param key          键值
     * @param defaultValue 默认值
     * @return JSONArray 返回JSONArray
     */
    public static JSONArray getJSONArray(JSONObject jsonObject, String key, JSONArray defaultValue) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getJSONArray(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * 从JsonObject中获取对应key的JSONArray值，如果没有返回默认值
     *
     * @param jsonData     json数据
     * @param key          键值
     * @param defaultValue 默认值
     * @return JSONArray 返回JSONArray
     */
    public static JSONArray getJSONArray(String jsonData, String key, JSONArray defaultValue) {
        if (StringUtils.isEmpty(jsonData) || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getJSONArray(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * 将给定的目标对象根据指定的条件参数转换成 {JSON} 格式的字符串
     * 该方法转换发生错误时，不会抛出任何异常。若发生错误时，通对象返回"{}"
     * 集合或数组对象返回"[]"
     *
     * @param target      目标对象
     * @param datePattern 日期字段的格式化模式
     * @return 目标对象的JSON格式的字符串
     */
    public static String toJson(Object target, String datePattern) {
        if (target == null) {
            return EMPTY_STRING;
        }

        GsonBuilder builder = new GsonBuilder();
        if (StringUtils.isEmpty(datePattern)) {
            datePattern = DEFAULT_DATE_PATTERN;
        }
        builder.setDateFormat(datePattern);
        String result;
        Gson gson = builder.create();
        try {
            result = gson.toJson(target);
        } catch (Exception e) {
            if (target instanceof Collection || target instanceof Iterator || target instanceof Enumeration
                    || target.getClass().isArray()) {
                result = EMPTY_ARRAY;
            } else {
                result = EMPTY_JSON;
            }
        }
        return result;
    }

    /**
     * 将给定的目标对象根据默认的时间格式转换成JSON格式的字符串
     *
     * @param target 目标对象
     * @return 指定时间格式的json
     */
    public static String toJson(Object target) {
        return toJson(target, DEFAULT_DATE_PATTERN);
    }

    /**
     * 将给定的JSON字符串转换成指定的类型对象
     *
     * @param json        json数据
     * @param datePattern 时间格式
     * @param <T>         泛型对象
     * @return T对象
     */
    public static <T> T fromJson(String json, Type type, String datePattern) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }

        GsonBuilder builder = new GsonBuilder();
        if (StringUtils.isEmpty(datePattern)) {
            datePattern = DEFAULT_DATE_PATTERN;
        }
        builder.setDateFormat(datePattern);
        Gson gson = builder.create();
        try {
            return gson.fromJson(json, type);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将给定的JSON字符串按照默认的时间格式转换成指定的类型对象
     *
     * @param json json数据
     * @param <T>  泛型对象
     * @return T对象
     */
    public static <T> T fromJson(String json, Type type) {
        return fromJson(json, type, DEFAULT_DATE_PATTERN);
    }

    /**
     * 将给定的JSON字符串转换成指定的类型对象
     *
     * @param json        json数据
     * @param cls         对象类名
     * @param datePattern 时间格式
     * @param <T>         泛型对象
     * @return T对象
     */
    public static <T> T fromJson(String json, Class<T> cls, String datePattern) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }

        GsonBuilder builder = new GsonBuilder();
        if (StringUtils.isEmpty(datePattern)) {
            datePattern = DEFAULT_DATE_PATTERN;
        }
        builder.setDateFormat(datePattern);
        Gson gson = builder.create();
        try {
            return gson.fromJson(json, cls);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将给定的JSON字符串按照默认的时间格式转换成指定的类型对象
     *
     * @param json json数据
     * @param cls  对象类名
     * @param <T>  泛型对象
     * @return T对象
     */
    public static <T> T fromJson(String json, Class<T> cls) {
        return fromJson(json, cls, DEFAULT_DATE_PATTERN);
    }

    /**
     * 将给定的JSON字符串转换成指定的类型List
     *
     * @param json        json对象
     * @param datePattern 时间格式
     * @param <T>         泛型对象
     * @return List<T>
     */
    public static <T> List<T> toList(String json, Type type, String datePattern) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }

        GsonBuilder builder = new GsonBuilder();
        if (StringUtils.isEmpty(datePattern)) {
            datePattern = DEFAULT_DATE_PATTERN;
        }
        builder.setDateFormat(datePattern);
        Gson gson = builder.create();
        try {
            return gson.fromJson(json, type);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将给定的JSON字符串按照默认时间格式转换成指定的类型List
     *
     * @param json json数据
     * @param <T>  泛型对象
     * @return List<T>
     */
    public static <T> List<T> toList(String json, Type type) {
        return toList(json, type, DEFAULT_DATE_PATTERN);
    }
}
