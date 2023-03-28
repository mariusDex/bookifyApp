package com.example.booking_android.lst_hotel_rooms.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_android.ProfileActivity;
import com.example.booking_android.R;
import com.example.booking_android.entities.Hotel;
import com.example.booking_android.entities.Room;
import com.example.booking_android.login.view.LoginActivity;
import com.example.booking_android.lst_hotel_rooms.LstRoomContract;
import com.example.booking_android.lst_hotel_rooms.presenter.LstRoomPresenter;


import java.util.ArrayList;

public class LstRoomActivity extends AppCompatActivity implements LstRoomContract.View {
    private RecyclerView recyclerView;
    private LstRoomAdapter roomAdapter;
    private LstRoomPresenter roomPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        initComponents();
        initPresenter();
        initData();
    }
    public void initComponents(){
        recyclerView = (RecyclerView)  findViewById(R.id.recyclerRooms);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        SharedPreferences prefs = getSharedPreferences("client_info", MODE_PRIVATE);
        String name = prefs.getString("client_name","");
        //String name = getIntent().getBundleExtra("client_info").getString("client_name");
        TextView user_name = findViewById(R.id.nombre_user_hoteles);
        user_name.setText(name);
    }
    public void initPresenter(){
        roomPresenter = new LstRoomPresenter(this);
    }
    public void initData(){

        SharedPreferences prefs = getSharedPreferences("client_info", MODE_PRIVATE);
        String check_in = prefs.getString("check_in","");
        String check_out = prefs.getString("check_out","");
        String guests = prefs.getString("guests","");
        Intent intent = getIntent();
        Room roomBean = new Room(
                intent.getStringExtra("hotel_id"),
                check_in,
                check_out,
                guests
        );
        roomPresenter.lstRoom(roomBean);
    }
    @Override
    public void successLstRoom(ArrayList<Room> lstRoom) {

        LstRoomAdapter adapter = new LstRoomAdapter(getBaseContext(),lstRoom);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void failureLstRoom(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
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