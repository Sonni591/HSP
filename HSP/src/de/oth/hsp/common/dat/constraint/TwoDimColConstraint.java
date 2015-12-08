package de.oth.hsp.common.dat.constraint;

import de.oth.hsp.common.dat.Constraint;
import de.oth.hsp.common.dat.DatEntry;
import de.oth.hsp.common.dat.value.SingleContent;
import de.oth.hsp.common.dat.value.TwoDimFieldContent;

/**
 * Ensures that the size of the columns in a {@link TwoDimFieldContent} fits.
 * 
 * @author Thomas Butz
 *
 */
public class TwoDimColConstraint extends Constraint<TwoDimFieldContent> {

    public TwoDimColConstraint(DatEntry<TwoDimFieldContent> dependent, DatEntry<SingleContent> root, int offset) {
        super(dependent, root, offset);
    }

    public TwoDimColConstraint(DatEntry<TwoDimFieldContent> dependent, DatEntry<SingleContent> root) {
        this(dependent, root, 0);
    }

    @Override
    protected void adjust() {
        final double[][] oldValues = getDependent().getContent().getDoubleValues();
        final int rows = oldValues.length;
        final int oldCols = oldValues[0].length;

        final int newCols = getRootSize();
        final double[][] newValues = new double[rows][newCols];

        int minCols = Math.min(oldCols, newCols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < minCols; col++) {
                newValues[row][col] = oldValues[row][col];
            }
        }

        getDependent().getContent().setValues(newValues);
    }

    @Override
    public int getDependentSize() {
        return getDependent().getContent().getValues()[0].length;
    }
}
