package de.oth.hsp.common.model;

/**
 * Describes an entry as specified by a <i>.mod</i> file.
 * 
 * @author Thomas Butz
 *
 */
public class EntryDesc {
    private final String name;
    private final ContentType conType;
    private final NumericalType numType;

    /**
     * Creates a new description for an entry.
     * 
     * @param name
     *            the name of the entry
     * @param conType
     *            the type of content associated with the entry
     * @param numType
     *            the numerical type of the values
     */
    public EntryDesc(String name, ContentType conType, NumericalType numType) {
        this.name = name;
        this.conType = conType;
        this.numType = numType;
    }

    public String getName() {
        return name;
    }

    public ContentType getConType() {
        return conType;
    }

    public NumericalType getNumType() {
        return numType;
    }

}
