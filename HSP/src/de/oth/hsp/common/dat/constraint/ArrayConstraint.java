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
public class ArrayConstraint extends Constraint<ArrayContent> {

    public ArrayConstraint(ArrayContent dependent, SingleContent root, int offset) {
        super(root, dependent, offset);
    }

    public ArrayConstraint(ArrayContent dependent, SingleContent root) {
        super(root, dependent);
    }

    @Override
    public boolean isCompliant() {
        return (getRoot().getIntValue() + getOffset()) == getDependent().getIntValues().length;
    }

    @Override
    public void adjust() {
        final double[] newValues = new double[getRoot().getIntValue() + getOffset()];
        final double[] oldValues = getDependent().getDoubleValues();

        int minSize = Math.min(newValues.length, oldValues.length);

        for (int i = 0; i < minSize; i++) {
            newValues[i] = oldValues[i];
        }

        getDependent().setValues(newValues);
    }
}
