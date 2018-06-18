package com.xing.weijian.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.xing.weijian.R;

public abstract class BaseToolbarActivity extends AppCompatActivity {

    protected Context mContext;

    protected Toolbar toolbar;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        // parentLinearLayout 上部分显示 Toolbar,下部分显示 activity content
        LinearLayout parentLinearLayout = new LinearLayout(this);
        parentLinearLayout.setOrientation(LinearLayout.VERTICAL);

        ViewGroup rootView = findViewById(android.R.id.content);
        rootView.removeAllViews();
        rootView.addView(parentLinearLayout);

        LayoutInflater inflater = LayoutInflater.from(this);
        // 设置 toolbar 布局
        inflater.inflate(R.layout.base_toolbar, parentLinearLayout, true);

        toolbar = findViewById(R.id.base_toolbar);
        setSupportActionBar(toolbar); // 需要设置supportActionBar(), 否则状态栏不会与 actionbar 颜色一致
        // 设置子类 activity 布局
        inflater.inflate(getLayoutResId(), parentLinearLayout, true);


        setButtonBack();

        init();

    }

    protected void init() {

    }

    protected void setButtonBack() {
        toolbar.setNavigationIcon(getNavigationIcon());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    protected int getNavigationIcon() {
        return R.drawable.vector_back;
    }

    protected abstract int getLayoutResId();
}


