package com.example.booking_android.login.model;


import com.example.booking_android.entities.Room;
import com.example.booking_android.entities.RoomRespuesta;
import com.example.booking_android.entities.User;
import com.example.booking_android.entities.UserRespuesta;
import com.example.booking_android.login.LoginContract;
import com.example.booking_android.utils.ApiClient;
import com.example.booking_android.utils.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel implements LoginContract.Model {

    @Override
    public void loginWS(User user, OnLoginListener onLoginListener) {


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserRespuesta> call =  apiService.getUser(user.getEmail(),user.getPassword());
        call.enqueue(new Callback<UserRespuesta>() {
            @Override
            public void onResponse(Call<UserRespuesta> call, Response<UserRespuesta> response) {
                User user = new User();
                ArrayList<User> userArray = response.body().getData();
                if(!userArray.isEmpty()){
                    user = userArray.get(0);
                }else{
                    user = null;
                }

                onLoginListener.onSuccess(user);
            }

            @Override
            public void onFailure(Call<UserRespuesta> call, Throwable t) {
                onLoginListener.onFailure("WS  :Error de consumo de hoteles. Error : " + t);
            }
        });
    }
}
