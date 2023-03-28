package com.example.booking_android.lst_hotel_rooms.model;


import android.content.SharedPreferences;
import android.os.Debug;
import android.util.Log;
import android.widget.TextView;

import com.example.booking_android.R;
import com.example.booking_android.entities.Room;
import com.example.booking_android.entities.RoomRespuesta;
import com.example.booking_android.lst_hotel_rooms.LstRoomContract;

import com.example.booking_android.utils.ApiClient;
import com.example.booking_android.utils.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LstRoomModel implements LstRoomContract.Model {

    @Override
    public void lstRoomWS(Room room, OnLstRoomListener onLstRoomListener) {

        // Sacamos los datos guardados en preferences para la búsqueda de las habitaciones disponibles




        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<RoomRespuesta> call =  apiService.getHotelRooms(room.getHotel_id(),room.getSelected_check_in(),room.getSelected_check_out(),room.getSelected_guests());
        call.enqueue(new Callback<RoomRespuesta>() {
            @Override
            public void onResponse(Call<RoomRespuesta> call, Response<RoomRespuesta> response) {
                ArrayList<Room> lstRoom = response.body().getData();
                onLstRoomListener.onSuccess(lstRoom);
            }

            @Override
            public void onFailure(Call<RoomRespuesta> call, Throwable t) {
                onLstRoomListener.onFailure("WS  :Error de consumo de hoteles. Error : " + t);
            }
        });
    }
}
