package com.xing.weijian.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xing.weijian.R;

public class BottomDialog extends DialogFragment {

    private Context mContext;

    private String[] items;

    private float textSize;

    private boolean canceledOnTouchOutside = true;

    private boolean hasCancelButton = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(mContext);
        LinearLayout contentView = new LinearLayout(mContext);
        contentView.setOrientation(LinearLayout.VERTICAL);
        contentView.setBackgroundColor(Color.parseColor("#eeeeee"));
        if (items == null || items.length == 0) {
            throw new IllegalArgumentException("items can't be null or size = 0");
        }
        // add listview
        final ListView listView = new ListView(mContext);
        listView.setVerticalScrollBarEnabled(false);
        listView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        listView.setFooterDividersEnabled(false);
        listView.setAdapter(new ArrayAdapter<>(mContext, R.layout.item_bottom_dialog, R.id.tv_bottom_dialog_title, items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null) {
                    listener.onDialogItemClick(position, view);
                }
                dismiss();
            }
        });
        // add cancel view
        if (hasCancelButton) {
            listView.addFooterView(initCancelButton());
        }
        contentView.addView(listView);
        dialog.setContentView(contentView);
        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable());
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        if (listView.getAdapter().getCount() > 6) {
            // listview item count > 6, bottom dialog height = 6 * itemheight
            int listViewHeight = getListViewHeight(listView, 6);
            wlp.height = listViewHeight;
        }
        wlp.gravity = Gravity.BOTTOM;
        window.setAttributes(wlp);
        window.setWindowAnimations(R.style.BottomDialogAnimStyle);
        return dialog;
    }

    /**
     * 根据 listView item的个数来计算 listview 高度
     *
     * @param listView
     * @return
     */
    private int getListViewHeight(ListView listView, int count) {
        if (listView == null) {
            return 0;
        }
        ListAdapter adapter = listView.getAdapter();
        if (adapter == null) {
            return 0;
        }
        int totalHeight = 0;
        for (int i = 0; i < count; i++) {
            View view = adapter.getView(i, null, listView);
            view.measure(0, 0);
            totalHeight += view.getMeasuredHeight();
        }
        return totalHeight;
    }


    private View initCancelButton() {
        LinearLayout footerLayout = new LinearLayout(mContext);
        footerLayout.setOrientation(LinearLayout.VERTICAL);
        footerLayout.setBackgroundColor(Color.parseColor("#eeeeee"));
        TextView cancelTextView = new TextView(mContext);
        cancelTextView.setText("取消");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.topMargin = dp2Px(5);
        lp.height = dp2Px(52);
        cancelTextView.setLayoutParams(lp);
        cancelTextView.setBackgroundColor(Color.WHITE);
        cancelTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        cancelTextView.setGravity(Gravity.CENTER);
        footerLayout.addView(cancelTextView);
        cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return footerLayout;
    }

    private OnDialogItemClickListener listener;

    public interface OnDialogItemClickListener {
        void onDialogItemClick(int position, View view);
    }

    public void setDialogItemClickListener(OnDialogItemClickListener listener) {
        this.listener = listener;
    }

    public static class Builder {
        private String[] items;

        private float textSize;

        private boolean canceledOnTouchOutside = true;

        private boolean hasCancelButton = true;

        private OnDialogItemClickListener listener;

        public Builder() {

        }

        public Builder setItems(String[] items) {
            this.items = items;
            return this;
        }

        public Builder setTextSize(int size) {
            this.textSize = size;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public Builder setHasCancelButton(boolean hasCancelButton) {
            this.hasCancelButton = hasCancelButton;
            return this;
        }

        public Builder setOnDialogItemClickListener(OnDialogItemClickListener listener) {
            this.listener = listener;
            return this;
        }

        public void show(FragmentManager fm, String tag) {
            BottomDialog bottomDialog = new BottomDialog();
            bottomDialog.items = items;
            bottomDialog.textSize = textSize;
            bottomDialog.canceledOnTouchOutside = canceledOnTouchOutside;
            bottomDialog.listener = listener;
            bottomDialog.show(fm, tag);
        }
    }

    private int dp2Px(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }

}