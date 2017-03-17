package com.rocky.rockyproject.service;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;

import com.rocky.rockyproject.utils.AppUtils;
import com.rocky.rockyproject.utils.LogUtils;

/**
 * 功能描述：下载相关工具类
 * <p>
 * 创 建 人：Rocky
 * 创建时间：2016-12-14
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2016-12-14）
 */
public class DownloadService extends Service {

    private long mTaskId;
    private DownloadManager mDownloadManager;
    private String filePath;

    public DownloadService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.i("onStartCommand:" + intent.getStringExtra("fileurl"));
        String fileurl = intent.getStringExtra("fileurl");
        downloadFile(fileurl);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void downloadFile(String fileurl) {
        filePath = "/sdcard/Download/" + fileurl.substring(fileurl.lastIndexOf("/") + 1);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fileurl));
        request.setDestinationInExternalPublicDir("/Download/", fileurl.substring(fileurl.lastIndexOf("/") + 1));
        mDownloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        mTaskId = mDownloadManager.enqueue(request);
        registerReceiver(mReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkDownloadStatus();
        }
    };

    private void checkDownloadStatus() {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(mTaskId);
        Cursor c = mDownloadManager.query(query);
        if (c.moveToFirst()) {
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            switch (status) {
               /* case DownloadManager.STATUS_PAUSED:
                    break;
                case DownloadManager.STATUS_FAILED:
                    break;
                case DownloadManager.STATUS_PENDING:
                    break;
                case DownloadManager.STATUS_RUNNING:
                    break;*/
                case DownloadManager.STATUS_SUCCESSFUL:
                    AppUtils.installApp(this, filePath);
                    break;
            }
        }


    }
}
