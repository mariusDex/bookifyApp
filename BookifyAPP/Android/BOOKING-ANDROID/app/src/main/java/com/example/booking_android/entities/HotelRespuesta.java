package com.example.booking_android.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HotelRespuesta{
    @SerializedName("data")
    private ArrayList<Hotel> data;

    public ArrayList<Hotel> getData(){
        return data;
    }

    public void setData(ArrayList<Hotel> data){
        this.data = data;
    }
}
