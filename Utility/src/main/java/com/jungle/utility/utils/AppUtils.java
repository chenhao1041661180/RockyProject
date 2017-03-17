package com.jungle.utility.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

/**
 * 功能描述：App工具类
 * <p/>
 * 创 建 人：陈浩
 * 创建时间：2017-03-10
 * 版 本 号：V1.0
 * 修 改 人：陈浩（2017-03-10）
 */
public class AppUtils {

    /**
     * 构造函数
     */
    private AppUtils() {
        throw new AssertionError();
    }

    /**
     * 获取PackageInfo
     *
     * @param context 上下文
     * @return PackageInfo
     */
    private static PackageInfo getPackageInfo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    /**
     * 获取应用程序名称
     *
     * @param context 上下文
     * @return 应用程序名称
     */
    public static String getAppName(Context context) {
        String appName = "";
        PackageInfo packageInfo = getPackageInfo(context);
        if (packageInfo != null) {
            int labelRes = packageInfo.applicationInfo.labelRes;
            appName = context.getResources().getString(labelRes);
        }
        return appName;
    }

    /**
     * 获取应该版本名称
     *
     * @param context 上下文
     * @return 应用程序版本名称
     */
    public static String getVersionName(Context context) {
        String versionName = "";
        PackageInfo packageInfo = getPackageInfo(context);
        if (packageInfo != null) {
            versionName = packageInfo.versionName;
        }
        return versionName;
    }

    /**
     * 获取应该版本号
     *
     * @param context 上下文
     * @return 应用程序版本号
     */
    public static int getVersionCode(Context context) {
        int versionCode = -1;
        PackageInfo packageInfo = getPackageInfo(context);
        if (packageInfo != null) {
            versionCode = packageInfo.versionCode;
        }
        return versionCode;
    }

