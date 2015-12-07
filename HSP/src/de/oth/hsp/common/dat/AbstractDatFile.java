package de.oth.hsp.common.dat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Children of this class represent certain <i>.dat</i> files.<br>
 * <br>
 * Steps to implement child classes:<br>
 * <li>provide a constructor without parameters
 * <li>create multiple fields of the type {@link DatEntry}
 * <li>annotate them using {@link Entry} to describe their structure
 * <li>provide Getters/Setters for convenient access
 * 
 * @author Thomas Butz
 */
public abstract class AbstractDatFile {
    /** the charset used by dat files */
    public static final Charset DAT_CHARSET = StandardCharsets.ISO_8859_1;
    private static final String MOD_PATH_TEMPLATE = "/resources/{0}.mod";

    private final List<Constraint<?>> constraints = new ArrayList<>();

    /**
     * Force child classes to register any {@link Constraint} relationships.
     */
    public AbstractDatFile() {
        registerConstraints(constraints);
    }

    /**
     * Sets the values of the fields to the given ones
     * 
     * @param entries
     *            the new values for the entries
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void setEntries(List<DatEntry<?>> entries) throws InstantiationException, IllegalAccessException {
        List<Field> fields = getEntryFields(this.getClass());

        for (int i = 0; i < entries.size(); i++) {
            Field field = fields.get(i);
            field.setAccessible(true);
            field.set(this, entries.get(i));
        }
    }

    public Path createTempModFile() throws IOException {
        Path tmpModPath = Files.createTempFile(getModName(), "mod");

        try (BufferedWriter writer = Files.newBufferedWriter(tmpModPath)) {
            writer.append(getModContent());
        }

        return tmpModPath;
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
     * @return an ordered and unmodifiable List of {@link Entry} objects
     */
    public static List<Entry> getEntryDescriptions(Class<? extends AbstractDatFile> datFileClass) {
        List<Entry> entryDescs = new ArrayList<>();

        for (Field field : getEntryFields(datFileClass)) {
            entryDescs.add(field.getAnnotation(Entry.class));
        }

        return Collections.unmodifiableList(entryDescs);
    }

    /**
     * Ensures that all given Constraints are satisfied.
     */
    public void ensureConstraints() {
        // TODO
    }

    /**
     * @return <i>true</i> if all registered {@link Constraint#isCompliant()}
     *         calls return <i>true</i>, otherwise <i>false</i>
     */
    public boolean isValid() {
        for (Constraint<?> constraint : constraints) {
            if (!constraint.isCompliant()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Force child classes to register any constraints on their {@link DatEntry}
     * objects
     */
    protected abstract void registerConstraints(List<Constraint<?>> constraints);

    /**
     * @return the name of the <i>mod</i> file (without file ending)
     */
    protected abstract String getModName();

    /**
     * @return the fields which are annotated with {@link Entry} ordered by
     *         their position.
     */
    private static List<Field> getEntryFields(Class<? extends AbstractDatFile> datFileClass) {
        List<Field> fields = new ArrayList<>();

        for (Field field : datFileClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Entry.class)) {
                fields.add(field);
            }
        }

        return Collections.unmodifiableList(fields);
    }

    /**
     * @return an ordered and unmodifiable List of {@link DatEntry} objects
     */
    public List<DatEntry<?>> getEntries() {
        Map<Integer, DatEntry<?>> entryMap = new TreeMap<>();

        for (Field field : getEntryFields(this.getClass())) {
            try {
                field.setAccessible(true);
                DatEntry<?> entry = (DatEntry<?>) field.get(this);
                entryMap.put(entry.getPosition(), entry);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return Collections.unmodifiableList(new ArrayList<>(entryMap.values()));
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
