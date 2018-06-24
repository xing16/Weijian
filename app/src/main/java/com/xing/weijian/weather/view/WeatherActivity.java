package com.xing.weijian.weather.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.xing.weijian.R;
import com.xing.weijian.base.BaseActivity;
import com.xing.weijian.city.view.CityListActivity;
import com.xing.weijian.utils.DateUtil;
import com.xing.weijian.utils.WeatherUtil;
import com.xing.weijian.weather.db.domain.Weather;
import com.xing.weijian.weather.presenter.WeatherPresenterImpl;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherActivity extends BaseActivity implements WeatherView {

    private static final String TAG = "WeatherActivity";

    public static final int CODE_CITY_LIST = 0x11;

    @BindView(R.id.base_toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_weather_date)
    TextView dateTxtView;

    @BindView(R.id.iv_weather_icon)
    ImageView weatherImgView;

    @BindView(R.id.tv_weather_desc)
    TextView weatherDescTxtView;

    @BindView(R.id.tv_weather_temperature)
    TextView temperatureTxtView;

    @BindView(R.id.tv_weather_temperature_more)
    TextView moreTemperatureTxtView;


    @BindView(R.id.tv_weather_wind_direction)
    TextView windTextView;

    @BindView(R.id.tv_weekly_today)
    TextView todayWeekTxtView;

    @BindView(R.id.tv_weekly_tomorrow)
    TextView tomorrowWeekTxtView;

    @BindView(R.id.tv_weekly_day_tomorrow)
    TextView dayTomorrowWeekTxtView;

    @BindView(R.id.iv_today_weather_image)
    ImageView todayWeatherImg;

    @BindView(R.id.iv_tomorrow_weather_image)
    ImageView tomorrowWeatherImg;

    @BindView(R.id.iv_day_tomorrow_weather_image)
    ImageView dayTomorrowWeatherImg;

    @BindView(R.id.tv_today_weather_desc)
    TextView todayWeatherDescTxtView;

    @BindView(R.id.tv_tomorrow_weather_desc)
    TextView tomorrowWeatherDescTxtView;

    @BindView(R.id.tv_day_tomorrow_weather_desc)
    TextView dayTomorrowWeatherDescTxtView;

    @BindView(R.id.tv_temp_low_high)
    TextView tempLowHighTxtView;


    private WeatherPresenterImpl weatherPresenter;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_weather;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void setListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d(TAG, "onMenuItemClick: ");
                switch (item.getItemId()) {
                    case R.id.action_change_city:
                        gotoCityListActivity();
                        break;
                }
                return false;
            }
        });
    }

    private void gotoCityListActivity() {
        Intent intent = new Intent(this, CityListActivity.class);
        startActivity(intent);
    }


    /**
     * 先定位,在根据 cityId 获取天气信息，可以使用 rxjava flatmap 操作符
     */
    @Override
    protected void initData() {
        weatherPresenter = new WeatherPresenterImpl(this);
        weatherPresenter.registerEvents();
        weatherPresenter.getLocation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: ");
        return super.onOptionsItemSelected(item);
    }

    /**
     * 使状态栏透明 * <p> * 适用于图片作为背景的界面,此时需要图片填充到状态栏 * * @param activity 需要设置的activity
     */
    public static void setTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (weatherPresenter != null) {
            weatherPresenter.unregisterEvents();
            weatherPresenter.detachView();
        }

    }

    /**
     * 定位成功回调，显示定位信息
     *
     * @param amapLocation
     */
    @Override
    public void showLocation(AMapLocation amapLocation) {
        if (amapLocation == null) {
            return;
        }
        //城市信息
        String city = amapLocation.getCity();
        toolbar.setTitle(city);
    }

    /**
     * 天气请求结果回调，显示天气信息
     *
     * @param weather
     */
    @Override
    public void showWeatherInfo(Weather weather) {
        if (weather == null) {
            return;
        }
        List<Weather.HeWeather6Bean.DailyForecastBean> dailyForecast = weather.getHeWeather6().get(0).getDaily_forecast();
        Weather.HeWeather6Bean.DailyForecastBean todayForecastBean = dailyForecast.get(0);
        Weather.HeWeather6Bean.DailyForecastBean tomorrowForecastBean = dailyForecast.get(1);
        Weather.HeWeather6Bean.DailyForecastBean afterTomorrowForecastBean = dailyForecast.get(2);
        Date todayDate = DateUtil.parseDate(todayForecastBean.getDate());
        dateTxtView.setText(DateUtil.formatMonthDay(todayDate) + " " + DateUtil.getWeekOfDate(todayDate));
        tempLowHighTxtView.setText("最低~最高");
        weatherImgView.setImageResource(WeatherUtil.getWeatherIcon(todayForecastBean.getCond_code_d()));
        weatherDescTxtView.setText(todayForecastBean.getCond_txt_d());
        temperatureTxtView.setText(todayForecastBean.getTmp_max() + "°");
        moreTemperatureTxtView.setText(todayForecastBean.getTmp_min() + "°  ~  " + todayForecastBean.getTmp_max() + "°");

        windTextView.setText(todayForecastBean.getWind_dir() + " " + todayForecastBean.getWind_sc());

        // week date
        Date tomorrowDate = DateUtil.parseDate(tomorrowForecastBean.getDate());
        Date dayTomorrowDate = DateUtil.parseDate(afterTomorrowForecastBean.getDate());
        todayWeekTxtView.setText(DateUtil.getWeekOfDate(todayDate));
        tomorrowWeekTxtView.setText(DateUtil.getWeekOfDate(tomorrowDate));
        dayTomorrowWeekTxtView.setText(DateUtil.getWeekOfDate(dayTomorrowDate));

        // weather icon
        todayWeatherImg.setImageResource(WeatherUtil.getWeatherIcon(todayForecastBean.getCond_code_d()));
        tomorrowWeatherImg.setImageResource(WeatherUtil.getWeatherIcon(tomorrowForecastBean.getCond_code_d()));
        dayTomorrowWeatherImg.setImageResource(WeatherUtil.getWeatherIcon(afterTomorrowForecastBean.getCond_code_d()));

        // weather desc
        todayWeatherDescTxtView.setText(todayForecastBean.getCond_txt_d());
        tomorrowWeatherDescTxtView.setText(tomorrowForecastBean.getCond_txt_d());
        dayTomorrowWeatherDescTxtView.setText(afterTomorrowForecastBean.getCond_txt_d());
    }
}
