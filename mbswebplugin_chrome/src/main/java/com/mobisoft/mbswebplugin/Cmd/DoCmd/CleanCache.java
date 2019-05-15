package com.mobisoft.mbswebplugin.Cmd.DoCmd;

import android.content.Context;

import com.mobisoft.mbswebplugin.Cmd.DoCmdMethod;
import com.mobisoft.mbswebplugin.MbsWeb.HybridWebView;
import com.mobisoft.mbswebplugin.MvpMbsWeb.Interface.MbsWebPluginContract;
import com.mobisoft.mbswebplugin.utils.FileUtils;

/**
 * Author：Created by fan.xd on 2018/9/28.
 * Email：fang.xd@mobisoft.com.cn
 * Description：清理缓存
 */
public class CleanCache extends DoCmdMethod {
	@Override
	public String doMethod(HybridWebView webView, Context context, MbsWebPluginContract.View view, MbsWebPluginContract.Presenter presenter, String cmd, String params, String callBack) {
		webView.clearCache(true);
		FileUtils.deleteFile(context.getCacheDir());

		return null;
	}
}
