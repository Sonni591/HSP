package de.oth.hsp.common.dat.constraint;

import de.oth.hsp.common.dat.DatEntry;
import de.oth.hsp.common.dat.IConstraint;
import de.oth.hsp.common.dat.value.DatContent;
import de.oth.hsp.common.dat.value.SingleContent;

/**
 * Children of this class define a relationship between one root
 * {@link DatEntry} and a dependent {@link DatEntry}.
 * 
 * @param <T>
 *            the {@link DatContent} type of the dependent {@link DatEntry}
 * 
 * @author Thomas Butz
 */
public abstract class AbstractUnaryConstraint<T extends DatContent> implements IConstraint {

    private final DatEntry<T> dependent;
    private final DatEntry<SingleContent> root;
    private final int offset;

    public AbstractUnaryConstraint(DatEntry<T> dependent, DatEntry<SingleContent> root, int offset) {
        this.root = root;
        this.dependent = dependent;
        this.offset = offset;
    }

    public AbstractUnaryConstraint(DatEntry<T> dependent, DatEntry<SingleContent> root) {
        this(dependent, root, 0);
    }

    /**
     * @return the root of the constraint
     */
    public DatEntry<SingleContent> getRoot() {
        return root;
    }

    /**
     * @return the entry which relies on the size of {@link #getRoot()}
     */
    public DatEntry<T> getDependent() {
        return dependent;
    }

    /**
     * @return the size which is being expected
     */
    @Override
    public int getExpectedSize() {
        return root.getContent().getIntValue() + offset;
    }
}
