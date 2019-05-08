package com.mobisoft.mbswebplugin.cmd.strategy;

/**
 * Author：Created by fan.xd on 2017/2/24.
 * Email：fang.xd@mobisoft.com.cn
 * Description：核心的执行抽象类
 */

public abstract class DoCmdMethod {
    public static String TAG = DoCmdMethod.class.getSimpleName();

    public DoCmdMethod() {
        try {
            TAG = getClass().getMethods()[0].getDeclaringClass().getSimpleName();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param webView   核心组件 webView
     * @param context   环境
     * @param view      视图类
     * @param presenter 持有者类
     * @param cmd       cmd命令
     * @param params    参数
     * @param callBack  js回掉的方法
     * @return 任意
     */
//    abstract public String doMethod(HybridWebView webView, Context context
//            , MbsWebPluginContract.View view, MbsWebPluginContract.Presenter presenter
//            , String cmd, String params, String callBack);
}
