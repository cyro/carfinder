package com.autoscout24.carfinder.ui

import com.autoscout24.carfinder.arch.data.database.VehicleEntry

class VehicleAdvertisementWrapper {

    var vehicleEntry: VehicleEntry? = null
    //Could change this later to fetch custom Advertisements
    var advertisement: String? = null

    fun isVehicle() = vehicleEntry != null
    fun isAdvertisement() = advertisement != null

    override fun toString(): String {
        return if (vehicleEntry != null) "Vehicle" else "Advertisement"
    }

    constructor(vehicleEntry: VehicleEntry) {
        this.vehicleEntry = vehicleEntry
    }

    constructor(advertisement: String) {
        this.advertisement = advertisement
    }
}