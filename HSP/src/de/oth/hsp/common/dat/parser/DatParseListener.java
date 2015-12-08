package de.oth.hsp.common.dat.parser;

import java.text.ParseException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import de.oth.hsp.common.dat.AbstractDatFile;
import de.oth.hsp.common.dat.ConstraintSatisfactionException;
import de.oth.hsp.common.dat.DatEntry;
import de.oth.hsp.common.dat.NumericalType;
import de.oth.hsp.common.dat.parser.gen.DatBaseListener;
import de.oth.hsp.common.dat.parser.gen.DatParser.ArrayValueContext;
import de.oth.hsp.common.dat.parser.gen.DatParser.DatBodyContext;
import de.oth.hsp.common.dat.parser.gen.DatParser.SingleValueContext;
import de.oth.hsp.common.dat.parser.gen.DatParser.ThreeDimFieldValueContext;
import de.oth.hsp.common.dat.parser.gen.DatParser.TwoDimFieldValueContext;
import de.oth.hsp.common.dat.parser.gen.DatParser.VarDeclarationContext;
import de.oth.hsp.common.dat.value.ArrayContent;
import de.oth.hsp.common.dat.value.DatContent;
import de.oth.hsp.common.dat.value.SingleContent;
import de.oth.hsp.common.dat.value.ThreeDimFieldContent;
import de.oth.hsp.common.dat.value.TwoDimFieldContent;

/**
 * Generates a List of DatEntry objects when applied to a ParseTreeWalker.
 * 
 * @author Thomas Butz
 *
 */
public class DatParseListener extends DatBaseListener {
    private final AbstractDatFile datFile;
    private final Deque<DatEntry<?>> entries = new ArrayDeque<>();

    public DatParseListener(AbstractDatFile datFile) {
        this.datFile = datFile;
        entries.addAll(datFile.getEntries());
    }

    @Override
    public void exitVarDeclaration(VarDeclarationContext ctx) {
        // stop after the first error
        if (ctx.exception != null) {
            return;
        }
        String name = ctx.varName().getText();
        ParserRuleContext valueContext = (ParserRuleContext) ctx.varValue().getChild(0);

        // compare the variable with the one we expected
        final DatEntry<?> untypedEntry = entries.pollFirst();
        if (untypedEntry == null) {
            throw new ParseCancellationException("Unexpected variable: \"" + name + "\"");
        }
        String expectedName = untypedEntry.getName();
        if (!expectedName.equals(name)) {
            throw new ParseCancellationException(
                    "Variable name missmatch: got variable \"" + name + "\" but expected \"" + expectedName + "\"");
        }

        // fill the entry
        fill(untypedEntry, valueContext);
    }

    @Override
    public void exitDatBody(DatBodyContext ctx) {
        // check for missing entries
        if (!entries.isEmpty()) {
            String missingEntryName = entries.getFirst().getName();
            throw new ParseCancellationException("Variable not found: \"" + missingEntryName + "\"");
        }

        // check if the constraints are being satisfied
        try {
            datFile.validate();
        } catch (ConstraintSatisfactionException e) {
            throw new ParseCancellationException(e);
        }
    }

    /**
     * Attempts to fill the {@link DatEntry} with values retrieved from the
     * given {@link ParserRuleContext}.
     * 
     * @param untypedEntry
     *            the entry to be filled
     * @param valueContext
     *            the context with the values
     */
    private void fill(DatEntry<?> untypedEntry, ParserRuleContext valueContext) {
        NumericalType numType = untypedEntry.getContent().getType();

        if (valueContext instanceof SingleValueContext) {
            DatEntry<SingleContent> entry = ensureContentType(untypedEntry, SingleContent.class);
            fill(entry, (SingleValueContext) valueContext, numType);
        } else if (valueContext instanceof ArrayValueContext) {
            DatEntry<ArrayContent> entry = ensureContentType(untypedEntry, ArrayContent.class);
            fill(entry, (ArrayValueContext) valueContext, numType);
        } else if (valueContext instanceof TwoDimFieldValueContext) {
            DatEntry<TwoDimFieldContent> entry = ensureContentType(untypedEntry, TwoDimFieldContent.class);
            fill(entry, (TwoDimFieldValueContext) valueContext, numType);
        } else {
            DatEntry<ThreeDimFieldContent> entry = ensureContentType(untypedEntry, ThreeDimFieldContent.class);
            fill(entry, (ThreeDimFieldValueContext) valueContext, numType);
        }
    }

    private void fill(DatEntry<SingleContent> entry, SingleValueContext valueCtx, NumericalType numType) {
        double value = getSingleValue(valueCtx, numType);
        entry.getContent().setValue(value);
    }

