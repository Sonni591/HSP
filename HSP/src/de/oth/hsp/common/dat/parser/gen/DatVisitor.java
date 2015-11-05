// Generated from Dat.g4 by ANTLR 4.5.1
package de.oth.hsp.common.dat.parser.gen;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DatParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DatVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DatParser#datBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDatBody(DatParser.DatBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link DatParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(DatParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DatParser#varName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarName(DatParser.VarNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link DatParser#varValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarValue(DatParser.VarValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link DatParser#singleValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleValue(DatParser.SingleValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link DatParser#arrayValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayValue(DatParser.ArrayValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link DatParser#fieldValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldValue(DatParser.FieldValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link DatParser#fieldEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldEntry(DatParser.FieldEntryContext ctx);
}