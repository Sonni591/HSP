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

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                for (int k = 0; k < numbers.length; k++) {
                    numbers[i][j][k] = values[i][j][k];
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

        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length; j++) {
                for (int k = 0; k < ints.length; k++) {
                    ints[i][j][k] = (int) values[i][j][k];
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
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                for (int k = 0; k < values.length; k++) {
                    this.values[i][j][k] = values[i][j][k];
                }
            }
        }
    }

    public void setValues(Number[][][] values) {
        this.values = new double[values.length][values[0].length][values[0][0].length];
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                for (int k = 0; k < values.length; k++) {
                    this.values[i][j][k] = values[i][j][k].doubleValue();
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
