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
		IF=20, THEN=21, AND=22, OR=23, TRUE=24, FALSE=25, MULT=26, DIV=27, PLUS=28, 
		MINUS=29, GT=30, GE=31, LT=32, LE=33, EQ=34, LPAREN=35, RPAREN=36, DECIMAL=37, 
		IDENTIFIER=38, SEMI=39, COMMA=40, COMMENT=41, WS=42;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"PAY", "CURRENT_SUBSCRIPTION_AMOUNT", "SUBSCRIPTION_CONFIRMATION", "RESIDUAL_DUE_PAYMENT", 
		"AFTER", "BEFORE", "OF", "DELIVERIES", "DELIVERY", "DEFAULT", "PROPORTION", 
		"PERCENTAGE", "IN", "ON", "N", "REMAININGN", "AMOUNT", "FOR", "EACH", 
		"IF", "THEN", "AND", "OR", "TRUE", "FALSE", "MULT", "DIV", "PLUS", "MINUS", 
		"GT", "GE", "LT", "LE", "EQ", "LPAREN", "RPAREN", "DECIMAL", "IDENTIFIER", 
		"SEMI", "COMMA", "COMMENT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'pay'", "'current subscription amount'", "'subscription confirmation'", 
		"'residual due amount'", "'after'", "'before'", "'of'", "'deliveries'", 
		"'delivery'", "'default'", "'proportion'", "'%'", "'in'", "'on'", "'N'", 
		"'remaining-N'", "'amount'", "'for'", "'each'", "'if'", "'then'", "'and'", 
		"'or'", "'true'", "'false'", "'*'", "'/'", "'+'", "'-'", "'>'", "'>='", 
		"'<'", "'<='", "'='", "'('", "')'", null, null, "';'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PAY", "CURRENT_SUBSCRIPTION_AMOUNT", "SUBSCRIPTION_CONFIRMATION", 
		"RESIDUAL_DUE_PAYMENT", "AFTER", "BEFORE", "OF", "DELIVERIES", "DELIVERY", 
		"DEFAULT", "PROPORTION", "PERCENTAGE", "IN", "ON", "N", "REMAININGN", 
		"AMOUNT", "FOR", "EACH", "IF", "THEN", "AND", "OR", "TRUE", "FALSE", "MULT", 
		"DIV", "PLUS", "MINUS", "GT", "GE", "LT", "LE", "EQ", "LPAREN", "RPAREN", 
		"DECIMAL", "IDENTIFIER", "SEMI", "COMMA", "COMMENT", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2,\u0163\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\3"+
		"\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3"+
		"\16\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3"+
		"\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3"+
		"\27\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3"+
		" \3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\5&\u0136\n&\3&\6&\u0139"+
		"\n&\r&\16&\u013a\3&\3&\6&\u013f\n&\r&\16&\u0140\5&\u0143\n&\3\'\3\'\7"+
		"\'\u0147\n\'\f\'\16\'\u014a\13\'\3(\3(\3)\3)\3*\3*\3*\3*\6*\u0154\n*\r"+
		"*\16*\u0155\3*\5*\u0159\n*\3*\3*\3+\6+\u015e\n+\r+\16+\u015f\3+\3+\3\u0155"+
		"\2,\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,\3\2\7\3\2\62;\5\2C\\aac|\6\2\62;C\\a"+
		"ac|\3\3\f\f\5\2\13\f\16\17\"\"\u0169\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2"+
		"\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2"+
		"\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3"+
		"\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2"+
		"\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2"+
		"\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\3W\3\2\2\2\5[\3\2\2\2\7w"+
		"\3\2\2\2\t\u0091\3\2\2\2\13\u00a5\3\2\2\2\r\u00ab\3\2\2\2\17\u00b2\3\2"+
		"\2\2\21\u00b5\3\2\2\2\23\u00c0\3\2\2\2\25\u00c9\3\2\2\2\27\u00d1\3\2\2"+
		"\2\31\u00dc\3\2\2\2\33\u00de\3\2\2\2\35\u00e1\3\2\2\2\37\u00e4\3\2\2\2"+
		"!\u00e6\3\2\2\2#\u00f2\3\2\2\2%\u00f9\3\2\2\2\'\u00fd\3\2\2\2)\u0102\3"+
		"\2\2\2+\u0105\3\2\2\2-\u010a\3\2\2\2/\u010e\3\2\2\2\61\u0111\3\2\2\2\63"+
		"\u0116\3\2\2\2\65\u011c\3\2\2\2\67\u011e\3\2\2\29\u0120\3\2\2\2;\u0122"+
		"\3\2\2\2=\u0124\3\2\2\2?\u0126\3\2\2\2A\u0129\3\2\2\2C\u012b\3\2\2\2E"+
		"\u012e\3\2\2\2G\u0130\3\2\2\2I\u0132\3\2\2\2K\u0135\3\2\2\2M\u0144\3\2"+
		"\2\2O\u014b\3\2\2\2Q\u014d\3\2\2\2S\u014f\3\2\2\2U\u015d\3\2\2\2WX\7r"+
		"\2\2XY\7c\2\2YZ\7{\2\2Z\4\3\2\2\2[\\\7e\2\2\\]\7w\2\2]^\7t\2\2^_\7t\2"+
		"\2_`\7g\2\2`a\7p\2\2ab\7v\2\2bc\7\"\2\2cd\7u\2\2de\7w\2\2ef\7d\2\2fg\7"+
		"u\2\2gh\7e\2\2hi\7t\2\2ij\7k\2\2jk\7r\2\2kl\7v\2\2lm\7k\2\2mn\7q\2\2n"+
		"o\7p\2\2op\7\"\2\2pq\7c\2\2qr\7o\2\2rs\7q\2\2st\7w\2\2tu\7p\2\2uv\7v\2"+
		"\2v\6\3\2\2\2wx\7u\2\2xy\7w\2\2yz\7d\2\2z{\7u\2\2{|\7e\2\2|}\7t\2\2}~"+
		"\7k\2\2~\177\7r\2\2\177\u0080\7v\2\2\u0080\u0081\7k\2\2\u0081\u0082\7"+
		"q\2\2\u0082\u0083\7p\2\2\u0083\u0084\7\"\2\2\u0084\u0085\7e\2\2\u0085"+
		"\u0086\7q\2\2\u0086\u0087\7p\2\2\u0087\u0088\7h\2\2\u0088\u0089\7k\2\2"+
		"\u0089\u008a\7t\2\2\u008a\u008b\7o\2\2\u008b\u008c\7c\2\2\u008c\u008d"+
		"\7v\2\2\u008d\u008e\7k\2\2\u008e\u008f\7q\2\2\u008f\u0090\7p\2\2\u0090"+
		"\b\3\2\2\2\u0091\u0092\7t\2\2\u0092\u0093\7g\2\2\u0093\u0094\7u\2\2\u0094"+
		"\u0095\7k\2\2\u0095\u0096\7f\2\2\u0096\u0097\7w\2\2\u0097\u0098\7c\2\2"+
		"\u0098\u0099\7n\2\2\u0099\u009a\7\"\2\2\u009a\u009b\7f\2\2\u009b\u009c"+
		"\7w\2\2\u009c\u009d\7g\2\2\u009d\u009e\7\"\2\2\u009e\u009f\7c\2\2\u009f"+
		"\u00a0\7o\2\2\u00a0\u00a1\7q\2\2\u00a1\u00a2\7w\2\2\u00a2\u00a3\7p\2\2"+
		"\u00a3\u00a4\7v\2\2\u00a4\n\3\2\2\2\u00a5\u00a6\7c\2\2\u00a6\u00a7\7h"+
		"\2\2\u00a7\u00a8\7v\2\2\u00a8\u00a9\7g\2\2\u00a9\u00aa\7t\2\2\u00aa\f"+
		"\3\2\2\2\u00ab\u00ac\7d\2\2\u00ac\u00ad\7g\2\2\u00ad\u00ae\7h\2\2\u00ae"+
		"\u00af\7q\2\2\u00af\u00b0\7t\2\2\u00b0\u00b1\7g\2\2\u00b1\16\3\2\2\2\u00b2"+
		"\u00b3\7q\2\2\u00b3\u00b4\7h\2\2\u00b4\20\3\2\2\2\u00b5\u00b6\7f\2\2\u00b6"+
		"\u00b7\7g\2\2\u00b7\u00b8\7n\2\2\u00b8\u00b9\7k\2\2\u00b9\u00ba\7x\2\2"+
		"\u00ba\u00bb\7g\2\2\u00bb\u00bc\7t\2\2\u00bc\u00bd\7k\2\2\u00bd\u00be"+
		"\7g\2\2\u00be\u00bf\7u\2\2\u00bf\22\3\2\2\2\u00c0\u00c1\7f\2\2\u00c1\u00c2"+
		"\7g\2\2\u00c2\u00c3\7n\2\2\u00c3\u00c4\7k\2\2\u00c4\u00c5\7x\2\2\u00c5"+
		"\u00c6\7g\2\2\u00c6\u00c7\7t\2\2\u00c7\u00c8\7{\2\2\u00c8\24\3\2\2\2\u00c9"+
		"\u00ca\7f\2\2\u00ca\u00cb\7g\2\2\u00cb\u00cc\7h\2\2\u00cc\u00cd\7c\2\2"+
		"\u00cd\u00ce\7w\2\2\u00ce\u00cf\7n\2\2\u00cf\u00d0\7v\2\2\u00d0\26\3\2"+
		"\2\2\u00d1\u00d2\7r\2\2\u00d2\u00d3\7t\2\2\u00d3\u00d4\7q\2\2\u00d4\u00d5"+
		"\7r\2\2\u00d5\u00d6\7q\2\2\u00d6\u00d7\7t\2\2\u00d7\u00d8\7v\2\2\u00d8"+
		"\u00d9\7k\2\2\u00d9\u00da\7q\2\2\u00da\u00db\7p\2\2\u00db\30\3\2\2\2\u00dc"+
		"\u00dd\7\'\2\2\u00dd\32\3\2\2\2\u00de\u00df\7k\2\2\u00df\u00e0\7p\2\2"+
		"\u00e0\34\3\2\2\2\u00e1\u00e2\7q\2\2\u00e2\u00e3\7p\2\2\u00e3\36\3\2\2"+
		"\2\u00e4\u00e5\7P\2\2\u00e5 \3\2\2\2\u00e6\u00e7\7t\2\2\u00e7\u00e8\7"+
		"g\2\2\u00e8\u00e9\7o\2\2\u00e9\u00ea\7c\2\2\u00ea\u00eb\7k\2\2\u00eb\u00ec"+
		"\7p\2\2\u00ec\u00ed\7k\2\2\u00ed\u00ee\7p\2\2\u00ee\u00ef\7i\2\2\u00ef"+
		"\u00f0\7/\2\2\u00f0\u00f1\7P\2\2\u00f1\"\3\2\2\2\u00f2\u00f3\7c\2\2\u00f3"+
		"\u00f4\7o\2\2\u00f4\u00f5\7q\2\2\u00f5\u00f6\7w\2\2\u00f6\u00f7\7p\2\2"+
		"\u00f7\u00f8\7v\2\2\u00f8$\3\2\2\2\u00f9\u00fa\7h\2\2\u00fa\u00fb\7q\2"+
		"\2\u00fb\u00fc\7t\2\2\u00fc&\3\2\2\2\u00fd\u00fe\7g\2\2\u00fe\u00ff\7"+
		"c\2\2\u00ff\u0100\7e\2\2\u0100\u0101\7j\2\2\u0101(\3\2\2\2\u0102\u0103"+
		"\7k\2\2\u0103\u0104\7h\2\2\u0104*\3\2\2\2\u0105\u0106\7v\2\2\u0106\u0107"+
		"\7j\2\2\u0107\u0108\7g\2\2\u0108\u0109\7p\2\2\u0109,\3\2\2\2\u010a\u010b"+
		"\7c\2\2\u010b\u010c\7p\2\2\u010c\u010d\7f\2\2\u010d.\3\2\2\2\u010e\u010f"+
		"\7q\2\2\u010f\u0110\7t\2\2\u0110\60\3\2\2\2\u0111\u0112\7v\2\2\u0112\u0113"+
		"\7t\2\2\u0113\u0114\7w\2\2\u0114\u0115\7g\2\2\u0115\62\3\2\2\2\u0116\u0117"+
		"\7h\2\2\u0117\u0118\7c\2\2\u0118\u0119\7n\2\2\u0119\u011a\7u\2\2\u011a"+
		"\u011b\7g\2\2\u011b\64\3\2\2\2\u011c\u011d\7,\2\2\u011d\66\3\2\2\2\u011e"+
		"\u011f\7\61\2\2\u011f8\3\2\2\2\u0120\u0121\7-\2\2\u0121:\3\2\2\2\u0122"+
		"\u0123\7/\2\2\u0123<\3\2\2\2\u0124\u0125\7@\2\2\u0125>\3\2\2\2\u0126\u0127"+
		"\7@\2\2\u0127\u0128\7?\2\2\u0128@\3\2\2\2\u0129\u012a\7>\2\2\u012aB\3"+
		"\2\2\2\u012b\u012c\7>\2\2\u012c\u012d\7?\2\2\u012dD\3\2\2\2\u012e\u012f"+
		"\7?\2\2\u012fF\3\2\2\2\u0130\u0131\7*\2\2\u0131H\3\2\2\2\u0132\u0133\7"+
		"+\2\2\u0133J\3\2\2\2\u0134\u0136\7/\2\2\u0135\u0134\3\2\2\2\u0135\u0136"+
		"\3\2\2\2\u0136\u0138\3\2\2\2\u0137\u0139\t\2\2\2\u0138\u0137\3\2\2\2\u0139"+
		"\u013a\3\2\2\2\u013a\u0138\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u0142\3\2"+
		"\2\2\u013c\u013e\7\60\2\2\u013d\u013f\t\2\2\2\u013e\u013d\3\2\2\2\u013f"+
		"\u0140\3\2\2\2\u0140\u013e\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0143\3\2"+
		"\2\2\u0142\u013c\3\2\2\2\u0142\u0143\3\2\2\2\u0143L\3\2\2\2\u0144\u0148"+
		"\t\3\2\2\u0145\u0147\t\4\2\2\u0146\u0145\3\2\2\2\u0147\u014a\3\2\2\2\u0148"+
		"\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149N\3\2\2\2\u014a\u0148\3\2\2\2"+
		"\u014b\u014c\7=\2\2\u014cP\3\2\2\2\u014d\u014e\7.\2\2\u014eR\3\2\2\2\u014f"+
		"\u0150\7\61\2\2\u0150\u0151\7\61\2\2\u0151\u0153\3\2\2\2\u0152\u0154\13"+
		"\2\2\2\u0153\u0152\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0156\3\2\2\2\u0155"+
		"\u0153\3\2\2\2\u0156\u0158\3\2\2\2\u0157\u0159\t\5\2\2\u0158\u0157\3\2"+
		"\2\2\u0159\u015a\3\2\2\2\u015a\u015b\b*\2\2\u015bT\3\2\2\2\u015c\u015e"+
		"\t\6\2\2\u015d\u015c\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u015d\3\2\2\2\u015f"+
		"\u0160\3\2\2\2\u0160\u0161\3\2\2\2\u0161\u0162\b+\2\2\u0162V\3\2\2\2\13"+
		"\2\u0135\u013a\u0140\u0142\u0148\u0155\u0158\u015f\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}