package com.autoscout24.carfinder.arch.data.database;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.text.DecimalFormat;
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
    private ArrayList<Images> images = new ArrayList<>();

    @Embedded
    private Seller seller;

    //Assumption made here that id is unique and is returned from webservice, otherwise we could create a seperate constructor service call and autogenerate Id = true
    public VehicleEntry(@NonNull int id, String make, String model, String fuel, double price, double mileage, String modelline, String firstRegistration, String color, String description, ArrayList<Images> images, Seller seller) {
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

    public ArrayList<Images> getImages() {
        return images;
    }

    public Seller getSeller() {
        return seller;
    }

    public VehicleEntry() {
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public void setModelline(String modelline) {
        this.modelline = modelline;
    }

    public void setFirstRegistration(String firstRegistration) {
        this.firstRegistration = firstRegistration;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImages(ArrayList<Images> images) {
        this.images = images;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    //Convience string methods
    @Ignore
    @Override
    public String toString(){
        return "Vehicle [id = "+id+", model = "+model+", mileage = "+mileage+", price = "+price+", description = "+description+" , fuel = "+fuel+", firstRegistration = "+firstRegistration+", make = "+make+"]";
    }

    @Ignore
    public String getMakeAndModel() {
        if(getMake()!= null && getModel() != null){
            return getMake() + " "+ getModel();
        }else {
            return "N/A";
        }
    }


}

