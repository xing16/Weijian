package com.xing.weijian.main.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.LoadingMoreFooter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;
import com.xing.weijian.R;
import com.xing.weijian.base.BaseFragment;
import com.xing.weijian.base.BaseLazyFragment;
import com.xing.weijian.coder.view.WebViewActivity;
import com.xing.weijian.coder.model.CoderBean;
import com.xing.weijian.coder.presenter.CoderPresenterImpl;
import com.xing.weijian.coder.view.CoderView;
import com.xing.weijian.view.recyclerview.DividerListItemDecoration;
import com.xing.weijian.view.recyclerview.OnRecyclerItemClickListener;
import com.xing.weijian.view.recyclerview.RecyclerAdapter;
import com.xing.weijian.view.recyclerview.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ViewPager 中的 Fragment, 实现了懒加载的数据请求
 */
public class TabFragment extends BaseLazyFragment implements CoderView {

    private static final String TAG = "TabFragment";

    public static final String TAB_ANDROID = "Android";

    public static final String TAB_IOS = "iOS";

    private String type = TAB_ANDROID;

    private int pageSize = 20;

    private int curPage = 1;

    @BindView(R.id.tab_recycler_view)
    XRecyclerView recyclerView;

    @BindView(R.id.pb_coder_progress)
    ProgressBar progressBar;

    private CoderPresenterImpl coderPresenterImpl;

    private RecyclerAdapter adapter;

    private List<CoderBean> mDataList;

    private Unbinder unbinder;

    public TabFragment() {
    }


    public static TabFragment newInstance(String type) {
        TabFragment tabFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_tab;
    }

    @Override
    protected void init() {
        unbinder = ButterKnife.bind(this, view);
        setRecyclerView();
        mDataList = new ArrayList<>();
        coderPresenterImpl = new CoderPresenterImpl();
        coderPresenterImpl.attachView(this);
        // 注册 EventBus
        coderPresenterImpl.onStart();
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString("type");
        }
        setListener();
    }


    private void setRecyclerView() {
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setArrowImageView(R.drawable.refresh_arrow_down);
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.CubeTransition);
    }


    @Override
    protected void loadData() {
        coderPresenterImpl.loadData(type, pageSize, curPage);
    }

    private void setListener() {
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                // refresh data
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // refresh complete
                        recyclerView.refreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                curPage++;
                coderPresenterImpl.loadData(type, pageSize, curPage);
            }
        });

        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", mDataList.get(viewHolder.getAdapterPosition()).getUrl());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_in_from_bottom, R.anim.anim_no_effect);
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder viewHolder) {

            }
        });
    }

    @Override
    public void refreshUI(final List<CoderBean> list, String reqType) {
        if (TextUtils.isEmpty(reqType)) {
            return;
        }
        if (type.equals(reqType)) {
            mDataList.addAll(list);
            if (adapter == null) {
                adapter = new RecyclerAdapter<CoderBean>(mContext, mDataList, R.layout.item_recycler_coder) {
                    @Override
                    public void convert(RecyclerViewHolder holder, CoderBean data) {
                        ImageView imageView = holder.getView(R.id.iv_icon);
                        TextView textView = holder.getView(R.id.tv_text);
                        textView.setText(data.getDesc());
                        List<String> images = data.getImages();
                        if (images != null) {
                            imageView.setVisibility(View.VISIBLE);
                            Glide.with(getActivity()).load(images.get(0)).into(imageView);
                        }
                    }
                };
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                recyclerView.addItemDecoration(new DividerListItemDecoration(mContext));
                recyclerView.setAdapter(adapter);
            } else {
                adapter.setDataList(mDataList);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        // 注销 EventBus
        if (coderPresenterImpl != null) {
            coderPresenterImpl.onStop();
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showError() {

    }
}
