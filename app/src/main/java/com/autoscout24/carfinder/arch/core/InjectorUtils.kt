package com.autoscout24.carfinder.arch.core

import android.content.Context
import com.autoscout24.carfinder.arch.data.VehicleRepository
import com.autoscout24.carfinder.arch.data.VehicleRepository.getInstance
import com.autoscout24.carfinder.arch.data.database.VehicleDatabase
import com.autoscout24.carfinder.arch.data.network.VehicleNetworkDataSource
import com.autoscout24.carfinder.ui.VehicleViewModelFactory
import java.util.concurrent.Executors

object InjectorUtils {
    private val executors by lazy {
        AppExecutors(Executors.newSingleThreadExecutor(),
                Executors.newFixedThreadPool(3),
                AppExecutors.MainThreadExecutor())
    }

    @JvmStatic
    fun provideRepository(context: Context): VehicleRepository {
        val database = VehicleDatabase.getInstance(context)
        val networkDataSource = VehicleNetworkDataSource.getInstance(context, executors)
        return getInstance(database.vehicleDao(), networkDataSource, executors)
    }

    @JvmStatic
    fun provideNetworkDataSource(context: Context): VehicleNetworkDataSource? {
        return VehicleNetworkDataSource.getInstance(context, executors)
    }

    @JvmStatic
    fun provideVehicleViewModelFactory(context: Context): VehicleViewModelFactory {
        val repository = provideRepository(context)
        return VehicleViewModelFactory(repository)
    }
}
