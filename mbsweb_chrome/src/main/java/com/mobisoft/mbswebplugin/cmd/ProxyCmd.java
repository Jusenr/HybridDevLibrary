package com.mobisoft.mbswebplugin.cmd;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;

import com.mobisoft.mbswebplugin.basic.DefaultHomePage;
import com.mobisoft.mbswebplugin.cmd.strategy.ErrorMethod;
import com.mobisoft.mbswebplugin.utils.LogUtils;
import com.mobisoft.mbswebplugin.utils.Preconditions;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 * Copyright  : Copyright (c) 2019
 * Email      : jusenr@163.com
 * Author     : Jusenr
 * Date       : 2019/05/08
 * Time       : 14:57
 * Project    ：HybridDevLibrary.
 */
public class ProxyCmd {
    public static final String TAG = "ProxyCmd";

    private static ProxyCmd cmd;
    private HomePage homePage;
    private Map<String, String> hashmap;
    private int addSize = 1;
    private int replaceSize = 1;

    private ProxyCmd() {
        if (hashmap == null) {
            hashmap = new ConcurrentHashMap<>();
        }
    }

    public static synchronized ProxyCmd getInstance() {
        if (cmd == null) {
            cmd = new ProxyCmd();
        }
        return cmd;
    }

    /**
     * 获取默认主页策略
     *
     * @return 默认主页策略
     */
    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new DefaultHomePage();
        }
        return homePage;
    }

    /**
     * 设置主页策略
     *
     * @param homePage 主页策略
     * @return ProxyCmd
     */
    public ProxyCmd setHomePage(final HomePage homePage) {
        this.homePage = Preconditions.checkNotNull(homePage);
        return this;
    }

    /**
     * 增加单个策略
     *
     * @param cmd       (不做大小写限制)
     * @param className 对象类名
     * @return ProxyCmd
     */
    public ProxyCmd putCmd(final String cmd, final String className) {
        if (TextUtils.isEmpty(cmd)) return this;
        if (TextUtils.isEmpty(className)) return this;
        hashmap.put(cmd, className);
        LogUtils.w(TAG, "putCmd: " + addSize++ + "--" + cmd + "------" + className);
        return this;
    }

    /**
     * 增加某些策略
     *
     * @param map (不做大小写限制)
     * @return ProxyCmd
     */
    public ProxyCmd putAllCmd(final Map<String, String> map) {
        if (map.size() == 0) return this;
        hashmap.putAll(map);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 1;
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    LogUtils.w(TAG, "putAllCmd: --" + i++ + "  key:" + key + "    value:" + value);
                }
            }
        }).start();
        return this;
    }

    /**
     * 替换单个策略
     *
     * @param cmd       (不做大小写限制)
     * @param className 对象类名
     * @return ProxyCmd
     */
    @TargetApi(Build.VERSION_CODES.N)
    public ProxyCmd replaceCmd(final String cmd, final String className) {
        if (TextUtils.isEmpty(cmd)) return this;
        if (TextUtils.isEmpty(className)) return this;
        hashmap.replace(cmd, className);
        LogUtils.w(TAG, "replaceCmd: " + replaceSize++ + "--" + cmd + "------" + className);
        return this;
    }

    /**
     * 删除单个策略
     *
     * @param cmd (不做大小写限制)
     * @return ProxyCmd
     */
    public ProxyCmd deleteCmd(final String cmd) {
        if (TextUtils.isEmpty(cmd)) return this;
        if (hashmap.containsKey(cmd)) {
            hashmap.remove(cmd);
            LogUtils.w(TAG, "deleteCmd: " + cmd);
        }
        return this;
    }

    /**
     * 删除全部策略
     *
     * @param clear 是否删除全部策略
     * @return ProxyCmd
     */
    public ProxyCmd deleteCmd(final boolean clear) {
        if (clear) hashmap.clear();
        return this;
    }

    /**
     * 获取全部策略
     *
     * @return map
     */
    public Map<String, String> getMap() {
        return this.hashmap;
    }

    /**
     * 根绝包名，通过反射获取到具体类
     *
     * @param cmd (不做大小写限制)
     * @return Class
     */
    public Class reflectMethod(final String cmd) {
        String className = hashmap.get(cmd);
        LogUtils.w(TAG, "reflectMethod-cmd: " + cmd + "    className: " + className);
        try {
            Class doCmdMethod = Class.forName(className);
            return doCmdMethod;
        } catch (Exception e) {
            e.printStackTrace();

            return ErrorMethod.class;
        }
    }

    /**
     * logcat打印全部策略
     *
     * @param show 是否logcat打印
     */
    public void logcatMap(final boolean show) {
        if (show) {
            LogUtils.w(TAG, "--------------show ALL JS CMD start--------------------");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int i = 1;
                    for (Map.Entry<String, String> entry : hashmap.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        LogUtils.w(TAG, "logcatMap: --" + i++ + "  key:" + key + "    value:" + value);
                    }
                }
            }).start();
            LogUtils.w(TAG, "--------------show ALL JS CMD end--------------------");
        }
    }
}
