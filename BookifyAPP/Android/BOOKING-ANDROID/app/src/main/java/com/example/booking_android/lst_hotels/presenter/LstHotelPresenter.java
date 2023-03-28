package com.example.booking_android.lst_hotels.presenter;

import com.example.booking_android.entities.Hotel;
import com.example.booking_android.entities.Parameters;
import com.example.booking_android.lst_hotels.LstHotelContract;
import com.example.booking_android.lst_hotels.model.LstHotelModel;

import java.util.ArrayList;

public class LstHotelPresenter implements LstHotelContract.Presenter {
    private LstHotelModel lstHotelModel;
    private LstHotelContract.View view;

    public LstHotelPresenter(LstHotelContract.View view){
        this.view = view;
        this.lstHotelModel = new LstHotelModel();
    }

    @Override
    public void lstHotel(Parameters parameters) {
        lstHotelModel.lstHotelWS(parameters, new LstHotelContract.Model.OnLstHotelListener() {
            @Override
            public void onSuccess(ArrayList<Hotel> lstHotel) {
                if(lstHotel != null && lstHotel.size() >0 ){
                    view.successLstHotel(lstHotel);
                }else{
                    view.failureLstHotel("lstHotel on Success : Error en consumo de hoteles lstHotel on Success");
                }
            }

            @Override
            public void onFailure(String error) {
                view.failureLstHotel("lstHotelError en consumo de hoteles. Error : " + error);
            }
        }) ;
    }
}
