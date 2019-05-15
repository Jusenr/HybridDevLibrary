package com.mobisoft.mbswebplugin.Cmd.DoCmd;

import android.content.Context;
import android.text.TextUtils;

import com.mobisoft.mbswebplugin.Cmd.DoCmdMethod;
import com.mobisoft.mbswebplugin.MbsWeb.HybridWebView;
import com.mobisoft.mbswebplugin.MvpMbsWeb.Interface.MbsWebPluginContract;
import com.mobisoft.mbswebplugin.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Author：Created by fan.xd on 2017/2/27.
 * Email：fang.xd@mobisoft.com.cn
 * Description： 转圈等待进度条
 */

public class ShowHudMethod extends DoCmdMethod {
    @Override
    public String doMethod(HybridWebView webView, Context context, MbsWebPluginContract.View view, MbsWebPluginContract.Presenter presenter, String cmd, String params, String callBack) {
        JSONObject object = null;
        try {
            object = new JSONObject(params);
            final String content = object.optString("content");
            final String action = object.optString("action");
            final String special = object.optString("special");
            //special == 1就是特殊的加载框 否则是原生的


            if (TextUtils.equals("hide", action)|| TextUtils.equals("false", action)) {
                view.hideHud();
                return null;
            }

            if (TextUtils.isEmpty(content)) {
                view.showHud(action, context.getString(R.string.loading),special);
            } else  {
                view.showHud(action, content,special);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
