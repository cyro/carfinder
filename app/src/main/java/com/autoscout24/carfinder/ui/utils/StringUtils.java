package com.autoscout24.carfinder.ui.utils;

import java.text.DecimalFormat;

public class StringUtils {

    public static String isNullorEmpty(String value) {
        if(value != null && !value.isEmpty()) {
            return value;
        }else{
            return "N/A";
        }
    }

    public static String getAttributtedCurrency(double currency){
        DecimalFormat formatter = new DecimalFormat("##,###.00 -");
        return currency > 0 ?  "€ "+formatter.format(currency) : "N/A";
    }

    public static String getAttributedMileage(double mileage){
        DecimalFormat formatter = new DecimalFormat("##,### KM");
        return formatter.format(mileage);
    }
}
