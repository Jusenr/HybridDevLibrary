package com.mobisoft.mbswebplugin.Cmd.DoCmd;

import android.content.Context;

import com.mobisoft.mbswebplugin.Cmd.DoCmdMethod;
import com.mobisoft.mbswebplugin.MbsWeb.HybridWebView;
import com.mobisoft.mbswebplugin.MvpMbsWeb.Interface.MbsWebPluginContract;
import com.mobisoft.mbswebplugin.dao.db.WebViewDao;
import com.mobisoft.mbswebplugin.utils.UrlUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Author：Created by fan.xd on 2017/3/1.
 * Email：fang.xd@mobisoft.com.cn
 * Description：读取数据库
 * {"account":"8101099","key":"xxx"，"data":"xxx"}account:工号
 * key:存取key值
 * data:所存的数据
 */
public class dbGet extends DoCmdMethod {
    @Override
    public String doMethod(HybridWebView webView, Context context, MbsWebPluginContract.View view, MbsWebPluginContract.Presenter presenter, String cmd, String params, String callBack) {
        try {
            JSONObject jsonObject = new JSONObject(params);
            /** 存储key*/
            String getkey = jsonObject.optString("key");
            /*工号*/
            String account = jsonObject.optString("account");
            String valueFromDB = getValueFromDB(context, account, getkey);
//            JsResult jsResult = new JsResult();
//            jsResult.setAccount(account);
//            jsResult.setKey(getkey);
//            jsResult.setResult(true);
//            jsResult.setValue(valueFromDB);
//            String json= JSON.toJSONString(jsResult);
            webView.loadUrl(UrlUtil.getFormatJs(callBack,valueFromDB));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 根据key 从数据库得到value
     *
     * @param account 工号
     * @param key     关键字
     * @return 根据acoutn 和 key查询据库的数据
     */
    protected String getValueFromDB(Context context, String account, String key) {
        WebViewDao mWebViewDao = new WebViewDao(context.getApplicationContext());
        String pram;
        try {
             pram = URLDecoder.decode(key, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            pram= key;
        }

        return mWebViewDao.getWebviewValuejson(account, pram);
    }
}
