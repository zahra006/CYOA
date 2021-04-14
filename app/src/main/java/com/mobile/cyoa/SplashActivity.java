package com.mobile.cyoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                boolean isLoggedIn = userPref.getBoolean("isLoggedIn",false);
                if (isLoggedIn){
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(SplashActivity.this, AuthActivity.class));
                    finish();
                }
            }
        },1500);
    }

}