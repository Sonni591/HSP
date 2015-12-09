package de.oth.hsp.common.dat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.oth.hsp.common.dat.value.SingleContent;

/**
 * Children of this class represent certain kinds <i>.dat</i> files.<br>
 * <br>
 * When implementing a new model class make sure to call {@link #initialize()}
 * at the end of its constructor.
 * 
 * @author Thomas Butz
 */
public abstract class AbstractDatFile {
    /** the charset used by dat files */
    public static final Charset DAT_CHARSET = StandardCharsets.ISO_8859_1;
    private static final String MOD_PATH_TEMPLATE = "/resources/{0}.mod";

    /**
     * @return an ordered and unmodifiable List of {@link DatEntry} objects
     */
    public abstract List<DatEntry<?>> getEntries();

    /**
     * @return an ordered and unmodifiable List of {@link AbstractConstraint}
     *         objects which define relationships between entries
     */
    public abstract List<AbstractConstraint> getConstraints();

    /**
     * Ensures that all given Constraints are satisfied.
     * 
     * @throws ConstraintException
     *             if a {@link DatEntry} contains a value which makes it
     *             impossible to satisfy its constraints
     */
    public void ensureConstraints() throws ConstraintException {
        for (DatEntry<SingleContent> rootEntry : getConstraintRoots()) {
            if (rootEntry.getContent().getIntValue() <= 0) {
                throw new ConstraintException("Unable to satisfy constraint(s) on \"" + rootEntry.getName()
                        + "\": positive non-zero value expected but got value" + rootEntry.getContent().getIntValue());
            }
        }

        for (AbstractConstraint constraint : getConstraints()) {
            constraint.ensure();
        }
    }

    /**
     * Checks if all {@link AbstractConstraint} relations are being satisfied.
     * 
     * @throws ConstraintException
     *             if one ore more Constraints are not being satisfied
     */
    public void validate() throws ConstraintException {
        for (AbstractConstraint constraint : getConstraints()) {
            constraint.validate();
        }
    }

    /**
     * Tries to save current content at the the specified location.
     * 
     * @param path
     *            the location the content should be saved to
     * @param allowOverride
     *            <i>true</i> if the method is allowed to override existing
     *            files, false otherwise
     * @throws IOException
     *             if an existing file is to be overwritten while
     *             <i>allowOverride</i> is set to false or a general
     *             {@link IOException} was thrown while trying to write to the
     *             file
     */
    public void saveAs(Path path, boolean allowOverride) throws IOException {
        if (!allowOverride && Files.exists(path)) {
            throw new IOException("The file already exists: \"" + path + "\"");
        }

        Writer writer = Files.newBufferedWriter(path);
        writer.append(this.toString());
        writer.close();
    }

    /**
     * Creates a temporary <i>mod</i> file which fits to this file.
     * 
     * @return the {@link Path} to the temporary <i>mod</i> file
     * @throws IOException
     *             if an error occured while creating the file
     */
    public Path createTempModFile() throws IOException {
        Path tmpModPath = Files.createTempFile(getModName(), "mod");

        try (BufferedWriter writer = Files.newBufferedWriter(tmpModPath)) {
            writer.append(getModContent());
        }

        return tmpModPath;
    }

    /**
     * @return the name of the <i>mod</i> file (without file ending)
     */
    protected abstract String getModName();

    /**
     * Preallocates the values of the model so that it adheres all
     * {@link AbstractConstraint} relations.
     */
    protected void initialize() {
        for (DatEntry<SingleContent> rootEntry : getConstraintRoots()) {
            rootEntry.getContent().setValue(1);
        }

        try {
            ensureConstraints();
        } catch (ConstraintException e) {
            // ignore
        }
    }

    /**
     * @return a List with all {@link DatEntry} objects which are the root of
     *         one or more {@link AbstractConstraint} relations.
     */
    private List<DatEntry<SingleContent>> getConstraintRoots() {
        Map<String, DatEntry<SingleContent>> rootMap = new HashMap<>();

        for (AbstractConstraint constraint : getConstraints()) {
            for (DatEntry<SingleContent> root : constraint.getRoots()) {
                rootMap.put(root.getName(), root);
            }
        }

        return Collections.unmodifiableList(new ArrayList<>(rootMap.values()));
    }

    /**
     * @return the content of the corresponding <i>mod</i> file
     */
    private String getModContent() {
        String modPathString = MessageFormat.format(MOD_PATH_TEMPLATE, getModName());

        List<String> lines;
        try {
            URL modUrl = getClass().getResource(modPathString);
            lines = Files.readAllLines(Paths.get(modUrl.toURI()));
        } catch (IOException | URISyntaxException e) {
            return null;
        }

        return String.join(System.lineSeparator(), lines);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (DatEntry<?> entry : getEntries()) {
            builder.append(entry).append(System.lineSeparator());
        }

        return builder.toString();
    }
}
