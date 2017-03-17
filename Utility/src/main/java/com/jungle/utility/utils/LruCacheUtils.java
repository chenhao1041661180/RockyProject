package com.jungle.utility.utils;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.util.LruCache;

import com.jungle.utility.base.BaseApplication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.Serializable;

/**
 * LruCache工具类
 * <p/>
 * 创建人：陈浩
 * 创建时间：2017年2月9日
 * 修改人：
 */
public class LruCacheUtils {
    /* 数据缓存 */
    private static LruCache<String, Object> mCache;

    /* 缓存类型 0：图片缓存 1：数据缓存 */
    public static final int BITMAP_CACHE = 0;
    public static final int JSON_CACHE = 1;
    private static LruCacheUtils mLruCacheUtils = null;
    //ACache简单本地缓存类
    private static ACache mAcache = null;

//    private LruCacheUtils() {
//        throw new AssertionError();
//    }

    /**
     * 构造方法，初始化相关数据
     */
    public LruCacheUtils() {
        initLruCache();
        initAcache();
    }

    /**
     * 初始化ACache缓存类
     */
    private void initAcache() {
        mAcache = ACache.get(BaseApplication.getInstance());
    }

    /**
     * 获取LruCache实例
     */
    public static synchronized LruCacheUtils getInstance() {
        if (mLruCacheUtils == null) {
            mLruCacheUtils = new LruCacheUtils();
        }
        return mLruCacheUtils;
    }

    /**
     * 获取LruCache实例
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public static void initLruCache() {
        if (mCache == null) {
            // 获取应用程序最大可用内存
            int maxMemory = (int) Runtime.getRuntime().maxMemory();
            int cacheSize = maxMemory / 8;
            // 设置图片缓存大小为程序最大可用内存的1/8
            mCache = new LruCache<String, Object>(cacheSize) {
                @Override
                protected int sizeOf(String key, Object value) {
                    return super.sizeOf(key, value);
                }
            };
        }
    }

    /**
     * 将String数据存储到LruCache中
     *
     * @param key   标记
     * @param value String源
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public void put(String key, String value) {
        if (!StringUtils.isEmpty(value)
                && StringUtils.isEmpty(getAsString(key))) {
            mCache.put(key, value);
            mAcache.put(key, value);
        }
    }

    /**
     * 将Se数据存储到LruCache中
     *
     * @param key   标记
     * @param value String源
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public void put(String key, Serializable value) {
        if (value != null && getAsObject(key) == null) {
            mCache.put(key, value);
            mAcache.put(key, value);
        }
    }

    /**
     * 保存 JSONObject数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的JSON数据
     */
    public void put(String key, JSONObject value) {
        if (value != null && getAsJSONObject(key) == null) {
            mCache.put(key, (JSONObject) value);
            mAcache.put(key, value);
        }
    }

    /**
     * 保存 JSONArray数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的JSONArray数据
     */
    public void put(String key, JSONArray value) {
        if (value != null && getAsJSONArray(key) == null) {
            mCache.put(key, (JSONArray) value);
            mAcache.put(key, value);
        }
    }

    /**
     * 将Bitmap数据存储到LruCache中
     *
     * @param key
     * @param value
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public void put(String key, Bitmap value) {
        if ((value != null) && (getAsBitmap(key) == null)) {
            mCache.put(key, value);
            mAcache.put(key, value);
        }
    }

    /**
     * 缓存Drawable
     *
     * @param key      标记
     * @param drawable Drawable对象
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public void put(String key, Drawable drawable) {
        if ((drawable != null) && (getAsDrawable(key) == null)) {
            mCache.put(key, drawable);

            mAcache.put(key, drawable);
        }
    }

    /**
     * 从LruCache中读取对应key的String数据
     *
     * @param key keyId
     * @return String 缓存的String对象数据
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public String getAsString(String key) {
        //取内存缓存
        String string = (String) mCache.get(key);
        if (StringUtils.isEmpty(string)) {
            Log.i("xx", "取本地String");
            //取本地缓存
            string = mAcache.getAsString(key);

            if (StringUtils.isEmpty(string)) {
                return "";
            }
            //添加到内存缓存
            mCache.put(key, string);
            return string;
        }
        return string;
    }

    /**
     * 从LruCache中读取对应key的Object数据
     *
     * @param key
     * @return String
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public Object getAsObject(String key) {
        //取内存缓存数据
        Object object = (Object) mCache.get(key);
        if (object == null) {
            //取本地缓存数据
            object = mAcache.getAsObject(key);
            if (object == null) {
                return null;
            }
            //添加到内存缓存中
            mCache.put(key, object);
            return object;
        }
        return object;
    }

    /**
     * 读取JSONArray数据
     *
     * @param key
     * @return JSONArray数据
     */
    public JSONArray getAsJSONArray(String key) {
        //取内存缓存
        JSONArray jSONArray = (JSONArray) mCache.get(key);
        if (jSONArray == null) {
            //取本地缓存
            jSONArray = mAcache.getAsJSONArray(key);
            if (jSONArray == null) {
                return null;
            }
            //添加到内存缓存
            mCache.put(key, jSONArray);
            return jSONArray;
        }
        return jSONArray;
    }

