package com.example.booking_android.entities;

public class Parameters {

    private String selected_check_in;
    private String selected_check_out;
    private String selected_country;
    private String selected_city;
    private String selected_guests;
    private String top_hotels;


    public Parameters(String selected_check_in, String selected_check_out, String selected_country, String selected_city, String selected_guests, String top_hotels) {
        this.selected_check_in = selected_check_in;
        this.selected_check_out = selected_check_out;
        this.selected_country = selected_country;
        this.selected_city = selected_city;
        this.selected_guests = selected_guests;
        this.top_hotels = top_hotels;
    }

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

    public String getSelected_country() {
        return selected_country;
    }

    public void setSelected_country(String selected_country) {
        this.selected_country = selected_country;
    }

    public String getSelected_city() {
        return selected_city;
    }

    public void setSelected_city(String selected_city) {
        this.selected_city = selected_city;
    }

    public String getSelected_guests() {
        return selected_guests;
    }

    public void setSelected_guests(String selected_guests) {
        this.selected_guests = selected_guests;
    }

    public String getTop_hotels() {
        return top_hotels;
    }

    public void setTop_hotels(String top_hotels) {
        this.top_hotels = top_hotels;
    }
}
