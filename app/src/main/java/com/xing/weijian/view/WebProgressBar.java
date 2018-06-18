package com.xing.weijian.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;

import com.xing.weijian.R;

import io.reactivex.BackpressureOverflowStrategy;

/**
 * 自定义 WebView 进度条
 * Created by Administrator on 2018/2/4.
 */

public class WebProgressBar extends View {

    private static final String TAG = "WebProgressBar";

    private static final int DEFAULT_PROGRESS_COLOR = R.color.colorAccent;

    private int progressColor;

    private int mWidth;

    private int progress;

    private RectF rect;

    private Paint paint;


    public WebProgressBar(Context context) {
        this(context, null);
    }

    public WebProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WebProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttrs(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(progressColor);
        paint.setStyle(Paint.Style.FILL);
    }

    /**
     * 获取自定义属性
     *
     * @param context
     * @param attrs
     */
    private void readAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WebProgressBar);
        progressColor = typedArray.getColor(R.styleable.WebProgressBar_progressColor, DEFAULT_PROGRESS_COLOR);
        typedArray.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredSize(widthMeasureSpec, dp2Px(100));
        int height = getMeasuredSize(heightMeasureSpec, dp2Px(2));
        setMeasuredDimension(width, height);
    }

    private int getMeasuredSize(int measureSpec, int defValue) {
        int value = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            value = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            value = Math.max(size, defValue);
        }
        return value;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        rect = new RectF(0, 0, progress / 100f * mWidth, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(rect, paint);
    }

    public void setProgress(int progress) {
        Log.d(TAG, "setProgress: progress = " + progress);
        if (this.progress != progress) {
            this.progress = progress;
            invalidate();
        }
    }


    private int dp2Px(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue,
                getResources().getDisplayMetrics());
    }
}
