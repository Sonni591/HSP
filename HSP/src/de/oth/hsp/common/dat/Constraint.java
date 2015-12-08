package de.oth.hsp.common.dat;

import de.oth.hsp.common.dat.value.DatContent;
import de.oth.hsp.common.dat.value.SingleContent;

/**
 * Implementations allow to define relationships between {@link DatEntry}
 * objects of an {@link AbstractDatFile}.
 * 
 * @author Thomas Butz
 */
public abstract class Constraint<T extends DatContent> {

    private final DatEntry<T> dependent;
    private final DatEntry<SingleContent> root;
    private final int offset;

    public Constraint(DatEntry<T> dependent, DatEntry<SingleContent> root, int offset) {
        this.root = root;
        this.dependent = dependent;
        this.offset = offset;
    }

    public Constraint(DatEntry<T> dependent, DatEntry<SingleContent> root) {
        this(dependent, root, 0);
    }

    /**
     * @return <i>true</i> if the constraint is being adhered, <i>false</i>
     *         otherwise
     */
    public boolean isCompliant() {
        return getRootSize() == getDependentSize();
    }

    /**
     * Ensures that the constraint is being satisfied.<br>
     * e.g.: by modifying the content of other {@link DatEntry} objects
     */
    public void ensure() {
        if (!isCompliant()) {
            adjust();
        }
    };

    /**
     * Modifies the size of a {@link DatContent} so it meets the constraint.
     */
    protected abstract void adjust();

    public DatEntry<SingleContent> getRoot() {
        return root;
    }

    public DatEntry<T> getDependent() {
        return dependent;
    }

    public int getOffset() {
        return offset;
    }

    public int getRootSize() {
        return root.getContent().getIntValue() + offset;
    }

    public abstract int getDependentSize();
}
