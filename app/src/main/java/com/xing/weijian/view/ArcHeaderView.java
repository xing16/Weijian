package com.xing.weijian.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;

import com.xing.weijian.R;

/**
 * 自定义弧形 headerview , 贝塞尔曲线实现
 * Created by Administrator on 2018/1/27.
 */

public class ArcHeaderView extends LinearLayout {

    private final int DEFAULT_MAX_ARC_HEIGHT = dp2Px(80);

    private int bottomPadding = 30;

    private static final String TAG = "BezierLayout";

    private Path path;

    private Paint paint;

    private int mWidth;

    private int mHeight;

    private float maxArcHeight;


    public ArcHeaderView(Context context) {
        this(context, null);
    }

    public ArcHeaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttrs(context, attrs);
        init();
    }

    private void readAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArcHeaderView);
        maxArcHeight = typedArray.getDimension(R.styleable.ArcHeaderView_maxArcHeight, DEFAULT_MAX_ARC_HEIGHT);
        typedArray.recycle();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);

        path = new Path();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        Log.d(TAG, ": mwidth = " + mWidth);

        path.moveTo(0, maxArcHeight - dp2Px(40));
        // 贝塞尔曲线,弧线
        path.quadTo(mWidth / 2f, maxArcHeight, mWidth, maxArcHeight - dp2Px(40));
        // 右侧竖线
        path.lineTo(mWidth, mHeight);
        path.lineTo(0, mHeight);
        path.close();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);


    }

    private int dp2Px(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue,
                getResources().getDisplayMetrics());
    }


}
