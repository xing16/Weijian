package com.xing.weijian.base.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/10/5.
 */

public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    private View itemView;

    private SparseArray<View> viewSparseArray;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        viewSparseArray = new SparseArray<>();
        this.itemView = itemView;
    }


    public View getItemView() {
        return itemView;
    }

    public <V extends View> V getView(int id) {
        View view = viewSparseArray.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            viewSparseArray.put(id, view);
        }
        return (V) view;
    }

    public void setText(int id, CharSequence text) {
        TextView textView = getView(id);
        textView.setText(text);
    }


}
