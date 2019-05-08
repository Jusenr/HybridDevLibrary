package com.jusenr.hybrid.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.jusenr.hybrid.test.R;

public class WebActivity extends AppCompatActivity {

    private WebView mWv_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        mWv_web = findViewById(R.id.wv_web);

        mWv_web.loadUrl("https://www.baidu.com/?tn=48021271_17_hao_pg");
    }
}
