package com.example.booking_android.entities;

import com.google.gson.annotations.SerializedName;

public class Room {

    @SerializedName("room_id")
    private String room_id;
    @SerializedName("price")
    private String price;
    @SerializedName("hotel_id")
    private String hotel_id;
    @SerializedName("people")
    private String people;
    @SerializedName("room_number")
    private String room_number;
    @SerializedName("description")
    private String description;
    @SerializedName("photo")
    private String photo;


    private String selected_check_in;
    private String selected_check_out;
    private String selected_guests;


    public String getSelected_check_in() {
        return selected_check_in;
    }

    public void setSelected_check_in(String selected_check_in) {
        this.selected_check_in = selected_check_in;
    }

    public String getSelected_check_out() {
        return selected_check_out;
    }

    public void setSelected_check_out(String selected_check_out) {
        this.selected_check_out = selected_check_out;
    }

    public String getSelected_guests() {
        return selected_guests;
    }

    public void setSelected_guests(String selected_guests) {
        this.selected_guests = selected_guests;
    }

    public Room(String hotel_id , String selected_check_in, String selected_check_out, String selected_guests) {
        this.hotel_id = hotel_id;
        this.selected_check_in = selected_check_in;
        this.selected_check_out = selected_check_out;
        this.selected_guests = selected_guests;
    }

    public Room(){

    }


    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
