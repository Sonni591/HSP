package de.oth.hsp.common.dat.constraint;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.oth.hsp.common.dat.AbstractConstraint;
import de.oth.hsp.common.dat.DatEntry;
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
public abstract class AbstractUnaryConstraint<T extends DatContent> extends AbstractConstraint {

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

    @Override
    public List<DatEntry<SingleContent>> getRoots() {
        return Collections.unmodifiableList(Arrays.asList(root));
    }

    /**
     * @return simplified access to {@link #getRoots()}.get(0)
     */
    protected DatEntry<SingleContent> getRoot() {
        return root;
    }

    /**
     * @return the entry which relies on the size of {@link #getRoots()}
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
