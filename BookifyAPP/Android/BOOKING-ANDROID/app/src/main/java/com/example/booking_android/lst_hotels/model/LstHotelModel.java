package com.example.booking_android.lst_hotels.model;

import com.example.booking_android.entities.Hotel;
import com.example.booking_android.entities.HotelRespuesta;
import com.example.booking_android.entities.Parameters;
import com.example.booking_android.lst_hotels.LstHotelContract;
import com.example.booking_android.utils.ApiClient;
import com.example.booking_android.utils.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LstHotelModel implements LstHotelContract.Model {
    @Override
    public void lstHotelWS(Parameters parameters, OnLstHotelListener onLstHotelListener) {
        /* onLstPokemonListener.onSuccess();
        onLstPokemonListener.onFailure(); */

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        /*

         */
        Call<HotelRespuesta> call =  apiService.getHotels(
                parameters.getSelected_check_in(),
                parameters.getSelected_check_out(),
                parameters.getSelected_country(),
                parameters.getSelected_city(),
                parameters.getSelected_guests(),
                parameters.getTop_hotels()
        );
        //Call<HotelRespuesta> call =  apiService.getHotelsFull();
        call.enqueue(new Callback<HotelRespuesta>() {
            @Override
            public void onResponse(Call<HotelRespuesta> call, Response<HotelRespuesta> response) {
                ArrayList<Hotel> lstHoteles = response.body().getData();
                onLstHotelListener.onSuccess(lstHoteles);
            }

            @Override
            public void onFailure(Call<HotelRespuesta> call, Throwable t) {
                onLstHotelListener.onFailure("WS  :Error de consumo de hoteles. Error : " + t);
            }
        });
    }
}
