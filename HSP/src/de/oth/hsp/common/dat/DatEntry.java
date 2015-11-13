package de.oth.hsp.common.dat;

import java.text.MessageFormat;

import de.oth.hsp.common.dat.value.DatContent;

/**
 * Represents entries in a <i>.dat</i> file.
 * 
 * @author Thomas Butz
 */
public class DatEntry {
    /** defines the structure of the entry */
    private static final String TEMPLATE = "{0} = {1};";

    private final String name;
    private final DatContent content;

    /**
     * @param name
     *            the name of the represented entry
     * @param content
     *            the content of the entry
     */
    public DatEntry(String name, DatContent content) {
        this.name = name;
        this.content = content;
    }

    /**
     * @return the name of the entry
     */
    public String getName() {
        return name;
    }

    /**
     * @return the content of this entry
     */
    public DatContent getContent() {
        return content;
    }

    /**
     * @param type
     *            the type of content
     * @return the content casted to the specified type or <i>null</i>
     */
    public <T extends DatContent> T getContent(Class<T> type) {
        try {
            return type.cast(content);
        } catch (ClassCastException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return MessageFormat.format(TEMPLATE, name, content);
    }
}
