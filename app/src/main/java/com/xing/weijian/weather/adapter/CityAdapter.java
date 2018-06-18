//package com.xing.weijian.weather.adapter;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.xing.weijian.R;
//
//import java.util.List;
//
//import static android.view.View.inflate;
//
///**
// * Created by Administrator on 2017/5/14.
// */
//
//public class CityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    private Context mContext;
//
//
//    private final int TYPE_GRID = 0;   // 网格的
//
//    private final int TYPE_LINEAR = 1;   // 线性的
//
//    private final int TYPE_LETTER = 2;
//
//    private final int TYPE_ADDRESS = 3;
//
//
//    public CityAdapter(Context mContext, List<City> dataList) {
//        this.mContext = mContext;
//        this.dataList = dataList;
//    }
//
//    public void setData(List<City> data) {
//        this.dataList = data;
//    }
//
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = null;
//        if (viewType == TYPE_GRID) {
//            itemView = View.inflate(mContext, R.layout.recycler_item_grid, null);
//            return new GridItemViewHolder(itemView);
//        } else {
//            itemView = inflate(mContext, R.layout.recyclere_item_linear, null);
//            if (viewType == TYPE_LETTER) {
//                itemView.setBackgroundColor(mContext.getResources().getColor(R.color.windowBackground));
//
//            } else {
//                itemView.setBackgroundColor(Color.WHITE);
//            }
//            return new ItemViewHolder(itemView);
//        }
//
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        City city = dataList.get(position);
//        if (position < 8) {
//            return TYPE_GRID;
//        } else {
//            if (city.getType() == City.Type.TYPE_ADDRESS) {
//                return TYPE_ADDRESS;
//            } else {
//                return TYPE_LETTER;
//            }
//        }
//    }
//
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        City city = dataList.get(position);
//        if (holder instanceof ItemViewHolder) {
//            ((ItemViewHolder) holder).textView.setText(city.getName());
//        } else if (holder instanceof GridItemViewHolder) {
//            ((GridItemViewHolder) holder).textView.setText(city.getName());
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataList.size();
//    }
//
//
//    class ItemViewHolder extends RecyclerView.ViewHolder {
//
//        public TextView textView;
//
//        public ItemViewHolder(View itemView) {
//            super(itemView);
//            textView = (TextView) itemView.findViewById(R.id.tv_item_city_name);
//        }
//    }
//
//    class GridItemViewHolder extends RecyclerView.ViewHolder {
//
//        public TextView textView;
//
//        public GridItemViewHolder(View itemView) {
//            super(itemView);
//            textView = (TextView) itemView.findViewById(R.id.tv_hot_city_name);
//        }
//    }
//
//
//}
