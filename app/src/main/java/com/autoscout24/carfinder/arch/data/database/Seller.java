package com.autoscout24.carfinder.arch.data.database;

public class Seller {

    private String phone;

    private String type;

    private String city;

    public Seller(String phone, String type, String city) {
        this.phone = phone;
        this.type = type;
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public String getType() {
        return type;
    }

    public String getCity() {
        return city;
    }

}
