package de.oth.hsp.common.utils;

import javafx.collections.ObservableList;

public class TableUtils {
    /**
     * Converts a ObservableList<Number[]> into a Number[][], without the first
     * column and the last row
     *
     * @param list
     * @return
     */
    public static Number[][] convertOListTo2DArray(ObservableList<Number[]> list) {
        if (list.size() > 1 && list.get(0).length > 1) {
            Number[][] result = new Number[list.size() - 1][list.get(0).length - 1];
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = 1; j < list.get(i).length; j++) {
                    result[i][j - 1] = list.get(i)[j];
                }
            }
            return result;
        } else {
            return new Number[1][1];
        }
    }

    public static Number[] convertOListToArray(ObservableList<Number[]> list) {
        return convertOListTo2DArray(list)[0];
    }
}
