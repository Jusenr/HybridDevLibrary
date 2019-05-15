package com.mobisoft.mbswebplugin.base;

/**
 * Created by fan on 2016/6/15.
 * 网络环境配置
 */
public class AppConfing {


    public static final String EXIST = "exist";

    public static final String NOTEXIST = "notexist";

    public static final String DOWNLOADING = "downloading";

    /**
     * 定位获取的地址Province
     */
    public static String LOCATION_Province;
    /**
     * 定位获取的地址city
     */
    public static String LOCATION_City;

    /**
     * 权限申请 code
     */
    public static final int PERMISSIONS_REQUEST_CODE = 10010;
    /**
     * 下载文件权限申请 code
     */
    public static final int PERMISSIONS_REQUEST_CODE_SD = 10011;

    /**
     * 聊天历史 界面 返回eventCode
     */
    public static final int CHTA_BACK = 110;
    /**
     * 工号ACCount；
     */
    public static String ACCOUNT = "";

    /**
     * MainActivity 的finsh方法   eventCode
     */
    public static final int MAIN_FINSH = 111;
    /**
     * MainActivity 的backDwon方法  eventCode
     */
    public static final int MAIN_BACK_DOWN = 112;

    /**
     * 改变状态栏的颜色  eventCode
     */
    public static final int STATUS_BAR = 113;


    /**
     * 群组聊天邀请好友  eventCode
     */
    public static final int INVITE_GROUP = 116;

    /**
     * 单聊天邀请好友  eventCode
     */
    public static final int INVITE_SINGLE = 117;

    /**
     * 传递单聊好友工号 eventCode
     */
    public static final int INVITE_SINGLE_MEMBRER = 118;



    /**
     * 请求打开相机
     */
    protected final int REQUEST_CODE_CAMERA = 1000;
    /**
     * 请求打开相册
     */
    protected final int REQUEST_CODE_GALLERY = 1001;
    /**
     * 请求打开相机 返回图片转 base64
     */
    protected final int REQUEST_CODE_CAMERA_B = 1002;
    /**
     * 请求打开相册 返回 图片转 base64
     */
    protected final int REQUEST_CODE_GALLERY_B = 1003;
    /**
     * openNextWebActivity  resultCode
     **/
    public static final int INTENT_REQUEST_CODE = 3;
    /**
     * 启动地图页面的 resultCode
     */
    public static final int REQUEST_CODE_MAP = 4;
    /**
     * 启动扫描二维码页面的 resultCode
     */
    public static final int INTENT_REQUEST_QRCODE = 6;
    /**
     * 创建手势解锁 resultCode
     */
    public static final int INTENT_REQUEST_GESTURE = 7;

    /**
     * 是否刷新！
     */
    public static final String REFRESH = "Refresh"; //

    /**
     * 是否刷新！
     */
    public static final String REFRESH_NO_ANIMATION = "NO_ANIMATION"; //
    /**
     * 是否局部刷新
     */
    public static final String local_Refresh = "localRefresh"; //
    /**
     * 返回参数
     */
    public static final String backParam = "backParam"; //
    /***
     * 刷新的url！
     */
    public static final String REFRESH_URL = "RefreshUrl"; //
    /**
     * log日志标签
     */
    protected static final String TAG = "WebAppActivity"; //
    /**
     * 初次进来webview 的 url （必须要）
     */
    public static final String URL = "url";

