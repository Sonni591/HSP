package de.oth.hsp.common.dat;

import java.text.MessageFormat;

import de.oth.hsp.common.dat.value.DatContent;

/**
 * Represents entries in a <i>.dat</i> file.
 * 
 * @param <E>
 *            the type of value stored in this entity
 * 
 * @author Thomas Butz
 */
public class DatEntry<E extends DatContent> {
	/** defines the structure of the entry */
	private static final String TEMPLATE = "{0} = {1};";

	private final String name;
	private E value;

	/**
	 * @param name
	 *            the name of the represented entry
	 */
	public DatEntry(String name) {
		this.name = name;
	}

	/**
	 * @param name
	 *            the name of the represented entry
	 * @param value
	 *            the value of the entry
	 */
	public DatEntry(String name, E value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * @return the name of the entry
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the value associated with this entry
	 */
	public E get() {
		return value;
	}

	/**
	 * @param value
	 *            the new value for the entry
	 */
	public void set(E value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return MessageFormat.format(TEMPLATE, name, value);
	}
}
