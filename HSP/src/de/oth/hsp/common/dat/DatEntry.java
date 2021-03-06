package de.oth.hsp.common.dat;

import java.text.MessageFormat;

import de.oth.hsp.common.dat.value.DatContent;

/**
 * Represents entries in a <i>.dat</i> file.
 * 
 * @param <T>
 *            the type of {@link DatContent}
 * 
 * @author Thomas Butz
 */
public class DatEntry<T extends DatContent> {
    /** defines the structure of the entry */
    private static final String TEMPLATE = "{0} = {1};";

    private final String name;
    private final T content;
    private final Class<? extends DatContent> contentType;

    /**
     * @param name
     *            the name of the represented entry
     * @param content
     *            the content of the entry
     */
    public DatEntry(String name, T content) {
        this.name = name;
        this.content = content;
        this.contentType = content.getClass();
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
    public T getContent() {
        return content;
    }

    /**
     * @return the type of content stored in this entry
     */
    public Class<? extends DatContent> getContentType() {
        return contentType;
    }

    @Override
    public String toString() {
        return MessageFormat.format(TEMPLATE, name, content);
    }
}
