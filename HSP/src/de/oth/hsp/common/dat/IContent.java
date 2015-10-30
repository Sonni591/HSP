package de.oth.hsp.common.dat;

/**
 * Classes implementing this interface represent the content of an entry in a
 * <i>.dat</i> file.
 *
 * @param <E>
 *            the type of content
 * 
 * @author Thomas Butz
 */
public interface IContent<E> {

	/**
	 * @param the
	 *            level of the element when residing in a nested structure
	 *            starting with 0 at the parent element (optional)
	 * @return the value formated as a String
	 */
	String getStringRepresentation(int level);
}
