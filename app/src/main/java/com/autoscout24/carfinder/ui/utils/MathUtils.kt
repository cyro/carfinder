package com.autoscout24.carfinder.ui.utils

object MathUtils {

    fun mod(x: Int, y: Int): Int {
        val result = x % y
        return if (result < 0) result + y else result
    }

    fun arraySizeRequiredForAdvertisements(arraySize: Int, positionOfElement: Int): Int {
        return if (positionOfElement > 0) {
            arraySize + mod(arraySize, positionOfElement)
        } else
            0


    }
}
