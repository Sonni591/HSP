package de.oth.hsp.common.dat.value;

import java.text.MessageFormat;
import java.util.List;

import de.oth.hsp.common.dat.IContent;

/**
 * Encapsulates one-dimensional fields of data.
 *
 * @param <E>
 *            the type of the values (Integer/Double)
 * 
 * @author Thomas Butz
 */
public class ArrayContent<E extends Number> implements IContent<E> {
	/** the template used to format the String representation */
	private static final String TEMPLATE  = "[ {0} ]";
	/** the separator used between field entries */
	private static final String SEPARATOR = ", ";

	private List<E> values;

	public ArrayContent(List<E> values) {
		this.values = values;
	}
	
	public List<E> getValues() {
		return values;
	}

	public void setValues(List<E> values) {
		this.values = values;
	}
	
	@Override
	public String getStringRepresentation(int level) {
		StringBuilder fieldBuilder = new StringBuilder();
		
		for (int i = 0; i < values.size(); i++) {
			fieldBuilder.append(values.get(i).toString());
			
			if ((i+1) < values.size()) {
				fieldBuilder.append(SEPARATOR);
			}
		}
		
		return MessageFormat.format(TEMPLATE, fieldBuilder);
	}
	
	@Override
	public String toString() {
		return getStringRepresentation(0);
	}
}
