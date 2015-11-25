package de.oth.hsp.common.dat;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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
 * <li>annotate them using {@link EntryDesc} to describe their structure
 * <li>provide Getters/Setters for convenient access
 * 
 * @author Thomas Butz
 */
public abstract class AbstractDatFile {
    /** the charset used by dat files */
    public static final Charset DAT_CHARSET = StandardCharsets.ISO_8859_1;

    /**
     * @return an ordered and unmodifiable List of {@link DatEntry} objects
     */
    private List<DatEntry<?>> getEntries() {
        List<DatEntry<?>> entries = new ArrayList<>();

        for (Field field : getEntryFields(this.getClass())) {
            try {
                field.setAccessible(true);
                entries.add((DatEntry<?>) field.get(this));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return Collections.unmodifiableList(entries);
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

    /**
     * @return an ordered and unmodifiable List of {@link EntryDesc} objects
     */
    public static List<EntryDesc> getEntryDescriptions(Class<? extends AbstractDatFile> datFileClass) {
        List<EntryDesc> entryDescs = new ArrayList<>();

        for (Field field : getEntryFields(datFileClass)) {
            entryDescs.add(field.getAnnotation(EntryDesc.class));
        }

        return Collections.unmodifiableList(entryDescs);
    }

    /**
     * @return the fields which are annotated with {@link EntryDesc} ordered by
     *         their position.
     */
    private static List<Field> getEntryFields(Class<? extends AbstractDatFile> datFileClass) {
        Map<Integer, Field> fieldMap = new TreeMap<>();

        for (Field field : datFileClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(EntryDesc.class)) {
                EntryDesc desc = field.getAnnotation(EntryDesc.class);
                fieldMap.put(desc.position(), field);
            }
        }

        return Collections.unmodifiableList(new ArrayList<>(fieldMap.values()));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (DatEntry<?> entry : getEntries()) {
            builder.append(entry).append(System.lineSeparator());
        }

        return builder.toString();
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
}
