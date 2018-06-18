package com.xing.weijian.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.xing.weijian.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }


    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                enterAdActivity();
            }
        }, 1000);
    }

    private void enterAdActivity() {
        Intent intent = new Intent(this, AdActivity.class);
        startActivity(intent);
        finish();
    }


}
