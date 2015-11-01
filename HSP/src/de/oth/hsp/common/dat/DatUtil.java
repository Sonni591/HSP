package de.oth.hsp.common.dat;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Shared utility methods.
 * 
 * @author Thomas Butz
 *
 */
public final class DatUtil {
	/** used to format floating point numbers */
	private static final DecimalFormat DOUBLE_FORMAT = new DecimalFormat("##0.00000",
			DecimalFormatSymbols.getInstance(Locale.ENGLISH));

	/**
	 * @param value
	 *            the value to be formated
	 * @return the proper String representation of the given double value
	 */
	public static String toString(double value) {
		return DOUBLE_FORMAT.format(value);
	}
}
