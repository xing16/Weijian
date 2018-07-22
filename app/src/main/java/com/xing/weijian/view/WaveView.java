package com.xing.weijian.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.xing.weijian.R;

import java.lang.reflect.TypeVariable;

/**
 * 自定义波纹view,贝塞尔曲线实现
 * Created by Administrator on 2018/1/27.
 */

public class WaveView extends View {

    private static final String TAG = "WaveView";

    // 上下两条波纹之间的默认间距大小
    private final float DEFAULT_MARGIN = dp2Px(15);

    // 默认颜色
    private final int DEFAULT_WAVE_COLOR = 0x5515b7a7;

    private Paint topPaint;

    private Paint bottomPaint;

    private Path topPath;

    private Path bottomPath;

    private int mWidth;

    private int mHeight;

    private int color;

    private Position position = Position.BOTTOM;

    private float waveMargin;
    private ValueAnimator valueAnimator;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttrs(context, attrs);
        init();
    }

    /**
     * 获取自定义属性
     *
     * @param context
     * @param attrs
     */
    private void readAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WaveView);
        int align = typedArray.getInt(R.styleable.WaveView_position, 1);
        if (align == 0) {
            position = Position.TOP;
        } else if (align == 1) {
            position = Position.BOTTOM;
        }
        color = typedArray.getColor(R.styleable.WaveView_waveColor, DEFAULT_WAVE_COLOR);
        waveMargin = typedArray.getDimension(R.styleable.WaveView_waveMargin, DEFAULT_MARGIN);
        typedArray.recycle();
    }

    private void init() {
        // 初始化画笔
        topPaint = createPaint(color);
        bottomPaint = createPaint(color);
        // 初始化 path
        topPath = new Path();
        bottomPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getMeasureHeight(heightMeasureSpec, (int) (waveMargin * 2));
        setMeasuredDimension(widthMeasureSpec, height);
    }


    private int getMeasureHeight(int heightMeasureSpec, int defValue) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            return size;
        } else {
            return defValue;
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        animateToLeft();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawTopPath(canvas);
        drawBottomPath(canvas);
    }

    private void drawTopPath(Canvas canvas) {
        topPath.reset();

        if (position == Position.BOTTOM) {
            topPath.moveTo(offset, mHeight - waveMargin);
            topPath.quadTo(mWidth / 4f + offset, mHeight - 2 * waveMargin, mWidth / 2f + offset, mHeight - waveMargin);
            topPath.quadTo(mWidth * 3 / 4f + offset, mHeight, mWidth + offset, mHeight - waveMargin);
            topPath.quadTo(mWidth * 5 / 4f + offset, mHeight - 2 * waveMargin, mWidth * 6 / 4f + offset, mHeight - waveMargin);
            topPath.quadTo(mWidth * 7 / 4f + offset, mHeight, mWidth * 2 + offset, mHeight - waveMargin);
            topPath.lineTo(mWidth, 0);
            topPath.lineTo(0, 0);
        } else if (position == Position.TOP) {
            topPath.moveTo(offset, waveMargin);
            topPath.quadTo(mWidth / 4f + offset, 2 * waveMargin, mWidth / 2f + offset, waveMargin);
            topPath.quadTo(mWidth * 3 / 4f + offset, 0, mWidth + offset, waveMargin);
            topPath.quadTo(mWidth * 5 / 4f + offset, 2 * waveMargin, mWidth * 6 / 4f + offset, waveMargin);
            topPath.quadTo(mWidth * 7 / 4f + offset, 0, mWidth * 2 + offset, waveMargin);
            topPath.lineTo(mWidth, mHeight);
            topPath.lineTo(0, mHeight);
        }
        topPath.close();
        canvas.drawPath(topPath, topPaint);
    }

    private void drawBottomPath(Canvas canvas) {
        bottomPath = new Path();
        bottomPath.reset();
        if (position == Position.TOP) {
            bottomPath.moveTo(offset, waveMargin);
            bottomPath.quadTo(mWidth / 4f + offset, 0, mWidth / 2f + offset, waveMargin);
            bottomPath.quadTo(mWidth * 3 / 4f + offset, 2 * waveMargin, mWidth + offset, waveMargin);
            bottomPath.quadTo(mWidth * 5 / 4f + offset, 0, mWidth * 6 / 4f + offset, waveMargin);
            bottomPath.quadTo(mWidth * 7 / 4f + offset, 2 * waveMargin, mWidth * 2 + offset, waveMargin);
            bottomPath.lineTo(mWidth, mHeight);
            bottomPath.lineTo(0, mHeight);
        } else if (position == Position.BOTTOM) {
            bottomPath.moveTo(offset, mHeight - waveMargin);
            bottomPath.quadTo(mWidth / 4f + offset, mHeight, mWidth / 2f + offset, mHeight - waveMargin);
            bottomPath.quadTo(mWidth * 3 / 4f + offset, mHeight - 2 * waveMargin, mWidth + offset, mHeight - waveMargin);
            bottomPath.quadTo(mWidth * 5 / 4f + offset, mHeight, mWidth * 6 / 4f + offset, mHeight - waveMargin);
            bottomPath.quadTo(mWidth * 7 / 4f + offset, mHeight - 2 * waveMargin, mWidth * 2 + offset, mHeight - waveMargin);
            bottomPath.lineTo(mWidth, 0);
            bottomPath.lineTo(0, 0);
        }

        bottomPath.close();
        canvas.drawPath(bottomPath, bottomPaint);
    }

    private Paint createPaint(int color) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    float offset = 0;

    private void animateToLeft() {
        if (valueAnimator == null) {
            valueAnimator = ValueAnimator.ofFloat(0, -mWidth);
        }
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setDuration(1500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                offset = (float) valueAnimator.getAnimatedValue();
                Log.d(TAG, "onAnimationUpdate: offset = " + offset);
                invalidate();
            }
        });
        valueAnimator.start();
    }

    public enum Position {
        TOP(0), BOTTOM(1);
        int value;

        Position(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private float dp2Px(int dpValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }

    public void stopAnimator() {
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }


}



