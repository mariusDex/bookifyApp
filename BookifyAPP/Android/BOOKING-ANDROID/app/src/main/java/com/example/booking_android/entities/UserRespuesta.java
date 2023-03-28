package com.example.booking_android.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserRespuesta {

    @SerializedName("data")
    private ArrayList<User> data;


    public ArrayList<User> getData(){
        return data;

    }

    public void setData(ArrayList<User> data){
        this.data = data;
    }

}