    private void fill(DatEntry<ArrayContent> entry, ArrayValueContext valueContext, NumericalType numType) {
        double[] values = getArrayValues(valueContext, numType);
        entry.getContent().setValues(values);
    }

    private void fill(DatEntry<TwoDimFieldContent> entry, TwoDimFieldValueContext valueCtx, NumericalType numType) {
        double[][] values = getTwoDimFieldValues(valueCtx, numType);
        entry.getContent().setValues(values);
    }

    private void fill(DatEntry<ThreeDimFieldContent> entry, ThreeDimFieldValueContext valueCtx, NumericalType numType) {
        double[][][] values = getThreeDimFieldValues(valueCtx, numType);
        entry.getContent().setValues(values);
    }

    /**
     * Parses the value from its {@link SingleValueContext} while checking its
     * {@link NumericalType}.
     * 
     * @param valueContext
     *            the value context
     * @param numType
     *            the expected type of the value
     * @return the value as a double
     */
    private double getSingleValue(SingleValueContext valueContext, NumericalType numType) {
        try {
            Number number = numType.getFormat().parse(valueContext.getText());
            return number.doubleValue();
        } catch (ParseException e) {
            throw new ParseCancellationException("Numerical type missmatch: expected type " + numType, e);
        }
    }

    /**
     * Parses the values of a {@link ArrayValueContext} while checking the
     * {@link NumericalType} of its values.
     * 
     * @param valueContext
     *            the value context
     * @param type
     *            the expected type of the values
     * @return the values as a double array
     */
    private double[] getArrayValues(ArrayValueContext valueContext, NumericalType type) {
        List<SingleValueContext> singleContexts = valueContext.singleValue();
        double[] values = new double[singleContexts.size()];

        for (int i = 0; i < values.length; i++) {
            values[i] = getSingleValue(singleContexts.get(i), type);
        }

        return values;
    }

    /**
     * Parses the values of a {@link TwoDimFieldValueContext} while checking the
     * {@link NumericalType} of its values.
     * 
     * @param valueContext
     *            the value context
     * @param type
     *            the expected type of the values
     * @return the values as a two-dimensional double array
     */
    private double[][] getTwoDimFieldValues(TwoDimFieldValueContext valueContext, NumericalType type) {
        List<ArrayValueContext> arrayContexts = valueContext.arrayValue();

        int rows = arrayContexts.size();
        int columns = arrayContexts.get(0).singleValue().size();

        double[][] field = new double[rows][columns];
        for (int row = 0; row < field.length; row++) {
            field[row] = getArrayValues(arrayContexts.get(row), type);
        }

        return field;
    }

    /**
     * Parses the values of a {@link ThreeDimFieldValueContext} while checking
     * the {@link NumericalType} of its values.
     * 
     * @param valueContext
     *            the value context
     * @param type
     *            the expected type of the values
     * @return the values as three-dimensional double array
     */
    private double[][][] getThreeDimFieldValues(ThreeDimFieldValueContext valueContext, NumericalType type) {
        List<TwoDimFieldValueContext> fieldContexts = valueContext.twoDimFieldValue();

        int fields = fieldContexts.size();
        int rows = fieldContexts.get(0).arrayValue().size();
        int columns = fieldContexts.get(0).arrayValue().get(0).singleValue().size();

        double[][][] fieldOfFields = new double[fields][rows][columns];
        for (int field = 0; field < fieldOfFields.length; field++) {
            fieldOfFields[field] = getTwoDimFieldValues(fieldContexts.get(field), type);
        }

        return fieldOfFields;
    }

    /**
     * Checks if the {@link DatContent} of the {@link DatEntry} is an instance
     * of the given class and returns the casted result.
     * 
     * @param <T>
     *            the expected subclass of {@link DatContent}
     * 
     * @param entry
     *            the entry to be checked
     * @param contentType
     *            the expected type
     * @return the casted {@link DatEntry} instance
     * @throws ParseCancellationException
     *             if a type mismatch occurs
     */
    @SuppressWarnings("unchecked")
    private <T extends DatContent> DatEntry<T> ensureContentType(DatEntry<?> entry, Class<T> contentType) {
        DatContent content = entry.getContent();
        if (!contentType.isInstance(content)) {
            String varName = entry.getName();
            String expectedType = entry.getClass().getSimpleName();
            String actualType = contentType.getSimpleName();

            throw new ParseCancellationException("ContentType missmatch: variable \"" + varName
                    + "\" should have type \"" + expectedType + "\" but was of type \"" + actualType + "\"");
        }

        return (DatEntry<T>) entry;
    }
}
