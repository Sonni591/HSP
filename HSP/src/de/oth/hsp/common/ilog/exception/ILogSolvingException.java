package de.oth.hsp.common.ilog.exception;

/**
 * Exception class for ilog exceptions
 *
 */
public class ILogSolvingException extends Exception {

    private static final long serialVersionUID = 1L;

    String title;
    String text;

    public ILogSolvingException() {
        super();
    }

    public ILogSolvingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ILogSolvingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ILogSolvingException(String message) {
        super(message);
    }

    public ILogSolvingException(Throwable cause) {
        super(cause);
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public void setText(String msg) {
        this.text = msg;
    }
}
