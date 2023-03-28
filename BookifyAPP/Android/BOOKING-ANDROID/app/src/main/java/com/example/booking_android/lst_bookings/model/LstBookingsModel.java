package com.example.booking_android.lst_bookings.model;


import com.example.booking_android.entities.Booking;
import com.example.booking_android.entities.BookingRespuesta;
import com.example.booking_android.entities.Room;
import com.example.booking_android.entities.RoomRespuesta;
import com.example.booking_android.lst_bookings.LstBookingsContract;
import com.example.booking_android.utils.ApiClient;
import com.example.booking_android.utils.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LstBookingsModel implements LstBookingsContract.Model {

    @Override
    public void lstBookingWS(String client_id, OnLstBookingListener onLstBookingListener) {

        // Sacamos los datos guardados en preferences para la búsqueda de las habitaciones disponibles




        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<BookingRespuesta> call =  apiService.getClientBookings(client_id);
        call.enqueue(new Callback<BookingRespuesta>() {
            @Override
            public void onResponse(Call<BookingRespuesta> call, Response<BookingRespuesta> response) {
                ArrayList<Booking> lstBooking = response.body().getData();
                onLstBookingListener.onSuccess(lstBooking);
            }

            @Override
            public void onFailure(Call<BookingRespuesta> call, Throwable t) {
                onLstBookingListener.onFailure("WS  :Error de consumo de hoteles. Error : " + t);
            }
        });
    }
}
