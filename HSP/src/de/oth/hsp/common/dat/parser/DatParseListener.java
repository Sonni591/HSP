package de.oth.hsp.common.dat.parser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import de.oth.hsp.common.dat.AbstractDatFile;
import de.oth.hsp.common.dat.DatEntry;
import de.oth.hsp.common.dat.EntryDesc;
import de.oth.hsp.common.dat.desc.ContentType;
import de.oth.hsp.common.dat.desc.NumericalType;
import de.oth.hsp.common.dat.parser.gen.DatBaseListener;
import de.oth.hsp.common.dat.parser.gen.DatParser.ArrayValueContext;
import de.oth.hsp.common.dat.parser.gen.DatParser.SingleValueContext;
import de.oth.hsp.common.dat.parser.gen.DatParser.ThreeDimFieldValueContext;
import de.oth.hsp.common.dat.parser.gen.DatParser.TwoDimFieldValueContext;
import de.oth.hsp.common.dat.parser.gen.DatParser.VarDeclarationContext;
import de.oth.hsp.common.dat.value.ArrayContent;
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
    private final List<DatEntry<?>> entries = new ArrayList<>();
    private final Queue<EntryDesc> expectedEntries = new LinkedList<>();

    public DatParseListener(Class<? extends AbstractDatFile> datClass) {
        expectedEntries.addAll(AbstractDatFile.getEntryDescriptions(datClass));
    }

    @Override
    public void exitVarDeclaration(VarDeclarationContext ctx) {
        if (ctx.exception != null) {
            return;
        }
        String name = ctx.varName().getText();
        ParserRuleContext valueContext = (ParserRuleContext) ctx.varValue().getChild(0);

        // compare the variable with the one we expected
        EntryDesc entryDesc = expectedEntries.poll();
        if (entryDesc == null) {
            throw new ParseCancellationException("Unexpected variable: \"" + name + "\"");
        }
        String expectedName = entryDesc.name();
        if (!expectedName.equals(name)) {
            throw new ParseCancellationException(
                    "Variable name missmatch: got variable \"" + name + "\" but expected \"" + expectedName + "\"");
        }

        DatEntry<?> entry;
        if (valueContext instanceof SingleValueContext) {
            entry = new DatEntry<SingleContent>(name,
                    getSingleContent((SingleValueContext) valueContext, name, entryDesc));
        } else if (valueContext instanceof ArrayValueContext) {
            entry = new DatEntry<ArrayContent>(name,
                    getArrayContent((ArrayValueContext) valueContext, name, entryDesc));
        } else if (valueContext instanceof TwoDimFieldValueContext) {
            entry = new DatEntry<TwoDimFieldContent>(name,
                    getTwoDimContent((TwoDimFieldValueContext) valueContext, name, entryDesc));
        } else {
            entry = new DatEntry<ThreeDimFieldContent>(name,
                    getThreeDimContent((ThreeDimFieldValueContext) valueContext, name, entryDesc));
        }

        entries.add(entry);
    }

    /**
     * @return the entries created by visiting the ParseTree nodes.
     */
    public List<DatEntry<?>> getEntries() {
        return entries;
    }

    /**
     * Creates a {@link SingleContent} from its {@link SingleValueContext}
     * representation.
     * 
     * @param singleValueContext
     *            the context
     * @param name
     *            the name of the entry
     * @param entryDesc
     *            the description of the entry from the model
     * @return an {@link SingleContent} object containing the parsed value
     */
    private SingleContent getSingleContent(SingleValueContext singleValueContext, String name, EntryDesc entryDesc) {
        checkType(name, entryDesc, ContentType.SINGLE);

        return new SingleContent(getValue(singleValueContext, entryDesc.numType()), entryDesc.numType());
    }

    /**
     * Creates a {@link ArrayContent} from its {@link ArrayValueContext}
     * representation.
     * 
     * @param arrayValueContext
     *            the context
     * @param name
     *            the name of the entry
     * @param entryDesc
     *            the description of the entry from the model
     * @return an {@link ArrayContent} object containing the parsed values
     */
    private ArrayContent getArrayContent(ArrayValueContext arrayValueContext, String name, EntryDesc entryDesc) {
        checkType(name, entryDesc, ContentType.ARRAY);

        double[] values = getArrayValues(arrayValueContext.singleValue(), entryDesc.numType());

        return new ArrayContent(values, entryDesc.numType());
    }

    /**
     * Creates a {@link TwoDimFieldContent} from a
     * {@link TwoDimFieldValueContext} representation.
     * 
     * @param twoDimFieldValueContext
     *            the context
     * @param name
     *            the name of the entry
     * @param entryDesc
     *            the description of the entry from the model
     * @return an {@link TwoDimFieldContent} object containing the parsed value
     */
    private TwoDimFieldContent getTwoDimContent(TwoDimFieldValueContext twoDimFieldValueContext, String name,
            EntryDesc entryDesc) {
        checkType(name, entryDesc, ContentType.TWO_DIM_FIELD);

        double[][] field = getTwoDimFieldValues(twoDimFieldValueContext.arrayValue(), entryDesc.numType());

        return new TwoDimFieldContent(field, entryDesc.numType());
    }

    private ThreeDimFieldContent getThreeDimContent(ThreeDimFieldValueContext threeDimFieldValueContext, String name,
            EntryDesc entryDesc) {
        checkType(name, entryDesc, ContentType.THREE_DIM_FIELD);

        double[][][] fieldOfFields = getThreeDimFieldValues(threeDimFieldValueContext.twoDimFieldValue(),
                entryDesc.numType());

        return new ThreeDimFieldContent(fieldOfFields, entryDesc.numType());
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

    /**
     * Checks if the actual type of the entry fits to the model.
     * 
     * @param name
     *            the name of the entry
     * @param entryDesc
     *            the description of the entry in the model
     * @param expectedType
     *            the expected content type of the entry
     */
    private void checkType(String name, EntryDesc entryDesc, ContentType expectedType) {
        ContentType type = entryDesc.conType();
        if (type != expectedType) {
            throw new ParseCancellationException("Content type missmatch: Variable \"" + name
                    + "\" should have had the type " + expectedType + " but has " + type);
        }
    }
}
