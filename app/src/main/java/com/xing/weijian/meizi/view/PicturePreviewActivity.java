package com.xing.weijian.meizi.view;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
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
import com.xing.weijian.http.RetrofitClient;
import com.xing.weijian.meizi.adapter.ViewPagerAdapter;
import com.xing.weijian.meizi.model.MeiziBean;
import com.xing.weijian.utils.ClipboardUtil;
import com.xing.weijian.utils.DateUtil;
import com.xing.weijian.utils.FileUtil;
import com.xing.weijian.utils.ScreenUtil;
import com.xing.weijian.utils.ToastUtil;
import com.xing.weijian.view.BottomDialog;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;

public class PicturePreviewActivity extends BaseActivity {

    private static final String TAG = "PicturePreviewActivity";
    private String[] titles = {"保存至本地", "复制链接"};

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
                return view;
            }
        });
        viewPager.setCurrentItem(index);
    }


    private class OnPictureLongClickListener implements View.OnLongClickListener {

        private final int position;

        public OnPictureLongClickListener(int position) {
            this.position = position;
        }

        @Override
        public boolean onLongClick(View v) {
            String url = meiziList.get(position).getUrl();
            Log.d(TAG, "onLongClick: URL = " + url);
            // show bottom dialog
            new BottomDialog.Builder()
                    .setItems(new String[]{"复制链接", "保存至本地"})
                    .setCanceledOnTouchOutside(true)
                    .setHasCancelButton(true)
                    .setTextSize(18)
                    .setOnDialogItemClickListener(new BottomDialog.OnDialogItemClickListener() {
                        @Override
                        public void onDialogItemClick(int pos, View view) {
                            switch (pos) {
                                case 0:   // 复制链接
                                    copyPictureURL(position);
                                    break;
                                case 1:   // 保存至本地
                                    savePictureToSdcard(position);
                                    break;
                            }
                        }
                    })
                    .show(PicturePreviewActivity.this.getSupportFragmentManager(), "bottom_dialog");

            return true;
        }


    }


    /**
     * 复制图片链接
     */
    private void copyPictureURL(int position) {
        if (position < 0 || position >= meiziList.size()) {
            return;
        }
        String url = meiziList.get(position).getUrl();
        ClipboardUtil.copyText(this, url);
        ToastUtil.showShortToast("复制成功");
    }


    /**
     * 保存图片至本地
     */
    private void savePictureToSdcard(int position) {
        if (position < 0 || position >= meiziList.size()) {
            return;
        }
        String url = meiziList.get(position).getUrl();
        RetrofitClient.getInstance()
                .getApiService()
                .downloadFile(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Log.d(TAG, "onNext: " + responseBody.byteStream().available());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        boolean flag = FileUtil.saveFileToSdcard(mContext, responseBody.byteStream(), "weijian/" + DateUtil.formatDate(System.currentTimeMillis()) + ".jpg");
                        ToastUtil.showShortToast(flag ? "保存成功" : "保存失败");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });

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
