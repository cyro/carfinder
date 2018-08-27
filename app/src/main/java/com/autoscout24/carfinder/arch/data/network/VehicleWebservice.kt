package com.autoscout24.carfinder.arch.data.network

import com.autoscout24.carfinder.arch.data.database.VehicleEntry
import retrofit2.Call
import retrofit2.http.GET

interface VehicleWebservice {
    @GET(" ")
    fun getAllVehicles(): Call<Array<VehicleEntry>>
}