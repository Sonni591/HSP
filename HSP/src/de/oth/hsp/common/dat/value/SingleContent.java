package de.oth.hsp.common.dat.value;

import de.oth.hsp.common.dat.DatUtil;
import de.oth.hsp.common.dat.IContent;

/**
 * Wraps a single integer or double value.
 * 
 * @param <E>
 *            the type of the value (Integer/Double)
 * 
 * @author Thomas Butz
 */
public class SingleContent<E extends Number> implements IContent<E> {	
	private E value;

	public SingleContent(E value) {
		this.value = value;
	}

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	@Override
	public String getStringRepresentation(int level) {
		if (value instanceof Integer) {
			return Integer.toString((Integer) value);
		}
		return DatUtil.toString((Double)value);
	}
	
	@Override
	public String toString() {
		return getStringRepresentation(0);
	}
}
