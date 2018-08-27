package com.autoscout24.carfinder.arch.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface VehicleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun bulkInsert(vararg vehicles: VehicleEntry)

    @Query("SELECT * FROM vehicles")
    fun getAllVehicles(): LiveData<List<VehicleEntry>>

    @Query("SELECT COUNT(id) FROM vehicles")
    fun countAllVehicles(): Int
}