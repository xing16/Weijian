package com.xing.weijian.main.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.xing.weijian.R;
import com.xing.weijian.about.AboutActivity;
import com.xing.weijian.base.BaseActivity;
import com.xing.weijian.main.presenter.MainPresenterImpl;
import com.xing.weijian.meizi.view.MeiziFragment;
import com.xing.weijian.utils.BlurUtil;
import com.xing.weijian.view.ShapeImageView;
import com.xing.weijian.weather.view.WeatherActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {

    private static final String TAG = "MainActivity";

    private static final String TAG_FRAGMENT_CODER = "CoderFragment";

    private static final String TAG_FRAGMENT_MEIZI = "MeiziFragment";

    ShapeImageView avatarImgView;

    TextView userTextView;

    ImageView avatarBackgroundImg;

    @BindView(R.id.layout_nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.base_toolbar)
    Toolbar toolbar;

    private ActionBarDrawerToggle mDrawerToggle;

    private MainPresenterImpl mainPresenter;

    private CoderFragment coderFragment;

    private MeiziFragment meiziFragment;

    private int curItemSelectedId = -1000;    // NavigationView 当前选中的item id

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        toolbar.setTitle("微见");
        setSupportActionBar(toolbar);
        setupNavigationView();
//        // 设置 DrawerLayout 沉浸式
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//                //将侧边栏顶部延伸至status bar
//                mDrawerLayout.setFitsSystemWindows(true);
//                //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
//                mDrawerLayout.setClipToPadding(false);
//            }
//        }
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    protected void initData() {
        super.initData();
        mainPresenter = new MainPresenterImpl();
        mainPresenter.attachView(this);
        // 初始化时显示 CoderFragment
        mainPresenter.switchNavigationView(R.id.nav_coder);
    }

    @Override
    protected void setListener() {
        // 菜单列表点击事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.
                OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mainPresenter.switchNavigationView(item.getItemId());
                navigationView.setCheckedItem(item.getItemId());
                mDrawerLayout.closeDrawer(Gravity.LEFT, false);  // 关闭左边抽屉drawer
                return false;
            }
        });
    }

    /**
     * 设置navigationView相关属性，
     */
    private void setupNavigationView() {
        navigationView.setItemIconTintList(null); // 菜单列表项图片设置不同颜色，否则将是同一种颜色
        // 设置menuitem文本选择器
        navigationView.setItemTextColor(getResources().getColorStateList(R.color.nav_menu_item_color));
        View headerView = navigationView.getHeaderView(0);
        avatarBackgroundImg = headerView.findViewById(R.id.iv_avatar_background);
        avatarImgView = headerView.findViewById(R.id.iv_avatar);
        userTextView = headerView.findViewById(R.id.tv_user_name);
        avatarImgView.setImageResource(R.drawable.avatar);
        userTextView.setText("星火燎原");
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avatar);
        avatarBackgroundImg.setImageBitmap(BlurUtil.blur(this, bitmap, 23));
    }

    @Override
    public void switchToCoder() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (coderFragment == null) {
            coderFragment = new CoderFragment();
            transaction.add(R.id.layout_main, coderFragment, TAG_FRAGMENT_CODER).show(coderFragment);
        } else {
            transaction.show(coderFragment).hide(meiziFragment);
        }
        transaction.commit();
    }

    @Override
    public void switchToMeizi() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (meiziFragment == null) {
            meiziFragment = new MeiziFragment();
            transaction.add(R.id.layout_main, meiziFragment, TAG_FRAGMENT_MEIZI).show(meiziFragment);
        } else {
            transaction.show(meiziFragment).hide(coderFragment);
        }
        transaction.commit();
    }

    @Override
    public void switchToWeather() {
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
    }

    @Override
    public void switchAbout() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public int getCurrentItemSelectedId() {
        return curItemSelectedId;
    }

    @Override
    public void setCurrentItemSelectedId(int selectedId) {
        if (curItemSelectedId == selectedId) {
            return;
        }
        this.curItemSelectedId = selectedId;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_meizi, menu);
        return true;
    }

    /**
     * 只在 meizi fragment 时才显示出更多菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_meizi_scan).setVisible(curItemSelectedId == R.id.nav_meizi);
        invalidateOptionsMenu();
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: " + item.getItemId());
        MeiziFragment fragment = (MeiziFragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_MEIZI);
        if (fragment != null) {
            fragment.changeShow(item.getItemId());
        }
        return true;
    }
}
