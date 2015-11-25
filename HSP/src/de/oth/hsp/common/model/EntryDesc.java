package de.oth.hsp.common.model;

import de.oth.hsp.common.dat.value.NumericalType;

/**
 * Describes an entry as specified by a <i>.mod</i> file.
 * 
 * @author Thomas Butz
 *
 */
public class EntryDesc {
    private final ContentType conType;
    private final NumericalType numType;

    /**
     * Creates a new description for an entry.
     * 
     * @param conType
     *            the type of content associated with the entry
     * @param numType
     *            the numerical type of the values
     */
    public EntryDesc(ContentType conType, NumericalType numType) {
        this.conType = conType;
        this.numType = numType;
    }

    public ContentType getConType() {
        return conType;
    }

    public NumericalType getNumType() {
        return numType;
    }

}
