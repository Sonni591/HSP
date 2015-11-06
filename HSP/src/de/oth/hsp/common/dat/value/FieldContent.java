package de.oth.hsp.common.dat.value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Represents multi-dimensional datastructures in <i>.dat</i> entries indexes.
 * 
 * @param <E>
 *            The kind of content residing in the field.
 * 
 * @author Thomas Butz
 */
public class FieldContent<E extends DatContent> extends DatContent implements Iterable<DatContent> {
	/** Declares the start of a field */
	private static final String FIELD_OPEN = "#[";
	/** Declares the end of a field */
	private static final String FIELD_END  = "]#";
	/** Used to prettify the structure of nested elements */
	private static final String INDENTATION = "   ";
	/** separator between index and content */
	private static final String INDEX_SEPARATOR = ": ";
	
	private final Map<Integer, DatContent> values;

	public FieldContent() {
		this.values = new LinkedHashMap<Integer, DatContent>();
	}

	/**
	 * @return the content of this field as a map(index -> element)
	 */
	public Map<Integer, DatContent> getValues() {
		return values;
	}

	/**
	 * @return the content of this field as a List
	 */
	public List<DatContent> getValuesAsList() {
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
	public void put(int index, DatContent content) {
		values.put(index, content);
	}

	@Override
	public Iterator<DatContent> iterator() {
		return getValuesAsList().iterator();
	}

	protected String getStringRepresentation(int level) {
		
		StringBuilder fieldBuilder = new StringBuilder();
		fieldBuilder.append(FIELD_OPEN);
		
		for (Entry<Integer, DatContent> entry : values.entrySet()) {
			fieldBuilder.append(System.lineSeparator());
			fieldBuilder.append(String.join("", Collections.nCopies(level+1, INDENTATION))).append(entry.getKey()).append(INDEX_SEPARATOR);
			fieldBuilder.append(entry.getValue().getStringRepresentation(level+1));
		}
	
		fieldBuilder.append(System.lineSeparator());
		fieldBuilder.append(String.join("", Collections.nCopies(level, INDENTATION))).append(FIELD_END);
		
		return fieldBuilder.toString();
	}
}
