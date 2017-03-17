package com.jungle.utility.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 功能描述：手机媒体操作工具类
 * <p/>
 * 创 建 人：Rocky
 * 创建时间：2017-03-10
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2017-03-10）
 */
public class MediaUtils {

    private static final String TAG = MediaUtils.class.getSimpleName();

    public static final int PHOTO_REQUEST_GALLERY = 1000;
    public static final int PHOTO_REQUEST_CAMERA = 1001;
    public static final int PHOTO_REQUEST_CUT = 1002;

    /**
     * 进入系统拍照
     * @param activity
     * @param file 照片输出路径 Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/image.jpg"))
     */
    public static void startActivityForCamera(Activity activity, int requestCode, File file) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        // 制定图片保存路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 进入系统拍照 (输出为Bitmap)<br>
     *
     * 获得输出
     * 在 @<code>onActivityResult</code>中<br>
     * 通过@<code>Bitmap bitmap = (Bitmap)intent.data.getExtras().get("data")</code>获取<br>
     *
     * Tips: 返回的Bitmap并非原图的Bitmap而是经过压缩的Bitmap
     * @param activity
     *
     */
    public static void startActivityForCamera(Activity activity, int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 进入系统图库<br>
     * 获得输出<br>
     * 在 @<code>onActivityResult</code>中通过@<code>Uri uri = intent.getData()</code>获取<br>
     * Uri返回路径格式为 content://media/external/images/media/32073<br>
     * 需要经过转换才能获得绝对路径 }
     * @param activity
     */
    public static void startActivityForGallery(Activity activity, int requestCode) {
        // 弹出系统图库
        Intent i = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(i, requestCode);
    }

    /**
     * 进入系统裁剪
     * @param inputUri 需裁剪的图片路径 Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/image.jpg")
     * @param outputUri 裁剪后图片路径 Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/image_cut.jpg")
     * @param width 裁剪后宽度(px)
     * @param height 裁剪后高度(px)
     */
    public static void startActivityForImageCut(Activity activity, int requestCode,
                                          Uri inputUri, Uri outputUri,
                                          int width, int height) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(inputUri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true); // 去黑边
        intent.putExtra("scaleUpIfNeeded", true); // 去黑边
        // aspectX aspectY 裁剪框宽高比例
        intent.putExtra("aspectX", width); // 输出是X方向的比例
        intent.putExtra("aspectY", height);
        // outputX outputY 输出图片宽高，切忌不要再改动下列数字，会卡死
        intent.putExtra("outputX", width); // 输出X方向的像素
        intent.putExtra("outputY", height);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        intent.putExtra("return-data", false); // 设置为不返回数据
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 跳至填充好phoneNumber的拨号界面
     *
     * @param context     上下文
     * @param phoneNumber 电话号码
     */
    public static void phoneDial(Context context, String phoneNumber) {
        context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber)));
    }

    /**
     * 拨打phoneNumber
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.CALL_PHONE"/>}</p>
     *
     * @param context     上下文
     * @param phoneNumber 电话号码
     */
    public static void phoneCall(Context context, String phoneNumber) {
        context.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneNumber)));
    }

    /**
     * 发送短信
     *
     * @param context     上下文
     * @param phoneNumber 电话号码
     * @param content     内容
     */
    public static void sendSms(Context context, String phoneNumber, String content) {
        Uri uri = Uri.parse("smsto:" + (StringUtils.isEmpty(phoneNumber) ? "" : phoneNumber));
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", StringUtils.isEmpty(content) ? "" : content);
        context.startActivity(intent);
    }

    /**
     * 获取手机联系人
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>}</p>
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.READ_CONTACTS"/>}</p>
     *
     * @param context 上下文;
     * @return 联系人链表
     */
    public static List<HashMap<String, String>> getAllContactInfo(Context context) {
        SystemClock.sleep(3000);
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        // 1.获取内容解析者
        ContentResolver resolver = context.getContentResolver();
        // 2.获取内容提供者的地址:com.android.contacts
        // raw_contacts表的地址 :raw_contacts
        // view_data表的地址 : data
        // 3.生成查询地址
        Uri raw_uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri date_uri = Uri.parse("content://com.android.contacts/data");
        // 4.查询操作,先查询raw_contacts,查询contact_id
        // projection : 查询的字段
        Cursor cursor = resolver.query(raw_uri, new String[]{"contact_id"},
                null, null, null);
        // 5.解析cursor
        while (cursor.moveToNext()) {
            // 6.获取查询的数据
            String contact_id = cursor.getString(0);
            // cursor.getString(cursor.getColumnIndex("contact_id"));//getColumnIndex
            // : 查询字段在cursor中索引值,一般都是用在查询字段比较多的时候
            // 判断contact_id是否为空
            if (!StringUtils.isEmpty(contact_id)) {//null   ""
                // 7.根据contact_id查询view_data表中的数据
                // selection : 查询条件
                // selectionArgs :查询条件的参数
                // sortOrder : 排序
                // 空指针: 1.null.方法 2.参数为null
                Cursor c = resolver.query(date_uri, new String[]{"data1",
                                "mimetype"}, "raw_contact_id=?",
                        new String[]{contact_id}, null);
                HashMap<String, String> map = new HashMap<String, String>();
                // 8.解析c
                while (c.moveToNext()) {
                    // 9.获取数据
                    String data1 = c.getString(0);
                    String mimetype = c.getString(1);
                    // 10.根据类型去判断获取的data1数据并保存
                    if (mimetype.equals("vnd.android.cursor.item/phone_v2")) {
                        // 电话
                        map.put("phone", data1);
                    } else if (mimetype.equals("vnd.android.cursor.item/name")) {
                        // 姓名
                        map.put("name", data1);
                    }
                }
                // 11.添加到集合中数据
                list.add(map);
                // 12.关闭cursor
                c.close();
            }
        }
        // 12.关闭cursor
        cursor.close();
        return list;
    }
}
