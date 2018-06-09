package com.autoscout24.carfinder.arch.data.network;

import com.autoscout24.carfinder.arch.data.database.VehicleEntry;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VehicleWebservice {
    @GET(" ")
    Call<VehicleEntry[]> getAllVehicles();

}
