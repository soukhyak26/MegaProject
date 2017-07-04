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

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PaymentGrammarParser extends Parser {
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
	public static final int
		RULE_payment_rule_set = 0, RULE_payment_rule = 1, RULE_advance_payment_expr = 2, 
		RULE_residual_due_payment_expr = 3, RULE_payment_source_expr = 4, RULE_proportion_value = 5, 
		RULE_delivery_value = 6, RULE_percent_source = 7, RULE_percent_value = 8, 
		RULE_delivery_count = 9, RULE_after_before = 10, RULE_delivery_number_list = 11, 
		RULE_delivery_number_expr = 12;
	public static final String[] ruleNames = {
		"payment_rule_set", "payment_rule", "advance_payment_expr", "residual_due_payment_expr", 
		"payment_source_expr", "proportion_value", "delivery_value", "percent_source", 
		"percent_value", "delivery_count", "after_before", "delivery_number_list", 
		"delivery_number_expr"
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
			setState(26);
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
			setState(38);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(28);
				advance_payment_expr();
				setState(29);
				match(AND);
				setState(30);
				residual_due_payment_expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(32);
				advance_payment_expr();
				setState(33);
				match(OR);
				setState(34);
				residual_due_payment_expr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(36);
				advance_payment_expr();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(37);
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
			setState(40);
			match(PAY);
			setState(41);
			percent_value();
			setState(42);
			match(PERCENTAGE);
			setState(43);
			match(OF);
			setState(44);
			percent_source();
			setState(45);
			match(ON);
			setState(46);
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
		public Payment_source_exprContext payment_source_expr() {
			return getRuleContext(Payment_source_exprContext.class,0);
		}
		public After_beforeContext after_before() {
			return getRuleContext(After_beforeContext.class,0);
		}
		public Delivery_number_listContext delivery_number_list() {
			return getRuleContext(Delivery_number_listContext.class,0);
		}
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
			setState(63);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				match(PAY);
				setState(49);
				payment_source_expr();
				setState(50);
				after_before();
				setState(51);
				delivery_number_list(0);
				setState(52);
				match(DELIVERY);
				setState(53);
				match(IN);
				setState(54);
				proportion_value();
				setState(55);
				match(PROPORTION);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				match(PAY);
				setState(58);
				payment_source_expr();
				setState(59);
				after_before();
				setState(60);
				delivery_number_list(0);
				setState(61);
				match(DELIVERY);
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

	public static class Payment_source_exprContext extends ParserRuleContext {
		public TerminalNode RESIDUAL_DUE_PAYMENT() { return getToken(PaymentGrammarParser.RESIDUAL_DUE_PAYMENT, 0); }
		public TerminalNode PLUS() { return getToken(PaymentGrammarParser.PLUS, 0); }
		public TerminalNode CURRENT_SUBSCRIPTION_AMOUNT() { return getToken(PaymentGrammarParser.CURRENT_SUBSCRIPTION_AMOUNT, 0); }
		public TerminalNode FOR() { return getToken(PaymentGrammarParser.FOR, 0); }
		public Delivery_valueContext delivery_value() {
			return getRuleContext(Delivery_valueContext.class,0);
		}
		public TerminalNode DELIVERY() { return getToken(PaymentGrammarParser.DELIVERY, 0); }
		public Payment_source_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_payment_source_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).enterPayment_source_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).exitPayment_source_expr(this);
		}
	}

	public final Payment_source_exprContext payment_source_expr() throws RecognitionException {
		Payment_source_exprContext _localctx = new Payment_source_exprContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_payment_source_expr);
		try {
			setState(73);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				match(RESIDUAL_DUE_PAYMENT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				match(RESIDUAL_DUE_PAYMENT);
				setState(67);
				match(PLUS);
				setState(68);
				match(CURRENT_SUBSCRIPTION_AMOUNT);
				setState(69);
				match(FOR);
				setState(70);
				delivery_value();
				setState(71);
				match(DELIVERY);
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
		enterRule(_localctx, 10, RULE_proportion_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
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
		enterRule(_localctx, 12, RULE_delivery_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
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
		public TerminalNode DECIMAL() { return getToken(PaymentGrammarParser.DECIMAL, 0); }
		public TerminalNode DELIVERY() { return getToken(PaymentGrammarParser.DELIVERY, 0); }
		public TerminalNode N() { return getToken(PaymentGrammarParser.N, 0); }
		public TerminalNode DIV() { return getToken(PaymentGrammarParser.DIV, 0); }
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
		enterRule(_localctx, 14, RULE_percent_source);
		try {
			setState(86);
			switch (_input.LA(1)) {
			case CURRENT_SUBSCRIPTION_AMOUNT:
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				match(CURRENT_SUBSCRIPTION_AMOUNT);
				}
				break;
			case DECIMAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(80);
				match(DECIMAL);
				setState(81);
				match(DELIVERY);
				}
				break;
			case N:
				enterOuterAlt(_localctx, 3);
				{
				setState(82);
				match(N);
				setState(83);
				match(DIV);
				setState(84);
				match(DECIMAL);
				setState(85);
				match(DELIVERY);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		enterRule(_localctx, 16, RULE_percent_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
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

	public static class Delivery_countContext extends ParserRuleContext {
		public TerminalNode N() { return getToken(PaymentGrammarParser.N, 0); }
		public TerminalNode REMAININGN() { return getToken(PaymentGrammarParser.REMAININGN, 0); }
		public Delivery_countContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delivery_count; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).enterDelivery_count(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).exitDelivery_count(this);
		}
	}

	public final Delivery_countContext delivery_count() throws RecognitionException {
		Delivery_countContext _localctx = new Delivery_countContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_delivery_count);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			_la = _input.LA(1);
			if ( !(_la==N || _la==REMAININGN) ) {
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

	public static class After_beforeContext extends ParserRuleContext {
		public TerminalNode AFTER() { return getToken(PaymentGrammarParser.AFTER, 0); }
		public TerminalNode BEFORE() { return getToken(PaymentGrammarParser.BEFORE, 0); }
		public After_beforeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_after_before; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).enterAfter_before(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).exitAfter_before(this);
		}
	}

	public final After_beforeContext after_before() throws RecognitionException {
		After_beforeContext _localctx = new After_beforeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_after_before);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			_la = _input.LA(1);
			if ( !(_la==AFTER || _la==BEFORE) ) {
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

	public static class Delivery_number_listContext extends ParserRuleContext {
		public Delivery_number_exprContext delivery_number_expr() {
			return getRuleContext(Delivery_number_exprContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(PaymentGrammarParser.COMMA, 0); }
		public TerminalNode EACH() { return getToken(PaymentGrammarParser.EACH, 0); }
		public List<Delivery_number_listContext> delivery_number_list() {
			return getRuleContexts(Delivery_number_listContext.class);
		}
		public Delivery_number_listContext delivery_number_list(int i) {
			return getRuleContext(Delivery_number_listContext.class,i);
		}
		public Delivery_number_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delivery_number_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).enterDelivery_number_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).exitDelivery_number_list(this);
		}
	}

	public final Delivery_number_listContext delivery_number_list() throws RecognitionException {
		return delivery_number_list(0);
	}

	private Delivery_number_listContext delivery_number_list(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Delivery_number_listContext _localctx = new Delivery_number_listContext(_ctx, _parentState);
		Delivery_number_listContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_delivery_number_list, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(95);
				delivery_number_expr();
				setState(96);
				match(COMMA);
				}
				break;
			case 2:
				{
				setState(98);
				delivery_number_expr();
				}
				break;
			case 3:
				{
				setState(99);
				match(EACH);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(107);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Delivery_number_listContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_delivery_number_list);
					setState(102);
					if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
					setState(103);
					match(COMMA);
					setState(104);
					delivery_number_list(5);
					}
					}
				}
				setState(109);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Delivery_number_exprContext extends ParserRuleContext {
		public List<TerminalNode> DECIMAL() { return getTokens(PaymentGrammarParser.DECIMAL); }
		public TerminalNode DECIMAL(int i) {
			return getToken(PaymentGrammarParser.DECIMAL, i);
		}
		public TerminalNode DIV() { return getToken(PaymentGrammarParser.DIV, 0); }
		public TerminalNode N() { return getToken(PaymentGrammarParser.N, 0); }
		public TerminalNode REMAININGN() { return getToken(PaymentGrammarParser.REMAININGN, 0); }
		public Delivery_number_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delivery_number_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).enterDelivery_number_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentGrammarListener) ((PaymentGrammarListener)listener).exitDelivery_number_expr(this);
		}
	}

	public final Delivery_number_exprContext delivery_number_expr() throws RecognitionException {
		Delivery_number_exprContext _localctx = new Delivery_number_exprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_delivery_number_expr);
		try {
			setState(118);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				match(DECIMAL);
				setState(111);
				match(DIV);
				setState(112);
				match(DECIMAL);
				setState(113);
				match(N);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(114);
				match(DECIMAL);
				setState(115);
				match(DIV);
				setState(116);
				match(DECIMAL);
				setState(117);
				match(REMAININGN);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return delivery_number_list_sempred((Delivery_number_listContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean delivery_number_list_sempred(Delivery_number_listContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3,{\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f"+
		"\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\5\3)\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5B\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\5\6L\n\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tY\n\t\3\n"+
		"\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\5\rg\n\r\3\r\3\r\3\r\7"+
		"\rl\n\r\f\r\16\ro\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16y\n"+
		"\16\3\16\2\3\30\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\5\4\2\f\f\'\'\3"+
		"\2\21\22\3\2\7\bx\2\34\3\2\2\2\4(\3\2\2\2\6*\3\2\2\2\bA\3\2\2\2\nK\3\2"+
		"\2\2\fM\3\2\2\2\16O\3\2\2\2\20X\3\2\2\2\22Z\3\2\2\2\24\\\3\2\2\2\26^\3"+
		"\2\2\2\30f\3\2\2\2\32x\3\2\2\2\34\35\5\4\3\2\35\3\3\2\2\2\36\37\5\6\4"+
		"\2\37 \7\30\2\2 !\5\b\5\2!)\3\2\2\2\"#\5\6\4\2#$\7\31\2\2$%\5\b\5\2%)"+
		"\3\2\2\2&)\5\6\4\2\')\5\b\5\2(\36\3\2\2\2(\"\3\2\2\2(&\3\2\2\2(\'\3\2"+
		"\2\2)\5\3\2\2\2*+\7\3\2\2+,\5\22\n\2,-\7\16\2\2-.\7\t\2\2./\5\20\t\2/"+
		"\60\7\20\2\2\60\61\7\5\2\2\61\7\3\2\2\2\62\63\7\3\2\2\63\64\5\n\6\2\64"+
		"\65\5\26\f\2\65\66\5\30\r\2\66\67\7\13\2\2\678\7\17\2\289\5\f\7\29:\7"+
		"\r\2\2:B\3\2\2\2;<\7\3\2\2<=\5\n\6\2=>\5\26\f\2>?\5\30\r\2?@\7\13\2\2"+
		"@B\3\2\2\2A\62\3\2\2\2A;\3\2\2\2B\t\3\2\2\2CL\7\6\2\2DE\7\6\2\2EF\7\36"+
		"\2\2FG\7\4\2\2GH\7\24\2\2HI\5\16\b\2IJ\7\13\2\2JL\3\2\2\2KC\3\2\2\2KD"+
		"\3\2\2\2L\13\3\2\2\2MN\t\2\2\2N\r\3\2\2\2OP\7\'\2\2P\17\3\2\2\2QY\7\4"+
		"\2\2RS\7\'\2\2SY\7\13\2\2TU\7\21\2\2UV\7\35\2\2VW\7\'\2\2WY\7\13\2\2X"+
		"Q\3\2\2\2XR\3\2\2\2XT\3\2\2\2Y\21\3\2\2\2Z[\7\'\2\2[\23\3\2\2\2\\]\t\3"+
		"\2\2]\25\3\2\2\2^_\t\4\2\2_\27\3\2\2\2`a\b\r\1\2ab\5\32\16\2bc\7*\2\2"+
		"cg\3\2\2\2dg\5\32\16\2eg\7\25\2\2f`\3\2\2\2fd\3\2\2\2fe\3\2\2\2gm\3\2"+
		"\2\2hi\f\6\2\2ij\7*\2\2jl\5\30\r\7kh\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2"+
		"\2\2n\31\3\2\2\2om\3\2\2\2pq\7\'\2\2qr\7\35\2\2rs\7\'\2\2sy\7\21\2\2t"+
		"u\7\'\2\2uv\7\35\2\2vw\7\'\2\2wy\7\22\2\2xp\3\2\2\2xt\3\2\2\2y\33\3\2"+
		"\2\2\t(AKXfmx";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}