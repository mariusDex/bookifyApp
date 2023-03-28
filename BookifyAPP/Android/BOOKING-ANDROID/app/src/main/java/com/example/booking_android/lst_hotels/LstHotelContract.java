package com.example.booking_android.lst_hotels;

import com.example.booking_android.entities.Hotel;
import com.example.booking_android.entities.Parameters;

import java.util.ArrayList;

public interface LstHotelContract {
    public interface View{
        void successLstHotel(ArrayList<Hotel> lstHotel);
        void failureLstHotel(String err);
    }

    public interface Presenter{
        // CASO DE USO
        void lstHotel(Parameters parameters);
    }

    public interface Model{
        interface OnLstHotelListener{
            void onSuccess(ArrayList<Hotel> lstHotel);
            void onFailure(String error);
        }
        void lstHotelWS(Parameters parameters,
                        OnLstHotelListener onLstHotelListener );

    }
}