    /**
     * title 颜色 （必须要）
     */
    public static final String TITLECOLOR = "titleColor";
    /**
     * 初始化webview时avtivity进入动画 （非必须，有默认值）
     */
    public static final String ANIMRES = "AnimRes";
    /**
     * title左右两边菜单模式 （非必须，有默认值）
     */
    public static final String SHOWMOUDLE = "showmodel";
    /**
     * 搜索页面没有title模式 （非必须，有默认值）
     */
    public static final String SHOWMOUDLESEARCHPAGE = "showModelSearchPage";
    /**
     * 沉浸式菜单栏颜色 （非必须，有默认值）
     */
    public static final String SYSTEM_BAR_COLOR = "SystemBarColor";
    /**
     * 标题中间文字颜色颜色 （非必须，有默认值）
     */
    public static final String TITLE_CENTER_TEXT_COLOR = "TitleCenterTextColor";
    /**
     * 标题左边文字颜色颜色 （非必须，有默认值）
     */
    public static final String TITLE_LEFT_TEXT_COLOR = "TitleLeftTextColor";
    /**
     * 标题右边文字颜色颜色 （非必须，有默认值）
     */
    public static final String TITLE_RIGHT_TEXT_COLOR = "TitleRightTextColor";
    /**
     * 标题返回图片 （非必须，有默认值）
     */
    public static final String ICON_BACK = "IconBack";
    /**
     * 是否左上角显示icon
     */
    public static final String ISSHOWLEFTICON = "IsShowLeftIcon";
    /**
     * 标题中间图片 （非必须，有默认值）
     */
    public static final String ICON_TITLE_CENTER = "IconTitleCenter";
    /**
     * 标题右边图片 （非必须，有默认值）
     */
    public static final String ICON_TITLE_RIGHT = "IconTitleRight";
    /**
     * 是否显示左边“返回”文字 （非必须，有默认值）
     */
    public static final String IS_LEFT_TEXT_SHOW = "IsLeftTextShow";
    /**
     * 是否显示左边“返回”图片 （非必须，有默认值）
     */
    public static final String IS_LEFT_ICON_SHOW = "IsLeftIconShow";
    /**
     * 是否显示沉浸式菜单栏 （非必须，有默认值）
     */
    public static final String IS_SYSTEM_BAR_SHOW = "IsSystemBarShow";
    /**
     * 是否支持刷新 （非必须，有默认值）
     */
    public static final String IS_REFRESH_ENABLE = "IsRefreshEnable";
    /**
     * activity是否支持打开方式 （非必须，有默认值）
     */
    public static final String IS_TRANSITION_MODE_ENABLE = "isTransitionModeEnable";
    /**
     * activity打开方式 （非必须，有默认值）
     */
    public static final String IS_TRANSITION_MODE = "isTransitionMode";

    /**
     * 是否开始隐藏导航栏 （非必须，有默认值）false 为 不隐藏，true 为隐藏导航栏
     */
    public static final String IS_HIDENAVIGATION = "isHideNavigation";
    /**
     * 是否开始隐藏状态栏 （非必须，有默认值）false 为 不隐藏，true 为隐藏导航栏
     */
    public static final String IS_SYSTEMUIBAR = "is_SystemUibar";
    /**
     * IS_RESTART 是否重启APP
     * trye
     */
    public static final String IS_RESTART = "IS_RESTART";

    /**
     * 耐克 申请权限
     */
    public static final int NICK_REQUEST_CAMERA_CODE = 105;
    /**
     * 调用相册
     */
    int SELECT_PIC = 1;

    /**
     * 启动相机 耐克
     */
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 300;
    /**
     * 启动相册 耐克
     */
    public static final int PICK_IMAGE_ACTIVITY_REQUEST_CODE = 200;

    /**
     * 启动通讯的请求码
     */
    public static final int CONTACTS_CODE = 10008;
    /**
     * 启动扫描二维码传递回掉方法 Key
     **/
    public static final String FUNCTION = "function";
    /**
     * 启动扫描二维码传递参数  Key
     **/
    public static final String PARAMTER = "paramter";
    /***
     * 下拉刷新 延时取消刷新（等待initpage方法执行结束）
     */
    public static final int DELAY_MILLIS = 400;
    /**
     * TYPE_WEB 按返回键的类型 调用web View.goback()方法
     */
    public static final String TYPE_WEB = "pageWeb";
    /**
     * TYPE_ACTIVITY 按返回键的类型调用 activity.finish()方法
     */
    public static final String TYPE_ACTIVITY = "activity";


    public static int UI_WIDTH = 720;
    public static int UI_HEIGHT = 1280;
    public static int UI_DENSITY = 4;
    public static String SHARED_PATH = "app_share";

}
