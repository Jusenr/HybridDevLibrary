package com.mobisoft.mbswebplugin.Cmd.DoCmd;

import android.content.Context;

import com.mobisoft.mbswebplugin.Cmd.DoCmdMethod;
import com.mobisoft.mbswebplugin.MbsWeb.HybridWebView;
import com.mobisoft.mbswebplugin.MvpMbsWeb.Interface.MbsWebPluginContract;
import com.mobisoft.mbswebplugin.utils.LogUtils;

/**
 * Author：Created by fan.xd on 2017/6/8.
 * Email：fang.xd@mobisoft.com.cn
 * Description：显示输入框
 */

public class ShowInPutWindows extends DoCmdMethod {
    @Override
    public String doMethod(HybridWebView webView, Context context, MbsWebPluginContract.View view, MbsWebPluginContract.Presenter presenter, String cmd, String params, String callBack) {
        LogUtils.i(TAG, "doMethod-params: " + params);
        LogUtils.i(TAG, "doMethod-callBack: " + callBack);
        view.showInputWindow(params, callBack);
        return null;
    }
}
