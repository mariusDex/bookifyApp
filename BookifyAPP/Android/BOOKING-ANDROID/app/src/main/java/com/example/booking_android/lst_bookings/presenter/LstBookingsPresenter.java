package com.example.booking_android.lst_bookings.presenter;


import com.example.booking_android.entities.Booking;
import com.example.booking_android.entities.Room;
import com.example.booking_android.lst_bookings.LstBookingsContract;
import com.example.booking_android.lst_bookings.model.LstBookingsModel;

import java.util.ArrayList;

public class LstBookingsPresenter implements LstBookingsContract.Presenter {
    private LstBookingsModel lstBookingModel;
    private LstBookingsContract.View view;

    public LstBookingsPresenter(LstBookingsContract.View view){
        this.view = view;
        this.lstBookingModel = new LstBookingsModel();
    }

    @Override
    public void lstBooking(String client_id) {
        lstBookingModel.lstBookingWS(client_id, new LstBookingsContract.Model.OnLstBookingListener() {
            @Override
            public void onSuccess(ArrayList<Booking> lstBooking) {
                if(lstBooking != null && lstBooking.size() >0 ){
                    view.successLstBooking(lstBooking);
                }else{
                    view.failureLstBooking("lstRoom on failure : Error en consumo de hoteles lstHotel on Success");
                }
            }

            @Override
            public void onFailure(String error) {
                view.failureLstBooking("lstHotelError en consumo de rooms. Error : " + error);
            }
        }) ;
    }
}
