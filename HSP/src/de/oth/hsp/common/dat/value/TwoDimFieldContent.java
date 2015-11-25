package de.oth.hsp.common.dat.value;

import de.oth.hsp.common.dat.desc.NumericalType;

/**
 * Represents two-dimensional datastructures in <i>.dat</i> entries.
 * 
 * @author Thomas Butz
 */
public class TwoDimFieldContent extends DatContent {

    private double[][] values;

    public TwoDimFieldContent(double[][] values, NumericalType type) {
        super(type);
        this.values = values;
    }

    /**
     * @return the content of this field as a map(index -> element)
     */
    public double[][] getDoubleValues() {
        return values;
    }

    public int[][] getIntValues() {
        int[][] ints = new int[values.length][values[0].length];

        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[0].length; j++) {
                ints[i][j] = (int) values[i][j];
            }
        }

        return ints;
    }

    public void setValues(double[][] values) {
        this.values = values;
    }

    public void setValues(int[][] values) {
        this.values = new double[values.length][values[0].length];

        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[0].length; j++) {
                this.values[i][j] = values[i][j];
            }
        }
    }

    @Override
    protected String getStringRepresentation() {
        return formatField(values, 0);
    }
}
