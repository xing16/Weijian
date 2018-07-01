package com.xing.weijian.meizi.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xing.weijian.R;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 */

public abstract class ViewPagerAdapter<T> extends PagerAdapter {

    protected Context mContext;

    private List<T> dataList;

    protected LayoutInflater inflater;

    public ViewPagerAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.dataList = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getView(dataList, position);
        container.addView(view);
        return view;
    }

    protected abstract View getView(List<T> dataList, int position);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
