package de.oth.hsp.common.dat.constraint;

import de.oth.hsp.common.dat.Constraint;
import de.oth.hsp.common.dat.value.SingleContent;
import de.oth.hsp.common.dat.value.ThreeDimFieldContent;

/**
 * Ensures that the size of the columns in a {@link ThreeDimFieldContent} fits.
 * 
 * @author Sascha Schertler 07.12.2015 2015 ThreeDimColConstraint.java
 */
public class ThreeDimRowConstraint extends Constraint<ThreeDimFieldContent> {

    public ThreeDimRowConstraint(SingleContent root, ThreeDimFieldContent dependent, int offset) {
        super(root, dependent, offset);
    }

    @Override
    public boolean isCompliant() {
        return (getRoot().getIntValue() + getOffset()) == getDependent().getValues()[0].length;
    }

    @Override
    protected void adjust() {
        final double[][][] oldValues = getDependent().getDoubleValues();
        final int oldRows = oldValues.length;
        final int cols = oldValues[0].length;
        final int dimension = oldValues[2].length;

        final int newRows = getRoot().getIntValue() + getOffset();
        final double[][][] newValues = new double[newRows][cols][dimension];

        int minRows = Math.min(oldRows, newRows);

        for (int row = 0; row < minRows; row++) {
            for (int col = 0; col < cols; col++) {
                for (int dim = 0; dim < dimension; dim++) {
                    newValues[row][col][dim] = oldValues[row][col][dim];
                }
            }
        }

        getDependent().setValues(newValues);
    }

}
