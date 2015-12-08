package de.oth.hsp.common.dat.constraint;

import de.oth.hsp.common.dat.Constraint;
import de.oth.hsp.common.dat.value.SingleContent;
import de.oth.hsp.common.dat.value.TwoDimFieldContent;

/**
 * Ensures that the size of the rows in a {@link TwoDimFieldContent} fits.
 * 
 * @author Thomas Butz
 *
 */
public class TwoDimRowConstraint extends Constraint<TwoDimFieldContent> {

    public TwoDimRowConstraint(TwoDimFieldContent dependent, SingleContent root, int offset) {
        super(root, dependent, offset);
    }

    public TwoDimRowConstraint(TwoDimFieldContent dependent, SingleContent root) {
        super(root, dependent);
    }

    @Override
    public boolean isCompliant() {
        return (getRoot().getIntValue() + getOffset()) == getDependent().getValues().length;
    }

    @Override
    protected void adjust() {
        final double[][] oldValues = getDependent().getDoubleValues();
        final int oldRows = oldValues.length;
        final int cols = oldValues[0].length;

        final int newRows = getRoot().getIntValue() + getOffset();
        final double[][] newValues = new double[newRows][cols];

        int minRows = Math.min(oldRows, newRows);

        for (int row = 0; row < minRows; row++) {
            for (int col = 0; col < cols; col++) {
                newValues[row][col] = oldValues[row][col];
            }
        }

        getDependent().setValues(newValues);
    }

}
