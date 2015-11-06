package de.oth.hsp.common.dat.parser;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import de.oth.hsp.common.dat.DatEntry;
import de.oth.hsp.common.dat.parser.gen.DatBaseListener;
import de.oth.hsp.common.dat.parser.gen.DatParser.ArrayValueContext;
import de.oth.hsp.common.dat.parser.gen.DatParser.FieldEntryContext;
import de.oth.hsp.common.dat.parser.gen.DatParser.FieldValueContext;
import de.oth.hsp.common.dat.parser.gen.DatParser.SingleValueContext;
import de.oth.hsp.common.dat.parser.gen.DatParser.VarDeclarationContext;
import de.oth.hsp.common.dat.parser.gen.DatParser.VarValueContext;
import de.oth.hsp.common.dat.value.ArrayContent;
import de.oth.hsp.common.dat.value.DatContent;
import de.oth.hsp.common.dat.value.FieldContent;
import de.oth.hsp.common.dat.value.SingleContent;

/**
 * Generates a List of DatEntry objects when applied to a ParseTreeWalker.
 * 
 * @author Thomas Butz
 *
 */
public class DatParseListener extends DatBaseListener {
	private final List<DatEntry<DatContent>> entries = new ArrayList<>();
	
	@Override
	public void exitVarDeclaration(VarDeclarationContext ctx) {
		if (ctx.exception != null) {
			return;
		}
		String name = ctx.varName().getText();
		DatContent content = getContent(ctx.varValue());

		entries.add(new DatEntry<DatContent>(name, content));
	}


	/**
	 * Creates a {@link DatContent} from its {@link VarValueContext}
	 * representation.
	 * 
	 * @param varValueContext
	 *            the context
	 * @return an object of a {@link DatContent} child class
	 */
	private DatContent getContent(VarValueContext varValueContext) {
		ParseTree child = varValueContext.getChild(0);

		if (child instanceof SingleValueContext) {
			return getSingleContent((SingleValueContext) child);
		}

		if (child instanceof ArrayValueContext) {
			return getArrayContent((ArrayValueContext) child);
		}

		return getFieldContent((FieldValueContext) child);
	}

	/**
	 * Creates a {@link SingleContent} from its {@link SingleValueContext}
	 * representation.
	 * 
	 * @param singleValueContext
	 *            the context
	 * @return an {@link SingleContent} object containing the parsed value
	 */
	private SingleContent getSingleContent(SingleValueContext singleValueContext) {
		Double value = Double.parseDouble(singleValueContext.getChild(0).getText());

		return new SingleContent(value);
	}

	/**
	 * Creates a {@link ArrayContent} from its {@link ArrayValueContext}
	 * representation.
	 * 
	 * @param arrayValueContext
	 *            the context
	 * @return an {@link ArrayContent} object containing the parsed values
	 */
	private ArrayContent getArrayContent(ArrayValueContext arrayValueContext) {
		
		List<SingleValueContext> childreen = arrayValueContext.singleValue();
		double[] values = new double[childreen.size()];
		
		for (int i = 0; i < values.length; i++) {
			values[i] = getSingleContent(childreen.get(i)).getValue();
		}
		
		
		return new ArrayContent(values);
	}

	/**
	 * Creates a {@link FieldContent} from its {@link FieldValueContext}
	 * representation.
	 * 
	 * @param fieldValueContext
	 *            the context
	 * @return an {@link FieldContent} object containing the parsed values
	 */
	private FieldContent<DatContent> getFieldContent(FieldValueContext fieldValueContext) {
		FieldContent<DatContent> field = new FieldContent<>();
		
		for (FieldEntryContext entry : fieldValueContext.fieldEntry()) {
			int index = Integer.parseInt(entry.IntegerLiteral().getText());
			
			field.put(index, getContent(entry.varValue()));
		}
		
		return field;
	}

	/**
	 * @return the entries created by visiting the ParseTree nodes.
	 */
	public List<DatEntry<DatContent>> getEntries() {
		return entries;
	}
}
