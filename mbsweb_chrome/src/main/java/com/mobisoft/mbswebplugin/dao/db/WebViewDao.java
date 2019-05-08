/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mobisoft.mbswebplugin.dao.db;

import android.content.Context;
import android.text.TextUtils;

public class WebViewDao {
	/**
	 * webview的表名
	 */
	public static final String TABLE_NAME = "webviewdao";
	/**
	 * 主键 id
	 */
	public static final String COLUMN_ACCOUNT = "account";
	/**
	 * webview 网络数据的key
	 */
	public static final String COLUMN_KEY = "key";
	/**
	 * webview 网络数据的json
	 */
	public static final String COLUMN_JSON = "value";

	public WebViewDao(Context context) {
		DemoDBManager.getInstance().onInit(context);
	}

	/**
	 * 保存 webview 到 数据库
	 *
	 * @param account 传入唯一 webview的标识
	 * @param key     传入webview 的key
	 * @param json    传入webview的json
	 */
	public long saveWebviewJson(String account, String key, String json) {
		return DemoDBManager.getInstance().saveWebviewJson(account, key, json);

	}

	/**
	 * 根绝key删除某一个值
	 *
	 * @param account account 为空不用传递
	 * @param key
	 */
	public int deleteWebviewList(String account, String key) {
		return DemoDBManager.getInstance().deletWebviewList(account, key);
	}

	/**
	 * @param account
	 * @param key
	 * @return
	 */
	public String getWebviewValuejson(String account, String key) {
		if (TextUtils.isEmpty(account)) {
			return DemoDBManager.getInstance().getWebviewJson(key);
		} else {
			return DemoDBManager.getInstance().getWebviewJson(account, key);

		}
	}

	/**
	 * @param key
	 * @return
	 */
	public String getWebviewValuejson(String key) {
		return DemoDBManager.getInstance().getWebviewJson(key);
	}

}
