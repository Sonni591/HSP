package de.oth.hsp.common.dat.value;

import de.oth.hsp.common.dat.NumericalType;

/**
 * Represents three-dimensional datastructures in <i>.dat</i> entries.
 * 
 * @author Thomas Butz
 */
public class ThreeDimFieldContent extends DatContent {

    private double values[][][];

    public ThreeDimFieldContent(NumericalType type) {
        this(new double[][][] { { { 0 } } }, type);
    }

    public ThreeDimFieldContent(double[][][] values, NumericalType type) {
        super(type);
        setValues(values);
    }

    public Number[][][] getValues() {
        Number[][][] numbers = new Number[values.length][values[0].length][values[0][0].length];

        for (int field = 0; field < numbers.length; field++) {
            for (int row = 0; row < numbers[0].length; row++) {
                for (int col = 0; col < numbers[0][0].length; col++) {
                    numbers[field][row][col] = values[field][row][col];
                }
            }
        }

        return numbers;
    }

    public double[][][] getDoubleValues() {
        return values;
    }

    public int[][][] getIntValues() {
        int[][][] ints = new int[values.length][values[0].length][values[0][0].length];

        for (int field = 0; field < ints.length; field++) {
            for (int row = 0; row < ints[0].length; row++) {
                for (int col = 0; col < ints[0][0].length; col++) {
                    ints[field][row][col] = (int) values[field][row][col];
                }
            }
        }

        return ints;
    }

    public void setValues(double[][][] values) {
        this.values = values;
    }

    public void setValues(int[][][] values) {
        this.values = new double[values.length][values[0].length][values[0][0].length];
        for (int field = 0; field < values.length; field++) {
            for (int row = 0; row < values[0].length; row++) {
                for (int col = 0; col < values[0][0].length; col++) {
                    this.values[field][row][col] = values[field][row][col];
                }
            }
        }
    }

    public void setValues(Number[][][] values) {
        this.values = new double[values.length][values[0].length][values[0][0].length];
        for (int field = 0; field < values.length; field++) {
            for (int row = 0; row < values[0].length; row++) {
                for (int col = 0; col < values[0][0].length; col++) {
                    this.values[field][row][col] = values[field][row][col].doubleValue();
                }
            }
        }
    }

    @Override
    protected String getStringRepresentation() {
        StringBuilder fieldBuilder = new StringBuilder();

        fieldBuilder.append(FIELD_OPEN);

        int fieldCount = 1;
        for (double[][] field : values) {
            fieldBuilder.append(System.lineSeparator());
            fieldBuilder.append(INDENTATION).append(fieldCount).append(INDEX_SEPARATOR);
            fieldBuilder.append(formatField(field));
            fieldCount++;
        }
        fieldBuilder.append(System.lineSeparator()).append(FIELD_END);

        return fieldBuilder.toString();
    }

    private String formatField(double[][] field) {
        return formatField(field, 1);
    }

}
