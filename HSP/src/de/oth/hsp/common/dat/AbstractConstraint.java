package de.oth.hsp.common.dat;

import java.util.List;

import de.oth.hsp.common.dat.value.SingleContent;

/**
 * Used to describe relationships between one/multiple {@link DatEntry} objects
 * and one dependent {@link DatEntry}.
 * 
 * @author Thomas Butz
 *
 */
public abstract class AbstractConstraint {

    /**
     * @return a List with all {@link DatEntry} objects the constraint
     *         originates from.
     */
    public abstract List<DatEntry<SingleContent>> getRoots();

    /**
     * @return <i>true</i> if the constraint is being adhered, <i>false</i>
     *         otherwise
     */
    public boolean isCompliant() {
        return getExpectedSize() == getActualSize();
    }

    /**
     * Ensures that the constraint is being satisfied.<br>
     * e.g.: by modifying the content of other {@link DatEntry} objects
     */
    public void ensure() {
        if (!isCompliant()) {
            adjust();
        }
    }

    /**
     * Checks if the constraint is being satisfied and throws an exception
     * otherwise
     * 
     * @throws ConstraintException
     *             if the constraint is not satisfied
     */
    public void validate() throws ConstraintException {
        if ((getExpectedSize() <= 0) || !isCompliant()) {
            throw new ConstraintException(createErrorMessage());
        }
    }

    /**
     * @return the size which is being expected
     */
    protected abstract int getExpectedSize();

    /**
     * @return the actual size
     */
    protected abstract int getActualSize();

    /**
     * Modifies the size of the dependent {@link DatEntry} so it meets the
     * constraint.
     */
    protected abstract void adjust();

    /**
     * @return an error message which can be used to generate a
     *         {@link ConstraintException}
     */
    protected abstract String createErrorMessage();
}
