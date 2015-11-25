// Generated from git/HSP/HSP/resources/Dat.g4 by ANTLR 4.5.1
package de.oth.hsp.common.dat.parser.gen;

import java.util.List;

import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast" })
public class DatParser extends Parser {
    static {
        RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
    public static final int ASSIGN = 1, COMMA = 2, SEMI = 3, COLON = 4, LARRAY = 5, RARRAY = 6, LFIELD = 7, RFIELD = 8,
            Identifier = 9, IntegerLiteral = 10, FloatingPointLiteral = 11, WS = 12, COMMENT = 13;
    public static final int RULE_datBody = 0, RULE_varDeclaration = 1, RULE_varName = 2, RULE_varValue = 3,
            RULE_singleValue = 4, RULE_arrayValue = 5, RULE_twoDimFieldValue = 6, RULE_threeDimFieldValue = 7;
    public static final String[] ruleNames = { "datBody", "varDeclaration", "varName", "varValue", "singleValue",
            "arrayValue", "twoDimFieldValue", "threeDimFieldValue" };

    private static final String[] _LITERAL_NAMES = { null, "'='", "','", "';'", "':'", "'['", "']'", "'#['", "']#'" };
    private static final String[] _SYMBOLIC_NAMES = { null, "ASSIGN", "COMMA", "SEMI", "COLON", "LARRAY", "RARRAY",
            "LFIELD", "RFIELD", "Identifier", "IntegerLiteral", "FloatingPointLiteral", "WS", "COMMENT" };
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;

    static {
        tokenNames = new String[_SYMBOLIC_NAMES.length];
        for (int i = 0; i < tokenNames.length; i++) {
            tokenNames[i] = VOCABULARY.getLiteralName(i);
            if (tokenNames[i] == null) {
                tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }

            if (tokenNames[i] == null) {
                tokenNames[i] = "<INVALID>";
            }
        }
    }

    @Override
    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override

    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }

