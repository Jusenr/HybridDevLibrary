package com.mobisoft.mbswebplugin.basic;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2019
 * Email      : jusenr@163.com
 * Author     : Jusenr
 * Date       : 2019/05/08
 * Time       : 17:27
 * Project    ：HybridDevLibrary.
 */
public class ActivityManager implements Application.ActivityLifecycleCallbacks {
    private final String TAG = "ActivityManager";

    private static ActivityManager manager;
    private LinkedList<Activity> mActivityList;
    private Activity mCurrentActivity;

    private ActivityManager() {
        if (mActivityList == null) {
            mActivityList = new LinkedList<>();
        }
    }

    public static synchronized ActivityManager get() {
        if (manager == null) {
            manager = new ActivityManager();
        }
        return manager;
    }

    /**
     * Register this to {@link Application}
     *  在 Application注册该类
     * @param context Application context
     */
    public void registerSelf(Context context) {
        Application application = (Application) context.getApplicationContext();
        application.registerActivityLifecycleCallbacks(this);
    }


    /**
     * 释放资源
     */
    public void release() {
        mActivityList.clear();
        mActivityList = null;
        mCurrentActivity = null;
    }

    /**
     * 获取栈顶activity
     *
     * @return
     */
    public Activity topActivity() {
        if (!mActivityList.isEmpty()) {
            return mActivityList.getLast();
        }
        return null;
    }

    /**
     * 获取栈底activity
     *
     * @return
     */
    public Activity bottomActivity() {
        if (!mActivityList.isEmpty()) {
            return mActivityList.getFirst();
        }
        return null;
    }

    /**
     * 返回一个存储所有未销毁的activity的集合
     *
     * @return
     */
    public List<Activity> getActivityList() {
        return mActivityList;
    }


    /**
     * 添加Activity到集合
     */
    public void addActivity(Activity activity) {
        if (mActivityList == null) {
            mActivityList = new LinkedList<>();
        }
        synchronized (ActivityManager.class) {
            if (!mActivityList.contains(activity)) {
                mActivityList.add(activity);
            }
        }
    }

    /**
     * 删除集合里的指定activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (mActivityList == null) {
            return;
        }
        synchronized (ActivityManager.class) {
            if (mActivityList.contains(activity)) {
                mActivityList.remove(activity);
            }
        }
    }

    /**
     * 删除集合里的指定位置的activity
     *
     * @param location
     */
    public Activity removeActivity(int location) {
        if (mActivityList == null) {
            return null;
        }
        synchronized (ActivityManager.class) {
            if (location > 0 && location < mActivityList.size()) {
                return mActivityList.remove(location);
            }
        }
        return null;
    }

    /**
     * 关闭指定activity
     *
     * @param activityClass
     */
    public void finishActivity(Class<?> activityClass) {
        if (mActivityList == null) {
            return;
        }
        for (Activity activity : mActivityList) {
            if (activity.getClass().equals(activityClass)) {
                activity.finish();
            }
        }
    }


    /**
     * 指定的activity实例是否存活
     *
     * @param activity
     * @return
     */
    public boolean activityInstanceIsLive(Activity activity) {
        if (mActivityList == null) {
            return false;
        }
        return mActivityList.contains(activity);
    }


    /**
     * 指定的activity class是否存活(一个activity可能有多个实例)
     *
     * @param activityClass
     * @return
     */
    public boolean activityClassIsLive(Class<?> activityClass) {
        if (mActivityList == null) {
            return false;
        }
        for (Activity activity : mActivityList) {
            if (activity.getClass().equals(activityClass)) {
                return true;
            }
        }

        return false;
    }


    /**
     * 关闭所有activity
     */
    public void finishAll() {
        Iterator<Activity> iterator = mActivityList.iterator();
        while (iterator.hasNext()) {
            iterator.next().finish();
            iterator.remove();
        }

    }

    /**
     * 关闭除activityClass之外的所有activity
     */
    public void finishAll(@NonNull Class<?>... activityClass) {
        Iterator<Activity> iterator = mActivityList.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (!Arrays.asList(activityClass).contains(activity.getClass())) {
                activity.finish();
                iterator.remove();
            }
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (!mActivityList.contains(activity)) {
            mActivityList.add(activity);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (mActivityList.contains(activity)) {
            mActivityList.remove(activity);
        }
    }
}
