package com.autoscout24.carfinder.arch.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import android.util.Log

import com.autoscout24.carfinder.arch.data.database.converter.StringListConverter

@Database(entities = arrayOf(VehicleEntry::class), version = 1)
@TypeConverters(StringListConverter::class)
abstract class VehicleDatabase : RoomDatabase() {

    abstract fun vehicleDao(): VehicleDao?

    companion object {

        private val LOG_TAG = VehicleDatabase::class.java.simpleName

        private val DATABASE_NAME = "vehicles"

        fun getInstance(context: Context): VehicleDatabase =
                Room.databaseBuilder(context.applicationContext,
                        VehicleDatabase::class.java, VehicleDatabase.DATABASE_NAME)
                        .build()
                        .also {
                            Log.d(LOG_TAG, "Created a new database")
                        }

    }
}
