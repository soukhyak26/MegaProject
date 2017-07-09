// Generated from com/affaince/subscription/PaymentGrammar.g4 by ANTLR 4.5
package com.affaince.subscription.compiler;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PaymentGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PAY=1, CURRENT_SUBSCRIPTION_AMOUNT=2, SUBSCRIPTION_CONFIRMATION=3, RESIDUAL_DUE_PAYMENT=4, 
		AFTER=5, BEFORE=6, OF=7, DELIVERIES=8, DELIVERY=9, DEFAULT=10, PROPORTION=11, 
		PERCENTAGE=12, IN=13, ON=14, N=15, REMAININGN=16, AMOUNT=17, FOR=18, EACH=19, 
		AND=20, OR=21, MULT=22, DIV=23, PLUS=24, MINUS=25, GT=26, GE=27, LT=28, 
		LE=29, EQ=30, DECIMAL=31, IDENTIFIER=32, SEMI=33, COMMA=34, COMMENT=35, 
		WS=36;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"PAY", "CURRENT_SUBSCRIPTION_AMOUNT", "SUBSCRIPTION_CONFIRMATION", "RESIDUAL_DUE_PAYMENT", 
		"AFTER", "BEFORE", "OF", "DELIVERIES", "DELIVERY", "DEFAULT", "PROPORTION", 
		"PERCENTAGE", "IN", "ON", "N", "REMAININGN", "AMOUNT", "FOR", "EACH", 
		"AND", "OR", "MULT", "DIV", "PLUS", "MINUS", "GT", "GE", "LT", "LE", "EQ", 
		"DECIMAL", "IDENTIFIER", "SEMI", "COMMA", "COMMENT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'pay'", "'current subscription amount'", "'subscription confirmation'", 
		"'residual due amount'", "'after'", "'before'", "'of'", "'deliveries'", 
		"'delivery'", "'default'", "'proportion'", "'%'", "'in'", "'on'", "'N'", 
		"'remaining-N'", "'amount'", "'for'", "'each'", "'and'", "'or'", "'*'", 
		"'/'", "'+'", "'-'", "'>'", "'>='", "'<'", "'<='", "'='", null, null, 
		"';'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PAY", "CURRENT_SUBSCRIPTION_AMOUNT", "SUBSCRIPTION_CONFIRMATION", 
		"RESIDUAL_DUE_PAYMENT", "AFTER", "BEFORE", "OF", "DELIVERIES", "DELIVERY", 
		"DEFAULT", "PROPORTION", "PERCENTAGE", "IN", "ON", "N", "REMAININGN", 
		"AMOUNT", "FOR", "EACH", "AND", "OR", "MULT", "DIV", "PLUS", "MINUS", 
		"GT", "GE", "LT", "LE", "EQ", "DECIMAL", "IDENTIFIER", "SEMI", "COMMA", 
		"COMMENT", "WS"
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


	public PaymentGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PaymentGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2&\u0140\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3"+
		"\25\3\25\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3"+
		"\33\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3 \5 \u0113\n \3"+
		" \6 \u0116\n \r \16 \u0117\3 \3 \6 \u011c\n \r \16 \u011d\5 \u0120\n "+
		"\3!\3!\7!\u0124\n!\f!\16!\u0127\13!\3\"\3\"\3#\3#\3$\3$\3$\3$\6$\u0131"+
		"\n$\r$\16$\u0132\3$\5$\u0136\n$\3$\3$\3%\6%\u013b\n%\r%\16%\u013c\3%\3"+
		"%\3\u0132\2&\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34"+
		"\67\359\36;\37= ?!A\"C#E$G%I&\3\2\7\3\2\62;\5\2C\\aac|\6\2\62;C\\aac|"+
		"\3\3\f\f\5\2\13\f\16\17\"\"\u0146\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2"+
		"\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2"+
		"\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\3K\3\2\2\2\5O\3\2\2\2\7"+
		"k\3\2\2\2\t\u0085\3\2\2\2\13\u0099\3\2\2\2\r\u009f\3\2\2\2\17\u00a6\3"+
		"\2\2\2\21\u00a9\3\2\2\2\23\u00b4\3\2\2\2\25\u00bd\3\2\2\2\27\u00c5\3\2"+
		"\2\2\31\u00d0\3\2\2\2\33\u00d2\3\2\2\2\35\u00d5\3\2\2\2\37\u00d8\3\2\2"+
		"\2!\u00da\3\2\2\2#\u00e6\3\2\2\2%\u00ed\3\2\2\2\'\u00f1\3\2\2\2)\u00f6"+
		"\3\2\2\2+\u00fa\3\2\2\2-\u00fd\3\2\2\2/\u00ff\3\2\2\2\61\u0101\3\2\2\2"+
		"\63\u0103\3\2\2\2\65\u0105\3\2\2\2\67\u0107\3\2\2\29\u010a\3\2\2\2;\u010c"+
		"\3\2\2\2=\u010f\3\2\2\2?\u0112\3\2\2\2A\u0121\3\2\2\2C\u0128\3\2\2\2E"+
		"\u012a\3\2\2\2G\u012c\3\2\2\2I\u013a\3\2\2\2KL\7r\2\2LM\7c\2\2MN\7{\2"+
		"\2N\4\3\2\2\2OP\7e\2\2PQ\7w\2\2QR\7t\2\2RS\7t\2\2ST\7g\2\2TU\7p\2\2UV"+
		"\7v\2\2VW\7\"\2\2WX\7u\2\2XY\7w\2\2YZ\7d\2\2Z[\7u\2\2[\\\7e\2\2\\]\7t"+
		"\2\2]^\7k\2\2^_\7r\2\2_`\7v\2\2`a\7k\2\2ab\7q\2\2bc\7p\2\2cd\7\"\2\2d"+
		"e\7c\2\2ef\7o\2\2fg\7q\2\2gh\7w\2\2hi\7p\2\2ij\7v\2\2j\6\3\2\2\2kl\7u"+
		"\2\2lm\7w\2\2mn\7d\2\2no\7u\2\2op\7e\2\2pq\7t\2\2qr\7k\2\2rs\7r\2\2st"+
		"\7v\2\2tu\7k\2\2uv\7q\2\2vw\7p\2\2wx\7\"\2\2xy\7e\2\2yz\7q\2\2z{\7p\2"+
		"\2{|\7h\2\2|}\7k\2\2}~\7t\2\2~\177\7o\2\2\177\u0080\7c\2\2\u0080\u0081"+
		"\7v\2\2\u0081\u0082\7k\2\2\u0082\u0083\7q\2\2\u0083\u0084\7p\2\2\u0084"+
		"\b\3\2\2\2\u0085\u0086\7t\2\2\u0086\u0087\7g\2\2\u0087\u0088\7u\2\2\u0088"+
		"\u0089\7k\2\2\u0089\u008a\7f\2\2\u008a\u008b\7w\2\2\u008b\u008c\7c\2\2"+
		"\u008c\u008d\7n\2\2\u008d\u008e\7\"\2\2\u008e\u008f\7f\2\2\u008f\u0090"+
		"\7w\2\2\u0090\u0091\7g\2\2\u0091\u0092\7\"\2\2\u0092\u0093\7c\2\2\u0093"+
		"\u0094\7o\2\2\u0094\u0095\7q\2\2\u0095\u0096\7w\2\2\u0096\u0097\7p\2\2"+
		"\u0097\u0098\7v\2\2\u0098\n\3\2\2\2\u0099\u009a\7c\2\2\u009a\u009b\7h"+
		"\2\2\u009b\u009c\7v\2\2\u009c\u009d\7g\2\2\u009d\u009e\7t\2\2\u009e\f"+
		"\3\2\2\2\u009f\u00a0\7d\2\2\u00a0\u00a1\7g\2\2\u00a1\u00a2\7h\2\2\u00a2"+
		"\u00a3\7q\2\2\u00a3\u00a4\7t\2\2\u00a4\u00a5\7g\2\2\u00a5\16\3\2\2\2\u00a6"+
		"\u00a7\7q\2\2\u00a7\u00a8\7h\2\2\u00a8\20\3\2\2\2\u00a9\u00aa\7f\2\2\u00aa"+
		"\u00ab\7g\2\2\u00ab\u00ac\7n\2\2\u00ac\u00ad\7k\2\2\u00ad\u00ae\7x\2\2"+
		"\u00ae\u00af\7g\2\2\u00af\u00b0\7t\2\2\u00b0\u00b1\7k\2\2\u00b1\u00b2"+
		"\7g\2\2\u00b2\u00b3\7u\2\2\u00b3\22\3\2\2\2\u00b4\u00b5\7f\2\2\u00b5\u00b6"+
		"\7g\2\2\u00b6\u00b7\7n\2\2\u00b7\u00b8\7k\2\2\u00b8\u00b9\7x\2\2\u00b9"+
		"\u00ba\7g\2\2\u00ba\u00bb\7t\2\2\u00bb\u00bc\7{\2\2\u00bc\24\3\2\2\2\u00bd"+
		"\u00be\7f\2\2\u00be\u00bf\7g\2\2\u00bf\u00c0\7h\2\2\u00c0\u00c1\7c\2\2"+
		"\u00c1\u00c2\7w\2\2\u00c2\u00c3\7n\2\2\u00c3\u00c4\7v\2\2\u00c4\26\3\2"+
		"\2\2\u00c5\u00c6\7r\2\2\u00c6\u00c7\7t\2\2\u00c7\u00c8\7q\2\2\u00c8\u00c9"+
		"\7r\2\2\u00c9\u00ca\7q\2\2\u00ca\u00cb\7t\2\2\u00cb\u00cc\7v\2\2\u00cc"+
		"\u00cd\7k\2\2\u00cd\u00ce\7q\2\2\u00ce\u00cf\7p\2\2\u00cf\30\3\2\2\2\u00d0"+
		"\u00d1\7\'\2\2\u00d1\32\3\2\2\2\u00d2\u00d3\7k\2\2\u00d3\u00d4\7p\2\2"+
		"\u00d4\34\3\2\2\2\u00d5\u00d6\7q\2\2\u00d6\u00d7\7p\2\2\u00d7\36\3\2\2"+
		"\2\u00d8\u00d9\7P\2\2\u00d9 \3\2\2\2\u00da\u00db\7t\2\2\u00db\u00dc\7"+
		"g\2\2\u00dc\u00dd\7o\2\2\u00dd\u00de\7c\2\2\u00de\u00df\7k\2\2\u00df\u00e0"+
		"\7p\2\2\u00e0\u00e1\7k\2\2\u00e1\u00e2\7p\2\2\u00e2\u00e3\7i\2\2\u00e3"+
		"\u00e4\7/\2\2\u00e4\u00e5\7P\2\2\u00e5\"\3\2\2\2\u00e6\u00e7\7c\2\2\u00e7"+
		"\u00e8\7o\2\2\u00e8\u00e9\7q\2\2\u00e9\u00ea\7w\2\2\u00ea\u00eb\7p\2\2"+
		"\u00eb\u00ec\7v\2\2\u00ec$\3\2\2\2\u00ed\u00ee\7h\2\2\u00ee\u00ef\7q\2"+
		"\2\u00ef\u00f0\7t\2\2\u00f0&\3\2\2\2\u00f1\u00f2\7g\2\2\u00f2\u00f3\7"+
		"c\2\2\u00f3\u00f4\7e\2\2\u00f4\u00f5\7j\2\2\u00f5(\3\2\2\2\u00f6\u00f7"+
		"\7c\2\2\u00f7\u00f8\7p\2\2\u00f8\u00f9\7f\2\2\u00f9*\3\2\2\2\u00fa\u00fb"+
		"\7q\2\2\u00fb\u00fc\7t\2\2\u00fc,\3\2\2\2\u00fd\u00fe\7,\2\2\u00fe.\3"+
		"\2\2\2\u00ff\u0100\7\61\2\2\u0100\60\3\2\2\2\u0101\u0102\7-\2\2\u0102"+
		"\62\3\2\2\2\u0103\u0104\7/\2\2\u0104\64\3\2\2\2\u0105\u0106\7@\2\2\u0106"+
		"\66\3\2\2\2\u0107\u0108\7@\2\2\u0108\u0109\7?\2\2\u01098\3\2\2\2\u010a"+
		"\u010b\7>\2\2\u010b:\3\2\2\2\u010c\u010d\7>\2\2\u010d\u010e\7?\2\2\u010e"+
		"<\3\2\2\2\u010f\u0110\7?\2\2\u0110>\3\2\2\2\u0111\u0113\7/\2\2\u0112\u0111"+
		"\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0115\3\2\2\2\u0114\u0116\t\2\2\2\u0115"+
		"\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u0115\3\2\2\2\u0117\u0118\3\2"+
		"\2\2\u0118\u011f\3\2\2\2\u0119\u011b\7\60\2\2\u011a\u011c\t\2\2\2\u011b"+
		"\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011e\3\2"+
		"\2\2\u011e\u0120\3\2\2\2\u011f\u0119\3\2\2\2\u011f\u0120\3\2\2\2\u0120"+
		"@\3\2\2\2\u0121\u0125\t\3\2\2\u0122\u0124\t\4\2\2\u0123\u0122\3\2\2\2"+
		"\u0124\u0127\3\2\2\2\u0125\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126B\3"+
		"\2\2\2\u0127\u0125\3\2\2\2\u0128\u0129\7=\2\2\u0129D\3\2\2\2\u012a\u012b"+
		"\7.\2\2\u012bF\3\2\2\2\u012c\u012d\7\61\2\2\u012d\u012e\7\61\2\2\u012e"+
		"\u0130\3\2\2\2\u012f\u0131\13\2\2\2\u0130\u012f\3\2\2\2\u0131\u0132\3"+
		"\2\2\2\u0132\u0133\3\2\2\2\u0132\u0130\3\2\2\2\u0133\u0135\3\2\2\2\u0134"+
		"\u0136\t\5\2\2\u0135\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u0138\b$"+
		"\2\2\u0138H\3\2\2\2\u0139\u013b\t\6\2\2\u013a\u0139\3\2\2\2\u013b\u013c"+
		"\3\2\2\2\u013c\u013a\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013e\3\2\2\2\u013e"+
		"\u013f\b%\2\2\u013fJ\3\2\2\2\13\2\u0112\u0117\u011d\u011f\u0125\u0132"+
		"\u0135\u013c\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}