// Generated from com/affaince/subscription/PaymentGrammar.g4 by ANTLR 4.5
package com.affaince.subscription.compiler;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PaymentGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PAY=1, CURRENT_SUBSCRIPTION_AMOUNT=2, SUBSCRIPTION_CONFIRMATION=3, RESIDUAL_DUE_PAYMENT=4, 
		AFTER=5, OF=6, DELIVERIES=7, DELIVERY=8, DEFAULT=9, PROPORTION=10, PERCENTAGE=11, 
		IN=12, ON=13, N=14, IF=15, THEN=16, AND=17, OR=18, TRUE=19, FALSE=20, 
		MULT=21, DIV=22, PLUS=23, MINUS=24, GT=25, GE=26, LT=27, LE=28, EQ=29, 
		LPAREN=30, RPAREN=31, DECIMAL=32, IDENTIFIER=33, SEMI=34, COMMA=35, COMMENT=36, 
		WS=37;
	public static final int
		RULE_payment_rule_set = 0, RULE_payment_rule = 1, RULE_advance_payment_expr = 2, 
		RULE_residual_due_payment_expr = 3, RULE_proportion_value = 4, RULE_delivery_value = 5, 
		RULE_percent_source = 6, RULE_percent_value = 7;
	public static final String[] ruleNames = {
		"payment_rule_set", "payment_rule", "advance_payment_expr", "residual_due_payment_expr", 
		"proportion_value", "delivery_value", "percent_source", "percent_value"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'pay'", "'current subscription amount'", "'subscription confirmation'", 
		"'residual due amount'", "'after'", "'of'", "'deliveries'", "'delivery'", 
		"'default'", "'proportion'", "'%'", "'in'", "'on'", "'N'", "'if'", "'then'", 
		"'and'", "'or'", "'true'", "'false'", "'*'", "'/'", "'+'", "'-'", "'>'", 
		"'>='", "'<'", "'<='", "'='", "'('", "')'", null, null, "';'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PAY", "CURRENT_SUBSCRIPTION_AMOUNT", "SUBSCRIPTION_CONFIRMATION", 
		"RESIDUAL_DUE_PAYMENT", "AFTER", "OF", "DELIVERIES", "DELIVERY", "DEFAULT", 
		"PROPORTION", "PERCENTAGE", "IN", "ON", "N", "IF", "THEN", "AND", "OR", 
		"TRUE", "FALSE", "MULT", "DIV", "PLUS", "MINUS", "GT", "GE", "LT", "LE", 
		"EQ", "LPAREN", "RPAREN", "DECIMAL", "IDENTIFIER", "SEMI", "COMMA", "COMMENT", 
		"WS"
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

	@Override
	public String getGrammarFileName() { return "PaymentGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PaymentGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Payment_rule_setContext extends ParserRuleContext {
		public Payment_ruleContext payment_rule() {
			return getRuleContext(Payment_ruleContext.class,0);
		}
		public Payment_rule_setContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_payment_rule_set; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).enterPayment_rule_set(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).exitPayment_rule_set(this);
		}
	}

	public final Payment_rule_setContext payment_rule_set() throws RecognitionException {
		Payment_rule_setContext _localctx = new Payment_rule_setContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_payment_rule_set);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			payment_rule();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Payment_ruleContext extends ParserRuleContext {
		public Advance_payment_exprContext advance_payment_expr() {
			return getRuleContext(Advance_payment_exprContext.class,0);
		}
		public TerminalNode AND() { return getToken(PaymentGrammarParser.AND, 0); }
		public Residual_due_payment_exprContext residual_due_payment_expr() {
			return getRuleContext(Residual_due_payment_exprContext.class,0);
		}
		public TerminalNode OR() { return getToken(PaymentGrammarParser.OR, 0); }
		public Payment_ruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_payment_rule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).enterPayment_rule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).exitPayment_rule(this);
		}
	}

	public final Payment_ruleContext payment_rule() throws RecognitionException {
		Payment_ruleContext _localctx = new Payment_ruleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_payment_rule);
		try {
			setState(28);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(18);
				advance_payment_expr();
				setState(19);
				match(AND);
				setState(20);
				residual_due_payment_expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(22);
				advance_payment_expr();
				setState(23);
				match(OR);
				setState(24);
				residual_due_payment_expr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(26);
				advance_payment_expr();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(27);
				residual_due_payment_expr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Advance_payment_exprContext extends ParserRuleContext {
		public TerminalNode PAY() { return getToken(PaymentGrammarParser.PAY, 0); }
		public Percent_valueContext percent_value() {
			return getRuleContext(Percent_valueContext.class,0);
		}
		public TerminalNode PERCENTAGE() { return getToken(PaymentGrammarParser.PERCENTAGE, 0); }
		public TerminalNode OF() { return getToken(PaymentGrammarParser.OF, 0); }
		public Percent_sourceContext percent_source() {
			return getRuleContext(Percent_sourceContext.class,0);
		}
		public TerminalNode ON() { return getToken(PaymentGrammarParser.ON, 0); }
		public TerminalNode SUBSCRIPTION_CONFIRMATION() { return getToken(PaymentGrammarParser.SUBSCRIPTION_CONFIRMATION, 0); }
		public Advance_payment_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_advance_payment_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).enterAdvance_payment_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).exitAdvance_payment_expr(this);
		}
	}

	public final Advance_payment_exprContext advance_payment_expr() throws RecognitionException {
		Advance_payment_exprContext _localctx = new Advance_payment_exprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_advance_payment_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(PAY);
			setState(31);
			percent_value();
			setState(32);
			match(PERCENTAGE);
			setState(33);
			match(OF);
			setState(34);
			percent_source();
			setState(35);
			match(ON);
			setState(36);
			match(SUBSCRIPTION_CONFIRMATION);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Residual_due_payment_exprContext extends ParserRuleContext {
		public TerminalNode PAY() { return getToken(PaymentGrammarParser.PAY, 0); }
		public TerminalNode RESIDUAL_DUE_PAYMENT() { return getToken(PaymentGrammarParser.RESIDUAL_DUE_PAYMENT, 0); }
		public TerminalNode AFTER() { return getToken(PaymentGrammarParser.AFTER, 0); }
		public Delivery_valueContext delivery_value() {
			return getRuleContext(Delivery_valueContext.class,0);
		}
		public TerminalNode OF() { return getToken(PaymentGrammarParser.OF, 0); }
		public TerminalNode N() { return getToken(PaymentGrammarParser.N, 0); }
		public TerminalNode DELIVERY() { return getToken(PaymentGrammarParser.DELIVERY, 0); }
		public TerminalNode IN() { return getToken(PaymentGrammarParser.IN, 0); }
		public Proportion_valueContext proportion_value() {
			return getRuleContext(Proportion_valueContext.class,0);
		}
		public TerminalNode PROPORTION() { return getToken(PaymentGrammarParser.PROPORTION, 0); }
		public Residual_due_payment_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_residual_due_payment_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).enterResidual_due_payment_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).exitResidual_due_payment_expr(this);
		}
	}

	public final Residual_due_payment_exprContext residual_due_payment_expr() throws RecognitionException {
		Residual_due_payment_exprContext _localctx = new Residual_due_payment_exprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_residual_due_payment_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(PAY);
			setState(39);
			match(RESIDUAL_DUE_PAYMENT);
			setState(40);
			match(AFTER);
			setState(41);
			delivery_value();
			setState(42);
			match(OF);
			setState(43);
			match(N);
			setState(44);
			match(DELIVERY);
			setState(45);
			match(IN);
			setState(46);
			proportion_value();
			setState(47);
			match(PROPORTION);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Proportion_valueContext extends ParserRuleContext {
		public TerminalNode DEFAULT() { return getToken(PaymentGrammarParser.DEFAULT, 0); }
		public TerminalNode DECIMAL() { return getToken(PaymentGrammarParser.DECIMAL, 0); }
		public Proportion_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proportion_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).enterProportion_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).exitProportion_value(this);
		}
	}

	public final Proportion_valueContext proportion_value() throws RecognitionException {
		Proportion_valueContext _localctx = new Proportion_valueContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_proportion_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			_la = _input.LA(1);
			if ( !(_la==DEFAULT || _la==DECIMAL) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Delivery_valueContext extends ParserRuleContext {
		public TerminalNode DECIMAL() { return getToken(PaymentGrammarParser.DECIMAL, 0); }
		public Delivery_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delivery_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).enterDelivery_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).exitDelivery_value(this);
		}
	}

	public final Delivery_valueContext delivery_value() throws RecognitionException {
		Delivery_valueContext _localctx = new Delivery_valueContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_delivery_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(DECIMAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Percent_sourceContext extends ParserRuleContext {
		public TerminalNode CURRENT_SUBSCRIPTION_AMOUNT() { return getToken(PaymentGrammarParser.CURRENT_SUBSCRIPTION_AMOUNT, 0); }
		public Percent_sourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_percent_source; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).enterPercent_source(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).exitPercent_source(this);
		}
	}

	public final Percent_sourceContext percent_source() throws RecognitionException {
		Percent_sourceContext _localctx = new Percent_sourceContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_percent_source);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(CURRENT_SUBSCRIPTION_AMOUNT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Percent_valueContext extends ParserRuleContext {
		public TerminalNode DECIMAL() { return getToken(PaymentGrammarParser.DECIMAL, 0); }
		public Percent_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_percent_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).enterPercent_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).exitPercent_value(this);
		}
	}

	public final Percent_valueContext percent_value() throws RecognitionException {
		Percent_valueContext _localctx = new Percent_valueContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_percent_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(DECIMAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\'<\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\37\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b"+
		"\3\t\3\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\3\4\2\13\13\"\"\66\2\22\3\2\2\2"+
		"\4\36\3\2\2\2\6 \3\2\2\2\b(\3\2\2\2\n\63\3\2\2\2\f\65\3\2\2\2\16\67\3"+
		"\2\2\2\209\3\2\2\2\22\23\5\4\3\2\23\3\3\2\2\2\24\25\5\6\4\2\25\26\7\23"+
		"\2\2\26\27\5\b\5\2\27\37\3\2\2\2\30\31\5\6\4\2\31\32\7\24\2\2\32\33\5"+
		"\b\5\2\33\37\3\2\2\2\34\37\5\6\4\2\35\37\5\b\5\2\36\24\3\2\2\2\36\30\3"+
		"\2\2\2\36\34\3\2\2\2\36\35\3\2\2\2\37\5\3\2\2\2 !\7\3\2\2!\"\5\20\t\2"+
		"\"#\7\r\2\2#$\7\b\2\2$%\5\16\b\2%&\7\17\2\2&\'\7\5\2\2\'\7\3\2\2\2()\7"+
		"\3\2\2)*\7\6\2\2*+\7\7\2\2+,\5\f\7\2,-\7\b\2\2-.\7\20\2\2./\7\n\2\2/\60"+
		"\7\16\2\2\60\61\5\n\6\2\61\62\7\f\2\2\62\t\3\2\2\2\63\64\t\2\2\2\64\13"+
		"\3\2\2\2\65\66\7\"\2\2\66\r\3\2\2\2\678\7\4\2\28\17\3\2\2\29:\7\"\2\2"+
		":\21\3\2\2\2\3\36";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}