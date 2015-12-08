package de.oth.hsp.common.dat;

/**
 * Used to describe relationships between one or multiple {@link DatEntry}
 * objects and one dependent {@link DatEntry}.
 * 
 * @author Thomas Butz
 *
 */
public interface IConstraint {

    /**
     * @return the size which is being expected
     */
    int getExpectedSize();

    /**
     * @return the actual size
     */
    int getActualSize();

    /**
     * @return <i>true</i> if the constraint is being adhered, <i>false</i>
     *         otherwise
     */
    default boolean isCompliant() {
        return getExpectedSize() == getActualSize();
    }

    /**
     * Ensures that the constraint is being satisfied.<br>
     * e.g.: by modifying the content of other {@link DatEntry} objects
     */
    default void ensure() {
        if (!isCompliant()) {
            adjust();
        }
    }

    /**
     * Modifies the size of the dependent {@link DatEntry} so it meets the
     * constraint.
     */
    void adjust();

    /**
     * Checks if the constraint is being satisfied and throws an exception
     * otherwise
     * 
     * @throws ConstraintSatisfactionException
     *             if the constraint is not satisfied
     */
    default void validate() throws ConstraintSatisfactionException {
        if (!isCompliant()) {
            throw new ConstraintSatisfactionException(createErrorMessage());
        }
    }

    /**
     * @return an error message which can be used to generate a
     *         {@link ConstraintSatisfactionException}
     */
    String createErrorMessage();
}
