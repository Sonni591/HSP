package de.oth.hsp.common.dat.constraint;

import de.oth.hsp.common.dat.Constraint;
import de.oth.hsp.common.dat.value.ArrayContent;
import de.oth.hsp.common.dat.value.SingleContent;

/**
 * Ensures that the size of a {@link ArrayContent} fits.
 * 
 * @author Thomas Butz
 *
 */
public class ArrayConstraint extends Constraint {

    private final SingleContent root;
    private final ArrayContent dependent;
    private final int offset;

    public ArrayConstraint(SingleContent root, ArrayContent dependent) {
        this(root, dependent, 0);
    }

    public ArrayConstraint(SingleContent root, ArrayContent dependent, int offset) {
        this.root = root;
        this.dependent = dependent;
        this.offset = offset;
    }

    @Override
    public boolean isCompliant() {
        return (root.getIntValue() + offset) == dependent.getIntValues().length;
    }

    @Override
    public void adjust() {
        final double[] newValues = new double[root.getIntValue() + offset];
        final double[] oldValues = dependent.getDoubleValues();

        int minSize = Math.min(newValues.length, oldValues.length);

        for (int i = 0; i < minSize; i++) {
            newValues[i] = oldValues[i];
        }

        dependent.setValues(newValues);
    }
}
