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
		AND=20, OR=21, MULT=22, DIV=23, PLUS=24, MINUS=25, GT=26, GE=27, LT=28, 
		LE=29, EQ=30, DECIMAL=31, IDENTIFIER=32, SEMI=33, COMMA=34, COMMENT=35, 
		WS=36;
	public static final int
		RULE_payment_rule_set = 0, RULE_payment_rule = 1, RULE_advance_payment_expr = 2, 
		RULE_residual_due_payment_expr = 3, RULE_proportion_value = 4, RULE_delivery_value = 5, 
		RULE_percent_source = 6, RULE_percent_value = 7, RULE_delivery_count = 8, 
		RULE_after_before = 9, RULE_delivery_number_list = 10, RULE_delivery_number_expr = 11;
	public static final String[] ruleNames = {
		"payment_rule_set", "payment_rule", "advance_payment_expr", "residual_due_payment_expr", 
		"proportion_value", "delivery_value", "percent_source", "percent_value", 
		"delivery_count", "after_before", "delivery_number_list", "delivery_number_expr"
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
			setState(24);
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
			setState(36);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(26);
				advance_payment_expr();
				setState(27);
				match(AND);
				setState(28);
				residual_due_payment_expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(30);
				advance_payment_expr();
				setState(31);
				match(OR);
				setState(32);
				residual_due_payment_expr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(34);
				advance_payment_expr();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(35);
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
			setState(38);
			match(PAY);
			setState(39);
			percent_value();
			setState(40);
			match(PERCENTAGE);
			setState(41);
			match(OF);
			setState(42);
			percent_source();
			setState(43);
			match(ON);
			setState(44);
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
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(PAY);
			setState(47);
			match(RESIDUAL_DUE_PAYMENT);
			setState(48);
			after_before();
			setState(49);
			delivery_number_list(0);
			setState(50);
			match(DELIVERY);
			setState(51);
			match(IN);
			setState(52);
			proportion_value(0);
			setState(53);
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
		public TerminalNode COMMA() { return getToken(PaymentGrammarParser.COMMA, 0); }
		public List<Proportion_valueContext> proportion_value() {
			return getRuleContexts(Proportion_valueContext.class);
		}
		public Proportion_valueContext proportion_value(int i) {
			return getRuleContext(Proportion_valueContext.class,i);
		}
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
		return proportion_value(0);
	}

	private Proportion_valueContext proportion_value(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Proportion_valueContext _localctx = new Proportion_valueContext(_ctx, _parentState);
		Proportion_valueContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_proportion_value, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(56);
				match(DEFAULT);
				}
				break;
			case 2:
				{
				setState(57);
				match(DECIMAL);
				setState(58);
				match(COMMA);
				}
				break;
			case 3:
				{
				setState(59);
				match(DECIMAL);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(67);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Proportion_valueContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_proportion_value);
					setState(62);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(63);
					match(COMMA);
					setState(64);
					proportion_value(4);
					}
					}
				}
				setState(69);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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
			setState(70);
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
		enterRule(_localctx, 12, RULE_percent_source);
		try {
			setState(79);
			switch (_input.LA(1)) {
			case CURRENT_SUBSCRIPTION_AMOUNT:
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				match(CURRENT_SUBSCRIPTION_AMOUNT);
				}
				break;
			case DECIMAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				match(DECIMAL);
				setState(74);
				match(DELIVERY);
				}
				break;
			case N:
				enterOuterAlt(_localctx, 3);
				{
				setState(75);
				match(N);
				setState(76);
				match(DIV);
				setState(77);
				match(DECIMAL);
				setState(78);
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
		enterRule(_localctx, 14, RULE_percent_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
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
		enterRule(_localctx, 16, RULE_delivery_count);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
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
		enterRule(_localctx, 18, RULE_after_before);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
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
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_delivery_number_list, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(88);
				delivery_number_expr();
				setState(89);
				match(COMMA);
				}
				break;
			case 2:
				{
				setState(91);
				delivery_number_expr();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(99);
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
					setState(94);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(95);
					match(COMMA);
					setState(96);
					delivery_number_list(4);
					}
					}
				}
				setState(101);
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
		enterRule(_localctx, 22, RULE_delivery_number_expr);
		try {
			setState(110);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				match(DECIMAL);
				setState(103);
				match(DIV);
				setState(104);
				match(DECIMAL);
				setState(105);
				match(N);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(106);
				match(DECIMAL);
				setState(107);
				match(DIV);
				setState(108);
				match(DECIMAL);
				setState(109);
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
		case 4:
			return proportion_value_sempred((Proportion_valueContext)_localctx, predIndex);
		case 10:
			return delivery_number_list_sempred((Delivery_number_listContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean proportion_value_sempred(Proportion_valueContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean delivery_number_list_sempred(Delivery_number_listContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3&s\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f"+
		"\t\f\4\r\t\r\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\'\n\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\5\6?\n\6\3\6\3\6\3\6\7\6D\n\6\f\6\16\6G\13\6\3\7\3"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bR\n\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f"+
		"\3\f\3\f\3\f\3\f\5\f_\n\f\3\f\3\f\3\f\7\fd\n\f\f\f\16\fg\13\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\5\rq\n\r\3\r\2\4\n\26\16\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\2\4\3\2\21\22\3\2\7\bq\2\32\3\2\2\2\4&\3\2\2\2\6(\3\2\2\2\b"+
		"\60\3\2\2\2\n>\3\2\2\2\fH\3\2\2\2\16Q\3\2\2\2\20S\3\2\2\2\22U\3\2\2\2"+
		"\24W\3\2\2\2\26^\3\2\2\2\30p\3\2\2\2\32\33\5\4\3\2\33\3\3\2\2\2\34\35"+
		"\5\6\4\2\35\36\7\26\2\2\36\37\5\b\5\2\37\'\3\2\2\2 !\5\6\4\2!\"\7\27\2"+
		"\2\"#\5\b\5\2#\'\3\2\2\2$\'\5\6\4\2%\'\5\b\5\2&\34\3\2\2\2& \3\2\2\2&"+
		"$\3\2\2\2&%\3\2\2\2\'\5\3\2\2\2()\7\3\2\2)*\5\20\t\2*+\7\16\2\2+,\7\t"+
		"\2\2,-\5\16\b\2-.\7\20\2\2./\7\5\2\2/\7\3\2\2\2\60\61\7\3\2\2\61\62\7"+
		"\6\2\2\62\63\5\24\13\2\63\64\5\26\f\2\64\65\7\13\2\2\65\66\7\17\2\2\66"+
		"\67\5\n\6\2\678\7\r\2\28\t\3\2\2\29:\b\6\1\2:?\7\f\2\2;<\7!\2\2<?\7$\2"+
		"\2=?\7!\2\2>9\3\2\2\2>;\3\2\2\2>=\3\2\2\2?E\3\2\2\2@A\f\5\2\2AB\7$\2\2"+
		"BD\5\n\6\6C@\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2F\13\3\2\2\2GE\3\2\2"+
		"\2HI\7!\2\2I\r\3\2\2\2JR\7\4\2\2KL\7!\2\2LR\7\13\2\2MN\7\21\2\2NO\7\31"+
		"\2\2OP\7!\2\2PR\7\13\2\2QJ\3\2\2\2QK\3\2\2\2QM\3\2\2\2R\17\3\2\2\2ST\7"+
		"!\2\2T\21\3\2\2\2UV\t\2\2\2V\23\3\2\2\2WX\t\3\2\2X\25\3\2\2\2YZ\b\f\1"+
		"\2Z[\5\30\r\2[\\\7$\2\2\\_\3\2\2\2]_\5\30\r\2^Y\3\2\2\2^]\3\2\2\2_e\3"+
		"\2\2\2`a\f\5\2\2ab\7$\2\2bd\5\26\f\6c`\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3"+
		"\2\2\2f\27\3\2\2\2ge\3\2\2\2hi\7!\2\2ij\7\31\2\2jk\7!\2\2kq\7\21\2\2l"+
		"m\7!\2\2mn\7\31\2\2no\7!\2\2oq\7\22\2\2ph\3\2\2\2pl\3\2\2\2q\31\3\2\2"+
		"\2\t&>EQ^ep";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}