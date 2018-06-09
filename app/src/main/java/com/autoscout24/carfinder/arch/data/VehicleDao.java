package com.autoscout24.carfinder.arch.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface VehicleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(VehicleEntry... vehicle);

    @Query("SELECT * FROM vehicles")
    VehicleEntry getAllVehicles();
}
