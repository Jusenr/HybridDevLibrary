package com.mobisoft.mbswebplugin.proxy.Setting;

import com.mobisoft.mbswebplugin.R;
import com.mobisoft.mbswebplugin.proxy.Cache.CacheManifest;
import com.mobisoft.mbswebplugin.proxy.Work.DownloadSrcCallback;

/**
 * Author：Created by fan.xd on 2017/3/17.
 * Email：fang.xd@mobisoft.com.cn
 * Description：ProxyConfig 代理库的基本配置；
 */

public class ProxyConfig {
	/**
	 * 端口号
	 */
	private int PORT;
	/**
	 * 缓存路径
	 */
	private String cachePath;
	/**
	 * 缓存路径
	 */
	private String h5Path;
	/**
	 * 是否弹窗
	 */
	private boolean isShowDialog;
	/**
	 * 缓存地址
	 */
	private String cacheUrl;
	/**
	 * 下拉刷新图标
	 */
	private int loadingIc;
	/**
	 * 下拉刷新背景色
	 */
	private int loadingBg;
	/**
	 * 是否https 连接
	 */
	private boolean changeHttps;
	/**
	 * 当前服务器的基本地址
	 */
	private String baseUrl;
	/**
	 * 当前服务器的, 本地地址
	 */
	private String localUlr;
	/**
	 * 网络地址的url
	 */
	private String netUrl;
	/**
	 * 当前图片服务器的基本本地址
	 */
	private String imageBaseUrl;
	/**
	 * 是否开启代理服务
	 */
	private boolean openProxy=false;
	/**
	 * 设置toolBar 的背景颜色
	 */
	private int backgroundColorId;
	/**
	 * 设置boolBar 的第二种颜色
	 */
	private int secondBackgroundColorId;
	/**
	 * 设置标题颜色
	 */
	private int textColorId;
	/**
	 * 设置标题的第二中颜色
	 */
	private int secondTextColorId;
	/**
	 * 设置返回图标
	 */
	private int backIcon;

	/**
	 * 设置缓存模式
	 */
	private int cacheMode = -2;

	/**
	 * 设置状态栏字体颜色深浅
	 */
	private boolean isDark;

	public boolean isDark() {
		return isDark;
	}

	public ProxyConfig setDark(boolean dark) {
		isDark = dark;
		return this;
	}

	public int getCacheMode() {
		return cacheMode;
	}

	public ProxyConfig setCacheMode(int cacheMode) {
		this.cacheMode = cacheMode;
		return this;
	}

	public int getBackIcon() {
		return backIcon;
	}

	public ProxyConfig setBackIcon(int backIcon) {
		this.backIcon = backIcon;
		return this;
	}

	private DownloadSrcCallback srcCallback;


	public static void setConfig(ProxyConfig config) {
		ProxyConfig.config = config;
	}

	public boolean isOpenProxy() {
		return openProxy;
	}

	public ProxyConfig setOpenProxy(boolean openProxy) {
		this.openProxy = openProxy;
		return this;
	}

	/**
	 * 设置加载回掉
	 *
	 * @param callback 图标ID
	 */
	public ProxyConfig setDownloadSrcCallback(DownloadSrcCallback callback) {
		this.srcCallback = callback;
		return this;
	}

	public DownloadSrcCallback getSrcCallback() {
		return srcCallback;
	}

	public int getLoadingIc() {
		return loadingIc == 0 ? R.drawable.loading_12 : loadingIc;
	}

	/**
	 * 设置加载图标
	 *
	 * @param loadingIc 图标ID
	 */
	public ProxyConfig setLoadingIc(int loadingIc) {
		this.loadingIc = loadingIc;
		return this;
	}

	public String getImageBaseUrl() {
		return imageBaseUrl;
	}

	public ProxyConfig setImageBaseUrl(String imageBaseUrl) {
		this.imageBaseUrl = imageBaseUrl;
		return this;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public ProxyConfig setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
		return this;
	}

