package com.mobisoft.mbswebplugin.proxy.Cache;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.mobisoft.mbswebplugin.proxy.DB.WebviewCaheDao;
import com.mobisoft.mbswebplugin.proxy.Setting.ProxyConfig;
import com.mobisoft.mbswebplugin.proxy.Work.DownloadSrcCallback;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/**
 * Author：Created by fan.xd on 2017/3/27.
 * Email：fang.xd@mobisoft.com.cn
 * Description：
 */

public class DownLoadSrcTask extends AsyncTask<String, Integer, Boolean> {
    private String TAG = "DownLoadSrcTask";
    private WebviewCaheDao webviewCaheDao;
    private DownloadSrcCallback srcCallback;
    private boolean isFinish;

    public DownLoadSrcTask(WebviewCaheDao webviewCaheDao) {
        this.webviewCaheDao = webviewCaheDao;
        srcCallback = ProxyConfig.getConfig().getSrcCallback();
    }

    @Override
    protected Boolean doInBackground(String... params) {
        String cachePath = params[0];
        if (TextUtils.isEmpty(cachePath)) {
            webviewCaheDao.saveUrlPath(params[1], "checkMD5", String.valueOf(true));
            return true;
        }
        try {
            URL uri = new URL(cachePath);
            Log.i(TAG, "下载：URL:" + cachePath);

            HttpURLConnection connection;
            if (cachePath.contains("https")) {
                SSLContext sc = SSLContext.getInstance("TLS");
                sc.init(null, new TrustManager[]{new MyTrustManager()}, new SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                HttpsURLConnection.setDefaultHostnameVerifier(new MyHostnameVerifier());
                connection = (HttpsURLConnection) uri.openConnection();
            } else {
                connection = (HttpURLConnection) uri.openConnection();
            }
            connection.setUseCaches(false);
            connection.setRequestProperty("Accept-Encoding", "identity");
//            connection.setRequestProperty("Cache-Control", "no-cache");

            String responseHeader = getResponseHeader(connection);
            Log.e(TAG, "头文件:" + responseHeader);
            File file1 = new File(params[1]);


            FileOutputStream output = new FileOutputStream(file1);
            if (!TextUtils.isEmpty(responseHeader)) {
                ByteArrayInputStream stringInputStream = new ByteArrayInputStream(
                        responseHeader.getBytes());
                byte[] buffer2 = new byte[1024];

                while (true) {
                    int r = stringInputStream.read(buffer2);
                    if (r < 0) {
                        break;
                    }
                    if (output != null) {
                        output.write(buffer2, 0, r);
                    }
                }
                output.close();
                output = new FileOutputStream(file1, true);
            }
            InputStream uristream = connection.getInputStream();

            byte[] buffer1 = new byte[1024];
//
            if (connection.getResponseCode() == 200 && connection.getContentLength() > 0) {

                while (true) {
                    int r = uristream.read(buffer1);
                    if (r < 0) {
                        break;
                    }
                    if (output != null) {
                        output.write(buffer1, 0, r);
                    }
                }

                Log.i(TAG, "下载完成 getResponseCode:" + file1.getAbsolutePath());
                webviewCaheDao.saveUrlPath(params[2], cachePath, file1.getAbsolutePath());
            } else {
                file1.delete();
                webviewCaheDao.deletKey(cachePath);
            }
            output.close();
            uristream.close();
            return false;
        } catch (FileNotFoundException e) {
            webviewCaheDao.deletKey(cachePath);
            e.printStackTrace();

        } catch (NoSuchAlgorithmException e) {
            webviewCaheDao.deletKey(cachePath);

            e.printStackTrace();
        } catch (MalformedURLException e) {
            webviewCaheDao.deletKey(cachePath);

            webviewCaheDao.deletKey(cachePath);

            e.printStackTrace();
        } catch (IOException e) {
            webviewCaheDao.deletKey(cachePath);

            e.printStackTrace();
        } catch (KeyManagementException e) {
            webviewCaheDao.deletKey(cachePath);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i(TAG, "开始下载：onPreExecute");

    }

    @Override
    protected void onPostExecute(Boolean isFinish) {
        if (srcCallback != null && isFinish) {
            srcCallback.downLoadFinish();
        }
        int n = webviewCaheDao.getCount();
        Log.i(TAG, "下载完成路径：" + isFinish + "/// : " + n);
    }

    /**
     * 读取响应头
     *
     * @param conn
     * @return
     */
    private String getResponseHeader(URLConnection conn) {
        boolean isHtml = conn.getURL().toString().contains(".html");
//        Log.e(TAG, "url地址，getResponseHeader：" + conn.getURL().toString());

        StringBuilder sbResponseHeader = new StringBuilder();

        sbResponseHeader.append("HTTP/1.1 200 OK");
        sbResponseHeader.append("\n");
        sbResponseHeader.append("Server: ");
        sbResponseHeader.append(conn.getHeaderField("Server"));
        sbResponseHeader.append("\n");

        sbResponseHeader.append("Date: ");
//        sbResponseHeader.append("Fri, 17 Feb 2017 11:29:57 GMT");
        sbResponseHeader.append(conn.getHeaderField("Date"));
        sbResponseHeader.append("\n");

        sbResponseHeader.append("Content-Type: ");
        sbResponseHeader.append(conn.getContentType());
        sbResponseHeader.append("\n");

        sbResponseHeader.append("Content-Length: ");
        sbResponseHeader.append(conn.getContentLength());
        sbResponseHeader.append("\n");


//        Calendar cd = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("EEE d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
////        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8")); // 设置时区为GMT  +8为北京时间东八区
//        String str = sdf.format(cd.getTime());
//        System.out.println(str);
        sbResponseHeader.append("Last-Modified: ");
        sbResponseHeader.append(conn.getHeaderField("Last-Modified"));
        sbResponseHeader.append("\n");

//        if (isHtml) {
//            sbResponseHeader.append("Transfer-Encoding: ");
//            sbResponseHeader.append("chunked");
//            sbResponseHeader.append("\n");
//        }


        sbResponseHeader.append("Connection: ");
        sbResponseHeader.append("close");
        sbResponseHeader.append("\n");

        sbResponseHeader.append("ETag: ");
        sbResponseHeader.append(conn.getHeaderField("ETag"));
        sbResponseHeader.append("\n");
        if (isHtml) {
            sbResponseHeader.append("Access-Control-Allow-Origin: *\n" +
                    "Access-Control-Allow-Methods: GET, POST, OPTIONS\n");
        }

//
        if (isHtml) {
            sbResponseHeader.append("Accept-Encoding: ");
            sbResponseHeader.append("identity");
        } else {
            sbResponseHeader.append("Accept-Ranges: ");
            sbResponseHeader.append("bytes");
        }
        sbResponseHeader.append("\n");
        sbResponseHeader.append("\n");


        return sbResponseHeader.toString();
    }

}
