package de.oth.hsp.common.dat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
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

import de.oth.hsp.clsp.model.ClspDatFile;
import de.oth.hsp.common.dat.parser.DatParseException;
import de.oth.hsp.common.dat.parser.DatParseListener;
import de.oth.hsp.common.dat.parser.gen.DatLexer;
import de.oth.hsp.common.dat.parser.gen.DatParser;
import de.oth.hsp.common.dat.parser.gen.DatParser.DatBodyContext;
import de.oth.hsp.hpplan.model.HpplanSimDatFile;

/**
 * Handles the parsing of <i>.dat</i> files.
 * 
 * @author Thomas Butz
 */
public class DatFileParser {

    /** prohibit initialization */
    private DatFileParser() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a {@link ClspDatFile} from its file representation.
     * 
     * @param path
     *            the path of the dat file
     * @return a {@link ClspDatFile} containing the parsed content of the
     *         <i>dat</i> file
     * @throws DatParseException
     *             if an error occurred while parsing the file
     */
    public static ClspDatFile parseClsp(Path path) throws DatParseException {
        return parseDatFile(path, ClspDatFile.class);
    }

    /**
     * Creates a {@link HpplanSimDatFile} from its file representation.
     * 
     * @param path
     *            the path of the dat file
     * @return a {@link HpplanSimDatFile} containing the parsed content of the
     *         <i>dat</i> file
     * @throws DatParseException
     *             if an error occurred while parsing the file
     */
    public static HpplanSimDatFile parseHpplanSim(Path path) throws DatParseException {
        return parseDatFile(path, HpplanSimDatFile.class);
    }

    /**
     * Uses reflection to parse <i>dat</i> files which are children of
     * {@link AbstractDatFile}.
     * 
     * @param <T>
     *            the class of dat file
     * @param path
     *            the path of the dat file
     * @param clazz
     *            the Class of file to be parsed
     * @return an object of a child class of {@link AbstractDatFile}
     * @throws DatParseException
     *             if an error occurred while parsing the file
     */
    private static <T extends AbstractDatFile> T parseDatFile(Path path, Class<T> clazz) throws DatParseException {
        T datFile = null;
        try {
            List<DatEntry<?>> entries = parseEntries(path, clazz);

            datFile = clazz.newInstance();
            datFile.setEntries(entries);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new DatParseException("Error caught while processing entries", e);
        }

        return datFile;
    }

    /**
     * Parses the file at the given path for its {@link DatEntry} objects.
     * 
     * @param path
     *            the path of the file to be parsed
     * @param modelDesc
     *            the model description to be used when parsing the <i>dat</i>
     *            file
     * 
     * @return the List of encountered {@link DatEntry} entities
     * @throws DatParseException
     *             if an error occurred while trying to parse the file
     */
    private static List<DatEntry<?>> parseEntries(Path path, Class<? extends AbstractDatFile> datClass)
            throws DatParseException {
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
            DatParseListener listener = new DatParseListener(datClass);
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
        CharsetDecoder decoder = AbstractDatFile.DAT_CHARSET.newDecoder();
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
