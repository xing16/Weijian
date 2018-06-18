package com.xing.weijian.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by Administrator on 2017/9/17.
 */

public class BaseWebView extends WebView {

    private WebProgressBar progressBar;

    private boolean hasProgressBar = true;


    public BaseWebView(Context context) {
        super(context);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initProgressBar();
        initWebSettings();
        setWebViewClient(new BaseWebViewClient());
        setWebChromeClient(new BaseWebViewChromeClient());


    }

    private void initProgressBar() {
        progressBar = new WebProgressBar(getContext());
        if (hasProgressBar) {
            addView(progressBar);
        }
    }

    /**
     * 对外提供接口，设置 progressbar 可见性
     *
     * @param isVisible
     */
    public void setProgressBarVisible(boolean isVisible) {
        if (isVisible) {  // 设置 progressbar 可见,当前没有进度条,则添加进度条
            if (!hasProgressBar) {
                progressBar.setVisibility(View.VISIBLE);
                addView(progressBar);
            }
        } else {   // 设置不显示进度条,当前有进度条,则移除
            if (hasProgressBar) {
                removeView(progressBar);
            }
        }
    }


    private void initWebSettings() {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBlockNetworkImage(false);
        settings.setSupportZoom(false);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
    }

    /**
     * 在 WebChromeClient 的 onProgressChanged() 方法中调用
     *
     * @param curProgress
     * @param newProgress
     */
    public void onProgressChanged(int curProgress, int newProgress) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(progressBar, "progress", curProgress, newProgress);
        objectAnimator.setDuration(300);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();
    }


    class BaseWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (callback != null) {
                callback.onPageStarted(view, url, favicon);
            }

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (callback != null) {
                callback.onPageFinished(view, url);
            }

        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            if (callback != null) {
                callback.onLoadResource(view, url);
            }

        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            if (callback != null) {
                callback.onReceivedError(view, request, error);
            }

        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }
    }

    class BaseWebViewChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (callback != null) {
                callback.onProgressChanged(view, newProgress);
            }
            int curProgress = view.getProgress();
            BaseWebView.this.onProgressChanged(curProgress, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (callback != null) {
                callback.onReceivedTitle(view, title);
            }
        }
    }

    private WebViewCallback callback;


    public void setCallback(WebViewCallback callback) {
        this.callback = callback;
    }


    public interface WebViewCallback {
        void onPageStarted(WebView view, String url, Bitmap favicon);

        void onPageFinished(WebView view, String url);

        void onLoadResource(WebView view, String url);

        void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error);

        void onProgressChanged(WebView view, int newProgress);

        void onReceivedTitle(WebView view, String title);

    }


    public static class DefWebViewCallback implements WebViewCallback {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

        }

        @Override
        public void onPageFinished(WebView view, String url) {

        }

        @Override
        public void onLoadResource(WebView view, String url) {

        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {

        }

        @Override
        public void onReceivedTitle(WebView view, String title) {

        }
    }


}
