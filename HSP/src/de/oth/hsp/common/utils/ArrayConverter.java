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
     * @return converted two dimensional array
     */

    public static float[][] convertTwoDimArray(double[][] array) {

        float[][] result = new float[array.length][array[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[0].length; col++) {
                // result[row][col] = (int) array[row][col];
                result[row][col] = (float) array[row][col];
            }
        }
        return result;
    }

    /**
     * Converts a given three dimensional double array to a three dimensional
     * float array
     * 
     * @param array
     *            source three dimensional double array
     * @return converted three dimensional array
     */
    public static float[][][] convertThreeDimArray(double[][][] array) {

        float[][][] result = new float[array.length][array[0].length][array[0][0].length];

        for (int field = 0; field < result.length; field++) {
            for (int row = 0; row < result[0].length; row++) {
                for (int col = 0; col < result[0][0].length; col++) {
                    result[field][row][col] = (float) array[field][row][col];
                }
            }
        }

        return result;
    }

    /**
     * This method converts a float array to a corresponding Number array
     * 
     * @param floatArray
     *            the float array that needs to be converted
     * @return returns a Number array build from the floatArray
     */
    public static Number[][] floatArrayToNumberArray(float[][] floatArray) {
        Number[][] numberArray = new Number[floatArray.length][floatArray[1].length];
        for (int i = 0; i < floatArray.length; i++) {
            for (int j = 0; j < floatArray[i].length; j++) {
                numberArray[i][j] = floatArray[i][j];
            }
        }
        return numberArray;
    }

    /**
     * This method converts a int array to a corresponding Number array
     * 
     * @param intArray
     *            the int array that needs to be converted
     * @return returns a Number array build from the intArray
     */
    public static Number[][] intArrayToNumberArray(int[][] intArray) {
        Number[][] numberArray = new Number[intArray.length][intArray[1].length];
        for (int i = 0; i < intArray.length; i++) {
            for (int j = 0; j < intArray[0].length; j++) {
                numberArray[i][j] = intArray[i][j];
            }
        }
        return numberArray;
    }
}
