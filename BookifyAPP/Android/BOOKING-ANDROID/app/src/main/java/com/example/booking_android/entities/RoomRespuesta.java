package com.example.booking_android.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RoomRespuesta {

    @SerializedName("data")
    private ArrayList<Room> data;


    public ArrayList<Room> getData(){
        return data;
    }

    public void setData(ArrayList<Room> data){
        this.data = data;
    }
}
