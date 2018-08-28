package com.autoscout24.carfinder.arch.data.network

import android.app.IntentService
import android.content.Intent
import com.autoscout24.carfinder.arch.core.InjectorUtils

class VehicleSyncIntentService : IntentService("VehicleSyncIntentService") {
    companion object {
        val LOG_TAG = VehicleSyncIntentService::class.java.simpleName
    }

    override fun onHandleIntent(intent: Intent?) {
        val networkDataSource = InjectorUtils.provideNetworkDataSource(applicationContext)
        networkDataSource?.fetchVehicles()
    }
}