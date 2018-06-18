package com.xing.weijian.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * 圆形刻度控件
 * Created by Administrator on 2017/7/1.
 */

public class CircleMarkView extends View {

    private int mWidth;

    private int mHeight;

    private float centerX;

    private float centerY;

    private float radius;

    private Paint degreePaint;

    private float strokeWidth = dp2Px(2);

    public CircleMarkView(Context context) {
        this(context, null);

    }

    public CircleMarkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {


        degreePaint = createPaint(Color.WHITE, strokeWidth, Paint.Style.STROKE);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        centerX = w / 2;
        centerY = h / 2;
        radius = Math.min(mWidth, mHeight) / 2f;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(centerX, centerY);
        canvas.drawCircle(0, 0, radius, degreePaint);
        canvas.restore();
    }

    private Paint createPaint(int color, float strokeWidth, Paint.Style style) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setStyle(style);
        paint.setStrokeWidth(strokeWidth);
        return paint;
    }

    private int dp2Px(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }


}
