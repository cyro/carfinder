package com.autoscout24.carfinder.arch.data.network;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.autoscout24.carfinder.arch.core.InjectorUtils;

public class VehicleSyncIntentService extends IntentService {
    private static final String LOG_TAG = VehicleSyncIntentService.class.getSimpleName();

    public VehicleSyncIntentService() {
        super("VehicleSyncIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(LOG_TAG, "Vehicle intent service started");
        VehicleNetworkDataSource networkDataSource = InjectorUtils.provideNetworkDataSource(this.getApplicationContext());
        networkDataSource.fetchVehicles();

    }
}
