package com.xing.weijian.view.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * 列表分割线 ：会根据 RecyclerView 当前设定的方向自动设置对应的分割线，不用外部指定 Divider 方向
 */
public class DividerListItemDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = "DividerList";

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    private Drawable mDriver;

    public DividerListItemDecoration(Context context) {
        TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        mDriver = typedArray.getDrawable(0);
        typedArray.recycle();
    }

    public DividerListItemDecoration(Context context, int drawableId) {
        mDriver = ContextCompat.getDrawable(context, drawableId);
    }


    // 画分割线,根据 RecyclerView 设置的方向，绘制竖直，水平的分割线
    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        int orientation = layoutManager.getOrientation();
        if (orientation == LinearLayoutManager.HORIZONTAL) {
            drawVerticalDivider(c, parent);
        } else if (orientation == LinearLayoutManager.VERTICAL) {
            drawHorizontalDivider(c, parent);
        }
    }

    // 当 RecyclerView 设置的方向为 Vertical 时，绘制水平分割线
    private void drawHorizontalDivider(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        Log.d(TAG, "drawHorizontalDivider: left = " + left);
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams =
                    (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + layoutParams.bottomMargin;
            int bottom = top + mDriver.getIntrinsicHeight();
            mDriver.setBounds(left, top, right, bottom);
            mDriver.draw(c);
        }
    }

    // 当 RecyclerView 设置的方向为 Horizontal 时，绘制竖直分割线
    private void drawVerticalDivider(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getLeft() + layoutParams.leftMargin;
            int right = left + mDriver.getIntrinsicWidth();
            mDriver.setBounds(left, top, right, bottom);
            mDriver.draw(c);
        }
    }



}
