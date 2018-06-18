package com.xing.weijian.coder.view;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.xing.weijian.R;
import com.xing.weijian.base.BaseActivity;
import com.xing.weijian.base.BaseToolbarActivity;
import com.xing.weijian.view.BaseWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class WebViewActivity extends BaseActivity {

    private static final String TAG = "WebViewActivity";

    @BindView(R.id.base_toolbar)
    Toolbar toolbar;

    @BindView(R.id.web_view)
    BaseWebView webView;

    private String url;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("url");
            webView.loadUrl(url);
        }
        webView.setCallback(new BaseWebView.DefWebViewCallback() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                toolbar.setTitle(title);
            }
        });
    }

    @Override
    protected void setListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_web_share:
                        OnekeyShare onekeyShare = new OnekeyShare();
                        onekeyShare.setUrl(webView.getUrl());
                        onekeyShare.show(mContext);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.anim_out_from_top);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return true;
    }
}
