package de.oth.hsp.common.dat.value;

import java.text.MessageFormat;

/**
 * Encapsulates one-dimensional fields of data.
 *
 * @author Thomas Butz
 */
public class ArrayContent extends DatContent {
	/** the template used to format the String representation */
	private static final String TEMPLATE  = "[ {0} ]";
	/** the separator used between field entries */
	private static final String SEPARATOR = ", ";

	private double[] values;

	public ArrayContent(double[] values) {
		this.values = values;
	}
	
	public double[] getValues() {
		return values;
	}

	public void setValues(double[] values) {
		this.values = values;
	}
	
	@Override
	protected String getStringRepresentation(int level) {
		StringBuilder fieldBuilder = new StringBuilder();
		
		for (int i = 0; i < values.length; i++) {
			fieldBuilder.append(SingleContent.NUMBER_FORMAT.format(values[i]));
			
			if ((i+1) < values.length) {
				fieldBuilder.append(SEPARATOR);
			}
		}
		
		return MessageFormat.format(TEMPLATE, fieldBuilder);
	}
}
