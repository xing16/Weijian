package com.xing.weijian.base.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/10/5.
 */

public interface Cell {

    /**
     * 创建 ViewHolder
     */
    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 绑定 ViewHolder
     */
    void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

    /**
     * 释放资源
     */
    void releaseResource();

    /**
     * 获取 ViewType
     */
    int getItemType();

}
