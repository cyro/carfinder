package com.autoscout24.carfinder.arch.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface VehicleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(VehicleEntry... vehicle);

    @Query("SELECT * FROM vehicles")
    LiveData<List<VehicleEntry>> getAllVehicles();

    @Query("SELECT COUNT(id) FROM vehicles")
    int countAllVehicles();

}
