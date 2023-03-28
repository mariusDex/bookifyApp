package com.example.booking_android.lst_hotels;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booking_android.ParametersActivity;
import com.example.booking_android.ProfileActivity;
import com.example.booking_android.R;

import com.example.booking_android.login.view.LoginActivity;
import com.example.booking_android.lst_hotel_rooms.view.LstRoomActivity;
import com.squareup.picasso.Picasso;

public class ShowInfoHotelActivity extends AppCompatActivity{
    private String hotel_id;
    private Boolean userLogged;
    private TextView client_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intentHotel = getIntent();
        setContentView(R.layout.individual_hotel);
        this.client_name = findViewById(R.id.client_name);
        this.hotel_id = intentHotel.getStringExtra("hotel_id");
        checkLoggedUser();
        setHotelInfo();




    }

    public void checkLoggedUser(){
        SharedPreferences prefs = getSharedPreferences("client_info", MODE_PRIVATE);
        String client_name_string  = prefs.getString("client_name","");
        if(client_name_string != ""){
            this.userLogged = true;
            client_name.setText(prefs.getString("client_name",""));
        }else{
            this.userLogged = false;
        }
    }
    public void seeHotelRooms(View view){
        Intent intentRooms;

        if(userLogged){
            intentRooms = new Intent(view.getContext(), LstRoomActivity.class);
            intentRooms.putExtra("hotel_id", this.hotel_id);
        }else{
            intentRooms = new Intent(getBaseContext(), LoginActivity.class);
            intentRooms.putExtra("isFromMain",false);
        }

        startActivity(intentRooms);
    }


    public void operacion(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.login_profile_button:
                if(userLogged){
                    intent = new Intent(getBaseContext(), ProfileActivity.class);
                }else{
                    intent = new Intent(getBaseContext(), LoginActivity.class);
                    intent.putExtra("isFromMain",false);
                }
                break;

        }

        startActivity(intent);
    }

    public void setHotelInfo(){

        TextView info_hotel_check_in   = findViewById(R.id.info_hotel_check_in);
        TextView info_hotel_check_out  = findViewById(R.id.info_hotel_check_out);
        ImageView info_photo   = findViewById(R.id.info_hotel_photo);
        TextView info_hotel_name =  findViewById(R.id.info_hotel_name);
        TextView info_hotel_address =  findViewById(R.id.info_hotel_address);
        TextView info_hotel_country = findViewById(R.id.info_hotel_country);
        TextView info_hotel_city    = findViewById(R.id.info_hotel_city);
        TextView info_hotel_description  = findViewById(R.id.info_hotel_description);


        Intent intent = getIntent();
        String check_in = intent.getStringExtra("check_in");
        String check_out = intent.getStringExtra("check_out");
        String photo    = intent.getStringExtra("photo");
        String name     = intent.getStringExtra("name");
        String address  = intent.getStringExtra("address");
        String country  = intent.getStringExtra("country");
        String city     = intent.getStringExtra("city");
        String description  = intent.getStringExtra("description");


        info_hotel_check_in.setText(check_in);
        info_hotel_check_out.setText(check_out);
        Picasso.get().load(photo).into(info_photo);
        info_hotel_name.setText(name);
        info_hotel_address.setText(address);
        info_hotel_country.setText(country);
        info_hotel_city.setText(city);
        info_hotel_description.setText(description);

    }



    public void goBack(View view){
        onBackPressed();
    }

}