    @Override
    public String getGrammarFileName() {
        return "Dat.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public DatParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    public static class DatBodyContext extends ParserRuleContext {
        public TerminalNode EOF() {
            return getToken(DatParser.EOF, 0);
        }

        public List<VarDeclarationContext> varDeclaration() {
            return getRuleContexts(VarDeclarationContext.class);
        }

        public VarDeclarationContext varDeclaration(int i) {
            return getRuleContext(VarDeclarationContext.class, i);
        }

        public DatBodyContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_datBody;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof DatListener)
                ((DatListener) listener).enterDatBody(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof DatListener)
                ((DatListener) listener).exitDatBody(this);
        }
    }

    public final DatBodyContext datBody() throws RecognitionException {
        DatBodyContext _localctx = new DatBodyContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_datBody);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(19);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == Identifier) {
                    {
                        {
                            setState(16);
                            varDeclaration();
                        }
                    }
                    setState(21);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(22);
                match(EOF);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class VarDeclarationContext extends ParserRuleContext {
        public VarNameContext varName() {
            return getRuleContext(VarNameContext.class, 0);
        }

        public VarValueContext varValue() {
            return getRuleContext(VarValueContext.class, 0);
        }

        public VarDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_varDeclaration;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof DatListener)
                ((DatListener) listener).enterVarDeclaration(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof DatListener)
                ((DatListener) listener).exitVarDeclaration(this);
        }
    }

    public final VarDeclarationContext varDeclaration() throws RecognitionException {
        VarDeclarationContext _localctx = new VarDeclarationContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_varDeclaration);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(24);
                varName();
                setState(25);
                match(ASSIGN);
                setState(26);
                varValue();
                setState(27);
                match(SEMI);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class VarNameContext extends ParserRuleContext {
        public TerminalNode Identifier() {
            return getToken(DatParser.Identifier, 0);
        }

        public VarNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_varName;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof DatListener)
                ((DatListener) listener).enterVarName(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof DatListener)
                ((DatListener) listener).exitVarName(this);
        }
    }

    public final VarNameContext varName() throws RecognitionException {
        VarNameContext _localctx = new VarNameContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_varName);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(29);
                match(Identifier);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class VarValueContext extends ParserRuleContext {
        public SingleValueContext singleValue() {
            return getRuleContext(SingleValueContext.class, 0);
        }

        public ArrayValueContext arrayValue() {
            return getRuleContext(ArrayValueContext.class, 0);
        }

        public TwoDimFieldValueContext twoDimFieldValue() {
            return getRuleContext(TwoDimFieldValueContext.class, 0);
        }

        public ThreeDimFieldValueContext threeDimFieldValue() {
            return getRuleContext(ThreeDimFieldValueContext.class, 0);
        }

        public VarValueContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_varValue;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof DatListener)
                ((DatListener) listener).enterVarValue(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof DatListener)
                ((DatListener) listener).exitVarValue(this);
        }
    }

    public final VarValueContext varValue() throws RecognitionException {
        VarValueContext _localctx = new VarValueContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_varValue);
        try {
            setState(35);
            switch (getInterpreter().adaptivePredict(_input, 1, _ctx)) {
            case 1:
                enterOuterAlt(_localctx, 1); {
                setState(31);
                singleValue();
            }
                break;
            case 2:
                enterOuterAlt(_localctx, 2); {
                setState(32);
                arrayValue();
            }
                break;
            case 3:
                enterOuterAlt(_localctx, 3); {
                setState(33);
                twoDimFieldValue();
            }
                break;
            case 4:
                enterOuterAlt(_localctx, 4); {
                setState(34);
                threeDimFieldValue();
            }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class SingleValueContext extends ParserRuleContext {
        public TerminalNode IntegerLiteral() {
            return getToken(DatParser.IntegerLiteral, 0);
        }

        public TerminalNode FloatingPointLiteral() {
            return getToken(DatParser.FloatingPointLiteral, 0);
        }

        public SingleValueContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_singleValue;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof DatListener)
                ((DatListener) listener).enterSingleValue(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof DatListener)
                ((DatListener) listener).exitSingleValue(this);
        }
    }

    public final SingleValueContext singleValue() throws RecognitionException {
        SingleValueContext _localctx = new SingleValueContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_singleValue);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(37);
                _la = _input.LA(1);
                if (!(_la == IntegerLiteral || _la == FloatingPointLiteral)) {
                    _errHandler.recoverInline(this);
                } else {
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ArrayValueContext extends ParserRuleContext {
        public List<SingleValueContext> singleValue() {
            return getRuleContexts(SingleValueContext.class);
        }

        public SingleValueContext singleValue(int i) {
            return getRuleContext(SingleValueContext.class, i);
        }

        public List<TerminalNode> IntegerLiteral() {
            return getTokens(DatParser.IntegerLiteral);
        }

        public TerminalNode IntegerLiteral(int i) {
            return getToken(DatParser.IntegerLiteral, i);
        }

        public ArrayValueContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_arrayValue;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof DatListener)
                ((DatListener) listener).enterArrayValue(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof DatListener)
                ((DatListener) listener).exitArrayValue(this);
        }
    }

    public final ArrayValueContext arrayValue() throws RecognitionException {
        ArrayValueContext _localctx = new ArrayValueContext(_ctx, getState());
        enterRule(_localctx, 10, RULE_arrayValue);
        int _la;
        try {
            int _alt;
            setState(62);
            switch (_input.LA(1)) {
            case LARRAY:
                enterOuterAlt(_localctx, 1); {
                setState(39);
                match(LARRAY);
                setState(46);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 3, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(40);
                                singleValue();
                                setState(42);
                                _la = _input.LA(1);
                                if (_la == COMMA) {
                                    {
                                        setState(41);
                                        match(COMMA);
                                    }
                                }

                            }
                        }
                    }
                    setState(48);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 3, _ctx);
                }
                setState(49);
                singleValue();
                setState(50);
                match(RARRAY);
            }
                break;
            case LFIELD:
                enterOuterAlt(_localctx, 2); {
                setState(52);
                match(LFIELD);
                setState(56);
                _errHandler.sync(this);
                _la = _input.LA(1);
                do {
                    {
                        {
                            setState(53);
                            match(IntegerLiteral);
                            setState(54);
                            match(COLON);
                            setState(55);
                            singleValue();
                        }
                    }
                    setState(58);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                } while (_la == IntegerLiteral);
                setState(60);
                match(RFIELD);
            }
                break;
            default:
                throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class TwoDimFieldValueContext extends ParserRuleContext {
        public List<TerminalNode> IntegerLiteral() {
            return getTokens(DatParser.IntegerLiteral);
        }

        public TerminalNode IntegerLiteral(int i) {
            return getToken(DatParser.IntegerLiteral, i);
        }

        public List<ArrayValueContext> arrayValue() {
            return getRuleContexts(ArrayValueContext.class);
        }

        public ArrayValueContext arrayValue(int i) {
            return getRuleContext(ArrayValueContext.class, i);
        }

        public TwoDimFieldValueContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_twoDimFieldValue;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof DatListener)
                ((DatListener) listener).enterTwoDimFieldValue(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof DatListener)
                ((DatListener) listener).exitTwoDimFieldValue(this);
        }
    }

    public final TwoDimFieldValueContext twoDimFieldValue() throws RecognitionException {
        TwoDimFieldValueContext _localctx = new TwoDimFieldValueContext(_ctx, getState());
        enterRule(_localctx, 12, RULE_twoDimFieldValue);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(64);
                match(LFIELD);
                setState(68);
                _errHandler.sync(this);
                _la = _input.LA(1);
                do {
                    {
                        {
                            setState(65);
                            match(IntegerLiteral);
                            setState(66);
                            match(COLON);
                            setState(67);
                            arrayValue();
                        }
                    }
                    setState(70);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                } while (_la == IntegerLiteral);
                setState(72);
                match(RFIELD);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ThreeDimFieldValueContext extends ParserRuleContext {
        public List<TerminalNode> IntegerLiteral() {
            return getTokens(DatParser.IntegerLiteral);
        }

        public TerminalNode IntegerLiteral(int i) {
            return getToken(DatParser.IntegerLiteral, i);
        }

        public List<TwoDimFieldValueContext> twoDimFieldValue() {
            return getRuleContexts(TwoDimFieldValueContext.class);
        }

        public TwoDimFieldValueContext twoDimFieldValue(int i) {
            return getRuleContext(TwoDimFieldValueContext.class, i);
        }

        public ThreeDimFieldValueContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_threeDimFieldValue;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof DatListener)
                ((DatListener) listener).enterThreeDimFieldValue(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof DatListener)
                ((DatListener) listener).exitThreeDimFieldValue(this);
        }
    }

    public final ThreeDimFieldValueContext threeDimFieldValue() throws RecognitionException {
        ThreeDimFieldValueContext _localctx = new ThreeDimFieldValueContext(_ctx, getState());
        enterRule(_localctx, 14, RULE_threeDimFieldValue);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(74);
                match(LFIELD);
                setState(78);
                _errHandler.sync(this);
                _la = _input.LA(1);
                do {
                    {
                        {
                            setState(75);
                            match(IntegerLiteral);
                            setState(76);
                            match(COLON);
                            setState(77);
                            twoDimFieldValue();
                        }
                    }
                    setState(80);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                } while (_la == IntegerLiteral);
                setState(82);
                match(RFIELD);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static final String _serializedATN = "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\17W\4\2\t\2\4\3\t"
            + "\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\7\2\24\n\2\f\2"
            + "\16\2\27\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\5\5"
            + "&\n\5\3\6\3\6\3\7\3\7\3\7\5\7-\n\7\7\7/\n\7\f\7\16\7\62\13\7\3\7\3\7\3"
            + "\7\3\7\3\7\3\7\3\7\6\7;\n\7\r\7\16\7<\3\7\3\7\5\7A\n\7\3\b\3\b\3\b\3\b"
            + "\6\bG\n\b\r\b\16\bH\3\b\3\b\3\t\3\t\3\t\3\t\6\tQ\n\t\r\t\16\tR\3\t\3\t"
            + "\3\t\2\2\n\2\4\6\b\n\f\16\20\2\3\3\2\f\rX\2\25\3\2\2\2\4\32\3\2\2\2\6"
            + "\37\3\2\2\2\b%\3\2\2\2\n\'\3\2\2\2\f@\3\2\2\2\16B\3\2\2\2\20L\3\2\2\2"
            + "\22\24\5\4\3\2\23\22\3\2\2\2\24\27\3\2\2\2\25\23\3\2\2\2\25\26\3\2\2\2"
            + "\26\30\3\2\2\2\27\25\3\2\2\2\30\31\7\2\2\3\31\3\3\2\2\2\32\33\5\6\4\2"
            + "\33\34\7\3\2\2\34\35\5\b\5\2\35\36\7\5\2\2\36\5\3\2\2\2\37 \7\13\2\2 "
            + "\7\3\2\2\2!&\5\n\6\2\"&\5\f\7\2#&\5\16\b\2$&\5\20\t\2%!\3\2\2\2%\"\3\2"
            + "\2\2%#\3\2\2\2%$\3\2\2\2&\t\3\2\2\2\'(\t\2\2\2(\13\3\2\2\2)\60\7\7\2\2"
            + "*,\5\n\6\2+-\7\4\2\2,+\3\2\2\2,-\3\2\2\2-/\3\2\2\2.*\3\2\2\2/\62\3\2\2"
            + "\2\60.\3\2\2\2\60\61\3\2\2\2\61\63\3\2\2\2\62\60\3\2\2\2\63\64\5\n\6\2"
            + "\64\65\7\b\2\2\65A\3\2\2\2\66:\7\t\2\2\678\7\f\2\289\7\6\2\29;\5\n\6\2"
            + ":\67\3\2\2\2;<\3\2\2\2<:\3\2\2\2<=\3\2\2\2=>\3\2\2\2>?\7\n\2\2?A\3\2\2"
            + "\2@)\3\2\2\2@\66\3\2\2\2A\r\3\2\2\2BF\7\t\2\2CD\7\f\2\2DE\7\6\2\2EG\5"
            + "\f\7\2FC\3\2\2\2GH\3\2\2\2HF\3\2\2\2HI\3\2\2\2IJ\3\2\2\2JK\7\n\2\2K\17"
            + "\3\2\2\2LP\7\t\2\2MN\7\f\2\2NO\7\6\2\2OQ\5\16\b\2PM\3\2\2\2QR\3\2\2\2"
            + "RP\3\2\2\2RS\3\2\2\2ST\3\2\2\2TU\7\n\2\2U\21\3\2\2\2\n\25%,\60<@HR";
    public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}