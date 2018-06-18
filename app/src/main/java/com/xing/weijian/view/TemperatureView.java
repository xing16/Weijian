package com.xing.weijian.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.xing.weijian.R;

/**
 * Created by Administrator on 2017/7/30.
 */

public class TemperatureView extends View {

    private static final String TAG = "TemperatureView";

    private int textColor;

    private float centerTextSize;

    private float degreeTextSize;

    private float degreeLength;

    private float degreeWidth;

    private int degreeColor;

    private int degreeSelectedColor;

    private Paint borderPaint;

    private Paint centerTextPaint;

    private Paint degreePaint;

    private Paint degreeTextPaint;

    private int mWidth;

    private int mHeight;

    private float borderWidth;

    private float radius;

    private float centerX;

    private float centerY;

    private RectF borderRectF;

    public TemperatureView(Context context) {
        this(context, null);
    }

    public TemperatureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAttrs(context, attrs);
    }

    private void readAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TemperatureView);
        try {
            borderWidth = typedArray.getDimension(R.styleable.TemperatureView_degreeBorderWidth, dp2Px(2));
            textColor = typedArray.getColor(R.styleable.TemperatureView_textColor, Color.WHITE);
            centerTextSize = typedArray.getDimension(R.styleable.TemperatureView_centerTextSize, dp2Px(54));
            degreeTextSize = typedArray.getDimension(R.styleable.TemperatureView_degreeTextSize, dp2Px(14));
            degreeLength = typedArray.getDimension(R.styleable.TemperatureView_degreeLength, dp2Px(20));
            degreeWidth = typedArray.getDimension(R.styleable.TemperatureView_degreeWidth, dp2Px(2));
            degreeColor = typedArray.getColor(R.styleable.TemperatureView_degreeColor, Color.WHITE);
            degreeSelectedColor = typedArray.getColor(R.styleable.TemperatureView_degreeSelectedColor, Color.GREEN);

        } catch (Exception e) {
            typedArray.recycle();
        }

        init();

    }


    private void init() {
        borderPaint = createPaint(textColor, Paint.Style.STROKE, borderWidth, 0);
        centerTextPaint = createPaint(textColor, Paint.Style.STROKE, 0, centerTextSize);
        degreePaint = createPaint(degreeColor, Paint.Style.FILL, degreeWidth, 0);
        borderRectF = new RectF();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "onSizeChanged: borderWidth = " + borderWidth);
        mWidth = (int) (w - borderWidth - borderWidth);
        mHeight = (int) (h - borderWidth - borderWidth);
        radius = Math.min(mWidth, mHeight) / 2f;
        centerX = w / 2f;
        centerY = h / 2f;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBorder(canvas);
        drawDegree(canvas);
        drawCenterText(canvas);
    }

    private void drawCenterText(Canvas canvas) {
        canvas.save();
        canvas.translate(centerX, centerY);
        String text = "26" + "℃";
        float textLength = centerTextPaint.measureText(text);
        canvas.drawText(text, -textLength / 2f, 0, centerTextPaint);
        canvas.restore();

    }

    private void drawDegree(Canvas canvas) {
        canvas.save();
        canvas.translate(centerX, centerY);
        canvas.rotate(30);
        for (int i = 0; i < 120; i++) {
            canvas.drawLine(0, radius - dp2Px(5), 0, radius - dp2Px(5) - degreeLength, degreePaint);
            canvas.rotate(300 / 119f);
        }
        canvas.restore();
    }

    /**
     * 绘制圆形边框
     *
     * @param canvas
     */
    private void drawBorder(Canvas canvas) {
        canvas.save();
        canvas.translate(centerX, centerY);
        borderRectF.set(-radius, -radius, radius, radius);
        canvas.drawArc(borderRectF, 120, 300, false, borderPaint);
        canvas.restore();
    }

    private Paint createPaint(int color, Paint.Style style, float strokeWidth, float textSize) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setStyle(style);
        paint.setStrokeWidth(strokeWidth);
        paint.setTextSize(textSize);
        return paint;
    }


    private int dp2Px(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }
}
