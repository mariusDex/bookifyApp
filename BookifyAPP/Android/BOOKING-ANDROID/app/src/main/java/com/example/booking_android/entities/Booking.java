package com.example.booking_android.entities;

import com.google.gson.annotations.SerializedName;

public class Booking {


    @SerializedName("booking_id")
    private String booking_id;
    @SerializedName("name")
    private String name;
    @SerializedName("guests")
    private String guests;
    @SerializedName("date")
    private String date;
    @SerializedName("entry_date")
    private String entry_date;
    @SerializedName("exit_date")
    private String exit_date;


    public Booking(String booking_id, String name, String guests, String date, String entry_date, String exit_date) {
        this.booking_id = booking_id;
        this.name = name;
        this.guests = guests;
        this.date = date;
        this.entry_date = entry_date;
        this.exit_date = exit_date;
    }

    public Booking(){

    }


    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuests() {
        return guests;
    }

    public void setGuests(String guests) {
        this.guests = guests;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(String entry_date) {
        this.entry_date = entry_date;
    }

    public String getExit_date() {
        return exit_date;
    }

    public void setExit_date(String exit_date) {
        this.exit_date = exit_date;
    }
}
