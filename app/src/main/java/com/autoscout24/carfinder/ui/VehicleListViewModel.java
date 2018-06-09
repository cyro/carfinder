package com.autoscout24.carfinder.ui;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.autoscout24.carfinder.arch.data.database.VehicleEntry;

public class VehicleListViewModel extends ViewModel {

    private MutableLiveData<VehicleEntry> mVehicle;

    public VehicleListViewModel() {
        mVehicle = new MutableLiveData<>();
    }

    public MutableLiveData<VehicleEntry> getVehicle() {
        return mVehicle;
    }

    public void setVehicle(VehicleEntry vehicle) {
        mVehicle.postValue(vehicle);
    }
}
