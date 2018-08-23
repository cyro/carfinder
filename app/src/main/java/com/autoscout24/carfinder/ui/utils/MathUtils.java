package com.autoscout24.carfinder.ui.utils;

public class MathUtils {

    public static int mod(int x, int y) {
        int result = x % y;
        return result < 0? result + y : result;
    }

    public static int arraySizeRequiredForAdvertisements(int arraySize, int positionOfElement) {
        if(positionOfElement > 0) {
            return (int)arraySize+mod(arraySize,positionOfElement);
        }else return 0;


    }
}
