package com.example.booking_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

public class SplashscreenCompra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen_compra);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Launch the next Activity here
            }
        }, 5000);
    }
}