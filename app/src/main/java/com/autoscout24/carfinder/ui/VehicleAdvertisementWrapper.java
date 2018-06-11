package com.autoscout24.carfinder.ui;

import com.autoscout24.carfinder.arch.data.database.VehicleEntry;

public class VehicleAdvertisementWrapper {

    private VehicleEntry mVehicleEntry;
    //Could change this later to fetch custom Advertisements
    private String mAdvertisement;

    public VehicleAdvertisementWrapper(VehicleEntry vehicleEntry) {
        this.mVehicleEntry = vehicleEntry;
    }

    public VehicleAdvertisementWrapper(String advertisement) {
        this.mAdvertisement = advertisement;
    }

    public VehicleEntry getVehicleEntry() {
        return mVehicleEntry;
    }

    public String getAdvertisement() {
        return mAdvertisement;
    }

    public boolean isVehicle() {
        return mVehicleEntry != null ? true : false;
    }

    public boolean isAdvertisement() {
        return mAdvertisement != null ? true : false;
    }

    @Override
    public String toString() {
        return mVehicleEntry != null ? "Vehicle" : "Advertisement";
    }
}
