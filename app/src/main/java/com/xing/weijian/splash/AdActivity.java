package com.xing.weijian.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xing.weijian.R;
import com.xing.weijian.main.view.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * splash 页面之后的广告页面
 */
public class AdActivity extends AppCompatActivity {

    private int count = 3000;

    @BindView(R.id.tv_count_down_time)
    TextView countDownView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        countDownTimer.start();
        countDownView.setText("跳过(" + count / 1000 + "s)");
    }

    /**
     * 倒计时按钮点击事件,点击进入 MainActivity
     */
    public void skipCountDown(View view) {
        enterMainActivity();
    }


    // 单位为毫秒
    private CountDownTimer countDownTimer = new CountDownTimer(count, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            Log.i("debug", "timer = " + millisUntilFinished / 1000);
            countDownView.setText((millisUntilFinished / 1000) + "s");
        }

        @Override
        public void onFinish() {
            countDownView.setText("0s");
            enterMainActivity();
        }
    };

    /**
     * 进入 MainActivity
     */
    private void enterMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, R.anim.anim_splash_exit);  // 0表示没有动画效果
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
}
