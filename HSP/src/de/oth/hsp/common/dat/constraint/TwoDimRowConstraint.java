package de.oth.hsp.common.dat.constraint;

import java.text.MessageFormat;

import de.oth.hsp.common.dat.DatEntry;
import de.oth.hsp.common.dat.value.SingleContent;
import de.oth.hsp.common.dat.value.TwoDimFieldContent;

/**
 * Ensures that the size of the rows in a {@link TwoDimFieldContent} fits.
 * 
 * @author Thomas Butz
 *
 */
public class TwoDimRowConstraint extends AbstractUnaryConstraint<TwoDimFieldContent> {

    public TwoDimRowConstraint(DatEntry<TwoDimFieldContent> dependent, DatEntry<SingleContent> root, int offset) {
        super(dependent, root, offset);
    }

    public TwoDimRowConstraint(DatEntry<TwoDimFieldContent> dependent, DatEntry<SingleContent> root) {
        super(dependent, root);
    }

    @Override
    public void adjust() {
        final double[][] oldValues = getDependent().getContent().getDoubleValues();
        final int oldRows = oldValues.length;
        final int cols = oldValues[0].length;

        final int newRows = getExpectedSize();
        final double[][] newValues = new double[newRows][cols];

        int minRows = Math.min(oldRows, newRows);

        for (int row = 0; row < minRows; row++) {
            for (int col = 0; col < cols; col++) {
                newValues[row][col] = oldValues[row][col];
            }
        }

        getDependent().getContent().setValues(newValues);
    }

    @Override
    public int getActualSize() {
        return getDependent().getContent().getValues().length;
    }

    @Override
    public String createErrorMessage() {
        String template = "Variable \"{0}\" depends on the size of \"{1}\": {2} row(s) expected but got {3}";
        return MessageFormat.format(template, getDependent().getName(), getRoot().getName(), getExpectedSize(),
                getActualSize());
    }
}
