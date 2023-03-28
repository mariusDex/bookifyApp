package com.example.booking_android.entities;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("client_id")
    private String client_id;
    @SerializedName("client_nif")
    private String client_nif;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String phone;
    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("birth_date")
    private String birth_date;
    @SerializedName("password")
    private String password;

    public User(){

    }
    public User(String client_nif, String email, String phone, String name, String surname, String birth_date, String password) {
        this.client_nif = client_nif;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.password = password;
    }

    public User(String email,String password){
        this.email = email;
        this.password = password;
    }


    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_nif() {
        return client_nif;
    }

    public void setClient_nif(String client_nif) {
        this.client_nif = client_nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
