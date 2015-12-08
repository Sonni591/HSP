package de.oth.hsp.common.dat.constraint;

import de.oth.hsp.common.dat.Constraint;
import de.oth.hsp.common.dat.value.SingleContent;
import de.oth.hsp.common.dat.value.TwoDimFieldContent;

/**
 * Ensures that the size of the columns in a {@link TwoDimFieldContent} fits.
 * 
 * @author Thomas Butz
 *
 */
public class TwoDimColConstraint extends Constraint<TwoDimFieldContent> {

    public TwoDimColConstraint(TwoDimFieldContent dependent, SingleContent root, int offset) {
        super(root, dependent, offset);
    }

    public TwoDimColConstraint(TwoDimFieldContent dependent, SingleContent root) {
        this(dependent, root, 0);
    }

    @Override
    public boolean isCompliant() {
        return (getRoot().getIntValue() + getOffset()) == getDependent().getValues()[0].length;
    }

    @Override
    protected void adjust() {
        final double[][] oldValues = getDependent().getDoubleValues();
        final int rows = oldValues.length;
        final int oldCols = oldValues[0].length;

        final int newCols = getRoot().getIntValue() + getOffset();
        final double[][] newValues = new double[rows][newCols];

        int minCols = Math.min(oldCols, newCols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < minCols; col++) {
                newValues[row][col] = oldValues[row][col];
            }
        }

        getDependent().setValues(newValues);
    }
}
