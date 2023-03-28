package com.example.booking_android.lst_hotel_rooms.presenter;


import com.example.booking_android.entities.Room;
import com.example.booking_android.lst_hotel_rooms.LstRoomContract;
import com.example.booking_android.lst_hotel_rooms.model.LstRoomModel;


import java.util.ArrayList;

public class LstRoomPresenter implements LstRoomContract.Presenter {
    private LstRoomModel lstRoomModel;
    private LstRoomContract.View view;

    public LstRoomPresenter(LstRoomContract.View view){
        this.view = view;
        this.lstRoomModel = new LstRoomModel();
    }

    @Override
    public void lstRoom(Room room) {
        lstRoomModel.lstRoomWS(room, new LstRoomContract.Model.OnLstRoomListener() {
            @Override
            public void onSuccess(ArrayList<Room> lstRoom) {
                if(lstRoom != null && lstRoom.size() >0 ){
                    view.successLstRoom(lstRoom);
                }else{
                    view.failureLstRoom("lstRoom on failure : Error en consumo de hoteles lstHotel on Success");
                }
            }

            @Override
            public void onFailure(String error) {
                view.failureLstRoom("lstHotelError en consumo de rooms. Error : " + error);
            }
        }) ;
    }
}
