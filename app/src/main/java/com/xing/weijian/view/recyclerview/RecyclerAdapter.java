package com.xing.weijian.view.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/9/3.
 */

public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    private Context mContext;

    private List<T> mDataList;

    private int layoutId;

    private LayoutInflater inflater;

    public RecyclerAdapter(Context context, List<T> data, int layoutId) {
        mContext = context;
        mDataList = data;
        inflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
    }

    public void setDataList(List<T> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RecyclerViewHolder.get(mContext, inflater, layoutId, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        convert(holder, mDataList.get(position));
    }

    public abstract void convert(RecyclerViewHolder holder, T data);

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }
}
