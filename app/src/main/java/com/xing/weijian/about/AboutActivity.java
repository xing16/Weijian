package com.xing.weijian.about;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.xing.weijian.R;
import com.xing.weijian.base.BaseActivity;
import com.xing.weijian.coder.view.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.base_toolbar)
    Toolbar toolbar;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.about);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    @Override
    protected void setListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void click(View view) {
        Intent intent = new Intent(this, WebViewActivity.class);
        if (view.getId() == R.id.layout_blog) {
            intent.putExtra("url", "http://blog.csdn.net/xingxtao");
            startActivity(intent);
        } else if (view.getId() == R.id.layout_github) {
            intent.putExtra("url", "https://github.com/xing16");
            startActivity(intent);
        }
        overridePendingTransition(R.anim.anim_in_from_bottom, R.anim.anim_no_effect);
    }
}
