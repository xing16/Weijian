package com.xing.weijian.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.gyf.barlibrary.ImmersionBar;
import com.xing.weijian.R;
import com.xing.weijian.base.mvp.BaseView;
import com.xing.weijian.utils.ToastUtil;


/**
 * Created by Administrator on 2017/5/6.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected Context mContext;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mContext = this;
        initView();
        initData();
        setListener();
    }

    protected void initData() {
    }

    protected abstract int getLayoutResId();

    protected abstract void initView();

    protected abstract void setListener();

    protected ProgressBar getProgressBar() {
        return null;
    }


    @Override
    public void showLoading() {
        if (getProgressBar() != null) {
            getProgressBar().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if (getProgressBar() != null) {
            getProgressBar().setVisibility(View.GONE);
        }
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showShortToast(msg);
    }

    @Override
    public void showError() {

    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    protected int getStatusBarHeight() {
        int statusBarHeight = -1;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }


    private void addStatusBarView() {
        View view = new View(this);
        view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight());
        ViewGroup decorView = findViewById(android.R.id.content);
        decorView.addView(view, params);
    }

    protected void startActivity(Class clazz) {
        startActivity(clazz, null);
    }

    /**
     * 开启 Activity
     */
    protected void startActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        mContext.startActivity(intent);
    }

    /**
     * 开启 Activity, 并销毁自己
     */
    protected void startActivity(Class clazz, Bundle bundle, boolean isDestroySelf) {
        if (isDestroySelf) {
            startActivity(clazz, bundle);
            finish();
        }
    }


    /**
     * 启动 Activity,并返回结果
     */
    protected void startActivityForResult(Class clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
