package com.example.booking_android.lst_bookings;



import com.example.booking_android.entities.Booking;
import com.example.booking_android.entities.Room;

import java.util.ArrayList;

public interface LstBookingsContract {
    public interface View{
        void successLstBooking(ArrayList<Booking> lstBooking);
        void failureLstBooking(String err);
    }

    public interface Presenter{
        // CASO DE USO
        void lstBooking(String client_id);
    }

    public interface Model{
        interface OnLstBookingListener{
            void onSuccess(ArrayList<Booking> lstBooking);
            void onFailure(String error);
        }
        void lstBookingWS(String client_id,
                       OnLstBookingListener onLstBookingListener );

    }
}
