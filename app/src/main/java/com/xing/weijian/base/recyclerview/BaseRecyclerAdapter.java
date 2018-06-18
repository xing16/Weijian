package com.xing.weijian.base.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/5.
 */

public class BaseRecyclerAdapter<C extends BaseCell> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<C> cellList;

    public BaseRecyclerAdapter() {
        cellList = new ArrayList<>();
    }


    public void setDataList(List<C> list) {
        cellList.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        for (int i = 0; i < getItemCount(); i++) {
            if (viewType == cellList.get(i).getItemType()) {
                return cellList.get(i).onCreateViewHolder(parent, viewType);
            }
        }
        throw new RuntimeException("wrong view type");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        cellList.get(position).onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return cellList == null ? 0 : cellList.size();
    }

    public void addCell(C cell) {
        cellList.add(cell);
        int index = cellList.indexOf(cell);
        notifyItemChanged(index);
    }

    public void addCell(int index, C cell) {
        cellList.add(index, cell);
        notifyItemChanged(index);
    }

    public void remove(C cell) {
        int index = cellList.indexOf(cell);
        cellList.remove(index);
    }


}
