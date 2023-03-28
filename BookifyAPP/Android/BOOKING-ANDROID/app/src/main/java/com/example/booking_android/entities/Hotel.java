package com.example.booking_android.entities;

import com.google.gson.annotations.SerializedName;

public class Hotel{


    @SerializedName("hotel_id")
    private String hotel_id;
    @SerializedName("name")
    private String name;
    @SerializedName("address")
    private String address;
    @SerializedName("city")
    private String city;
    @SerializedName("country")
    private String country;
    @SerializedName("description")
    private String description;
    @SerializedName("score")
    private String score;
    @SerializedName("check_in")
    private String check_in;
    @SerializedName("check_out")
    private String check_out;
    @SerializedName("people_voted")
    private String people_voted;
    @SerializedName("outstanding")
    private String outstanding;
    @SerializedName("photo")
    private String photo;

    public Hotel(){

    }
    public Hotel(String hotel_id, String name, String address, String city, String country, String description, String score, String check_in, String check_out, String people_voted, String outstanding, String photo) {
        this.hotel_id = hotel_id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.description = description;
        this.score = score;
        this.check_in = check_in;
        this.check_out = check_out;
        this.people_voted = people_voted;
        this.outstanding = outstanding;
        this.photo = photo;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    public String getPeople_voted() {
        return people_voted;
    }

    public void setPeople_voted(String people_voted) {
        this.people_voted = people_voted;
    }

    public String getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(String outstanding) {
        this.outstanding = outstanding;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    @Override
    public String toString() {
        return "Hotel{" +
                "hotel_id=" + hotel_id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", description='" + description + '\'' +
                ", score='" + score + '\'' +
                ", check_in='" + check_in + '\'' +
                ", check_out='" + check_out + '\'' +
                ", people_voted='" + people_voted + '\'' +
                ", outstanding='" + outstanding + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
