package com.example.booking_android.lst_hotels.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_android.FailureParametersActivity;
import com.example.booking_android.ParametersActivity;
import com.example.booking_android.ProfileActivity;
import com.example.booking_android.R;
import com.example.booking_android.entities.Hotel;
import com.example.booking_android.entities.Parameters;
import com.example.booking_android.entities.User;
import com.example.booking_android.login.view.LoginActivity;
import com.example.booking_android.lst_hotels.LstHotelContract;
import com.example.booking_android.lst_hotels.presenter.LstHotelPresenter;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LstHotelActivity extends AppCompatActivity implements LstHotelContract.View {
    private RecyclerView recyclerView;
    private LstHotelAdapter hotelAdapter;
    private LstHotelPresenter hotelPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_hotels);

        initComponents();
        initPresenter();
        initData();
    }
    public void initComponents(){
        recyclerView = (RecyclerView)  findViewById(R.id.recyclerHoteles);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        SharedPreferences prefs = getSharedPreferences("client_info", MODE_PRIVATE);
        String name = prefs.getString("client_name","");
        //String name = getIntent().getBundleExtra("client_info").getString("client_name");
        TextView user_name = findViewById(R.id.nombre_user_hoteles);
        user_name.setText(name);
    }
    public void initPresenter(){
        hotelPresenter = new LstHotelPresenter(this);
    }
    public void initData(){
        Parameters paramaters = new Parameters(
                getIntent().getStringExtra("selected_check_in").toString(),
                getIntent().getStringExtra("selected_check_out").toString(),
                getIntent().getStringExtra("selected_country").toString(),
                getIntent().getStringExtra("selected_city").toString(),
                getIntent().getStringExtra("selected_guests").toString(),
                getIntent().getStringExtra("top_hotels").toString()
                );
        hotelPresenter.lstHotel(paramaters); //SELECT * FROM HOTEL
    }
    @Override
    public void successLstHotel(ArrayList<Hotel> lstHotel) {

        LstHotelAdapter adapter = new LstHotelAdapter(getBaseContext(),lstHotel);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void failureLstHotel(String err) {
        Intent intent = new Intent(getBaseContext(), FailureParametersActivity.class);
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