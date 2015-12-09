package de.oth.hsp.common.dat.constraint;

import de.oth.hsp.common.dat.Constraint;
import de.oth.hsp.common.dat.value.SingleContent;
import de.oth.hsp.common.dat.value.ThreeDimFieldContent;

/**
 * Ensures that the size of the dimensions in a {@link ThreeDimFieldContent}
 * fits.
 * 
 * @author Sascha Schertler 07.12.2015 2015 ThreeDimColConstraint.java
 */
public class ThreeDimDimConstraint extends Constraint<ThreeDimFieldContent> {

    public ThreeDimDimConstraint(SingleContent root, ThreeDimFieldContent dependent, int offset) {
        super(root, dependent, offset);
    }

    @Override
    public boolean isCompliant() {
        return (getRoot().getIntValue() + getOffset()) == getDependent().getValues()[2].length;
    }

    @Override
    protected void adjust() {
        final double[][][] oldValues = getDependent().getDoubleValues();
        final int rows = oldValues.length;
        final int cols = oldValues[0].length;
        final int oldDim = oldValues[2].length;

        final int newDim = getRoot().getIntValue() + getOffset();
        final double[][][] newValues = new double[rows][cols][newDim];

        int minDim = Math.min(oldDim, newDim);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                for (int dim = 0; dim < minDim; dim++) {
                    newValues[row][col][dim] = oldValues[row][col][dim];
                }
            }
        }
        getDependent().setValues(newValues);
    }

}
