package com.xing.weijian.meizi.view;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xing.weijian.R;
import com.xing.weijian.base.BaseFragment;
import com.xing.weijian.meizi.model.MeiziBean;
import com.xing.weijian.meizi.presenter.MeiziPresenterImpl;
import com.xing.weijian.view.recyclerview.OnRecyclerItemClickListener;
import com.xing.weijian.view.recyclerview.RecyclerAdapter;
import com.xing.weijian.view.recyclerview.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeiziFragment extends BaseFragment implements MeiziView {

    private static final String TAG = "MeiziFragment";

    @BindView(R.id.meizi_recycler_view)
    XRecyclerView recyclerView;

    private MeiziPresenterImpl meiziPresenter;

    private int pageSize = 20;

    private int curPage = 1;

    private List<MeiziBean> mDataList = new ArrayList<>();

    private RecyclerAdapter<MeiziBean> mAdapter;

    private RecyclerView.LayoutManager layoutManager;

    private LinearLayoutManager linearLayoutManager;

    private GridLayoutManager gridLayoutManager;

    private StaggeredGridLayoutManager staggeredGridLayoutManager;

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
        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition() - 1;
                Log.d(TAG, "onItemClick: position = " + position);
                PicturePreviewActivity.startActivity(mContext, position, mDataList);
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder viewHolder) {

            }
        });

    }

    private void setRecyclerView() {
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setArrowImageView(R.drawable.refresh_arrow_down);
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.CubeTransition);
        layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
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
                public void convert(final RecyclerViewHolder holder, MeiziBean data) {
                    if (data == null) {
                        return;
                    }
                    final ImageView picImgView = holder.getView(R.id.iv_meizi_pic);
                    Glide.with(mContext)
                            .load(data.getUrl())
                            .asBitmap()
                            .placeholder(R.drawable.ic_default_pic)
                            .error(R.drawable.ic_default_pic)
                            .centerCrop()
                            .into(picImgView);
//                            .into(new SimpleTarget<Bitmap>() {
//                                @Override
//                                public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
//                                    int picWidth = bitmap.getWidth();
//                                    int picHeight = bitmap.getHeight();
//                                    int destWidth = picWidth;
//                                    int destHeight = picHeight;
//                                    Log.d(TAG, "onResourceReady: " + (recyclerView.getLayoutManager() instanceof LinearLayoutManager));
//                                    if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
//                                        destWidth = ScreenUtil.getScreenWidth(mContext) - DensityUtil.dp2px(mContext, 20);
//                                        destHeight = (int) (destWidth * 0.8f);
//                                    } else {
//                                        destWidth = ScreenUtil.getScreenWidth(mContext) / 2;
//                                        // picWidth * layoutParams.height = picHeight * layoutParams.width
//                                        destHeight = destWidth * picHeight / picWidth;
//                                    }
//                                    View itemView = holder.getItemView();
//                                    ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
//                                    layoutParams.width = destWidth;
//                                    layoutParams.height = destHeight;
//                                    // 设置 item view 控件的宽高
//                                    itemView.setLayoutParams(layoutParams);
//                                    // 设置图片的宽高
//                                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, destWidth, destHeight, false);
//                                    picImgView.setImageBitmap(scaledBitmap);
//                                }
//                            });
                }
            };
            recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setDataList(mDataList);
        }

    }

    public void changeShow(int menuId) {
        switch (menuId) {
            case R.id.action_meizi_linear:
                linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(linearLayoutManager);
                break;
            case R.id.action_meizi_grid:
                gridLayoutManager = new GridLayoutManager(mContext, 2);
                recyclerView.setLayoutManager(gridLayoutManager);
                break;
            case R.id.action_meizi_stragger:
                staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        meiziPresenter.unregisterEvents();
    }
}
