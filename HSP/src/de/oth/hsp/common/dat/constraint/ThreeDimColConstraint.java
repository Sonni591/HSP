package de.oth.hsp.common.dat.constraint;

import de.oth.hsp.common.dat.Constraint;
import de.oth.hsp.common.dat.value.SingleContent;
import de.oth.hsp.common.dat.value.ThreeDimFieldContent;

/**
 * Ensures that the size of the columns in a {@link ThreeDimFieldContent} fits.
 * 
 * @author Sascha Schertler 07.12.2015 2015 ThreeDimColConstraint.java
 */
public class ThreeDimColConstraint extends Constraint<ThreeDimFieldContent> {

    public ThreeDimColConstraint(SingleContent root, ThreeDimFieldContent dependent, int offset) {
        super(root, dependent, offset);
    }

    @Override
    public boolean isCompliant() {
        return (getRoot().getIntValue() + getOffset()) == getDependent().getValues()[0].length;
    }

    @Override
    protected void adjust() {
        final double[][][] oldValues = getDependent().getDoubleValues();
        final int rows = oldValues.length;
        final int oldCols = oldValues[0].length;
        final int dimension = oldValues[2].length;

        final int newCols = getRoot().getIntValue() + getOffset();
        final double[][][] newValues = new double[rows][newCols][dimension];

        int minCols = Math.min(oldCols, newCols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < minCols; col++) {
                for (int dim = 0; dim < dimension; dim++) {
                    newValues[row][col][dim] = oldValues[row][col][dim];
                }
            }
        }

        getDependent().setValues(newValues);
    }

}
