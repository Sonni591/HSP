package de.oth.hsp.common.dat;

import de.oth.hsp.common.dat.value.DatContent;

/**
 * Implementations allow to define relationships between {@link DatEntry}
 * objects of an {@link AbstractDatFile}.
 * 
 * @author Thomas Butz
 */
public abstract class Constraint {
    /**
     * @return <i>true</i> if the constraint is being adhered, <i>false</i>
     *         otherwise
     */
    public abstract boolean isCompliant();

    /**
     * Ensures that the constraint is being satisfied.<br>
     * e.g.: by modifying the content of other {@link DatEntry} objects
     */
    public void ensure() {
        if (!isCompliant()) {
            adjust();
        }
    };

    /**
     * Modifies the size of a {@link DatContent} so it meets the constraint.
     */
    protected abstract void adjust();
}
