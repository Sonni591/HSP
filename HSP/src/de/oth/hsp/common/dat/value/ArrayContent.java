package de.oth.hsp.common.dat.value;

import de.oth.hsp.common.dat.NumericalType;

/**
 * Encapsulates one-dimensional fields of data.
 *
 * @author Thomas Butz
 */
public class ArrayContent extends DatContent {

    private double[] values;

    public ArrayContent(NumericalType type) {
        this(new double[] { 0 }, type);
    }

    public ArrayContent(double[] values, NumericalType type) {
        super(type);
        this.values = values;
    }

    public Number[] getValues() {
        Number[] numbers = new Number[values.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = values[i];
        }

        return numbers;
    }

    public double[] getDoubleValues() {
        return values;
    }

    public int[] getIntValues() {
        int[] ints = new int[values.length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) values[i];
        }

        return ints;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    public void setValues(int[] values) {
        this.values = new double[values.length];

        for (int i = 0; i < values.length; i++) {
            this.values[i] = values[i];
        }
    }

    public void setValues(Number[] values) {
        this.values = new double[values.length];

        for (int i = 0; i < values.length; i++) {
            this.values[i] = values[i].doubleValue();
        }
    }

    @Override
    protected String getStringRepresentation() {
        return formatArray(values);
    }
}
