package de.oth.hsp.common.dat.value;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.oth.hsp.common.dat.IContent;

/**
 * Represents multi-dimensional datastructures in <i>.dat</i> entries indexes.
 * 
 * @param <E>
 *            The kind of content residing in the field.
 * 
 * @author Thomas Butz
 */
public class FieldContent<E> implements IContent<E>, Iterable<IContent<E>> {
	/** the template used to format the String representation */
	private static final String TEMPLATE = "#[\n{0}]#";
	/** separator between index and content */
	private static final String INDEX_SEPARATOR = ": ";
	/** number of strings used for indentation */
	private static final int INDENTATION_SIZE = 3;
	
	private final Map<Integer, IContent<E>> values;

	public FieldContent() {
		this.values = new LinkedHashMap<Integer, IContent<E>>();
	}

	/**
	 * @return the content of this field as a map(index -> element)
	 */
	public Map<Integer, IContent<E>> getValues() {
		return values;
	}

	/**
	 * @return the content of this field as a List
	 */
	public List<IContent<E>> getValuesAsList() {
		return new ArrayList<>(values.values());
	}

	/**
	 * Adds the given content to the field at the specified index. An existing value
	 * at the given index will be replaced.
	 * 
	 * @param index
	 *            the index within the field
	 * @param content
	 *            the content to be added
	 */
	public void put(int index, IContent<E> content) {
		values.put(index, content);
	}

	@Override
	public String getStringRepresentation(int level) {
		
		StringBuilder fieldBuilder = new StringBuilder();
		
		StringBuilder indentation = new StringBuilder();
		for (int i = 0; i < (INDENTATION_SIZE * (level+1)); i++) {
			indentation.append(" ");
		}
		
		for (Entry<Integer, IContent<E>> entry : values.entrySet()) {
			fieldBuilder.append(indentation).append(entry.getKey()).append(INDEX_SEPARATOR);
			fieldBuilder.append(entry.getValue().getStringRepresentation(level+1));
			fieldBuilder.append(System.lineSeparator());
		}
		
		return MessageFormat.format(TEMPLATE, fieldBuilder);
	}

	@Override
	public Iterator<IContent<E>> iterator() {
		return getValuesAsList().iterator();
	}
	
	@Override
	public String toString() {
		return getStringRepresentation(0);
	}
}
