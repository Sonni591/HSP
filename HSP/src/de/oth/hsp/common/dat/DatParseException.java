package de.oth.hsp.common.dat;

import de.oth.hsp.common.dat.parser.DatFileParser;

/**
 * Wraps exceptions which have been encountered while parsing a <i>.dat</i> file with a {@link DatFileParser}.
 * 
 * @author Thomas Butz
 *
 */
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
