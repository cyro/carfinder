package com.autoscout24.carfinder.arch.data.database.converter

import android.arch.persistence.room.TypeConverter
import com.autoscout24.carfinder.arch.data.database.Images
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object StringListConverter {
    @JvmStatic
    @TypeConverter
    fun fromString(value: String): ArrayList<Images> {
        val listType = object : TypeToken<ArrayList<Images>>() {
        }.type
        return Gson().fromJson(value, listType)
    }

    @JvmStatic
    @TypeConverter
    fun fromArrayList(list: ArrayList<Images>): String {
        val json = Gson().toJson(list)
        return json
    }


}