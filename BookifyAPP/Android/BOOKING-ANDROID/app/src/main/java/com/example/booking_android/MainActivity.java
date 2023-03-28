package com.example.booking_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.booking_android.login.view.LoginActivity;
import com.example.booking_android.lst_hotels.view.LstHotelActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent;
        SharedPreferences prefs = getSharedPreferences("client_info", MODE_PRIVATE);
        String name = prefs.getString("client_name","");

        if(name == ""){
            setContentView(R.layout.activity_main);
        }else{
            intent = new Intent(getBaseContext(),ParametersActivity.class);
            startActivity(intent);
        }
    }


    public void operacionInicial(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.go_guest:
                intent = new Intent(getBaseContext(), ParametersActivity.class);
                break;
            case R.id.go_login:
                intent = new Intent(getBaseContext(), LoginActivity.class);
                intent.putExtra("isFromMain",true);
                break;
        }

        startActivity(intent);
    }
}