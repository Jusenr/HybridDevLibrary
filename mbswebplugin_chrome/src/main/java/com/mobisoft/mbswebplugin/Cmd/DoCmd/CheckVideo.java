package com.mobisoft.mbswebplugin.Cmd.DoCmd;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.mobisoft.mbswebplugin.BuildConfig;
import com.mobisoft.mbswebplugin.Cmd.DoCmdMethod;
import com.mobisoft.mbswebplugin.Cmd.Working.DownloadVideoService;
import com.mobisoft.mbswebplugin.Entity.DownloadVideoVo;
import com.mobisoft.mbswebplugin.Entity.DownloadVideoVoDao;
import com.mobisoft.mbswebplugin.Entity.Videos;
import com.mobisoft.mbswebplugin.MbsWeb.HybridWebView;
import com.mobisoft.mbswebplugin.MvpMbsWeb.Interface.MbsRequestPermissionsListener;
import com.mobisoft.mbswebplugin.MvpMbsWeb.Interface.MbsWebPluginContract;
import com.mobisoft.mbswebplugin.R;
import com.mobisoft.mbswebplugin.base.AppConfing;
import com.mobisoft.mbswebplugin.dao.greenDao.GreenDBManager;
import com.mobisoft.mbswebplugin.utils.FileUtils;
import com.mobisoft.mbswebplugin.utils.LogUtils;
import com.mobisoft.mbswebplugin.utils.ToastUtil;
import com.mobisoft.mbswebplugin.utils.UrlUtil;

import org.greenrobot.greendao.query.QueryBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import static com.mobisoft.mbswebplugin.base.AppConfing.NOTEXIST;

/**
 * Author：Created by fan.xd on 2018/6/19.
 * Email：fang.xd@mobisoft.com.cn
 * Description：检查视频状态
 * 命令文档：1.29.检查视频状态
 * 参数	名称	类型	备注
 * 命令	cmd	字符串	checkVideo
 * 视频主键	course_no	字符串
 * 返回参数
 * status	字符串	notexist  不存在  downloading 正在下载 exist 已下载
 */
public class CheckVideo extends DoCmdMethod {
    private static final int PERMISSIONS_READ_WRITE_EXTERNAL_STORAGE = 102;

    private Context context;
    private DownloadVideoVo videoVo;

    @Override
    public String doMethod(HybridWebView webView, final Context context, MbsWebPluginContract.View view, MbsWebPluginContract.Presenter presenter, String cmd, String params, String callBack) {
        LogUtils.i(TAG, "doMethod-callBack: " + callBack);
        LogUtils.i(TAG, "doMethod-params: " + params);

        this.context = context;
        try {
            JSONObject object = new JSONObject(params);
            String course_no = object.optString("course_no");
            String column = object.optString("column");

            videoVo = getValueFromDB(context, TextUtils.isEmpty(column) ? "column" : column, course_no);
            if (videoVo == null) {
                videoVo = new DownloadVideoVo();
                videoVo.setStatus(NOTEXIST);
                videoVo.setCourse_no(course_no);
            } else {
                List<Videos> listVideo = videoVo.getVideos();

                for (int i = 0; i < listVideo.size(); i++) {
                    String courseItem_no = listVideo.get(i).getCourseItem_no();
                    String dirType = FileUtils.getDownLoadVideoAbsoluteath(videoVo);
                    File file = new File(dirType);
                    if (file.exists() && listVideo.get(i).isDownload()) {
//						listVideo.get(i).setIsDownload("true");
                        if (TextUtils.equals(videoVo.getStatus(), NOTEXIST) || TextUtils.equals(videoVo.getStatus(), "downloading"))
                            videoVo.setStatus(AppConfing.DOWNLOADING);
                        else
                            videoVo.setStatus(AppConfing.EXIST);
                    } else {
//						listVideo.get(i).setIsDownload("false");
                        if (i == 0) videoVo.setStatus(NOTEXIST);
                        else if (TextUtils.equals(videoVo.getStatus(), AppConfing.EXIST) || TextUtils.equals(videoVo.getStatus(), "downloading"))
                            videoVo.setStatus("downloading");
                        else if (TextUtils.equals(videoVo.getStatus(), NOTEXIST))
                            videoVo.setStatus(NOTEXIST);
                    }
                }
                if (!TextUtils.equals(videoVo.getStatus(), AppConfing.EXIST)) {
                    presenter.setMbsRequestPermissionsResultListener(new MbsRequestPermissionsListener() {
                        @Override
                        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                            if (requestCode == PERMISSIONS_READ_WRITE_EXTERNAL_STORAGE) {
                                if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                                    startService(videoVo);
                                } else {
                                    ToastUtil.showShortToast(context, context.getString(R.string.lack_sd_permiss));
                                }
                            }
                        }
                    });
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                        startService(videoVo);
                    } else {
                        //6.0
//                      System.out.println("sdk 6.0");
                        boolean permission_sdcard_read = ContextCompat.checkSelfPermission(this.context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
                        boolean permission_sdcard_write = ContextCompat.checkSelfPermission(this.context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
                        if (permission_sdcard_read && permission_sdcard_write) {
                            //该权限已经有了
                            startService(videoVo);
                        } else {
                            //申请该权限
                            final String[] permission = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                            ActivityCompat.requestPermissions((Activity) this.context, permission, PERMISSIONS_READ_WRITE_EXTERNAL_STORAGE);
                        }
                    }
                }
                String url = UrlUtil.getFormatJs(callBack, JSON.toJSONString(videoVo));
                webView.loadUrl(url);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void startService(final DownloadVideoVo videoVo) {
        Intent intent = new Intent(context, DownloadVideoService.class);
        Bundle b = new Bundle();
        b.putSerializable("videoInfo", videoVo);
        intent.putExtras(b);
        intent.setAction(BuildConfig.APPLICATION_ID + "DownloadVideoService");
        context.startService(intent);
    }

    /**
     * 根据key 从数据库得到value
     *
     * @param column 栏目
     * @param key    关键字
     * @return 根据acoutn 和 key查询据库的数据
     */
    protected DownloadVideoVo getValueFromDB(Context context, String column, String key) {
        DownloadVideoVoDao dao = GreenDBManager.getInstance().getVideoDao(context);
        QueryBuilder qb = dao.queryBuilder();
        List<DownloadVideoVo> back = (List<DownloadVideoVo>) qb.where(DownloadVideoVoDao.Properties.Course_no.eq(key)
        )
                .orderAsc(DownloadVideoVoDao.Properties.Id)
                .list();
        if (back != null && back.size() >= 1) {
            return back.get(0);
        }
        return null;
    }
}
