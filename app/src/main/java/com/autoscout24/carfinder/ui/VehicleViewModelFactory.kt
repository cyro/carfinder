package com.autoscout24.carfinder.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders

import com.autoscout24.carfinder.arch.data.VehicleRepository

class VehicleViewModelFactory(private val mRepository: VehicleRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VehicleListViewModel(mRepository) as T
    }
}