	/**
	 * 设置背景颜色
	 *
	 * @return 背景色ID
	 */
	public ProxyConfig setLoadingBg(int loadingBg) {
		this.loadingBg = loadingBg;
		return this;
	}

	/**
	 * 设置cacheManifest地址
	 *
	 * @param cacheUrl
	 * @return
	 */
	public ProxyConfig setCacheUrl(String cacheUrl) {
		this.cacheUrl = cacheUrl;
		return this;
	}

	/**
	 * 设置缓存路径
	 *
	 * @param cachePath 缓存路径
	 * @return
	 */
	public ProxyConfig setCachePath(String cachePath) {
		this.cachePath = cachePath;
		return this;
	}

	public String getNetUrl() {
		return netUrl;
	}

	public ProxyConfig setNetUrl(String netUrl) {
		this.netUrl = netUrl;
		return this;
	}

	public String getLocalUlr() {
		return localUlr;
	}

	public ProxyConfig setLocalUlr(String localUlr) {
		this.localUlr = localUlr;
		return this;
	}

	/**
	 * 设置代理的端口号
	 *
	 * @param PORT
	 * @return
	 */
	public ProxyConfig setPORT(int PORT) {
		this.PORT = PORT;
		return this;
	}

	/**
	 * 设置h5下载包的地址
	 *
	 * @return
	 */
	public String getH5Path() {
		return h5Path;
	}

	/**
	 * 设置h5下载包的地址
	 *
	 * @param h5Path
	 * @return
	 */
	public ProxyConfig setH5Path(String h5Path) {
		this.h5Path = h5Path;
		return this;
	}

	/**
	 * 设置是否弹出缓存进度窗口
	 *
	 * @param showDialog false ： 不显示
	 * @return
	 */
	public ProxyConfig setShowDialog(boolean showDialog) {
		isShowDialog = showDialog;
		return this;
	}

	public String getCacheUrl() {
		return cacheUrl;
	}

	public boolean isShowDialog() {
		return isShowDialog;
	}

	public int getLoadingBg() {
		return loadingBg == 0 ? R.color.gray : loadingBg;
	}

	public boolean isChangeHttps() {
		return changeHttps;
	}

	public ProxyConfig setChangeHttps(boolean changeHttps) {
		this.changeHttps = changeHttps;
		return this;
	}

	public int getPORT() {
		if (PORT < 1024) {
			PORT = 8182;
		}
		return PORT;
	}

	public String getCachePath() {
		return cachePath;
	}

	private static ProxyConfig config;

	/**
	 * 获取实例
	 *
	 * @return
	 */
	public static ProxyConfig getConfig() {
		if (config == null) {
			config = new ProxyConfig();
		}
		return config;
	}

	public void excuet() {
		new CacheManifest().execute();
	}


	public int getBackgroundColorId() {
		return backgroundColorId == 0 ? R.color.main_toolBar_bar : backgroundColorId;
	}

	public ProxyConfig setBackgroundColorId(int backgroundColorId) {
		this.backgroundColorId = backgroundColorId;
		return this;
	}

	public int getSecondBackgroundColorId() {
		return secondBackgroundColorId == 0 ? R.color.main_system_status_bar : secondBackgroundColorId;
	}

	public ProxyConfig setSecondBackgroundColorId(int secondBackgroundColorId) {
		this.secondBackgroundColorId = secondBackgroundColorId;
		return this;
	}

	public int getTextColorId() {
		return textColorId == 0 ? R.color.mbs_web_plugin_title_color : textColorId;
	}

	public ProxyConfig setTextColorId(int textColorId) {
		this.textColorId = textColorId;
		return this;
	}

	public int getSecondTextColorId() {
		return secondTextColorId == 0 ? R.color.color_black : secondTextColorId;
	}

	public ProxyConfig setSecondTextColorId(int secondTextColorId) {
		this.secondTextColorId = secondTextColorId;
		return this;

	}

	public ProxyConfig setSrcCallback(DownloadSrcCallback srcCallback) {
		this.srcCallback = srcCallback;
		return this;

	}
}
