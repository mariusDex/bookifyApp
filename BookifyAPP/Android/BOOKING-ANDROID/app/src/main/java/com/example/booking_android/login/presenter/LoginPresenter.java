package com.example.booking_android.login.presenter;


import android.util.Log;

import com.example.booking_android.entities.User;
import com.example.booking_android.login.LoginContract;
import com.example.booking_android.login.model.LoginModel;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginModel loginModel;
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view){
        this.view = view;
        this.loginModel = new LoginModel();
    }

    @Override
    public void user(User user) {
        loginModel.loginWS(user, new LoginContract.Model.OnLoginListener() {
            @Override
            public void onSuccess(User user) {
                if(user != null){
                    Log.e("EMAIL USER","Email del usuario " + user.getEmail());
                    view.successLogin(user);
                }else{
                    view.failureLogin("Email o contraseña incorrectos");
                }
            }

            @Override
            public void onFailure(String error) {
                view.failureLogin("Error al buscar clientes. Error : " + error);
            }
        }) ;
    }
}
