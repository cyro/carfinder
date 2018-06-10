package com.autoscout24.carfinder.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;

import com.autoscout24.carfinder.arch.data.VehicleRepository;

public class VehicleViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private VehicleRepository mRepository;

    public VehicleViewModelFactory(VehicleRepository repository) {
        this.mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new VehicleListViewModel(mRepository);
    }
}
