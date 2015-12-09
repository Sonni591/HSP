package de.oth.hsp.common.dat.constraint;

import java.text.MessageFormat;

import de.oth.hsp.common.dat.DatEntry;
import de.oth.hsp.common.dat.value.SingleContent;
import de.oth.hsp.common.dat.value.ThreeDimFieldContent;

/**
 * Ensures that the size of the columns in a {@link ThreeDimFieldContent} fits.
 * 
 * @author Sascha Schertler
 */
public class ThreeDimColConstraint extends AbstractUnaryConstraint<ThreeDimFieldContent> {

    public ThreeDimColConstraint(DatEntry<ThreeDimFieldContent> dependent, DatEntry<SingleContent> root, int offset) {
        super(dependent, root, offset);
    }

    public ThreeDimColConstraint(DatEntry<ThreeDimFieldContent> dependent, DatEntry<SingleContent> root) {
        this(dependent, root, 0);
    }

    @Override
    protected void adjust() {
        final double[][][] oldValues = getDependent().getContent().getDoubleValues();
        final int fields = oldValues.length;
        final int rows = oldValues[0].length;
        final int oldCols = oldValues[0][0].length;

        final int newCols = getExpectedSize();
        final double[][][] newValues = new double[fields][rows][newCols];

        int cols = Math.min(oldCols, newCols);

        for (int field = 0; field < fields; field++) {
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    newValues[field][row][col] = oldValues[field][row][col];
                }
            }
        }

        getDependent().getContent().setValues(newValues);
    }

    @Override
    protected int getActualSize() {
        return getDependent().getContent().getValues()[0][0].length;
    }

    @Override
    protected String createErrorMessage() {
        String template = "Variable \"{0}\" depends on the size of \"{1}\": {2} column(s) expected but got {3}";
        return MessageFormat.format(template, getDependent().getName(), getRoot().getName(), getExpectedSize(),
                getActualSize());
    }

}
