package de.oth.hsp.common.dat;

public class DatParseException extends Exception {
	private static final long serialVersionUID = 6164804636098844318L;

	/**
	 * Constructs a new exception with a given message.
	 * 
	 * @param message
	 *            the error message
	 */
	public DatParseException(String message) {
		super(message);
	}

	/**
	 * Constructs a new exception with the specified cause.
	 * 
	 * @param cause
	 *            the cause of the exception
	 */
	public DatParseException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new exception with a message and the specified cause.
	 * 
	 * @param message
	 *            the error message
	 * @param cause
	 *            the cause of the exception
	 */
	public DatParseException(String message, Throwable cause) {
		super(message, cause);
	}
}
