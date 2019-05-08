package com.mobisoft.mbswebplugin.cmd;

import android.content.Context;
import android.os.Bundle;

/**
 * Author：Created by fan.xd on 2017/3/27.
 * Email：fang.xd@mobisoft.com.cn
 * Description：跳转到App主页
 */

public interface HomePage {
    /**
     * 跳转到homePage
     *
     * @param context context
     * @param bundle  传递数据
     * @param url     url地址
     * @param action  action
     */
    void goHomePage(Context context, Bundle bundle, String url, String action);

}
