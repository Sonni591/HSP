package de.oth.hsp.common.dat;

import java.util.List;

/**
 * Children of this class define the structure of certain <i>.dat</i> files.
 * 
 * @author Thomas Butz
 */
public abstract class AbstractDatFile {

    /**
     * @return an unmodifiable List of {@link DatEntry} objects
     */
    public abstract List<DatEntry<?>> getEntries();

    /**
     * @return a List of {@link IConstraint} objects which define relationships
     *         between {@link DatEntry} objects
     */
    public abstract List<IConstraint> getConstraints();

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (DatEntry<?> entry : getEntries()) {
            builder.append(entry).append(System.lineSeparator());
        }

        return builder.toString();
    }
}
