package com.app.theleague78;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Timer RunSplash = new Timer();
        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {


                    Intent myIntent = new Intent(SplashScreen.this,
                            WelcomeScreen.class);
                    startActivity(myIntent);
                    finish();

            }
        };

        RunSplash.schedule(ShowSplash, 3000);
    }
}
