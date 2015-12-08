package de.oth.hsp.common.dat.constraint;

import java.text.MessageFormat;

import de.oth.hsp.common.dat.DatEntry;
import de.oth.hsp.common.dat.value.ArrayContent;
import de.oth.hsp.common.dat.value.SingleContent;

/**
 * Ensures that the size of a {@link ArrayContent} fits.
 * 
 * @author Thomas Butz
 *
 */
public class ArrayConstraint extends AbstractUnaryConstraint<ArrayContent> {

    public ArrayConstraint(DatEntry<ArrayContent> dependent, DatEntry<SingleContent> root, int offset) {
        super(dependent, root, offset);
    }

    public ArrayConstraint(DatEntry<ArrayContent> dependent, DatEntry<SingleContent> root) {
        super(dependent, root);
    }

    @Override
    public int getActualSize() {
        return getDependent().getContent().getIntValues().length;
    }

    @Override
    public void adjust() {
        final double[] newValues = new double[getExpectedSize()];
        final double[] oldValues = getDependent().getContent().getDoubleValues();

        int minSize = Math.min(newValues.length, oldValues.length);

        for (int i = 0; i < minSize; i++) {
            newValues[i] = oldValues[i];
        }

        getDependent().getContent().setValues(newValues);
    }

    @Override
    public String createErrorMessage() {
        String template = "Variable \"{0}\" depends on the size of \"{1}\": {2} value(s) expected but got {3}";
        return MessageFormat.format(template, getDependent().getName(), getRoot().getName(), getExpectedSize(),
                getActualSize());
    }
}
