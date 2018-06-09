package com.autoscout24.carfinder.arch.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;

@Entity(tableName = "vehicles")
public class VehicleEntry {
    @PrimaryKey
    @NonNull
    private int id;

    private String make;
    private String model;
    private String fuel;
    private double price;
    private double mileage;
    private String modelline;
    private String firstRegistration;
    private String color;
    private String description;
    private ArrayList<String> images;

    @Embedded
    private Seller seller;

    //Assumption made here that id is unique and is returned from webservice, otherwise we could create a seperate constructor service call and autogenerate Id = true
    public VehicleEntry(@NonNull int id, String make, String model, String fuel, double price, double mileage, String modelline, String firstRegistration, String color, String description, ArrayList<String> images, Seller seller) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.fuel = fuel;
        this.price = price;
        this.mileage = mileage;
        this.modelline = modelline;
        this.firstRegistration = firstRegistration;
        this.color = color;
        this.description = description;
        this.images = images;
        this.seller = seller;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getFuel() {
        return fuel;
    }

    public double getPrice() {
        return price;
    }

    public double getMileage() {
        return mileage;
    }

    public String getModelline() {
        return modelline;
    }

    public String getFirstRegistration() {
        return firstRegistration;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public Seller getSeller() {
        return seller;
    }
}

