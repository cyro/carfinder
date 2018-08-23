package com.autoscout24.carfinder.ui.utils

import java.text.DecimalFormat

object StringUtils {
    @JvmStatic
    fun isNullorEmpty(value: String): String {
        var result = ""

        when((value != null && value.isNotBlank())){
            true -> result = value
            false-> result = "N/A"
        }
        return result
    }
    @JvmStatic
    fun getAttributtedCurrency(currency : Double) : String {
        val formatter = DecimalFormat("##,###.00 -")
        return if(currency > 0)  "€ "+formatter.format(currency) else "N/A"
    }
    @JvmStatic
    fun getAttributedMileage(mileage : Double) : String{
        val formatter = DecimalFormat("##,### KM")
        return formatter.format(mileage)
    }

}

