package de.oth.hsp.common.ilog.exception;

/**
 * exception class if the ilog model was not solvable
 *
 */
public class NotSolvableException extends ILogSolvingException {

    private static final long serialVersionUID = 1L;

    public NotSolvableException() {

        super("Dieses Problem ist nicht lösbar!");

        title = "NotSolvableException";
        text = "Dieses Problem ist nicht lösbar!";

    }

}