    /**
     * 获取应用图标
     *
     * @param context     上下文
     * @param packageName 包名
     * @return 应用图标
     */
    public static Drawable getAppIcon(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        Drawable appIcon = null;
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);
            appIcon = applicationInfo.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appIcon;
    }

    /**
     * 获取应用更新时间
     *
     * @param context     上下文
     * @param packageName 包名
     * @return 应用更新时间
     */
    public static long getAppDate(Context context, String packageName) {
        long lastUpdateTime = 0;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            lastUpdateTime = packageInfo.lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return lastUpdateTime;
    }

    /**
     * 获取应用大小
     *
     * @param context     上下文
     * @param packageName 包名
     * @return 应用大小
     */
    public static long getAppSize(Context context, String packageName) {
        long appSize = 0;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
            appSize = new File(applicationInfo.sourceDir).length();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appSize;
    }

    /**
     * 获取串号
     *
     * @param context 上下文
     * @return 手机串号
     */
    public static String getSerial(Context context) {
        try {
            TelephonyManager telephonemanage = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
            return telephonemanage.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
            return "00000000000";
        }
    }

    /**
     * 获取mac
     *
     * @param context 上下文
     * @return mac字符串
     */
    public static String getMac(Context context) {
        try {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            return info.getMacAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 用来获取手机拨号上网（包括CTWAP和CTNET）时由PDSN分配给手机终端的源IP地址。
     *
     * @return ip地址
     */
    public static String getIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
        }
        return "0.0.0.0";
    }

    /**
     * 获取指定路径apk文件的版本号
     *
     * @param context 上下文
     * @param path    路径
     * @param apkName apk名称
     * @return apk版本号
     */
    public static int getApkVersion(Context context, String path, String apkName) {
        String filePath = path + File.separator + apkName;
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);
        if (packageInfo == null) {
            return -1;
        }
        return packageInfo.versionCode;
    }

    /**
     * 应用是否安装
     *
     * @param context     上下文
     * @param packageName 包名
     * @return 是否安装
     */
    public static boolean isInstalled(Context context, String packageName) {
        boolean installed = false;
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        List<ApplicationInfo> installedApplications = context.getPackageManager().getInstalledApplications(0);
        for (ApplicationInfo in : installedApplications) {
            if (packageName.equals(in.packageName)) {
                installed = true;
                break;
            } else {
                installed = false;
            }
        }
        return installed;
    }

    /**
     * 安装apk
     *
     * @param context 上下文
     * @param file    路径
     */
    public static void installApk(Context context, File file) {
        try {
            Intent intent = new Intent();
            // 执行动作
            intent.setAction(Intent.ACTION_VIEW);
            // 执行的数据类型
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // 编者按：此处Android应为android，否则造成安装不了
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 卸载应用
     *
     * @param context     上下文
     * @param packageName 包名
     * @return 是否成功
     */
    public static boolean uninstallApk(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        Intent i = new Intent(Intent.ACTION_DELETE, Uri.parse("package:" + packageName));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        return true;
    }

    /**
     * 是否系统应用
     *
     * @param context     上下文
     * @param packageName 包名
     * @return 是否系统应用
     */
    public static boolean isSystemApp(Context context, String packageName) {
        boolean isSys = false;
        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);
            if (applicationInfo != null && (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {
                isSys = true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            isSys = false;
        }
        return isSys;
    }

    /**
     * 启动应用
     *
     * @param context     上下文
     * @param packageName 包名
     */
    public static void runApp(Context context, String packageName) {
        context.startActivity(new Intent(context.getPackageManager().getLaunchIntentForPackage(packageName)));
    }

    /**
     * 设置震动
     *
     * @param activity     Activity
     * @param milliseconds 震动时长 单位是毫秒
     */
    public static void Vibrate(final Activity activity, long milliseconds) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(milliseconds);
    }

    /**
     * 设置震动
     *
     * @param activity Activity
     * @param pattern  [静止时长，震动时长，静止时长，震动时长] 单位是毫秒
     * @param isRepeat 是否反复震动
     */
    public static void Vibrate(final Activity activity, long[] pattern, boolean isRepeat) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(pattern, isRepeat ? 1 : -1);
    }

    /**
     * 用来判断服务是否运行.
     *
     * @param context   上下文
     * @param className 判断的服务名字：包名+类名
     * @return true 在运行, false 不在运行
     */
    public static boolean isServiceRunning(Context context, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
        if (!(serviceList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    /**
     * 获取已安装应用的MateData的信息
     *
     * @param context     上下文
     * @param packageName 包名
     * @param key         mateData的key
     * @return 信息
     * 创建人:徐晓健（2016-8-1）
     */
    public static String getMateData(Context context, String packageName, String key) {
        String channel = "";
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            channel = appInfo.metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channel;
    }

    /**
     * 在apk中获取MateData信息
     *
     * @param context         上下文
     * @param archiveFilePath 路径名
     * @param key             mateData的key
     * @return 信息
     * 创建人:徐晓健（2016-8-1）
     */
    public static String getMateDataByCard(Context context, String archiveFilePath, String key) {
        String channel = "";
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(archiveFilePath, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            ApplicationInfo appInfo = info.applicationInfo;
            channel = appInfo.metaData.getString(key);
        }
        return channel;
    }

    /**
     * 获取当前的显示的activity
     *
     * @return 创建人：陈浩（2016年8月19日）
     * 修改人：
     */
    public static String getClassName(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        return cn.getClassName();
    }

    /**
     * 获取当前位置
     *
     * @return
     */
    public static String getLocation(Context mContext) {
        //定位管理
        LocationManager locMan = (LocationManager) mContext
                .getSystemService(Context.LOCATION_SERVICE);
        Location location = null;
        try {//GPS定位
            location = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location == null) {//网络定位
                location = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
        } catch (SecurityException se) {
        }
        return location != null ? location.toString() : "";
    }

    /**
     * 获取uuid
     *
     * @return
     */
    public static String getUuid(Context mContext) {
        final TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(mContext.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String uniqueId = deviceUuid.toString();
        return uniqueId;
    }
}
