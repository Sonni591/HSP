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
        Number[][] result = new Number[list.size()][list.get(0).length];
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length; j++) {
                result[i][j] = list.get(i)[j];
            }
        }
        return result;
    }

    public static Number[] convertOListToArray(ObservableList<Number[]> list) {
        return convertOListTo2DArray(list)[0];
    }
}
