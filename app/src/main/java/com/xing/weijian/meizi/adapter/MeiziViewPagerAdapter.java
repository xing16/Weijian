package com.xing.weijian.meizi.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.github.chrisbanes.photoview.PhotoView;
import com.xing.weijian.R;
import com.xing.weijian.meizi.model.MeiziBean;
import com.xing.weijian.utils.ScreenUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 */

public class MeiziViewPagerAdapter extends ViewPagerAdapter<MeiziBean> {

    private static final String TAG = "MeiziViewPagerAdapter";

    public MeiziViewPagerAdapter(Context context, List<MeiziBean> list) {
        super(context, list);

    }

    @Override
    protected View getView(List<MeiziBean> dataList, int position) {
        View view = inflater.inflate(R.layout.item_pager_meizi, null);
        final PhotoView photoView = view.findViewById(R.id.pv_meizi_detail);
        Glide.with(mContext)
                .load(dataList.get(position).getUrl())
                .asBitmap()
                .centerCrop()
                .into(photoView);
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
//                        int picWidth = bitmap.getWidth();
//                        int picHeight = bitmap.getHeight();
//                        Log.d(TAG, "onResourceReady: picWidth = " + picWidth + ",picHeight = " + picHeight);
//                        int destWidth = ScreenUtil.getScreenWidth(mContext);
//                        // destWidth / picWidth   = destHeight / picHeight
//                        int destHeight = destWidth * picHeight / picWidth;
//                        Log.d(TAG, "onResourceReady: destWidth = " + destWidth + ", destHeight = " + destHeight);
//                        // 设置空间宽高
////                        ViewGroup.LayoutParams layoutParams = photoView.getLayoutParams();
////                        layoutParams.width = destWidth;
////                        layoutParams.height = destHeight;
////                        photoView.setLayoutParams(layoutParams);
//
//                        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, destWidth, destHeight, false);
//                        photoView.setImageBitmap(scaledBitmap);
//                    }
//                });
        return view;
    }
}
