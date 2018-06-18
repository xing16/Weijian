package com.xing.weijian.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.xing.weijian.R;

/**
 * Created by Administrator on 2018/6/16.
 */

public class BaseToolbar extends Toolbar {

    private Context mContext;

    private Activity activity;

    public BaseToolbar(Context context) {
        super(context);
        init(context);
    }

    public BaseToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        setNavigationIcon(R.drawable.vector_back);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        activity = (Activity) getContext();
        setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null) {
                    activity.finish();
                }
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
    }
}
