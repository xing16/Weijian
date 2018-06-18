package com.xing.weijian.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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

    public void setFragmentList(String[] titles, List<T> fragments) {
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
}
