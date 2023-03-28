package com.example.booking_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booking_android.login.view.LoginActivity;

public class FailureParametersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.failure_parameters_activity);

    }


    public void operacion(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.go_parameters:
                intent = new Intent(getBaseContext(), ParametersActivity.class);
                startActivity(intent);
                break;

        }
    }
}
