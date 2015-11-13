package de.oth.hsp.common.dat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import de.oth.hsp.common.dat.parser.DatParseException;
import de.oth.hsp.common.dat.parser.DatParseListener;
import de.oth.hsp.common.dat.parser.gen.DatLexer;
import de.oth.hsp.common.dat.parser.gen.DatParser;
import de.oth.hsp.common.dat.parser.gen.DatParser.DatBodyContext;

/**
 * Handles the parsing of <i>.dat</i> files.
 * 
 * @author Thomas Butz
 */
public class DatFileParser {
    /** the charset used by dat files */
    private static final Charset DAT_CHARSET = StandardCharsets.ISO_8859_1;

    /** prohibit initialization */
    private DatFileParser() {
        throw new UnsupportedOperationException();
    }

    /**
     * Parses the file at the given path.
     * 
     * @param path
     *            the path of the file to be parsed
     * 
     * @return the List of encountered {@link DatEntry} entities
     * @throws DatParseException
     *             if an error occurred while trying to parse the file
     */
    public static List<DatEntry<?>> parse(Path path) throws DatParseException {
        Objects.requireNonNull(path);

        try (Reader reader = createTolerantReader(path)) {
            // create the lexer
            DatLexer lexer = new DatLexer(new ANTLRInputStream(reader));
            TokenStream tokenStream = new CommonTokenStream(lexer);

            // create the parser
            DatParser parser = new DatParser(tokenStream);
            parser.setBuildParseTree(true);

            // attach error listener
            DatParseErrorListener errorListener = new DatParseErrorListener();
            parser.addErrorListener(errorListener);

            // build the parse tree
            DatBodyContext tree = parser.datBody();

            // check for an error
            DatParseException exc = errorListener.getException();
            if (exc != null) {
                throw exc;
            }

            // create DatEntry objects
            DatParseListener listener = new DatParseListener();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(listener, tree);

            return listener.getEntries();
        } catch (IOException e) {
            throw new DatParseException("An error occured while trying to read from \"" + path + "\"", e);
        }
    }

    /**
     * Creates a {@link BufferedReader} using a decoder which will replace
     * unmappable characters.
     * 
     * @param path
     *            the Path of the dat file
     * @return the created reader
     * @throws IOException
     *             if the creation of the reader fails
     */
    private static Reader createTolerantReader(Path path) throws IOException {
        CharsetDecoder decoder = DAT_CHARSET.newDecoder();
        decoder.onUnmappableCharacter(CodingErrorAction.REPLACE);

        Reader reader = new InputStreamReader(Files.newInputStream(path), decoder);

        return new BufferedReader(reader);
    }

    /**
     * Used to capture error information while parsing
     */
    private static class DatParseErrorListener extends BaseErrorListener {
        /** captured exception */
        private DatParseException exc;

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e) {
            // ignore following errors
            if (exc == null) {
                exc = new DatParseException("line " + line + ":" + charPositionInLine + " " + msg, e);
            }
        }

        /**
         * @return the exception which has been caught or null
         */
        public DatParseException getException() {
            return exc;
        }
    }
}
