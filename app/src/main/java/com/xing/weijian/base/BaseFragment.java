package com.xing.weijian.base;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.xing.weijian.base.mvp.BaseView;
import com.xing.weijian.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements BaseView {

    protected Context mContext;

    protected View view;

    private Unbinder unbinder;

    public BaseFragment() {
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container,
                                   Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        setListener();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    protected abstract void initView();

    protected void setListener() {
    }

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
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
        ToastUtil.showShortToast("load failed");
    }
}
