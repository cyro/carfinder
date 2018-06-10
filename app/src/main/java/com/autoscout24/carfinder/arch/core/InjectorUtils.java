package com.autoscout24.carfinder.arch.core;

import android.content.Context;

import com.autoscout24.carfinder.arch.data.VehicleRepository;
import com.autoscout24.carfinder.arch.data.database.VehicleDatabase;
import com.autoscout24.carfinder.arch.data.network.VehicleNetworkDataSource;
import com.autoscout24.carfinder.ui.VehicleViewModelFactory;

public class InjectorUtils {

    public static VehicleRepository provideRepository(Context context) {
        VehicleDatabase database = VehicleDatabase.getInstance(context);
        AppExecutors executors = AppExecutors.getInstance();
        VehicleNetworkDataSource networkDataSource = VehicleNetworkDataSource.getInstance(context,
                executors);
        return VehicleRepository.getInstance(database.vehicleDao(), networkDataSource, executors);

    }

    public static VehicleNetworkDataSource provideNetworkDataSource(Context context) {
        AppExecutors executors = AppExecutors.getInstance();
        return VehicleNetworkDataSource.getInstance(context,executors);
    }

    public static VehicleViewModelFactory provideVehicleViewModelFactory(Context context) {
        VehicleRepository repository = provideRepository(context);
        return new VehicleViewModelFactory(repository);
    }
}
