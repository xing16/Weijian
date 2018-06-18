package com.xing.weijian.meizi.view;


import android.support.v7.widget.RecyclerView;

import com.xing.weijian.R;
import com.xing.weijian.base.BaseFragment;

public class MeiziFragment extends BaseFragment {

    private RecyclerView recyclerView;

    public MeiziFragment() {
    }

    @Override
    protected void initView() {
        recyclerView = view.findViewById(R.id.meizi_recycler_view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_meizi;
    }

    protected void initData() {

    }


}
