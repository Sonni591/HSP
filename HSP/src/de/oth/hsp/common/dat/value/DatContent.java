package de.oth.hsp.common.dat.value;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.Locale;

import de.oth.hsp.common.dat.DatEntry;
import de.oth.hsp.common.dat.NumericalType;

/**
 * Abstract base class for all kind of content in a {@link DatEntry}.
 * 
 * @author Thomas Butz
 *
 */
public abstract class DatContent {
    /** Declares the start of an array */
    public static final String ARRAY_OPEN = "[";
    /** Declares the end of an array */
    public static final String ARRAY_END = "]";
    /** Declares the start of a field */
    public static final String FIELD_OPEN = "#[";
    /** Declares the end of a field */
    public static final String FIELD_END = "]#";
    /** Used to prettify the structure of nested elements */
    public static final String INDENTATION = "   ";
    /** separator between index and content */
    public static final String INDEX_SEPARATOR = ": ";
    /** the separator used between field entries */
    public static final String VALUE_SEPARATOR = ", ";

    /** used to format floating point values */
    public static final DecimalFormat FLOAT_FORMAT = new DecimalFormat("0.#####",
            DecimalFormatSymbols.getInstance(Locale.ENGLISH));
    /** used to format integer values */
    public static final DecimalFormat INT_FORMAT = new DecimalFormat("#",
            DecimalFormatSymbols.getInstance(Locale.ENGLISH));

    private final NumericalType type;

    public DatContent(NumericalType type) {
        this.type = type;
    }

    /**
     * * @param the level of the element when residing in a nested structure
     * starting with 0 at the parent element (optional)
     * 
     * @return the value formated as a String
     */
    protected abstract String getStringRepresentation();

    @Override
    public String toString() {
        return getStringRepresentation();
    }

    public NumericalType getType() {
        return type;
    }

    protected String formatArray(double[] values) {
        StringBuilder fieldBuilder = new StringBuilder();
        fieldBuilder.append(ARRAY_OPEN);

        DecimalFormat numberFormat;
        if (getType() == NumericalType.INTEGER) {
            numberFormat = INT_FORMAT;
        } else {
            numberFormat = FLOAT_FORMAT;
        }

        for (int i = 0; i < values.length; i++) {
            fieldBuilder.append(numberFormat.format(values[i]));

            if ((i + 1) < values.length) {
                fieldBuilder.append(VALUE_SEPARATOR);
            }
        }

        fieldBuilder.append(ARRAY_END);

        return fieldBuilder.toString();
    }

    protected String formatField(double[][] values, int level) {
        StringBuilder fieldBuilder = new StringBuilder();
        fieldBuilder.append(FIELD_OPEN);

        int rowCount = 1;
        for (double[] row : values) {
            fieldBuilder.append(System.lineSeparator());
            fieldBuilder.append(String.join("", Collections.nCopies(level + 1, INDENTATION))).append(rowCount)
                    .append(INDEX_SEPARATOR);
            fieldBuilder.append(formatArray(row));
            rowCount++;
        }

        fieldBuilder.append(System.lineSeparator()).append(String.join("", Collections.nCopies(level, INDENTATION)))
                .append(FIELD_END);

        return fieldBuilder.toString();
    }
}
