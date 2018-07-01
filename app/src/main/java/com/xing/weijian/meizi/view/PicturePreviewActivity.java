package com.xing.weijian.meizi.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.github.chrisbanes.photoview.PhotoView;
import com.xing.weijian.R;
import com.xing.weijian.base.BaseActivity;
import com.xing.weijian.constants.Extras;
import com.xing.weijian.meizi.adapter.ViewPagerAdapter;
import com.xing.weijian.meizi.model.MeiziBean;
import com.xing.weijian.utils.ScreenUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PicturePreviewActivity extends BaseActivity {

    private static final String TAG = "PicturePreviewActivity";

    @BindView(R.id.meizi_pic_view_pager)
    ViewPager viewPager;

    private int index = 0;

    private List<MeiziBean> meiziList = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_picture_preview;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent != null) {
            index = intent.getIntExtra(Extras.EXTRAS_INDEX, 0);
            meiziList = (List<MeiziBean>) intent.getSerializableExtra(Extras.EXTRAS_MEIZI_LIST);
        }
        viewPager.setAdapter(new ViewPagerAdapter<MeiziBean>(mContext, meiziList) {
            @Override
            protected View getView(List<MeiziBean> dataList, int position) {
                View view = inflater.inflate(R.layout.item_pager_meizi, null);
                final PhotoView photoView = view.findViewById(R.id.pv_meizi_detail);
                // 长按事件
                photoView.setOnLongClickListener(new OnPictureLongClickListener(position));
                Glide.with(mContext)
                        .load(dataList.get(position).getUrl())
                        .asBitmap()
                        .centerCrop()
                        .into(photoView);
//                        .into(new SimpleTarget<Bitmap>() {
//                            @Override
//                            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
//                                int picWidth = bitmap.getWidth();
//                                int picHeight = bitmap.getHeight();
//                                Log.d(TAG, "onResourceReady: picWidth = " + picWidth + ",picHeight = " + picHeight);
//                                int destWidth = ScreenUtil.getScreenWidth(mContext);
//                                // destWidth / picWidth   = destHeight / picHeight
//                                int destHeight = destWidth * picHeight / picWidth;
//                                Log.d(TAG, "onResourceReady: destWidth = " + destWidth + ", destHeight = " + destHeight);
//                                // 设置空间宽高
//                                ViewGroup.LayoutParams layoutParams = photoView.getLayoutParams();
//                                layoutParams.width = destWidth;
//                                layoutParams.height = destHeight;
//                                photoView.setLayoutParams(layoutParams);
//
//                                Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, destWidth, destHeight, false);
//                                photoView.setImageBitmap(scaledBitmap);
//                            }
//                        });
                return view;
            }
        });
        viewPager.setCurrentItem(index);
    }

    private class OnPictureLongClickListener implements View.OnLongClickListener {

        private int position;

        public OnPictureLongClickListener(int position) {
            this.position = position;
        }

        @Override
        public boolean onLongClick(View v) {
            String url = meiziList.get(position).getUrl();
            Log.d(TAG, "onLongClick: URL = " + url);
            // show bottom dialog

            return true;
        }
    }

    @Override
    protected void setListener() {

    }

    public static void startActivity(Context context, int index, List<MeiziBean> list) {
        Intent intent = new Intent(context, PicturePreviewActivity.class);
        intent.putExtra(Extras.EXTRAS_INDEX, index);
        intent.putExtra(Extras.EXTRAS_MEIZI_LIST, (Serializable) list);
        context.startActivity(intent);
    }
}
