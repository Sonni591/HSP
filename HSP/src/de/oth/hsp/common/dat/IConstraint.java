package de.oth.hsp.common.dat;

/**
 * Implementations allow to define relationships between {@link DatEntry}
 * objects of an {@link AbstractDatFile}.
 * 
 * @author Thomas Butz
 */
public interface IConstraint {

    /**
     * @return the {@link DatEntry} this constraint relies on
     */
    DatEntry<?> getEntry();

    /**
     * @return <i>true</i> if the constraint is being adhered, <i>false</i>
     *         otherwise
     */
    boolean isCompliant();

    /**
     * Ensures that the constraint is being satisfied.<br>
     * e.g.: by modifying the content of other {@link DatEntry} objects
     */
    void ensureCompliance();
}