    /**
     * 读取JSONObject数据
     *
     * @param key
     * @return JSONArray数据
     */
    public JSONObject getAsJSONObject(String key) {
        //取内存缓存
        JSONObject jSONObject = (JSONObject) mCache.get(key);
        if (jSONObject == null) {
            //取本地缓存
            jSONObject = mAcache.getAsJSONObject(key);
            if (jSONObject == null) {
                return null;
            }
            //添加到内存缓存
            mCache.put(key, jSONObject);
            return jSONObject;
        }
        return jSONObject;
    }

    /**
     * 从LruCache中读取对应key的Bitmap
     *
     * @param key
     * @return Bitmap
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public Bitmap getAsBitmap(String key) {
        //取内存缓存
        Bitmap bitmap = (Bitmap) mCache.get(key);
        if (bitmap == null) {
            //取本地缓存
            bitmap = mAcache.getAsBitmap(key);
            if (bitmap == null) {
                return null;
            }
            //添加到内存缓存
            mCache.put(key, bitmap);
            return bitmap;
        }
        return bitmap;
    }

    /**
     * 从LruCache中读取对应key的Drawable
     *
     * @param key
     * @return Bitmap
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public Drawable getAsDrawable(String key) {
        //取内存缓存
        Drawable drawable = (Drawable) mCache.get(key);
        if (drawable == null) {
            Log.i("xx", "取本地Drawable");
            //取本地缓存
            drawable = mAcache.getAsDrawable(key);
            if (drawable == null) {
                return null;
            }
            //添加到内存缓存
            mCache.put(key, drawable);
            return drawable;
        }
        return drawable;
    }

    /**
     * 移除Bitmap缓存
     *
     * @param key keyId
     */
    public synchronized void removeBitmapCache(String key) {
        if (key != null) {
            if (mCache != null) {
                Bitmap bm = (Bitmap) mCache.remove(key);
                if (bm != null) {
                    bm.recycle();
                    bm = null;
                }
            }
            if (mAcache != null) {
                mAcache.remove(key);
            }
        }
    }

    /**
     * 移除Drawable缓存
     *
     * @param key keyId
     */
    public synchronized void removeDrawableCache(String key) {
        if (key != null) {
            if (mCache != null) {
                Drawable drawable = (Drawable) mCache.remove(key);
                if (drawable != null) {
                    drawable = null;
                }
            }
            if (mAcache != null) {
                mAcache.remove(key);
            }
        }
    }

    /**
     * 移除String缓存
     *
     * @param key keyId
     */
    public synchronized void removeStringCache(String key) {
        if (key != null) {
            if (mCache != null) {
                String string = (String) mCache.remove(key);
                if (string != null) {
                    string = null;
                }
            }
            if (mAcache != null) {
                mAcache.remove(key);
            }
        }
    }

    /**
     * 移除Object缓存
     *
     * @param key keyId
     */
    public synchronized void removeObjectCache(String key) {
        if (key != null) {
            if (mCache != null) {
                Object object = (Object) mCache.remove(key);
                if (object != null) {
                    object = null;
                }
            }
            if (mAcache != null) {
                mAcache.remove(key);
            }
        }
    }

    /**
     * 移除JSONObject缓存
     *
     * @param key keyId
     */
    public synchronized void removeJSONObjectCache(String key) {
        if (key != null) {
            if (mCache != null) {
                JSONObject jSONObject = (JSONObject) mCache.remove(key);
                if (jSONObject != null) {
                    jSONObject = null;
                }
            }
            if (mAcache != null) {
                mAcache.remove(key);
            }
        }
    }

    /**
     * 移除JSONArray缓存
     *
     * @param key keyId
     */
    public synchronized void removeJSONArrayCache(String key) {
        if (key != null) {
            if (mCache != null) {
                JSONArray jSONArray = (JSONArray) mCache.remove(key);
                if (jSONArray != null) {
                    jSONArray = null;
                }
            }
        }
    }

    /**
     * 清空全部缓存
     */
    public void clearAllCache() {
        if (mCache != null) {
            if (mCache.size() > 0) {
                Log.d("CacheUtils",
                        "mMemoryCache.size() " + mCache.size());
                mCache.evictAll();
                Log.d("CacheUtils", "mMemoryCache.size()" + mCache.size());
            }
            mCache = null;
            mLruCacheUtils = null;
        }
        if (mAcache != null){
            mAcache.clear();
			mAcache = null;
			}
    }

    /**
     * 获取换成大小
     *
     * @return 缓存Size
     */
    public int getCacheSize() {
        int size = 0;
        if (mCache != null)
            size += mCache.size();
        if (mAcache!=null){
            File cacheFile = mAcache.getCacheDir();
            try {
                size += FileUtils.getFileSizes(cacheFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return size;
    }
}
