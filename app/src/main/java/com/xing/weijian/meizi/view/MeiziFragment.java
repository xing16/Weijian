package com.xing.weijian.meizi.view;


import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xing.weijian.R;
import com.xing.weijian.base.BaseFragment;
import com.xing.weijian.meizi.model.MeiziBean;
import com.xing.weijian.meizi.presenter.MeiziPresenterImpl;
import com.xing.weijian.utils.DensityUtil;
import com.xing.weijian.view.recyclerview.RecyclerAdapter;
import com.xing.weijian.view.recyclerview.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeiziFragment extends BaseFragment implements MeiziView {

    @BindView(R.id.meizi_recycler_view)
    XRecyclerView recyclerView;

    private MeiziPresenterImpl meiziPresenter;

    private int pageSize = 10;

    private int curPage = 1;

    private List<MeiziBean> mDataList = new ArrayList<>();

    private RecyclerAdapter<MeiziBean> mAdapter;

    public MeiziFragment() {
    }

    protected int getLayoutResId() {
        return R.layout.fragment_meizi;
    }


    @Override
    protected void initView() {
        ButterKnife.bind(this, view);
        setRecyclerView();
    }

    @Override
    protected void initData() {
        meiziPresenter = new MeiziPresenterImpl();
        meiziPresenter.attachView(this);
        meiziPresenter.registerEvents();
        meiziPresenter.loadData(pageSize, curPage);
    }

    private void setRecyclerView() {
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setArrowImageView(R.drawable.refresh_arrow_down);
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.CubeTransition);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }


    @Override
    public void refreshUI(List<MeiziBean> meiziBeanList) {
        if (meiziBeanList == null) {
            return;
        }
        mDataList.addAll(meiziBeanList);
        if (mAdapter == null) {
            mAdapter = new RecyclerAdapter<MeiziBean>(mContext, mDataList, R.layout.item_recycler_meizi) {
                @Override
                public void convert(RecyclerViewHolder holder, MeiziBean data) {
                    if (data == null) {
                        return;
                    }
                    ImageView picImgView = holder.getView(R.id.iv_meizi_pic);
                    Glide.with(mContext)
                            .load(data.getUrl())
                            .into(picImgView);
                }
            };
            recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setDataList(mDataList);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        meiziPresenter.unregisterEvents();
    }
}
