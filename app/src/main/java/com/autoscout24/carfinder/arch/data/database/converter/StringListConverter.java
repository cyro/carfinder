package com.autoscout24.carfinder.arch.data.database.converter;

import android.arch.persistence.room.TypeConverter;

import com.autoscout24.carfinder.arch.data.database.Images;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class StringListConverter {

    @TypeConverter
    public static ArrayList<Images> fromString(String value) {
        Type listType = new TypeToken<ArrayList<Images>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Images> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
