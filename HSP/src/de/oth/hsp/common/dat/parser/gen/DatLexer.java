// Generated from Dat.g4 by ANTLR 4.5.1
package de.oth.hsp.common.dat.parser.gen;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DatLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ASSIGN=1, COMMA=2, SEMI=3, COLON=4, LARRAY=5, RARRAY=6, LFIELD=7, RFIELD=8, 
		Identifier=9, IntegerLiteral=10, FloatingPointLiteral=11, WS=12, COMMENT=13;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ASSIGN", "COMMA", "SEMI", "COLON", "LARRAY", "RARRAY", "LFIELD", "RFIELD", 
		"Identifier", "IntegerLiteral", "FloatingPointLiteral", "Character", "Letter", 
		"DecimalValue", "Digit", "NonZeroDigit", "WS", "COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'='", "','", "';'", "':'", "'['", "']'", "'#['", "']#'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ASSIGN", "COMMA", "SEMI", "COLON", "LARRAY", "RARRAY", "LFIELD", 
		"RFIELD", "Identifier", "IntegerLiteral", "FloatingPointLiteral", "WS", 
		"COMMENT"
	};
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


	public DatLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Dat.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\17x\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\n\3\n\7\n<\n\n\f\n\16\n?\13\n\3\13\5\13B\n\13\3\13\3\13"+
		"\3\f\5\fG\n\f\3\f\3\f\3\f\6\fL\n\f\r\f\16\fM\3\r\3\r\3\16\6\16S\n\16\r"+
		"\16\16\16T\3\17\3\17\3\17\7\17Z\n\17\f\17\16\17]\13\17\5\17_\n\17\3\20"+
		"\3\20\5\20c\n\20\3\21\3\21\3\22\6\22h\n\22\r\22\16\22i\3\22\3\22\3\23"+
		"\3\23\3\23\3\23\7\23r\n\23\f\23\16\23u\13\23\3\23\3\23\2\2\24\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\2\33\2\35\2\37\2!\2#\16"+
		"%\17\3\2\7\6\2\62;C\\aac|\4\2C\\c|\3\2\63;\5\2\13\f\16\17\"\"\4\2\f\f"+
		"\17\17|\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\3\'\3\2\2\2\5)\3\2\2\2\7+\3\2\2\2\t-\3\2"+
		"\2\2\13/\3\2\2\2\r\61\3\2\2\2\17\63\3\2\2\2\21\66\3\2\2\2\239\3\2\2\2"+
		"\25A\3\2\2\2\27F\3\2\2\2\31O\3\2\2\2\33R\3\2\2\2\35^\3\2\2\2\37b\3\2\2"+
		"\2!d\3\2\2\2#g\3\2\2\2%m\3\2\2\2\'(\7?\2\2(\4\3\2\2\2)*\7.\2\2*\6\3\2"+
		"\2\2+,\7=\2\2,\b\3\2\2\2-.\7<\2\2.\n\3\2\2\2/\60\7]\2\2\60\f\3\2\2\2\61"+
		"\62\7_\2\2\62\16\3\2\2\2\63\64\7%\2\2\64\65\7]\2\2\65\20\3\2\2\2\66\67"+
		"\7_\2\2\678\7%\2\28\22\3\2\2\29=\5\33\16\2:<\5\31\r\2;:\3\2\2\2<?\3\2"+
		"\2\2=;\3\2\2\2=>\3\2\2\2>\24\3\2\2\2?=\3\2\2\2@B\7/\2\2A@\3\2\2\2AB\3"+
		"\2\2\2BC\3\2\2\2CD\5\35\17\2D\26\3\2\2\2EG\7/\2\2FE\3\2\2\2FG\3\2\2\2"+
		"GH\3\2\2\2HI\5\35\17\2IK\7\60\2\2JL\5\37\20\2KJ\3\2\2\2LM\3\2\2\2MK\3"+
		"\2\2\2MN\3\2\2\2N\30\3\2\2\2OP\t\2\2\2P\32\3\2\2\2QS\t\3\2\2RQ\3\2\2\2"+
		"ST\3\2\2\2TR\3\2\2\2TU\3\2\2\2U\34\3\2\2\2V_\7\62\2\2W[\5!\21\2XZ\5\37"+
		"\20\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\_\3\2\2\2][\3\2\2\2^V"+
		"\3\2\2\2^W\3\2\2\2_\36\3\2\2\2`c\7\62\2\2ac\5!\21\2b`\3\2\2\2ba\3\2\2"+
		"\2c \3\2\2\2de\t\4\2\2e\"\3\2\2\2fh\t\5\2\2gf\3\2\2\2hi\3\2\2\2ig\3\2"+
		"\2\2ij\3\2\2\2jk\3\2\2\2kl\b\22\2\2l$\3\2\2\2mn\7\61\2\2no\7\61\2\2os"+
		"\3\2\2\2pr\n\6\2\2qp\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2tv\3\2\2\2u"+
		"s\3\2\2\2vw\b\23\2\2w&\3\2\2\2\r\2=AFMT[^bis\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}