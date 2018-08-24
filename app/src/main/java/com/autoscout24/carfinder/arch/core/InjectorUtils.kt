package com.autoscout24.carfinder.arch.core

import android.content.Context
import com.autoscout24.carfinder.arch.data.VehicleRepository
import com.autoscout24.carfinder.arch.data.database.VehicleDatabase
import com.autoscout24.carfinder.arch.data.network.VehicleNetworkDataSource
import com.autoscout24.carfinder.ui.VehicleViewModelFactory

object InjectorUtils {
    @JvmStatic
    fun provideRepository(context: Context): VehicleRepository {
        val database = VehicleDatabase.getInstance(context)
        val executors = AppExecutors.getInstance()
        val networkDataSource = VehicleNetworkDataSource.getInstance(context, executors)
        return VehicleRepository.getInstance(database.vehicleDao(), networkDataSource, executors)
    }

    @JvmStatic
    fun provideNetworkDataSource(context: Context): VehicleNetworkDataSource {
        val executors = AppExecutors.getInstance()
        return VehicleNetworkDataSource.getInstance(context, executors)
    }

    @JvmStatic
    fun provideVehicleViewModelFactory(context: Context): VehicleViewModelFactory {
        val repository = provideRepository(context)
        return VehicleViewModelFactory(repository)
    }
}