package com.mobisoft.mbswebplugin.Cmd.DoCmd;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.mobisoft.mbswebplugin.Cmd.DoCmdMethod;
import com.mobisoft.mbswebplugin.MbsWeb.HybridWebView;
import com.mobisoft.mbswebplugin.MvpMbsWeb.Interface.MbsRequestPermissionsListener;
import com.mobisoft.mbswebplugin.MvpMbsWeb.Interface.MbsResultListener;
import com.mobisoft.mbswebplugin.MvpMbsWeb.Interface.MbsWebPluginContract;
import com.mobisoft.mbswebplugin.R;
import com.mobisoft.mbswebplugin.core.QrcodeActivity;
import com.mobisoft.mbswebplugin.utils.LogUtils;
import com.mobisoft.mbswebplugin.utils.ToastUtil;
import com.mobisoft.mbswebplugin.utils.UrlUtil;

import org.json.JSONException;
import org.json.JSONObject;

import static android.app.Activity.RESULT_OK;

/**
 * Author：Created by fan.xd on 2017/8/8.
 * Email：fang.xd@mobisoft.com.cn
 * Description：二位吗识别
 */

public class QrCode extends DoCmdMethod implements MbsResultListener {
    private static final int PERMISSIONS_CAMERA_AND_READ_EXTERNAL_STORAGE = 101;
    private String callback;
    private Context context;

    @Override
    public String doMethod(HybridWebView webView, final Context context, MbsWebPluginContract.View view, MbsWebPluginContract.Presenter presenter, String cmd, String params, String callBack) {
        this.context = context;
        this.callback = callBack;

        presenter.setResultListener(this);
        presenter.setMbsRequestPermissionsResultListener(new MbsRequestPermissionsListener() {
            @Override
            public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                if (requestCode == PERMISSIONS_CAMERA_AND_READ_EXTERNAL_STORAGE) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                            && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                        openScanUI();
                    } else {
                        ToastUtil.showShortToast(context, context.getString(R.string.lack_camera_permiss));
                    }
                }
            }
        });
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openScanUI();
        } else {
            //6.0
//            System.out.println("sdk 6.0");
            boolean permission_sdcard_read = ContextCompat.checkSelfPermission(this.context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
            boolean permission_camera = ContextCompat.checkSelfPermission(this.context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
            if (permission_sdcard_read && permission_camera) {
                //该权限已经有了
                openScanUI();
            } else {
                //申请该权限
                final String[] permission = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
                ActivityCompat.requestPermissions((Activity) this.context, permission, PERMISSIONS_CAMERA_AND_READ_EXTERNAL_STORAGE);
            }
        }

        return null;
    }

    @Override
    public void onActivityResult(Context context, MbsWebPluginContract.View view, int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String result = data.getStringExtra("result");
            LogUtils.i("onScanQRCodeSuccess", "result：" + result);
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("result", result);
                view.loadUrl(UrlUtil.getFormatJs(callback, jsonObject.toString()));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void openScanUI() {
        Intent intent = new Intent(context, QrcodeActivity.class);
        ((Activity) context).startActivityForResult(intent, QrcodeActivity.RESULT_QR_CODE);
    }
}
