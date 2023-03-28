package com.example.booking_android.lst_bookings.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_android.FailureGetBookingsActivity;
import com.example.booking_android.ParametersActivity;
import com.example.booking_android.ProfileActivity;
import com.example.booking_android.R;
import com.example.booking_android.entities.Booking;
import com.example.booking_android.entities.Room;
import com.example.booking_android.login.view.LoginActivity;
import com.example.booking_android.lst_bookings.LstBookingsContract;
import com.example.booking_android.lst_bookings.presenter.LstBookingsPresenter;

import java.util.ArrayList;

public class LstBookingsActivity extends AppCompatActivity implements LstBookingsContract.View {
    private RecyclerView recyclerView;
    private LstBookingsAdapter bookingAdapter;
    private LstBookingsPresenter bookingPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_bookings);

        initComponents();
        initPresenter();
        initData();
    }
    public void initComponents(){
        recyclerView = (RecyclerView)  findViewById(R.id.recycler_bookings);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        SharedPreferences prefs = getSharedPreferences("client_info", MODE_PRIVATE);
        String name = prefs.getString("client_name","");
        //String name = getIntent().getBundleExtra("client_info").getString("client_name");
        TextView user_name = findViewById(R.id.nombre_user_hoteles);
        user_name.setText(name);
    }
    public void initPresenter(){
        bookingPresenter = new LstBookingsPresenter(this);
    }
    public void initData(){

        SharedPreferences prefs = getSharedPreferences("client_info", MODE_PRIVATE);

        bookingPresenter.lstBooking(prefs.getString("client_id","1"));
    }
    @Override
    public void successLstBooking(ArrayList<Booking> lstBooking) {

        LstBookingsAdapter adapter = new LstBookingsAdapter(getBaseContext(),lstBooking);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void failureLstBooking(String err) {
        Intent intent = new Intent(getBaseContext(), FailureGetBookingsActivity.class);
        startActivity(intent);
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
                break;

        }

        startActivity(intent);
    }

    public void goBack(View view){
        onBackPressed();
    }
}