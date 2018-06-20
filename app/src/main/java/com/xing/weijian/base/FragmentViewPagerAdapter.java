package com.xing.weijian.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/9/2.
 */

public class FragmentViewPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {

    private List<T> fragmentList;

    private String[] titles;

    public FragmentViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setPages(String[] titles, List<T> fragments) {
        this.titles = titles;
        this.fragmentList = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList == null ? null : fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    /**
     * 在没有重写FragmentPagerAdapter 的 destroyItem(),切换过的 Fragment 再次显示时，会销毁重新创建，
     * 请求的数据也丢失，为了保留已经加载过的 Fragment 数据，需要 Fragment 不销毁。
     * 即不调用父类方法
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
    }


    @Override
    public void destroyItem(View container, int position, Object object) {
//            super.destroyItem(container, position, object);
    }
}