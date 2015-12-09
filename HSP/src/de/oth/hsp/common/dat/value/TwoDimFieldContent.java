package de.oth.hsp.common.dat.value;

import de.oth.hsp.common.dat.NumericalType;

/**
 * Represents two-dimensional datastructures in <i>.dat</i> entries.
 * 
 * @author Thomas Butz
 */
public class TwoDimFieldContent extends DatContent {

    private double[][] values;

    public TwoDimFieldContent(NumericalType type) {
        this(new double[][] { { 0 } }, type);
    }

    public TwoDimFieldContent(double[][] values, NumericalType type) {
        super(type);
        this.values = values;
    }

    public Number[][] getValues() {
        Number[][] numbers = new Number[values.length][values[0].length];

        for (int row = 0; row < numbers.length; row++) {
            for (int col = 0; col < numbers[0].length; col++) {
                numbers[row][col] = values[row][col];
            }
        }

        return numbers;
    }

    /**
     * @return the content of this field as a map(index -> element)
     */
    public double[][] getDoubleValues() {
        return values;
    }

    public int[][] getIntValues() {
        int[][] ints = new int[values.length][values[0].length];

        for (int row = 0; row < ints.length; row++) {
            for (int col = 0; col < ints[0].length; col++) {
                ints[row][col] = (int) values[row][col];
            }
        }

        return ints;
    }

    public void setValues(double[][] values) {
        this.values = values;
    }

    public void setValues(int[][] values) {
        this.values = new double[values.length][values[0].length];

        for (int row = 0; row < values.length; row++) {
            for (int col = 0; col < values[0].length; col++) {
                this.values[row][col] = values[row][col];
            }
        }
    }

    public void setValues(Number[][] values) {
        this.values = new double[values.length][values[0].length];

        for (int row = 0; row < values.length; row++) {
            for (int col = 0; col < values[0].length; col++) {
                this.values[row][col] = values[row][col].doubleValue();
            }
        }
    }

    @Override
    protected String getStringRepresentation() {
        return formatField(values, 0);
    }
}
