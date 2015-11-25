package de.oth.hsp.common.dat.desc;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Defines the numerical type of all values stored in an entry.
 * 
 * @author Thomas Butz
 */
public enum NumericalType {
    INTEGER(new DecimalFormat("#", DecimalFormatSymbols.getInstance(Locale.ENGLISH))),
    FLOAT(new DecimalFormat("0.#####", DecimalFormatSymbols.getInstance(Locale.ENGLISH)));

    private final DecimalFormat format;

    /**
     * @param format
     *            the format String used to print/parse numbers of this type
     */
    private NumericalType(DecimalFormat format) {
        this.format = format;
    }

    /**
     * @param format
     *            the format String used to print/parse numbers of this type
     */
    public DecimalFormat getFormat() {
        return format;
    }
}
