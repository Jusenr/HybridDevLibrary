package com.mobisoft.mbswebplugin.base;

import android.app.Application;

import com.mobisoft.mbswebplugin.utils.LogUtils;
import com.tencent.smtt.sdk.QbSdk;


public abstract class BaseApp extends Application {
    public static final String TAG = "BaseApp";
    public static BaseApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ActivityManager.get().registerSelf(this);
        LogUtils.init(isDebug());
        //Tencent X5
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                LogUtils.e(TAG, "加载Tencent X5内核是否成功: " + b);
            }
        });

    }

    public static BaseApp getApplication() {
        return mInstance;
    }

    public abstract boolean isDebug();
}
