package com.autoscout24.carfinder.arch.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import com.autoscout24.carfinder.arch.data.database.converter.StringListConverter;

@Database(entities = {VehicleEntry.class}, version = 1)
@TypeConverters({StringListConverter.class})
public abstract class VehicleDatabase extends RoomDatabase {

    private static final String LOG_TAG = VehicleDatabase.class.getSimpleName();

    private static final String DATABASE_NAME = "vehicles";

    public abstract VehicleDao vehicleDao();

    //For Singleton instantiation to ensure thread safety
    private static final Object LOCK = new Object();
    private static volatile  VehicleDatabase sInstance;

    public static VehicleDatabase getInstance(Context context) {
        if(sInstance == null) {
            synchronized (LOCK) {
                if(sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            VehicleDatabase.class, VehicleDatabase.DATABASE_NAME).build();
                    Log.d(LOG_TAG,"Created a new database");
                }
            }
        }
        return sInstance;
    }
}
