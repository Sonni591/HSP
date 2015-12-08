package de.oth.hsp.common.dat;

/**
 * Used to indicate an error when validating a {@link AbstractConstraint} relation.
 */
public class ConstraintSatisfactionException extends Exception {

    private static final long serialVersionUID = 4252559050648889913L;

    /**
     * @param message
     *            the detail message
     */
    public ConstraintSatisfactionException(String message) {
        super(message);
    }

}