package com.autoscout24.carfinder.ui;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.autoscout24.carfinder.arch.data.VehicleRepository;
import com.autoscout24.carfinder.arch.data.database.VehicleEntry;

import java.util.List;

public class VehicleListViewModel extends ViewModel {

    private final LiveData<List<VehicleEntry>> mVehicles;
    private final VehicleRepository mRepository;

    public VehicleListViewModel(VehicleRepository repository) {
        mRepository = repository;
        mVehicles = mRepository.getAllVehicles();
    }

    public LiveData<List<VehicleEntry>> getVehicles() {
        return mVehicles;
    }
    
}
