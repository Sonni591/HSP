// Generated from git/HSP/HSP/resources/Dat.g4 by ANTLR 4.5.1
package de.oth.hsp.common.dat.parser.gen;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DatParser}.
 */
public interface DatListener extends ParseTreeListener {
    /**
     * Enter a parse tree produced by {@link DatParser#datBody}.
     * 
     * @param ctx
     *            the parse tree
     */
    void enterDatBody(DatParser.DatBodyContext ctx);

    /**
     * Exit a parse tree produced by {@link DatParser#datBody}.
     * 
     * @param ctx
     *            the parse tree
     */
    void exitDatBody(DatParser.DatBodyContext ctx);

    /**
     * Enter a parse tree produced by {@link DatParser#varDeclaration}.
     * 
     * @param ctx
     *            the parse tree
     */
    void enterVarDeclaration(DatParser.VarDeclarationContext ctx);

    /**
     * Exit a parse tree produced by {@link DatParser#varDeclaration}.
     * 
     * @param ctx
     *            the parse tree
     */
    void exitVarDeclaration(DatParser.VarDeclarationContext ctx);

    /**
     * Enter a parse tree produced by {@link DatParser#varName}.
     * 
     * @param ctx
     *            the parse tree
     */
    void enterVarName(DatParser.VarNameContext ctx);

    /**
     * Exit a parse tree produced by {@link DatParser#varName}.
     * 
     * @param ctx
     *            the parse tree
     */
    void exitVarName(DatParser.VarNameContext ctx);

    /**
     * Enter a parse tree produced by {@link DatParser#varValue}.
     * 
     * @param ctx
     *            the parse tree
     */
    void enterVarValue(DatParser.VarValueContext ctx);

    /**
     * Exit a parse tree produced by {@link DatParser#varValue}.
     * 
     * @param ctx
     *            the parse tree
     */
    void exitVarValue(DatParser.VarValueContext ctx);

    /**
     * Enter a parse tree produced by {@link DatParser#singleValue}.
     * 
     * @param ctx
     *            the parse tree
     */
    void enterSingleValue(DatParser.SingleValueContext ctx);

    /**
     * Exit a parse tree produced by {@link DatParser#singleValue}.
     * 
     * @param ctx
     *            the parse tree
     */
    void exitSingleValue(DatParser.SingleValueContext ctx);

    /**
     * Enter a parse tree produced by {@link DatParser#arrayValue}.
     * 
     * @param ctx
     *            the parse tree
     */
    void enterArrayValue(DatParser.ArrayValueContext ctx);

    /**
     * Exit a parse tree produced by {@link DatParser#arrayValue}.
     * 
     * @param ctx
     *            the parse tree
     */
    void exitArrayValue(DatParser.ArrayValueContext ctx);

    /**
     * Enter a parse tree produced by {@link DatParser#twoDimFieldValue}.
     * 
     * @param ctx
     *            the parse tree
     */
    void enterTwoDimFieldValue(DatParser.TwoDimFieldValueContext ctx);

    /**
     * Exit a parse tree produced by {@link DatParser#twoDimFieldValue}.
     * 
     * @param ctx
     *            the parse tree
     */
    void exitTwoDimFieldValue(DatParser.TwoDimFieldValueContext ctx);

    /**
     * Enter a parse tree produced by {@link DatParser#threeDimFieldValue}.
     * 
     * @param ctx
     *            the parse tree
     */
    void enterThreeDimFieldValue(DatParser.ThreeDimFieldValueContext ctx);

    /**
     * Exit a parse tree produced by {@link DatParser#threeDimFieldValue}.
     * 
     * @param ctx
     *            the parse tree
     */
    void exitThreeDimFieldValue(DatParser.ThreeDimFieldValueContext ctx);
}