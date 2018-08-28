package com.autoscout24.carfinder.ui


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import com.autoscout24.carfinder.arch.data.VehicleRepository
import com.autoscout24.carfinder.arch.data.database.VehicleEntry

class VehicleListViewModel(private val mRepository: VehicleRepository) : ViewModel() {

    val vehicles: LiveData<List<VehicleEntry>>

    init {
        vehicles = mRepository.allVehicles
    }

}
