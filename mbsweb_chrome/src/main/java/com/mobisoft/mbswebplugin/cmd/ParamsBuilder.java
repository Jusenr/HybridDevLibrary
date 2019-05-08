package com.mobisoft.mbswebplugin.cmd;

import java.util.HashMap;

/**
 * Description:
 * Copyright  : Copyright (c) 2019
 * Email      : jusenr@163.com
 * Author     : Jusenr
 * Date       : 2019/05/08
 * Time       : 15:00
 * Project    ：HybridDevLibrary.
 */
public class ParamsBuilder {
    public static final String PARAM_KEY_UID = "uid";                   //账户UID
    public static final String PARAM_KEY_APP_ID = "appid";              //平台id
    public static final String PARAM_KEY_TOKEN = "token";               //登录token
    public static final String PARAM_KEY_DEVICE_ID = "device_id";       //设备id
    public static final String PARAM_KEY_LANGUAGE = "language";         //语言环境

    public static final String PARAM_KEY_SIGN = "sign";                 //令牌

    public static final String PARAM_KEY_PUSH_TOKEN = "push_token";     //推送时用的token
    public static final String PARAM_KEY_PUSH_APPID = "push_appid";     //推送时用的appid

    public static final String PARAM_KEY_TYPE = "type";//类型
    public static final String PARAM_KEY_EXT = "ext";
    public static final String PARAM_KEY_FILE_NAME = "file_name";
    public static final String PARAM_KEY_HASH = "hash";

    public static final String VALUE_UPLOADPHOTOS = "uploadPhotos";

    private HashMap<String, String> mParams;

    /**
     * 添加固定参数 uid/parent_uid 、appid、token、device_id
     */
    private ParamsBuilder() {
        mParams = new HashMap<>();
        mParams.put(PARAM_KEY_DEVICE_ID, "");
    }

    /**
     * (由于参数字段不统一，因此需要再次添加相关字段参数)
     *
     * @return
     */
    public static ParamsBuilder start() {
        ParamsBuilder paramsBuilder = new ParamsBuilder();
        return paramsBuilder;
    }

    /**
     * 添加固定参数 push_token、push_appid
     *
     * @return
     */
    public static ParamsBuilder gpush() {
        ParamsBuilder paramsBuilder = new ParamsBuilder();
        paramsBuilder.mParams.put(PARAM_KEY_PUSH_TOKEN, "");
        paramsBuilder.mParams.put(PARAM_KEY_PUSH_APPID, "");
        return paramsBuilder;
    }

    public ParamsBuilder put(String k, String v) {
        if (v == null) return this;
        mParams.put(k, v);
        return this;
    }

    public ParamsBuilder put(String k, int v) {
        mParams.put(k, String.valueOf(v));
        return this;
    }

    public ParamsBuilder put(String k, long v) {
        mParams.put(k, String.valueOf(v));
        return this;
    }

    public ParamsBuilder remove(Object k) {
        if (k == null) return this;
        mParams.remove(k);
        return this;
    }

    public ParamsBuilder mock(boolean mock) {
        if (mock) {
            mParams.clear();
        }
        return this;
    }

    public HashMap<String, String> build() {
        return mParams;
    }
}
