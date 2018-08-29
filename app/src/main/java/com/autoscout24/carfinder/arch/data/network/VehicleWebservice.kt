package com.autoscout24.carfinder.arch.data.network

import android.view.View
import com.autoscout24.carfinder.arch.data.database.VehicleEntry
import com.google.gson.annotations.Until
import retrofit2.Call
import retrofit2.http.GET

interface VehicleWebservice {
    @GET(" ")
    fun getAllVehicles(): Call<Array<VehicleEntry>>
}
