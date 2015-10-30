package de.oth.hsp.common.dat;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import de.oth.hsp.common.dat.value.ArrayContent;
import de.oth.hsp.common.dat.value.SingleContent;

/**
 * Handles the parsing of <i>.dat</i> files.
 * 
 * @author Thomas Butz
 */
public class DatParser {
	/** indentifies a comment in the dat file */
	private static final String COMMENT_IDENTIFIER = "//";
	/** indicates an assignment in the dat file */
	private static final String ASSIGNMENT_OPERATOR = "=";
	/** marks the end of an entry in a dat file */
	private static final String ENTRY_DELIMITER = ";";
	/** indicates the declaration of a field */
	private static final String FIELD_INDICATOR = "#";
	/** indicates the declaration of an array */
	private static final String ARRAY_INDICATOR = "[";
	
	/** used to split an array string to single values */
	private static final String ARRAY_SPLIT_REGEX = "[, ]+";

	/** holds parsed entries */
	private final List<DatEntry<?>> entries;

	/**
	 * Creates a new parser for <i>.dat</i> files.
	 */
	public DatParser() {
		this.entries = new ArrayList<>();
	}

	/**
	 * @return a List of the entries found while parsing the file
	 */
	public List<DatEntry<?>> getResults() {
		return entries;
	}

	/**
	 * Parses the file at the given path.
	 * 
	 * @param path
	 *            the path of the file to be parsed
	 */
	public void parse(Path path) throws DatParseException {
		entries.clear();

		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

			StringBuilder element = new StringBuilder();
			while (reader.ready()) {
				final String line = stripComments(reader.readLine());

				// skip comments and empty lines
				if (line.isEmpty()) {
					continue;
				}

				element.append(line).append("\n");

				// parse the entry
				if (line.endsWith(ENTRY_DELIMITER)) {
					entries.add(parseEntry(element.toString()));
					element.setLength(0);
				}

			}
		} catch (IOException e) {
			throw new DatParseException("An error occured while trying to read from \"" + path + "\"", e);
		}
	}

	/**
	 * Creates a {@link DatEntry} from its String representation.
	 * 
	 * @param entryString
	 *            the representation of the entity
	 * @return the parsed entry
	 */
	private DatEntry<?> parseEntry(String entryString) {
		String[] entryParts = entryString.split(ASSIGNMENT_OPERATOR);

		String name = entryParts[0].trim();
		String content = entryParts[1].trim();

		// remove trailing semicolon
		content = content.substring(0, content.length() - 1);

		return new DatEntry<IContent<?>>(name, parseContent(content));
	}

	/**
	 * Creates an {@link IContent} instance from its String representation
	 * 
	 * @param contentString
	 *            the representation of the content
	 * @return the parsed content
	 */
	private IContent<?> parseContent(String contentString) {
		String firstLetter = contentString.substring(0, 0);
		
		switch (firstLetter) {
		case ARRAY_INDICATOR:
			return parseArrayContent(contentString);
		case FIELD_INDICATOR:
			return parseFieldContent(contentString);
		default:
			return parseSingleValueContent(contentString);
		}
	}

	/**
	 * Creates a {@link SingleContent} from its String representation.
	 * @param contentString the representation of the content
	 * @return the parsed content as a {@link SingleContent} instance
	 */
	private SingleContent<? extends Number> parseSingleValueContent(String contentString) {
		if (contentString.contains(".")) {
			return new SingleContent<Double>(Double.parseDouble(contentString));
		}
		
		return new SingleContent<Integer>(Integer.parseInt(contentString));
	}

	/**
	 * Creates an {@link ArrayContent} from its String representation.
	 * @param contentString the representation of the content
	 * @return the parsed content as a {@link ArrayContent} instance
	 */
	private ArrayContent<? extends Number> parseFieldContent(String contentString) {
		// strip brackets
		String line = contentString.substring(1, contentString.length()-1).trim();
		
		String[] values = line.split(ARRAY_SPLIT_REGEX);
		
		// TODO
		
		return null;
	}

	private IContent<?> parseArrayContent(String contentString) {
		// TODO Automatisch generierter Methodenstub
		return null;
	}

	/**
	 * Removes a comment from the given line.
	 * 
	 * @param line
	 *            the oneline String which comment should be removed
	 * @return the line without a comment and leading/trailing whitespaces
	 */
	private String stripComments(String line) {
		String stripped = line.split(COMMENT_IDENTIFIER)[0];

		return stripped.trim();
	}
}
