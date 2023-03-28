package com.example.booking_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class FailureGetBookingsActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.failure_getbookings_activity);

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
