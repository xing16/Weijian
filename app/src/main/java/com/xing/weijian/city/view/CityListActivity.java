package com.xing.weijian.city.view;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xing.weijian.R;
import com.xing.weijian.base.BaseActivity;
import com.xing.weijian.city.presenter.CityPresenterImpl;
import com.xing.weijian.view.QuickIndexView;
import com.xing.weijian.view.recyclerview.OnRecyclerItemClickListener;
import com.xing.weijian.view.recyclerview.RecyclerAdapter;
import com.xing.weijian.view.recyclerview.RecyclerViewHolder;
import com.xing.weijian.view.recyclerview.SectionItemDecoration;
import com.xing.weijian.weather.db.domain.City;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 展示中国城市列表信息
 */
public class CityListActivity extends BaseActivity implements CityView {

    private static final String TAG = "CityListActivity";

    private String[] words = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};


    @BindView(R.id.base_toolbar)
    Toolbar toolbar;

    @BindView(R.id.index_view)
    QuickIndexView indexView;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.pb_loading)
    ProgressBar progressBar;

    @BindView(R.id.tv_current_index)
    TextView currentIndexTxtView;

    private RecyclerAdapter mAdapter;

    private SearchView mSearchView;

    private CityPresenterImpl presenter;

    private List<City> mDataList = new ArrayList<>();

    private List<City> mSearchList = new ArrayList<>();

    private LinearLayoutManager layoutManager;
    private SectionItemDecoration itemDecoration;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_city_list;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.change_city);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        layoutManager = new LinearLayoutManager(mContext,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void initData() {
        indexView.setWords(words);
        presenter = new CityPresenterImpl();
        presenter.attachView(this);
        presenter.registerEvents();

        // 请求数据
        presenter.getData();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void setListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        indexView.setOnIndexChangeListener(new QuickIndexView.OnIndexChangeListener() {
            @Override
            public void onIndexChange(String text, int index) {
                currentIndexTxtView.setVisibility(View.VISIBLE);
                currentIndexTxtView.setText(text);
                for (int i = 0; i < mDataList.size(); i++) {
                    if (text.equalsIgnoreCase(String.valueOf(mDataList.get(i).getPinyin().charAt(0)))) {
                        // RecyclerView 滚动指定位置
                        recyclerView.scrollToPosition(i);
                        layoutManager.scrollToPositionWithOffset(i, 0);
                        break;
                    }
                }
            }

            @Override
            public void onRelease() {
                currentIndexTxtView.setVisibility(View.GONE);
            }
        });

        // 为recyclerView 设置点击事件，
        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getPosition();
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder viewHolder) {

            }
        });

        Log.d(TAG, "setListener: mSearchView == null" + (mSearchView == null));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_city, menu);
        MenuItem searchItem = menu.findItem(R.id.action_city_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setSubmitButtonEnabled(true);   // 显示提交按钮
        mSearchView.setQueryHint("请输入城市名称或拼音");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit: ");
                mSearchList.clear();
                for (int i = 0; i < mDataList.size(); i++) {
                    City city = mDataList.get(i);
                    String pinyin = city.getPinyin();
                    String cityName = city.getCityName();
                    if (pinyin.contains(query) || cityName.contains(query)) {
                        mSearchList.add(city);
                    }
                }
                mAdapter.setDataList(mSearchList);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "onQueryTextChange: ");
                return true;
            }
        });
        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mAdapter.setDataList(mDataList);
                return false;
            }
        });

        return true;
    }

    @Override
    public void showCityList(List<City> list) {
        mDataList = list;
        if (mAdapter == null) {
            mAdapter = new RecyclerAdapter<City>(mContext, list, R.layout.item_recycler_city) {

                @Override
                public void convert(RecyclerViewHolder holder, City city) {
                    TextView textView = holder.getView(R.id.tv_address_name);
                    textView.setText(city.getCityName());
                }
            };
            recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setDataList(list);
        }

        // 添加分割线
        itemDecoration = new SectionItemDecoration(this, new SectionItemDecoration.OnGroupListener() {
            @Override
            public String getGroupName(int position) {
                return String.valueOf(mDataList.get(position).getPinyin().charAt(0));
            }
        });
        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unregisterEvents();
        presenter.detachView();
    }
}
