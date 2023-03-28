package com.example.booking_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booking_android.login.LoginContract;
import com.example.booking_android.login.view.LoginActivity;
import com.example.booking_android.lst_bookings.view.LstBookingsActivity;

public class ProfileActivity extends AppCompatActivity {
    private TextView client_name;
    private TextView client_nif;
    private TextView client_email;
    private TextView client_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        initComponents();
    }


    public void initComponents(){
        client_name = findViewById(R.id.client_name);
        client_nif  = findViewById(R.id.client_nif);
        client_email = findViewById(R.id.client_email_2);
        client_phone = findViewById(R.id.client_phone);

        // Seteamos lo que tenemos guardado en local de preferences
        SharedPreferences prefs = getSharedPreferences("client_info", MODE_PRIVATE);
        client_name.setText("Hey " + prefs.getString("client_name","") + " !");
        client_nif.setText(prefs.getString("client_nif",""));
        client_email.setText(prefs.getString("client_email",""));
        client_phone.setText(prefs.getString("client_phone",""));

    }





    public void operacion(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.flecha_perfil:
                goBack(view);
                break;

            case R.id.log_out:
                SharedPreferences prefs = getSharedPreferences("client_info", MODE_PRIVATE);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("client_name", "");
                editor.putString("client_id","");
                editor.putString("client_name","");
                editor.putString("client_surname","");
                editor.putString("client_nif","");
                editor.putString("client_email","");
                editor.putString("client_birth_date","");
                editor.putString("client_phone","");
                editor.apply();
                intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
                break;
            case R.id.my_bookings:
                intent = new Intent(getBaseContext(), LstBookingsActivity.class);
                startActivity(intent);
        }


    }

    public void goBack(View view){
        onBackPressed();
    }


}