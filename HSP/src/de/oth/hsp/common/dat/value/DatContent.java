package de.oth.hsp.common.dat.value;

import de.oth.hsp.common.dat.DatEntry;

/**
 * Abstract base class for all kind of content in a {@link DatEntry}.
 * 
 * @author Thomas Butz
 *
 */
public abstract class DatContent {

	/**
	 * * @param the level of the element when residing in a nested structure
	 * starting with 0 at the parent element (optional)
	 * 
	 * @return the value formated as a String
	 */
	protected abstract String getStringRepresentation(int level);
	
	/**
	 * @return the value formated as a String
	 */
	protected String getStirngRepresentation() {
		return getStringRepresentation(0);
	}

	@Override
	public String toString() {
		return getStringRepresentation(0);
	}
}
