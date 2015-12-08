package de.oth.hsp.common.dat.parser;

import java.text.ParseException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import de.oth.hsp.common.dat.AbstractDatFile;
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
    private final Deque<DatEntry<?>> entries = new ArrayDeque<>();

    public DatParseListener(AbstractDatFile datFile) {
        entries.addAll(datFile.getEntries());
    }

    @Override
    public void exitVarDeclaration(VarDeclarationContext ctx) {
        if (ctx.exception != null) {
            return;
        }
        String name = ctx.varName().getText();
        ParserRuleContext valueContext = (ParserRuleContext) ctx.varValue().getChild(0);

        // compare the variable with the one we expected
        final DatEntry<?> entry = entries.pollFirst();
        if (entry == null) {
            throw new ParseCancellationException("Unexpected variable: \"" + name + "\"");
        }
        String expectedName = entry.getName();
        if (!expectedName.equals(name)) {
            throw new ParseCancellationException(
                    "Variable name missmatch: got variable \"" + name + "\" but expected \"" + expectedName + "\"");
        }

        // fill the content
        if (valueContext instanceof SingleValueContext) {
            fillContent(ensureContentType(entry, SingleContent.class), (SingleValueContext) valueContext);
        } else if (valueContext instanceof ArrayValueContext) {
            fillContent(ensureContentType(entry, ArrayContent.class), (ArrayValueContext) valueContext);
        } else if (valueContext instanceof TwoDimFieldValueContext) {
            fillContent(ensureContentType(entry, TwoDimFieldContent.class), (TwoDimFieldValueContext) valueContext);
        } else {
            fillContent(ensureContentType(entry, ThreeDimFieldContent.class), (ThreeDimFieldValueContext) valueContext);
        }
    }

    @Override
    public void exitDatBody(DatBodyContext ctx) {
        if (!entries.isEmpty()) {
            String missingEntryName = entries.getFirst().getName();
            throw new ParseCancellationException("Variable not found: \"" + missingEntryName + "\"");
        }
    }

    private void fillContent(DatEntry<SingleContent> entry, SingleValueContext valueContext) {
        NumericalType type = entry.getContent().getType();
        entry.getContent().setValue(getValue(valueContext, type));
    }

    private void fillContent(DatEntry<ArrayContent> entry, ArrayValueContext valueContext) {
        NumericalType type = entry.getContent().getType();
        entry.getContent().setValues(getArrayValues(valueContext.singleValue(), type));
    }

    private void fillContent(DatEntry<TwoDimFieldContent> entry, TwoDimFieldValueContext valueContext) {
        NumericalType type = entry.getContent().getType();
        entry.getContent().setValues(getTwoDimFieldValues(valueContext.arrayValue(), type));
    }

    private void fillContent(DatEntry<ThreeDimFieldContent> entry, ThreeDimFieldValueContext valueContext) {
        NumericalType type = entry.getContent().getType();
        entry.getContent().setValues(getThreeDimFieldValues(valueContext.twoDimFieldValue(), type));
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
    private double getValue(SingleValueContext valueContext, NumericalType numType) {
        try {
            Number number = numType.getFormat().parse(valueContext.getText());
            return number.doubleValue();
        } catch (ParseException e) {
            throw new ParseCancellationException("Numerical type missmatch: expected type " + numType, e);
        }
    }

    /**
     * Parses the values from a List of {@link SingleValueContext} objects while
     * checking its {@link NumericalType}.
     * 
     * @param valueContexts
     *            the value contexts
     * @param type
     *            the expected type of the values
     * @return the values as a double array
     */
    private double[] getArrayValues(List<SingleValueContext> valueContexts, NumericalType type) {
        double[] values = new double[valueContexts.size()];

        for (int i = 0; i < values.length; i++) {
            values[i] = getValue(valueContexts.get(i), type);
        }

        return values;
    }

    /**
     * Parses the values from a List of {@link ArrayValueContext} objects while
     * checking their {@link NumericalType}.
     * 
     * @param valueContexts
     *            the value contexts
     * @param type
     *            the expected type of the values
     * @return the values as a two-dimensional double array
     */
    private double[][] getTwoDimFieldValues(List<ArrayValueContext> valueContexts, NumericalType type) {
        int rows = valueContexts.size();
        int columns = valueContexts.get(0).singleValue().size();

        double[][] field = new double[rows][columns];
        for (int row = 0; row < field.length; row++) {
            field[row] = getArrayValues(valueContexts.get(row).singleValue(), type);
        }

        return field;
    }

    /**
     * Parses the values from a List of {@link TwoDimFieldValueContext} objects
     * while checking their {@link NumericalType}.
     * 
     * @param valueContexts
     *            the value contexts
     * @param type
     *            the expected type of the values
     * @return the values as three-dimensional double array
     */
    private double[][][] getThreeDimFieldValues(List<TwoDimFieldValueContext> twoDimFieldValue, NumericalType type) {
        int fields = twoDimFieldValue.size();
        int rows = twoDimFieldValue.get(0).arrayValue().size();
        int columns = twoDimFieldValue.get(0).arrayValue().get(0).singleValue().size();

        double[][][] fieldOfFields = new double[fields][rows][columns];
        for (int field = 0; field < fieldOfFields.length; field++) {
            fieldOfFields[field] = getTwoDimFieldValues(twoDimFieldValue.get(field).arrayValue(), type);
        }

        return fieldOfFields;
    }

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
