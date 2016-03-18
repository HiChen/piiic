package com.gewara.piiic;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2016/2/16.
 */
public class PiiicApplication extends Application {
    public static String APPLICATION_VERSION = "1.0";
    public static int APPLICATION_VERSION_CODE = 1;
    private static PiiicApplication application;
    private static Context applicationContext;
    public static LayoutInflater inflater;

    public static PiiicApplication getApplication() {
        return application;
    }

    public void onCreate() {
        super.onCreate();
        try {
            application = this;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            applicationContext = getApplicationContext();
            PackageInfo localPackageInfo = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0);
            APPLICATION_VERSION = localPackageInfo.versionName;
            APPLICATION_VERSION_CODE = localPackageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
            Log.e(getClass().getName(), localNameNotFoundException.getMessage());
        }
    }


    //运用list来保存们每一个activity是关键
    private List<Activity> mList = new LinkedList<Activity>();
    //为了实现每次使用该类时不创建新的对象而创建的静态对象
    private static PiiicApplication instance;
    //构造方法
    private PiiicApplication(){}
    //实例化一次
    public synchronized static PiiicApplication getInstance(){
        if (null == instance) {
            instance = new PiiicApplication();
        }
        return instance;
    }
    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }
    //关闭每一个list内的activity
    public void exit() {
        try {
            for (Activity activity:mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
    //杀进程
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
