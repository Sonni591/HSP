package de.oth.hsp.common.dat.constraint;

import java.text.MessageFormat;

import de.oth.hsp.common.dat.DatEntry;
import de.oth.hsp.common.dat.value.SingleContent;
import de.oth.hsp.common.dat.value.TwoDimFieldContent;

/**
 * Ensures that the size of the columns in a {@link TwoDimFieldContent} fits.
 * 
 * @author Thomas Butz
 *
 */
public class TwoDimColConstraint extends AbstractUnaryConstraint<TwoDimFieldContent> {

    public TwoDimColConstraint(DatEntry<TwoDimFieldContent> dependent, DatEntry<SingleContent> root, int offset) {
        super(dependent, root, offset);
    }

    public TwoDimColConstraint(DatEntry<TwoDimFieldContent> dependent, DatEntry<SingleContent> root) {
        this(dependent, root, 0);
    }

    @Override
    public void adjust() {
        final double[][] oldValues = getDependent().getContent().getDoubleValues();
        final int rows = oldValues.length;
        final int oldCols = oldValues[0].length;

        final int newCols = getExpectedSize();
        final double[][] newValues = new double[rows][newCols];

        int cols = Math.min(oldCols, newCols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                newValues[row][col] = oldValues[row][col];
            }
        }

        getDependent().getContent().setValues(newValues);
    }

    @Override
    public int getActualSize() {
        return getDependent().getContent().getValues()[0].length;
    }

    @Override
    public String createErrorMessage() {
        String template = "Variable \"{0}\" depends on the size of \"{1}\": {2} column(s) expected but got {3}";
        return MessageFormat.format(template, getDependent().getName(), getRoot().getName(), getExpectedSize(),
                getActualSize());
    }
}
