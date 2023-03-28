package com.example.booking_android.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookingRespuesta {

    @SerializedName("data")
    private ArrayList<Booking> data;

    public ArrayList<Booking> getData(){
        return data;
    }

    public void setData(ArrayList<Booking> data){
        this.data = data;
    }
}
