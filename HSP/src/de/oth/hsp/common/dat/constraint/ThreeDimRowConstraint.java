package de.oth.hsp.common.dat.constraint;

import java.text.MessageFormat;

import de.oth.hsp.common.dat.DatEntry;
import de.oth.hsp.common.dat.value.SingleContent;
import de.oth.hsp.common.dat.value.ThreeDimFieldContent;

/**
 * Ensures that the size of the columns in a {@link ThreeDimFieldContent} fits.
 * 
 * @author Sascha Schertler 07.12.2015 2015 ThreeDimColConstraint.java
 */
public class ThreeDimRowConstraint extends AbstractUnaryConstraint<ThreeDimFieldContent> {

    public ThreeDimRowConstraint(DatEntry<ThreeDimFieldContent> dependent, DatEntry<SingleContent> root, int offset) {
        super(dependent, root, offset);
    }

    public ThreeDimRowConstraint(DatEntry<ThreeDimFieldContent> dependent, DatEntry<SingleContent> root) {
        this(dependent, root, 0);
    }

    @Override
    protected void adjust() {
        final double[][][] oldValues = getDependent().getContent().getDoubleValues();
        final int fields = oldValues.length;
        final int oldRows = oldValues[0].length;
        final int cols = oldValues[0][0].length;

        final int newRows = getExpectedSize();
        final double[][][] newValues = new double[fields][newRows][cols];

        int rows = Math.min(oldRows, newRows);

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
        return getDependent().getContent().getValues()[0].length;
    }

    @Override
    protected String createErrorMessage() {
        String template = "Variable \"{0}\" depends on the size of \"{1}\": {2} row(s) expected but got {3}";
        return MessageFormat.format(template, getDependent().getName(), getRoot().getName(), getExpectedSize(),
                getActualSize());
    }

}
