package com.mobisoft.mbswebplugin.MbsWeb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.mobisoft.mbswebplugin.base.AppConfing;
import com.mobisoft.mbswebplugin.helper.CoreConfig;
import com.mobisoft.mbswebplugin.helper.FunctionConfig;
import com.mobisoft.mbswebplugin.helper.ThemeConfig;


/**
 * 启动WebAppActivity的 配置信息
 */
public class HybridWebApp {
    static final int REQUEST_CODE = 1001;
    /**
     * activity状态的初始化设置
     */
    private static FunctionConfig mFunctionConfig;
    /**
     * activity主题设置
     */
    private static ThemeConfig mThemeConfig;
    /**
     * 启动混合式开发web View的配置
     */
    private static CoreConfig mCoreConfig;
    private static int mRequestCode = REQUEST_CODE;

    public HybridWebApp(CoreConfig coreConfig) {
        mCoreConfig = coreConfig;
        mThemeConfig = coreConfig.getThemeConfig();
        mFunctionConfig = coreConfig.getFunctionConfig();
    }

    public static HybridWebApp init(CoreConfig coreConfig) {
        return new HybridWebApp(coreConfig);
    }

    public void startWebActivity(Context context, Class mclass, Bundle bundle) {

        Intent intent = getIntent(context, mclass, bundle);
        context.startActivity(intent);
    }
    public void startWebActivity(Context context, Class mclass, Bundle bundle,int flag) {

        Intent intent = getIntent(context, mclass, bundle);
        intent.setFlags(flag|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(flag);
        context.startActivity(intent);
    }
    /***
     *
     * @param context
     * @param mclass
     * @param bundle
     * @return
     */
    @NonNull
    private Intent getIntent(Context context, Class mclass, Bundle bundle) {
        Intent intent = new Intent(context, mclass);
        intent.putExtra(AppConfing.URL, mCoreConfig.getUrl());
        intent.putExtra(AppConfing.ACCOUNT, mCoreConfig.getAccount());
        intent.putExtra(AppConfing.ANIMRES, mCoreConfig.getAnimRes());
        intent.putExtra(AppConfing.IS_HIDENAVIGATION, mCoreConfig.getHideNavigation());


        intent.putExtra(AppConfing.TITLECOLOR, mThemeConfig.getTitleBgColor());
        intent.putExtra(AppConfing.SYSTEM_BAR_COLOR, mThemeConfig.getSystemBarColor());


        intent.putExtra(AppConfing.ICON_BACK, mThemeConfig.getIconBack());
        intent.putExtra(AppConfing.ICON_TITLE_RIGHT, mThemeConfig.getIconTitleRight());
        intent.putExtra(AppConfing.ICON_TITLE_CENTER, mThemeConfig.getIconTitleCenter());
        intent.putExtra(AppConfing.TITLE_LEFT_TEXT_COLOR, mThemeConfig.getTitleLeftTextColor());
        intent.putExtra(AppConfing.TITLE_RIGHT_TEXT_COLOR, mThemeConfig.getTitleRightTextColor());
        intent.putExtra(AppConfing.TITLE_CENTER_TEXT_COLOR, mThemeConfig.getTitleCenterTextColor());

        intent.putExtra(AppConfing.SHOWMOUDLE, mFunctionConfig.getIsLeftAndRightMenu());
        intent.putExtra(AppConfing.SHOWMOUDLESEARCHPAGE, mFunctionConfig.getIsNoTilte());
        intent.putExtra(AppConfing.IS_LEFT_ICON_SHOW, mFunctionConfig.getIsLeftIconShow());
        intent.putExtra(AppConfing.IS_LEFT_TEXT_SHOW, mFunctionConfig.getIsLeftTextShow());
        intent.putExtra(AppConfing.IS_SYSTEM_BAR_SHOW, mFunctionConfig.getIsSystemBarShow());
        intent.putExtra(AppConfing.IS_REFRESH_ENABLE, mFunctionConfig.getIsRefreshEnable());
        intent.putExtra(AppConfing.IS_TRANSITION_MODE_ENABLE, mFunctionConfig.getIsTransitionModeEnable());
        intent.putExtra(AppConfing.IS_TRANSITION_MODE, mFunctionConfig.getIsTransitionMode().name());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        /**
         * mvp模式下的改进方法
         */
        if (bundle == null)
            bundle = new Bundle();
        bundle.putString(AppConfing.URL, mCoreConfig.getUrl());
        bundle.putString(AppConfing.ACCOUNT, mCoreConfig.getAccount());
        bundle.putInt(AppConfing.ANIMRES, mCoreConfig.getAnimRes());
        bundle.putBoolean(AppConfing.IS_HIDENAVIGATION, mCoreConfig.getHideNavigation());

        bundle.putInt(AppConfing.TITLECOLOR, mThemeConfig.getTitleBgColor());
        bundle.putInt(AppConfing.SYSTEM_BAR_COLOR, mThemeConfig.getSystemBarColor());

        bundle.putInt(AppConfing.ICON_BACK, mThemeConfig.getIconBack());
        bundle.putInt(AppConfing.ICON_TITLE_RIGHT, mThemeConfig.getIconTitleRight());
        bundle.putInt(AppConfing.ICON_TITLE_CENTER, mThemeConfig.getIconTitleCenter());
        bundle.putInt(AppConfing.TITLE_LEFT_TEXT_COLOR, mThemeConfig.getTitleLeftTextColor());
        bundle.putInt(AppConfing.TITLE_RIGHT_TEXT_COLOR, mThemeConfig.getTitleRightTextColor());
        bundle.putInt(AppConfing.TITLE_CENTER_TEXT_COLOR, mThemeConfig.getTitleCenterTextColor());

        bundle.putBoolean(AppConfing.SHOWMOUDLE, mFunctionConfig.getIsLeftAndRightMenu());
        bundle.putBoolean(AppConfing.SHOWMOUDLESEARCHPAGE, mFunctionConfig.getIsNoTilte());
        bundle.putBoolean(AppConfing.IS_LEFT_ICON_SHOW, mFunctionConfig.getIsLeftIconShow());
        bundle.putBoolean(AppConfing.IS_LEFT_TEXT_SHOW, mFunctionConfig.getIsLeftTextShow());
        bundle.putBoolean(AppConfing.IS_SYSTEM_BAR_SHOW, mFunctionConfig.getIsSystemBarShow());
        bundle.putBoolean(AppConfing.IS_REFRESH_ENABLE, mFunctionConfig.getIsRefreshEnable());
        bundle.putBoolean(AppConfing.IS_TRANSITION_MODE_ENABLE, mFunctionConfig.getIsTransitionModeEnable());
        bundle.putString(AppConfing.IS_TRANSITION_MODE, mFunctionConfig.getIsTransitionMode().name());


        intent.putExtras(bundle);
        return intent;
    }

    /***
     *
     * @param context
     * @param mclass
     */
    public void startWebActivity(Context context, Class mclass) {
        startWebActivity(context, mclass, null);
    }

    /**
     * 启动首页
     * @param context
     * @param mclass
     * @param bundle
     */
    public void startHomeActivity(Context context, Class mclass,Bundle bundle) {
        Intent intent = getIntent(context, mclass, bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
    /**
     * 启动首页
     * @param context
     * @param mclass
     * @param bundle
     * @param FLAG 启动模式
     */
    public void startHomeActivity(Context context, Class mclass,Bundle bundle,int FLAG) {
        Intent intent = getIntent(context, mclass, bundle);
        intent.addFlags(FLAG);
        intent.setFlags(FLAG);
        context.startActivity(intent);
    }
    /**
     * 目前还没有完善，请勿使用
     */
//    public void startWebActivityForResult(int requestCode) {
//        Intent intent = new Intent(mCoreConfig.getContext(), WebAppActivity.class);
//        intent.putExtra(WebAppActivity.URL, mCoreConfig.getUrl());
//        intent.putExtra(WebAppActivity.ACCOUNT, mCoreConfig.getAccount());
//        intent.putExtra(WebAppActivity.ANIMRES, mCoreConfig.getAnimRes());
//        intent.putExtra(WebAppActivity.TITLECOLOR, mThemeConfig.getTitleBgColor());
//        intent.putExtra(WebAppActivity.SYSTEM_BAR_COLOR, mThemeConfig.getSystemBarColor());
//
//        intent.putExtra(WebAppActivity.ICON_BACK, mThemeConfig.getIconBack());
//        intent.putExtra(WebAppActivity.ICON_TITLE_RIGHT, mThemeConfig.getIconTitleRight());
//        intent.putExtra(WebAppActivity.ICON_TITLE_CENTER, mThemeConfig.getIconTitleCenter());
//        intent.putExtra(WebAppActivity.TITLE_LEFT_TEXT_COLOR, mThemeConfig.getTitleLeftTextColor());
//        intent.putExtra(WebAppActivity.TITLE_RIGHT_TEXT_COLOR, mThemeConfig.getTitleRightTextColor());
//        intent.putExtra(WebAppActivity.TITLE_CENTER_TEXT_COLOR, mThemeConfig.getTitleCenterTextColor());
//
//        intent.putExtra(WebAppActivity.SHOWMOUDLE, mFunctionConfig.getIsLeftAndRightMenu());
//        intent.putExtra(WebAppActivity.SHOWMOUDLESEARCHPAGE, mFunctionConfig.getIsNoTilte());
//        intent.putExtra(WebAppActivity.IS_LEFT_ICON_SHOW, mFunctionConfig.getIsLeftIconShow());
//        intent.putExtra(WebAppActivity.IS_LEFT_TEXT_SHOW, mFunctionConfig.getIsLeftTextShow());
//        intent.putExtra(WebAppActivity.IS_SYSTEM_BAR_SHOW, mFunctionConfig.getIsSystemBarShow());
//        intent.putExtra(WebAppActivity.IS_REFRESH_ENABLE, mFunctionConfig.getIsRefreshEnable());
//
//        ((Activity) mCoreConfig.getContext()).startActivityForResult(intent, requestCode);
//    }

//    public Fragment getWebAppFragment() {
//        WebAppFragment mWebAppFragment = new WebAppFragment();
//        Bundle mBundle = new Bundle();
//        mBundle.putString(WebAppActivity.URL, mCoreConfig.getUrl());
//        mBundle.putString(WebAppActivity.ACCOUNT, mCoreConfig.getAccount());
//        mBundle.putInt(WebAppActivity.ANIMRES, mCoreConfig.getAnimRes());
//        mBundle.putInt(WebAppActivity.TITLECOLOR, mThemeConfig.getTitleBgColor());
//        mBundle.putInt(WebAppActivity.SYSTEM_BAR_COLOR, mThemeConfig.getSystemBarColor());
//
//        mBundle.putInt(WebAppActivity.ICON_BACK, mThemeConfig.getIconBack());
//        mBundle.putInt(WebAppActivity.ICON_TITLE_RIGHT, mThemeConfig.getIconTitleRight());
//        mBundle.putInt(WebAppActivity.ICON_TITLE_CENTER, mThemeConfig.getIconTitleCenter());
//        mBundle.putInt(WebAppActivity.TITLE_LEFT_TEXT_COLOR, mThemeConfig.getTitleLeftTextColor());
//        mBundle.putInt(WebAppActivity.TITLE_RIGHT_TEXT_COLOR, mThemeConfig.getTitleRightTextColor());
//        mBundle.putInt(WebAppActivity.TITLE_CENTER_TEXT_COLOR, mThemeConfig.getTitleCenterTextColor());
//
//        mBundle.putBoolean(WebAppActivity.SHOWMOUDLE, mFunctionConfig.getIsLeftAndRightMenu());
//        mBundle.putBoolean(WebAppActivity.SHOWMOUDLESEARCHPAGE, mFunctionConfig.getIsNoTilte());
//        mBundle.putBoolean(WebAppActivity.IS_LEFT_ICON_SHOW, mFunctionConfig.getIsLeftIconShow());
//        mBundle.putBoolean(WebAppActivity.IS_LEFT_TEXT_SHOW, mFunctionConfig.getIsLeftTextShow());
//        mBundle.putBoolean(WebAppActivity.IS_SYSTEM_BAR_SHOW, mFunctionConfig.getIsSystemBarShow());
//        mBundle.putBoolean(WebAppActivity.IS_REFRESH_ENABLE, mFunctionConfig.getIsRefreshEnable());
//
//        mWebAppFragment.setArguments(mBundle);
//        return mWebAppFragment;
//    }
    public static CoreConfig getCoreConfig() {
        return mCoreConfig;
    }

    public static FunctionConfig getFunctionConfig() {
        return mFunctionConfig;
    }

    public static int getRequestCode() {
        return mRequestCode;
    }
}