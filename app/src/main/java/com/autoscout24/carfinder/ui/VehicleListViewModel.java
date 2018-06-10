package com.autoscout24.carfinder.ui;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.autoscout24.carfinder.arch.data.VehicleRepository;
import com.autoscout24.carfinder.arch.data.database.VehicleEntry;

public class VehicleListViewModel extends ViewModel {

    private final LiveData<VehicleEntry> mVehicle;
    private final VehicleRepository mRepository;

    public VehicleListViewModel(VehicleRepository repository) {
        mRepository = repository;
        mVehicle = mRepository.getAllVehicles();
    }

    public LiveData<VehicleEntry> getVehicle() {
        return mVehicle;
    }
    
}
