package de.oth.hsp.common.dat.value;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import de.oth.hsp.common.dat.desc.NumericalType;

/**
 * Wraps a single value.
 * 
 * @author Thomas Butz
 */
public class SingleContent extends DatContent {
    /** used to format floating point numbers */
    public static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("0.#####",
            DecimalFormatSymbols.getInstance(Locale.ENGLISH));

    private double value;

    public SingleContent(double value, NumericalType type) {
        super(type);
        this.value = value;
    }

    public double getDoubleValue() {
        return value;
    }

    public int getIntValue() {
        return (int) value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    protected String getStringRepresentation() {
        return NUMBER_FORMAT.format(value);
    }
}
