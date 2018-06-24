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

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected Context mContext;

    protected View view;

    private Unbinder unbinder;

    public BaseFragment() {
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container,
                                   Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutResId(), container, false);
//        unbinder = ButterKnife.bind(this, view);
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

    protected abstract int getLayoutResId();

    protected abstract void initData();

    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        unbinder.unbind();
    }
}
