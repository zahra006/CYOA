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
                isFirstTime();
            }

            private void isFirstTime() {
                //check if app is running for the first time in forever
                //save the value to the SharedPreferences
                SharedPreferences preferences = getApplication().getSharedPreferences("onBoard", Context.MODE_PRIVATE);
                boolean isFirstTime = preferences.getBoolean("isFirstTime",true);//default value = true

                if (isFirstTime){
                    //if it's true then it's first time, and we will chage it to false
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isFirstTime",false);
                    editor.apply();

                    //then start Auth Activity
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {

                    //start Home Activity
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },1500);
    }

}