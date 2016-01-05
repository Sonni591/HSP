package de.oth.hsp.common.utils;

/**
 * This class provides some methods for converting © @author Sascha Schertler
 * 05.01.2016 2016 ArrayConverter.java
 */
public class ArrayConverter {

    /**
     * Converts a given one dimensional double array to a one dimensional float
     * array
     * 
     * @param array
     *            source double array
     * @return converted float array
     */
    public static float[] convertOneDimArray(double[] array) {

        float[] result = new float[array.length];

        for (int i = 0; i < array.length; i++) {
            result[i] = (float) array[i];
        }

        return result;
    }

    /**
     * Converts a given two dimensional double array to a two dimensional float
     * array
     * 
     * @param array
     *            source two dimensional double array
     * @return converted two dimen
     */

    public static float[][] convertTwoDimArray(double[][] array) {

        float[][] result = new float[array.length][array[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[0].length; col++) {
                result[row][col] = (int) array[row][col];
            }
        }
        return result;
    }
}
