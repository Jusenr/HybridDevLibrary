package com.mobisoft.mbswebplugin.Cmd.DoCmd;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import com.mobisoft.mbswebplugin.Cmd.DoCmdMethod;
import com.mobisoft.mbswebplugin.MbsWeb.HybridWebView;
import com.mobisoft.mbswebplugin.MvpMbsWeb.Interface.MbsRequestPermissionsListener;
import com.mobisoft.mbswebplugin.MvpMbsWeb.Interface.MbsWebPluginContract;
import com.mobisoft.mbswebplugin.utils.Utils;

/**
 * Author：Created by fan.xd on 2017/4/27.
 * Email：fang.xd@mobisoft.com.cn
 * Description： 打电话
 */

public class Cellphone extends DoCmdMethod implements MbsRequestPermissionsListener{
    private String params;
    private Context context;
    private MbsWebPluginContract.Presenter presenter;

    @Override
    public String doMethod(HybridWebView webView, Context context, MbsWebPluginContract.View view, MbsWebPluginContract.Presenter presenter, String cmd, String params, String callBack) {
        this.context = context;
        this.params = params;
        this.presenter = presenter;
        presenter.setMbsRequestPermissionsResultListener(this);
        Utils.getPhone(context, params);



        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(permissions[0].equals(Manifest.permission.CALL_PHONE) && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Utils.cellPhone(context, params);
            presenter.setMbsRequestPermissionsResultListener(null);

        }
    }
}
