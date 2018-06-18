package com.xing.weijian.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.xing.weijian.R;

/**
 * Created by Administrator on 2017/5/14.
 */

public class QuickIndexView extends View {

    private String[] words = {};

    private int mWidth;

    private int mHeight;

    private Paint paint;

    private float textSize;

    private int textColor;

    private float textGap;

    private int fillMode;
    private int textHeight;


    public QuickIndexView(Context context) {
        this(context, null);
    }

    public QuickIndexView(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAttrs(context, attrs);
        init();

    }

    private void readAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.QuickIndexView);
        textSize = typedArray.getDimension(R.styleable.QuickIndexView_letterSize, dp2Px(12));
        textColor = typedArray.getColor(R.styleable.QuickIndexView_letterColor, 0xff555555);
        textGap = typedArray.getDimension(R.styleable.QuickIndexView_letterGap, dp2Px(5));
        fillMode = typedArray.getInt(R.styleable.QuickIndexView_mode, 0);
        typedArray.recycle();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setFakeBoldText(true); // 设置文字粗体
        paint.setTextAlign(Paint.Align.CENTER);
        textHeight = (int) (paint.descent() - paint.ascent());

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }


    /**
     * 宽度设置为所有字符宽度的最大值，高度设置为match_parent
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = getMeasureWidth(widthMeasureSpec);
        int measureHeight = getMeasureHeight(heightMeasureSpec);
        setMeasuredDimension(measureWidth, measureHeight);
    }

    private int getMeasureHeight(int heightMeasureSpec) {
        int value = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            value = size;
        } else {
            if (fillMode == 0) {  // wrap
                value = (int) ((textHeight + textGap) * words.length) + getPaddingTop()
                        + getPaddingBottom();
            } else if (fillMode == 1) {  // fill
                value = size;
            }
        }
        return value;
    }

    /**
     * 测量宽度,如果指定固定宽度，即为该指定值，否则为字母中宽度值中最大的那个
     *
     * @param widthMeasureSpec
     * @return
     */
    private int getMeasureWidth(int widthMeasureSpec) {
        int value = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            value = size;
        } else {
            for (String s : words) {
                int textWidth = (int) paint.measureText(s);
                value = textWidth > value ? textWidth : value;
            }
            // 将 leftPadding,rightPadding 考虑进去
            value = value + getPaddingLeft() + getPaddingRight();
        }
        return value;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(getResources().getColor(android.R.color.transparent));
        canvas.save();
        canvas.translate(getMeasuredWidth() / 2f, 0);
        if (fillMode == 1) {  // fill height
            textGap = (getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - textHeight * words.length) / words.length;
        }
        for (int i = 0; i < words.length; i++) {
            int y = (int) (getPaddingTop() + textGap / 2f + (i + 1) * textHeight + i * textGap);
            canvas.drawText(words[i], 0, y, paint);
        }
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int touchY = (int) event.getY();  // 获取该触摸点在该控件坐标系中的y坐标位置。
                for (int i = 0; i < words.length; i++) {
                    int top = (int) (getPaddingTop() + textGap / 2f + i * (textHeight + textGap));
                    int bottom = (int) (getPaddingTop() + textGap / 2f + i * (textHeight + textGap) + textHeight);
                    if (touchY > top && touchY < bottom) {
                        if (listener != null) {
                            listener.onIndexChange(words[i], i);
                        }
                        break;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (listener != null) {
                    listener.onRelease();
                }
                break;
        }
        return true;
    }

    public void setWords(String[] words) {
        this.words = words;
        invalidate();
    }

    public interface OnIndexChangeListener {
        void onIndexChange(String text, int index);

        void onRelease();
    }

    private OnIndexChangeListener listener;

    public void setOnIndexChangeListener(OnIndexChangeListener listener) {
        this.listener = listener;
    }


    private float dp2Px(int dpValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }
}
