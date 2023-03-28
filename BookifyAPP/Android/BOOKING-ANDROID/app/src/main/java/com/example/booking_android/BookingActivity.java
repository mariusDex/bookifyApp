package com.example.booking_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booking_android.entities.Parameters;
import com.example.booking_android.entities.User;
import com.example.booking_android.entities.UserRespuesta;
import com.example.booking_android.login.LoginContract;
import com.example.booking_android.login.view.LoginActivity;
import com.example.booking_android.lst_hotels.view.LstHotelActivity;
import com.example.booking_android.utils.ApiClient;
import com.example.booking_android.utils.ApiInterface;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity {
    private String room_price;
    private String guests;
    private String room_description;
    private String room_number;
    private String room_id;
    private String check_in;
    private String check_out;
    private String client_id;
    private String client_name;
    private Button insertBooking;
    private String room_photo;
    private TextView name_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Intent incomingIntent = getIntent();
        insertBooking  = findViewById(R.id.insert_booking);
        SharedPreferences prefs = getSharedPreferences("client_info", MODE_PRIVATE);
        this.client_name = prefs.getString("client_name","");
        this.name_user = findViewById(R.id.nombre_user_hoteles);
        this.name_user.setText(this.client_name);
        this.client_id = prefs.getString("client_id","");
        this.check_in = prefs.getString("check_in","");
        this.check_out = prefs.getString("check_out","");
        this.guests = prefs.getString("guests","");

        // room
        this.room_price = incomingIntent.getStringExtra("room_price");
        this.room_description = incomingIntent.getStringExtra("room_description");
        this.room_number = incomingIntent.getStringExtra("room_number");
        this.room_id = incomingIntent.getStringExtra("room_id");
        this.room_photo = incomingIntent.getStringExtra("room_photo");

        // Metemos all data
        TextView room_description = findViewById(R.id.room_description);
        TextView check_in_string  = findViewById(R.id.check_in);
        TextView check_out_string = findViewById(R.id.check_out);
        TextView room_guests_string = findViewById(R.id.room_guests);
        TextView room_price_string =  findViewById(R.id.room_price);
        ImageView room_photo_view = findViewById(R.id.room_photo);

        Picasso.get().load(room_photo).into(room_photo_view);
        room_description.setText(this.room_description);
        check_in_string.setText(this.check_in);
        check_out_string.setText(this.check_out);
        room_guests_string.setText(this.guests);
        room_price_string.setText(this.room_price);

    }

    public void insertBookingWS(String client_id,String room_id,String guests, String check_in, String check_out, String room_description) {


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Integer> call =  apiService.insertBooking(client_id,room_id,guests,check_in,check_out,room_description);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call,Response<Integer> response) {
                Toast.makeText(BookingActivity.this, "FUNCIONÓ EL INSERT PAAAA", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Context context = getApplicationContext();

                LayoutInflater inflater = getLayoutInflater();

                View customToastView = inflater.inflate(R.layout.custom_toast, null);

                TextView toastMessage = customToastView.findViewById(R.id.toast_message);
                toastMessage.setText("Booking complete !");

                Toast customToast = new Toast(context);
                customToast.setDuration(Toast.LENGTH_LONG);
                customToast.setView(customToastView);
                customToast.setGravity(Gravity.TOP, 0, 200);

                customToast.show();

                Intent intent2  = new Intent(getBaseContext(), ParametersActivity.class);
                startActivity(intent2);
            }
        });
    }


    public void operacion(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.login_profile_button:

                SharedPreferences prefs = getSharedPreferences("client_info", MODE_PRIVATE);
                String name = prefs.getString("client_name","");
                if(name == ""){
                    intent = new Intent(getBaseContext(), LoginActivity.class);
                    intent.putExtra("isFromMain",false);
                }else{
                    intent = new Intent(getBaseContext(), ProfileActivity.class);
                }

                startActivity(intent);
                break;

            case R.id.insert_booking:
                insertBookingWS(
                        this.client_id,
                        this.room_id,
                        this.guests,
                        this.check_in,
                        this.check_out,
                        this.room_description
                );
                break;

        }


    }

    public void goBack(View view){
        onBackPressed();
    }
}