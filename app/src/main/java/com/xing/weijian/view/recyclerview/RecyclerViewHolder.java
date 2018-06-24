package com.xing.weijian.view.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/9/3.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;

    private View convertView;

    private SparseArray<View> viewSparseArray;


    public RecyclerViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        convertView = itemView;
        viewSparseArray = new SparseArray<>();
    }

    public static RecyclerViewHolder get(Context context, LayoutInflater inflater, int layoutId, ViewGroup parent) {
        View itemView = inflater.inflate(layoutId, parent, false);
        RecyclerViewHolder holder = new RecyclerViewHolder(context, itemView);
        return holder;
    }

    public <T extends View> T getView(int resId) {
        View view = viewSparseArray.get(resId);
        if (view == null) {
            view = convertView.findViewById(resId);
            viewSparseArray.put(resId, view);
        }
        return (T) view;
    }

}
