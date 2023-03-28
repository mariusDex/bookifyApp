package com.example.booking_android.login;



import com.example.booking_android.entities.Room;
import com.example.booking_android.entities.User;

import java.util.ArrayList;

public interface LoginContract {
    public interface View{
        void successLogin(User user);
        void failureLogin(String err);
    }

    public interface Presenter{
        // CASO DE USO
        void user(User user);
    }

    public interface Model{
        interface OnLoginListener{
            void onSuccess(User user);
            void onFailure(String error);
        }
        void loginWS(User user,
                       OnLoginListener onLoginListener );

    }
}
