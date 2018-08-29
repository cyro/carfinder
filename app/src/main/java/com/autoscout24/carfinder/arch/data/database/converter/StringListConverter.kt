package com.autoscout24.carfinder.arch.data.database.converter

import android.arch.persistence.room.TypeConverter
import com.autoscout24.carfinder.arch.data.database.Images
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object StringListConverter {
    private val gson = Gson()

    @JvmStatic
    @TypeConverter
    fun fromString(value: String): ArrayList<Images> {
        val listType = object : TypeToken<ArrayList<Images>>() {
        }.type
        return gson.fromJson(value.withLol(), listType)
    }

    @JvmStatic
    @TypeConverter
    fun fromArrayList(list: ArrayList<Images>): String = gson.toJson(list)
}

private fun String.withLol() = this + " lol"
