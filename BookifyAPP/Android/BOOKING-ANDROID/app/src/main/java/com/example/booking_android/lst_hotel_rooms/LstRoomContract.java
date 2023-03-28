package com.example.booking_android.lst_hotel_rooms;



import com.example.booking_android.entities.Room;

import java.util.ArrayList;

public interface LstRoomContract {
    public interface View{
        void successLstRoom(ArrayList<Room> lstRoom);
        void failureLstRoom(String err);
    }

    public interface Presenter{
        // CASO DE USO
        void lstRoom(Room room);
    }

    public interface Model{
        interface OnLstRoomListener{
            void onSuccess(ArrayList<Room> lstRoom);
            void onFailure(String error);
        }
        void lstRoomWS(Room room,
                       OnLstRoomListener onLstRoomListener );

    }
}
