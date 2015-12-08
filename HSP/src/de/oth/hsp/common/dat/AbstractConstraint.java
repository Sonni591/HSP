package de.oth.hsp.common.dat;

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
     * @return the root of the constraint
     */
    public abstract DatEntry<SingleContent> getRoot();

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
     * @throws ConstraintSatisfactionException
     *             if the constraint is not satisfied
     */
    public void validate() throws ConstraintSatisfactionException {
        if (!isCompliant()) {
            throw new ConstraintSatisfactionException(createErrorMessage());
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
     *         {@link ConstraintSatisfactionException}
     */
    protected abstract String createErrorMessage();
}
