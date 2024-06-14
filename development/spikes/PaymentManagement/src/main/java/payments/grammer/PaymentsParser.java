// Generated from Payments.g4 by ANTLR 4.13.0

package payments.grammer;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class PaymentsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, GIVEN=2, ASINPUT=3, COMPUTE=4, EACH=5, SUMOF=6, ELIGIBLEWHEN=7, 
		PAY=8, BEFORE=9, AFTER=10, IN=11, ON=12, PROPORTION=13, DEFAULT=14, OF=15, 
		THEN=16, ORSTR=17, RESIDUALPAY=18, CURRENTSUBSCRIPTIONAMOUNT=19, ADVANCE_PAYMENT_EVENT=20, 
		EACHDELIVERY=21, FOR=22, DELIVERIES=23, PLUS=24, NullLiteral=25, NUMBER=26, 
		LPAREN=27, RPAREN=28, LBRACE=29, RBRACE=30, LBRACK=31, RBRACK=32, COMMA=33, 
		SEMI=34, COLON=35, ASSIGN=36, GT=37, LT=38, BANG=39, EQUAL=40, LE=41, 
		GE=42, NOTEQUAL=43, INC=44, DEC=45, ADD=46, SUB=47, MUL=48, DIV=49, MOD=50, 
		QUESTIONMARK=51, BooleanLiteral=52, CharacterLiteral=53, StringLiteral=54, 
		IDENTIFIER=55, WS=56, COMMENT=57, LINE_COMMENT=58;
	public static final int
		RULE_scheme = 0, RULE_givenUnit = 1, RULE_givenBody = 2, RULE_computeUnit = 3, 
		RULE_computeBlock = 4, RULE_advancePayUnit = 5, RULE_residualPayUnit = 6, 
		RULE_eligibilityUnit = 7, RULE_payBefore = 8, RULE_payAfter = 9, RULE_payMultiplier = 10, 
		RULE_variableDeclarationStatement = 11, RULE_variableDeclaratorId = 12, 
		RULE_variableInitializer = 13, RULE_arrayInitializer = 14, RULE_conditionalExpression = 15, 
		RULE_conditionalOrExpression = 16, RULE_conditionalAndExpression = 17, 
		RULE_relationalOp = 18, RULE_relationalExpression = 19, RULE_additiveExpression = 20, 
		RULE_multiplicativeExpression = 21, RULE_unaryExpression = 22, RULE_primary = 23, 
		RULE_variableName = 24, RULE_connectorOr = 25, RULE_parExpression = 26, 
		RULE_nonDefaultProportionExpression = 27, RULE_proportionExpression = 28, 
		RULE_expressionList = 29, RULE_expression = 30, RULE_literal = 31, RULE_iterativeStatement = 32, 
		RULE_iterativeAggregationExpression = 33, RULE_statement = 34, RULE_statementExpression = 35, 
		RULE_block = 36, RULE_blockStatement = 37, RULE_connectorAnd = 38;
	private static String[] makeRuleNames() {
		return new String[] {
			"scheme", "givenUnit", "givenBody", "computeUnit", "computeBlock", "advancePayUnit", 
			"residualPayUnit", "eligibilityUnit", "payBefore", "payAfter", "payMultiplier", 
			"variableDeclarationStatement", "variableDeclaratorId", "variableInitializer", 
			"arrayInitializer", "conditionalExpression", "conditionalOrExpression", 
			"conditionalAndExpression", "relationalOp", "relationalExpression", "additiveExpression", 
			"multiplicativeExpression", "unaryExpression", "primary", "variableName", 
			"connectorOr", "parExpression", "nonDefaultProportionExpression", "proportionExpression", 
			"expressionList", "expression", "literal", "iterativeStatement", "iterativeAggregationExpression", 
			"statement", "statementExpression", "block", "blockStatement", "connectorAnd"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'and'", "'given'", "' as input'", "'compute'", "'each '", "'sumOf '", 
			"'eligibleWhen'", "'pay'", "'before'", "'after'", "'in'", "'on'", "'proportion'", 
			"'default'", "'of'", "'then'", "'or'", "'residual due amount'", "'current subscription amount'", 
			"'subscription confirmation'", "'each delivery'", "'for'", "'deliveries'", 
			"'plus'", "'null'", null, "'('", "')'", "'{'", "'}'", "'['", "']'", "','", 
			"';'", "':'", "'='", "'>'", "'<'", "'!'", "'=='", "'<='", "'>='", "'!='", 
			"'++'", "'--'", "'+'", "'-'", "'*'", "'/'", "'%'", "'?'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "GIVEN", "ASINPUT", "COMPUTE", "EACH", "SUMOF", "ELIGIBLEWHEN", 
			"PAY", "BEFORE", "AFTER", "IN", "ON", "PROPORTION", "DEFAULT", "OF", 
			"THEN", "ORSTR", "RESIDUALPAY", "CURRENTSUBSCRIPTIONAMOUNT", "ADVANCE_PAYMENT_EVENT", 
			"EACHDELIVERY", "FOR", "DELIVERIES", "PLUS", "NullLiteral", "NUMBER", 
			"LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "COMMA", 
			"SEMI", "COLON", "ASSIGN", "GT", "LT", "BANG", "EQUAL", "LE", "GE", "NOTEQUAL", 
			"INC", "DEC", "ADD", "SUB", "MUL", "DIV", "MOD", "QUESTIONMARK", "BooleanLiteral", 
			"CharacterLiteral", "StringLiteral", "IDENTIFIER", "WS", "COMMENT", "LINE_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
	public String getGrammarFileName() { return "Payments.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PaymentsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SchemeContext extends ParserRuleContext {
		public GivenUnitContext givenUnit() {
			return getRuleContext(GivenUnitContext.class,0);
		}
		public AdvancePayUnitContext advancePayUnit() {
			return getRuleContext(AdvancePayUnitContext.class,0);
		}
		public TerminalNode THEN() { return getToken(PaymentsParser.THEN, 0); }
		public ResidualPayUnitContext residualPayUnit() {
			return getRuleContext(ResidualPayUnitContext.class,0);
		}
		public TerminalNode EOF() { return getToken(PaymentsParser.EOF, 0); }
		public SchemeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scheme; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterScheme(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitScheme(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitScheme(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SchemeContext scheme() throws RecognitionException {
		SchemeContext _localctx = new SchemeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_scheme);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			givenUnit();
			setState(79);
			advancePayUnit();
			setState(80);
			match(THEN);
			setState(81);
			residualPayUnit();
			setState(82);
			match(EOF);
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

	@SuppressWarnings("CheckReturnValue")
	public static class GivenUnitContext extends ParserRuleContext {
		public TerminalNode GIVEN() { return getToken(PaymentsParser.GIVEN, 0); }
		public GivenBodyContext givenBody() {
			return getRuleContext(GivenBodyContext.class,0);
		}
		public GivenUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterGivenUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitGivenUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitGivenUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GivenUnitContext givenUnit() throws RecognitionException {
		GivenUnitContext _localctx = new GivenUnitContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_givenUnit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(GIVEN);
			setState(85);
			givenBody();
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

	@SuppressWarnings("CheckReturnValue")
	public static class GivenBodyContext extends ParserRuleContext {
		public List<VariableDeclarationStatementContext> variableDeclarationStatement() {
			return getRuleContexts(VariableDeclarationStatementContext.class);
		}
		public VariableDeclarationStatementContext variableDeclarationStatement(int i) {
			return getRuleContext(VariableDeclarationStatementContext.class,i);
		}
		public GivenBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterGivenBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitGivenBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitGivenBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GivenBodyContext givenBody() throws RecognitionException {
		GivenBodyContext _localctx = new GivenBodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_givenBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENTIFIER) {
				{
				{
				setState(87);
				variableDeclarationStatement();
				}
				}
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ComputeUnitContext extends ParserRuleContext {
		public TerminalNode COMPUTE() { return getToken(PaymentsParser.COMPUTE, 0); }
		public ComputeBlockContext computeBlock() {
			return getRuleContext(ComputeBlockContext.class,0);
		}
		public ComputeUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_computeUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterComputeUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitComputeUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitComputeUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComputeUnitContext computeUnit() throws RecognitionException {
		ComputeUnitContext _localctx = new ComputeUnitContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_computeUnit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(COMPUTE);
			setState(94);
			computeBlock();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ComputeBlockContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ComputeBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_computeBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterComputeBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitComputeBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitComputeBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComputeBlockContext computeBlock() throws RecognitionException {
		ComputeBlockContext _localctx = new ComputeBlockContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_computeBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			block();
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

	@SuppressWarnings("CheckReturnValue")
	public static class AdvancePayUnitContext extends ParserRuleContext {
		public TerminalNode PAY() { return getToken(PaymentsParser.PAY, 0); }
		public ComputeBlockContext computeBlock() {
			return getRuleContext(ComputeBlockContext.class,0);
		}
		public TerminalNode ON() { return getToken(PaymentsParser.ON, 0); }
		public TerminalNode ADVANCE_PAYMENT_EVENT() { return getToken(PaymentsParser.ADVANCE_PAYMENT_EVENT, 0); }
		public TerminalNode SEMI() { return getToken(PaymentsParser.SEMI, 0); }
		public AdvancePayUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_advancePayUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterAdvancePayUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitAdvancePayUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitAdvancePayUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdvancePayUnitContext advancePayUnit() throws RecognitionException {
		AdvancePayUnitContext _localctx = new AdvancePayUnitContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_advancePayUnit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(PAY);
			setState(99);
			computeBlock();
			setState(100);
			match(ON);
			setState(101);
			match(ADVANCE_PAYMENT_EVENT);
			setState(102);
			match(SEMI);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ResidualPayUnitContext extends ParserRuleContext {
		public TerminalNode PAY() { return getToken(PaymentsParser.PAY, 0); }
		public ComputeBlockContext computeBlock() {
			return getRuleContext(ComputeBlockContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<PayMultiplierContext> payMultiplier() {
			return getRuleContexts(PayMultiplierContext.class);
		}
		public PayMultiplierContext payMultiplier(int i) {
			return getRuleContext(PayMultiplierContext.class,i);
		}
		public List<VariableNameContext> variableName() {
			return getRuleContexts(VariableNameContext.class);
		}
		public VariableNameContext variableName(int i) {
			return getRuleContext(VariableNameContext.class,i);
		}
		public ProportionExpressionContext proportionExpression() {
			return getRuleContext(ProportionExpressionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(PaymentsParser.SEMI, 0); }
		public PayBeforeContext payBefore() {
			return getRuleContext(PayBeforeContext.class,0);
		}
		public PayAfterContext payAfter() {
			return getRuleContext(PayAfterContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(PaymentsParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PaymentsParser.COMMA, i);
		}
		public ResidualPayUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_residualPayUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterResidualPayUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitResidualPayUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitResidualPayUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ResidualPayUnitContext residualPayUnit() throws RecognitionException {
		ResidualPayUnitContext _localctx = new ResidualPayUnitContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_residualPayUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(PAY);
			setState(105);
			computeBlock();
			setState(108);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BEFORE:
				{
				setState(106);
				payBefore();
				}
				break;
			case AFTER:
				{
				setState(107);
				payAfter();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(110);
			expression();
			setState(111);
			payMultiplier();
			setState(112);
			variableName();
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(113);
				match(COMMA);
				setState(114);
				expression();
				setState(115);
				payMultiplier();
				setState(116);
				variableName();
				}
				}
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(123);
			proportionExpression();
			setState(124);
			match(SEMI);
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

	@SuppressWarnings("CheckReturnValue")
	public static class EligibilityUnitContext extends ParserRuleContext {
		public TerminalNode ELIGIBLEWHEN() { return getToken(PaymentsParser.ELIGIBLEWHEN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public EligibilityUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eligibilityUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterEligibilityUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitEligibilityUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitEligibilityUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EligibilityUnitContext eligibilityUnit() throws RecognitionException {
		EligibilityUnitContext _localctx = new EligibilityUnitContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_eligibilityUnit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(ELIGIBLEWHEN);
			setState(127);
			block();
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

	@SuppressWarnings("CheckReturnValue")
	public static class PayBeforeContext extends ParserRuleContext {
		public TerminalNode BEFORE() { return getToken(PaymentsParser.BEFORE, 0); }
		public PayBeforeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_payBefore; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterPayBefore(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitPayBefore(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitPayBefore(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PayBeforeContext payBefore() throws RecognitionException {
		PayBeforeContext _localctx = new PayBeforeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_payBefore);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			match(BEFORE);
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

	@SuppressWarnings("CheckReturnValue")
	public static class PayAfterContext extends ParserRuleContext {
		public TerminalNode AFTER() { return getToken(PaymentsParser.AFTER, 0); }
		public PayAfterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_payAfter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterPayAfter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitPayAfter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitPayAfter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PayAfterContext payAfter() throws RecognitionException {
		PayAfterContext _localctx = new PayAfterContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_payAfter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(AFTER);
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

	@SuppressWarnings("CheckReturnValue")
	public static class PayMultiplierContext extends ParserRuleContext {
		public TerminalNode OF() { return getToken(PaymentsParser.OF, 0); }
		public PayMultiplierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_payMultiplier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterPayMultiplier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitPayMultiplier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitPayMultiplier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PayMultiplierContext payMultiplier() throws RecognitionException {
		PayMultiplierContext _localctx = new PayMultiplierContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_payMultiplier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(OF);
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

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclarationStatementContext extends ParserRuleContext {
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(PaymentsParser.SEMI, 0); }
		public TerminalNode ASSIGN() { return getToken(PaymentsParser.ASSIGN, 0); }
		public VariableInitializerContext variableInitializer() {
			return getRuleContext(VariableInitializerContext.class,0);
		}
		public TerminalNode ASINPUT() { return getToken(PaymentsParser.ASINPUT, 0); }
		public VariableDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterVariableDeclarationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitVariableDeclarationStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitVariableDeclarationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationStatementContext variableDeclarationStatement() throws RecognitionException {
		VariableDeclarationStatementContext _localctx = new VariableDeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_variableDeclarationStatement);
		try {
			setState(147);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(135);
				variableDeclaratorId();
				setState(136);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				variableDeclaratorId();
				setState(139);
				match(ASSIGN);
				setState(140);
				variableInitializer();
				setState(141);
				match(SEMI);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(143);
				variableDeclaratorId();
				setState(144);
				match(ASINPUT);
				setState(145);
				match(SEMI);
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

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclaratorIdContext extends ParserRuleContext {
		public VariableNameContext variableName() {
			return getRuleContext(VariableNameContext.class,0);
		}
		public TerminalNode LBRACK() { return getToken(PaymentsParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(PaymentsParser.RBRACK, 0); }
		public VariableDeclaratorIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaratorId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterVariableDeclaratorId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitVariableDeclaratorId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitVariableDeclaratorId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclaratorIdContext variableDeclaratorId() throws RecognitionException {
		VariableDeclaratorIdContext _localctx = new VariableDeclaratorIdContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_variableDeclaratorId);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			variableName();
			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACK) {
				{
				setState(150);
				match(LBRACK);
				setState(151);
				match(RBRACK);
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class VariableInitializerContext extends ParserRuleContext {
		public ArrayInitializerContext arrayInitializer() {
			return getRuleContext(ArrayInitializerContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterVariableInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitVariableInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitVariableInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableInitializerContext variableInitializer() throws RecognitionException {
		VariableInitializerContext _localctx = new VariableInitializerContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_variableInitializer);
		try {
			setState(157);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(154);
				arrayInitializer();
				}
				break;
			case EACH:
			case SUMOF:
			case NullLiteral:
			case NUMBER:
			case LPAREN:
			case ADD:
			case SUB:
			case BooleanLiteral:
			case CharacterLiteral:
			case StringLiteral:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(155);
				expression();
				}
				break;
			case RBRACE:
			case COMMA:
			case SEMI:
				enterOuterAlt(_localctx, 3);
				{
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayInitializerContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(PaymentsParser.LBRACE, 0); }
		public List<VariableInitializerContext> variableInitializer() {
			return getRuleContexts(VariableInitializerContext.class);
		}
		public VariableInitializerContext variableInitializer(int i) {
			return getRuleContext(VariableInitializerContext.class,i);
		}
		public TerminalNode RBRACE() { return getToken(PaymentsParser.RBRACE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PaymentsParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PaymentsParser.COMMA, i);
		}
		public ArrayInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterArrayInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitArrayInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitArrayInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayInitializerContext arrayInitializer() throws RecognitionException {
		ArrayInitializerContext _localctx = new ArrayInitializerContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_arrayInitializer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(LBRACE);
			setState(160);
			variableInitializer();
			setState(165);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(161);
				match(COMMA);
				setState(162);
				variableInitializer();
				}
				}
				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(168);
			match(RBRACE);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ConditionalExpressionContext extends ParserRuleContext {
		public ConditionalOrExpressionContext conditionalOrExpression() {
			return getRuleContext(ConditionalOrExpressionContext.class,0);
		}
		public TerminalNode QUESTIONMARK() { return getToken(PaymentsParser.QUESTIONMARK, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode COLON() { return getToken(PaymentsParser.COLON, 0); }
		public ConditionalExpressionContext conditionalExpression() {
			return getRuleContext(ConditionalExpressionContext.class,0);
		}
		public ConditionalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterConditionalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitConditionalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitConditionalExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalExpressionContext conditionalExpression() throws RecognitionException {
		ConditionalExpressionContext _localctx = new ConditionalExpressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_conditionalExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			conditionalOrExpression();
			setState(176);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(171);
				match(QUESTIONMARK);
				setState(172);
				expression();
				setState(173);
				match(COLON);
				setState(174);
				conditionalExpression();
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class ConditionalOrExpressionContext extends ParserRuleContext {
		public List<ConditionalAndExpressionContext> conditionalAndExpression() {
			return getRuleContexts(ConditionalAndExpressionContext.class);
		}
		public ConditionalAndExpressionContext conditionalAndExpression(int i) {
			return getRuleContext(ConditionalAndExpressionContext.class,i);
		}
		public List<ConnectorOrContext> connectorOr() {
			return getRuleContexts(ConnectorOrContext.class);
		}
		public ConnectorOrContext connectorOr(int i) {
			return getRuleContext(ConnectorOrContext.class,i);
		}
		public ConditionalOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalOrExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterConditionalOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitConditionalOrExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitConditionalOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalOrExpressionContext conditionalOrExpression() throws RecognitionException {
		ConditionalOrExpressionContext _localctx = new ConditionalOrExpressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_conditionalOrExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			conditionalAndExpression();
			setState(184);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(179);
					connectorOr();
					setState(180);
					conditionalAndExpression();
					}
					}
				}
				setState(186);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ConditionalAndExpressionContext extends ParserRuleContext {
		public List<RelationalExpressionContext> relationalExpression() {
			return getRuleContexts(RelationalExpressionContext.class);
		}
		public RelationalExpressionContext relationalExpression(int i) {
			return getRuleContext(RelationalExpressionContext.class,i);
		}
		public List<ConnectorAndContext> connectorAnd() {
			return getRuleContexts(ConnectorAndContext.class);
		}
		public ConnectorAndContext connectorAnd(int i) {
			return getRuleContext(ConnectorAndContext.class,i);
		}
		public ConditionalAndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalAndExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterConditionalAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitConditionalAndExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitConditionalAndExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalAndExpressionContext conditionalAndExpression() throws RecognitionException {
		ConditionalAndExpressionContext _localctx = new ConditionalAndExpressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_conditionalAndExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			relationalExpression();
			setState(193);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(188);
					connectorAnd();
					setState(189);
					relationalExpression();
					}
					}
				}
				setState(195);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RelationalOpContext extends ParserRuleContext {
		public TerminalNode GT() { return getToken(PaymentsParser.GT, 0); }
		public TerminalNode GE() { return getToken(PaymentsParser.GE, 0); }
		public TerminalNode LE() { return getToken(PaymentsParser.LE, 0); }
		public TerminalNode LT() { return getToken(PaymentsParser.LT, 0); }
		public TerminalNode EQUAL() { return getToken(PaymentsParser.EQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(PaymentsParser.NOTEQUAL, 0); }
		public RelationalOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterRelationalOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitRelationalOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitRelationalOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationalOpContext relationalOp() throws RecognitionException {
		RelationalOpContext _localctx = new RelationalOpContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_relationalOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 16904991277056L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RelationalExpressionContext extends ParserRuleContext {
		public List<AdditiveExpressionContext> additiveExpression() {
			return getRuleContexts(AdditiveExpressionContext.class);
		}
		public AdditiveExpressionContext additiveExpression(int i) {
			return getRuleContext(AdditiveExpressionContext.class,i);
		}
		public List<RelationalOpContext> relationalOp() {
			return getRuleContexts(RelationalOpContext.class);
		}
		public RelationalOpContext relationalOp(int i) {
			return getRuleContext(RelationalOpContext.class,i);
		}
		public IterativeStatementContext iterativeStatement() {
			return getRuleContext(IterativeStatementContext.class,0);
		}
		public RelationalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterRelationalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitRelationalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitRelationalExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationalExpressionContext relationalExpression() throws RecognitionException {
		RelationalExpressionContext _localctx = new RelationalExpressionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_relationalExpression);
		try {
			int _alt;
			setState(216);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUMOF:
			case NullLiteral:
			case NUMBER:
			case LPAREN:
			case ADD:
			case SUB:
			case BooleanLiteral:
			case CharacterLiteral:
			case StringLiteral:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(198);
				additiveExpression();
				setState(204);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(199);
						relationalOp();
						setState(200);
						additiveExpression();
						}
						}
					}
					setState(206);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				}
				}
				break;
			case EACH:
				enterOuterAlt(_localctx, 2);
				{
				setState(207);
				iterativeStatement();
				setState(213);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(208);
						relationalOp();
						setState(209);
						additiveExpression();
						}
						}
					}
					setState(215);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class AdditiveExpressionContext extends ParserRuleContext {
		public List<MultiplicativeExpressionContext> multiplicativeExpression() {
			return getRuleContexts(MultiplicativeExpressionContext.class);
		}
		public MultiplicativeExpressionContext multiplicativeExpression(int i) {
			return getRuleContext(MultiplicativeExpressionContext.class,i);
		}
		public List<TerminalNode> ADD() { return getTokens(PaymentsParser.ADD); }
		public TerminalNode ADD(int i) {
			return getToken(PaymentsParser.ADD, i);
		}
		public List<TerminalNode> SUB() { return getTokens(PaymentsParser.SUB); }
		public TerminalNode SUB(int i) {
			return getToken(PaymentsParser.SUB, i);
		}
		public IterativeAggregationExpressionContext iterativeAggregationExpression() {
			return getRuleContext(IterativeAggregationExpressionContext.class,0);
		}
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterAdditiveExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitAdditiveExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitAdditiveExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_additiveExpression);
		int _la;
		try {
			int _alt;
			setState(227);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NullLiteral:
			case NUMBER:
			case LPAREN:
			case ADD:
			case SUB:
			case BooleanLiteral:
			case CharacterLiteral:
			case StringLiteral:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(218);
				multiplicativeExpression();
				setState(223);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(219);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(220);
						multiplicativeExpression();
						}
						} 
					}
					setState(225);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				}
				}
				break;
			case SUMOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(226);
				iterativeAggregationExpression();
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

	@SuppressWarnings("CheckReturnValue")
	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public List<UnaryExpressionContext> unaryExpression() {
			return getRuleContexts(UnaryExpressionContext.class);
		}
		public UnaryExpressionContext unaryExpression(int i) {
			return getRuleContext(UnaryExpressionContext.class,i);
		}
		public List<TerminalNode> MUL() { return getTokens(PaymentsParser.MUL); }
		public TerminalNode MUL(int i) {
			return getToken(PaymentsParser.MUL, i);
		}
		public List<TerminalNode> DIV() { return getTokens(PaymentsParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(PaymentsParser.DIV, i);
		}
		public List<TerminalNode> MOD() { return getTokens(PaymentsParser.MOD); }
		public TerminalNode MOD(int i) {
			return getToken(PaymentsParser.MOD, i);
		}
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterMultiplicativeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitMultiplicativeExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitMultiplicativeExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_multiplicativeExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			unaryExpression();
			setState(234);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1970324836974592L) != 0)) {
				{
				{
				setState(230);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1970324836974592L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(231);
				unaryExpression();
				}
				}
				setState(236);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class UnaryExpressionContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(PaymentsParser.ADD, 0); }
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public TerminalNode SUB() { return getToken(PaymentsParser.SUB, 0); }
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterUnaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitUnaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitUnaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_unaryExpression);
		try {
			setState(242);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
				enterOuterAlt(_localctx, 1);
				{
				setState(237);
				match(ADD);
				setState(238);
				unaryExpression();
				}
				break;
			case SUB:
				enterOuterAlt(_localctx, 2);
				{
				setState(239);
				match(SUB);
				setState(240);
				unaryExpression();
				}
				break;
			case NullLiteral:
			case NUMBER:
			case LPAREN:
			case BooleanLiteral:
			case CharacterLiteral:
			case StringLiteral:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 3);
				{
				setState(241);
				primary();
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

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryContext extends ParserRuleContext {
		public ParExpressionContext parExpression() {
			return getRuleContext(ParExpressionContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public VariableNameContext variableName() {
			return getRuleContext(VariableNameContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_primary);
		try {
			setState(247);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(244);
				parExpression();
				}
				break;
			case NullLiteral:
			case NUMBER:
			case BooleanLiteral:
			case CharacterLiteral:
			case StringLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(245);
				literal();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 3);
				{
				setState(246);
				variableName();
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

	@SuppressWarnings("CheckReturnValue")
	public static class VariableNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(PaymentsParser.IDENTIFIER, 0); }
		public VariableNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterVariableName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitVariableName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitVariableName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableNameContext variableName() throws RecognitionException {
		VariableNameContext _localctx = new VariableNameContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_variableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			match(IDENTIFIER);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ConnectorOrContext extends ParserRuleContext {
		public TerminalNode ORSTR() { return getToken(PaymentsParser.ORSTR, 0); }
		public ConnectorOrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_connectorOr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterConnectorOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitConnectorOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitConnectorOr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConnectorOrContext connectorOr() throws RecognitionException {
		ConnectorOrContext _localctx = new ConnectorOrContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_connectorOr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			match(ORSTR);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ParExpressionContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(PaymentsParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PaymentsParser.RPAREN, 0); }
		public ParExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterParExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitParExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitParExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParExpressionContext parExpression() throws RecognitionException {
		ParExpressionContext _localctx = new ParExpressionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_parExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			match(LPAREN);
			setState(254);
			expression();
			setState(255);
			match(RPAREN);
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

	@SuppressWarnings("CheckReturnValue")
	public static class NonDefaultProportionExpressionContext extends ParserRuleContext {
		public List<TerminalNode> NUMBER() { return getTokens(PaymentsParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(PaymentsParser.NUMBER, i);
		}
		public List<TerminalNode> COLON() { return getTokens(PaymentsParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(PaymentsParser.COLON, i);
		}
		public NonDefaultProportionExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonDefaultProportionExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterNonDefaultProportionExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitNonDefaultProportionExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitNonDefaultProportionExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NonDefaultProportionExpressionContext nonDefaultProportionExpression() throws RecognitionException {
		NonDefaultProportionExpressionContext _localctx = new NonDefaultProportionExpressionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_nonDefaultProportionExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			match(NUMBER);
			setState(258);
			match(COLON);
			setState(259);
			match(NUMBER);
			setState(262); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(260);
				match(COLON);
				setState(261);
				match(NUMBER);
				}
				}
				setState(264); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==COLON );
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

	@SuppressWarnings("CheckReturnValue")
	public static class ProportionExpressionContext extends ParserRuleContext {
		public TerminalNode IN() { return getToken(PaymentsParser.IN, 0); }
		public TerminalNode PROPORTION() { return getToken(PaymentsParser.PROPORTION, 0); }
		public TerminalNode DEFAULT() { return getToken(PaymentsParser.DEFAULT, 0); }
		public NonDefaultProportionExpressionContext nonDefaultProportionExpression() {
			return getRuleContext(NonDefaultProportionExpressionContext.class,0);
		}
		public ProportionExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proportionExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterProportionExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitProportionExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitProportionExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProportionExpressionContext proportionExpression() throws RecognitionException {
		ProportionExpressionContext _localctx = new ProportionExpressionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_proportionExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			match(IN);
			setState(269);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DEFAULT:
				{
				setState(267);
				match(DEFAULT);
				}
				break;
			case NUMBER:
				{
				setState(268);
				nonDefaultProportionExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(271);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PaymentsParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PaymentsParser.COMMA, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			expression();
			setState(278);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(274);
				match(COMMA);
				setState(275);
				expression();
				}
				}
				setState(280);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ConditionalExpressionContext conditionalExpression() {
			return getRuleContext(ConditionalExpressionContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(PaymentsParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			conditionalExpression();
			setState(284);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(282);
				match(ASSIGN);
				setState(283);
				expression();
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(PaymentsParser.NUMBER, 0); }
		public TerminalNode CharacterLiteral() { return getToken(PaymentsParser.CharacterLiteral, 0); }
		public TerminalNode StringLiteral() { return getToken(PaymentsParser.StringLiteral, 0); }
		public TerminalNode BooleanLiteral() { return getToken(PaymentsParser.BooleanLiteral, 0); }
		public TerminalNode NullLiteral() { return getToken(PaymentsParser.NullLiteral, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 31525197492256768L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class IterativeStatementContext extends ParserRuleContext {
		public TerminalNode EACH() { return getToken(PaymentsParser.EACH, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IterativeStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iterativeStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterIterativeStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitIterativeStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitIterativeStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IterativeStatementContext iterativeStatement() throws RecognitionException {
		IterativeStatementContext _localctx = new IterativeStatementContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_iterativeStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			match(EACH);
			setState(289);
			expression();
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

	@SuppressWarnings("CheckReturnValue")
	public static class IterativeAggregationExpressionContext extends ParserRuleContext {
		public TerminalNode SUMOF() { return getToken(PaymentsParser.SUMOF, 0); }
		public TerminalNode EACH() { return getToken(PaymentsParser.EACH, 0); }
		public VariableDeclarationStatementContext variableDeclarationStatement() {
			return getRuleContext(VariableDeclarationStatementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IterativeAggregationExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iterativeAggregationExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterIterativeAggregationExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitIterativeAggregationExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitIterativeAggregationExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IterativeAggregationExpressionContext iterativeAggregationExpression() throws RecognitionException {
		IterativeAggregationExpressionContext _localctx = new IterativeAggregationExpressionContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_iterativeAggregationExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			match(SUMOF);
			setState(292);
			match(EACH);
			setState(295);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(293);
				variableDeclarationStatement();
				}
				break;
			case 2:
				{
				setState(294);
				expression();
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public StatementExpressionContext statementExpression() {
			return getRuleContext(StatementExpressionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(PaymentsParser.SEMI, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			statementExpression();
			setState(299);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(298);
				match(SEMI);
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class StatementExpressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterStatementExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitStatementExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitStatementExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementExpressionContext statementExpression() throws RecognitionException {
		StatementExpressionContext _localctx = new StatementExpressionContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_statementExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			expression();
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

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public List<BlockStatementContext> blockStatement() {
			return getRuleContexts(BlockStatementContext.class);
		}
		public BlockStatementContext blockStatement(int i) {
			return getRuleContext(BlockStatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 67765100877971552L) != 0)) {
				{
				{
				setState(303);
				blockStatement();
				}
				}
				setState(308);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class BlockStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public VariableDeclarationStatementContext variableDeclarationStatement() {
			return getRuleContext(VariableDeclarationStatementContext.class,0);
		}
		public BlockStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterBlockStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitBlockStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitBlockStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockStatementContext blockStatement() throws RecognitionException {
		BlockStatementContext _localctx = new BlockStatementContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_blockStatement);
		try {
			setState(311);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(309);
				statement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(310);
				variableDeclarationStatement();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ConnectorAndContext extends ParserRuleContext {
		public ConnectorAndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_connectorAnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).enterConnectorAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PaymentsListener ) ((PaymentsListener)listener).exitConnectorAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PaymentsVisitor ) return ((PaymentsVisitor<? extends T>)visitor).visitConnectorAnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConnectorAndContext connectorAnd() throws RecognitionException {
		ConnectorAndContext _localctx = new ConnectorAndContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_connectorAnd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			match(T__0);
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
		"\u0004\u0001:\u013c\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0005\u0002Y\b\u0002\n\u0002\f\u0002\\\t\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006m\b\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0005\u0006w\b\u0006\n\u0006\f\u0006z\t\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0094\b\u000b\u0001\f"+
		"\u0001\f\u0001\f\u0003\f\u0099\b\f\u0001\r\u0001\r\u0001\r\u0003\r\u009e"+
		"\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u00a4"+
		"\b\u000e\n\u000e\f\u000e\u00a7\t\u000e\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f"+
		"\u00b1\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010"+
		"\u00b7\b\u0010\n\u0010\f\u0010\u00ba\t\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0005\u0011\u00c0\b\u0011\n\u0011\f\u0011\u00c3\t\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0005\u0013\u00cb\b\u0013\n\u0013\f\u0013\u00ce\t\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u00d4\b\u0013\n\u0013\f\u0013"+
		"\u00d7\t\u0013\u0003\u0013\u00d9\b\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0005\u0014\u00de\b\u0014\n\u0014\f\u0014\u00e1\t\u0014\u0001\u0014"+
		"\u0003\u0014\u00e4\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015"+
		"\u00e9\b\u0015\n\u0015\f\u0015\u00ec\t\u0015\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u00f3\b\u0016\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0003\u0017\u00f8\b\u0017\u0001\u0018\u0001\u0018\u0001"+
		"\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0004\u001b\u0107"+
		"\b\u001b\u000b\u001b\f\u001b\u0108\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0003\u001c\u010e\b\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0005\u001d\u0115\b\u001d\n\u001d\f\u001d\u0118\t\u001d\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u011d\b\u001e\u0001\u001f\u0001"+
		"\u001f\u0001 \u0001 \u0001 \u0001!\u0001!\u0001!\u0001!\u0003!\u0128\b"+
		"!\u0001\"\u0001\"\u0003\"\u012c\b\"\u0001#\u0001#\u0001$\u0005$\u0131"+
		"\b$\n$\f$\u0134\t$\u0001%\u0001%\u0003%\u0138\b%\u0001&\u0001&\u0001&"+
		"\u0000\u0000\'\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJL\u0000\u0004\u0002\u0000"+
		"%&(+\u0001\u0000./\u0001\u000002\u0002\u0000\u0019\u001a46\u0132\u0000"+
		"N\u0001\u0000\u0000\u0000\u0002T\u0001\u0000\u0000\u0000\u0004Z\u0001"+
		"\u0000\u0000\u0000\u0006]\u0001\u0000\u0000\u0000\b`\u0001\u0000\u0000"+
		"\u0000\nb\u0001\u0000\u0000\u0000\fh\u0001\u0000\u0000\u0000\u000e~\u0001"+
		"\u0000\u0000\u0000\u0010\u0081\u0001\u0000\u0000\u0000\u0012\u0083\u0001"+
		"\u0000\u0000\u0000\u0014\u0085\u0001\u0000\u0000\u0000\u0016\u0093\u0001"+
		"\u0000\u0000\u0000\u0018\u0095\u0001\u0000\u0000\u0000\u001a\u009d\u0001"+
		"\u0000\u0000\u0000\u001c\u009f\u0001\u0000\u0000\u0000\u001e\u00aa\u0001"+
		"\u0000\u0000\u0000 \u00b2\u0001\u0000\u0000\u0000\"\u00bb\u0001\u0000"+
		"\u0000\u0000$\u00c4\u0001\u0000\u0000\u0000&\u00d8\u0001\u0000\u0000\u0000"+
		"(\u00e3\u0001\u0000\u0000\u0000*\u00e5\u0001\u0000\u0000\u0000,\u00f2"+
		"\u0001\u0000\u0000\u0000.\u00f7\u0001\u0000\u0000\u00000\u00f9\u0001\u0000"+
		"\u0000\u00002\u00fb\u0001\u0000\u0000\u00004\u00fd\u0001\u0000\u0000\u0000"+
		"6\u0101\u0001\u0000\u0000\u00008\u010a\u0001\u0000\u0000\u0000:\u0111"+
		"\u0001\u0000\u0000\u0000<\u0119\u0001\u0000\u0000\u0000>\u011e\u0001\u0000"+
		"\u0000\u0000@\u0120\u0001\u0000\u0000\u0000B\u0123\u0001\u0000\u0000\u0000"+
		"D\u0129\u0001\u0000\u0000\u0000F\u012d\u0001\u0000\u0000\u0000H\u0132"+
		"\u0001\u0000\u0000\u0000J\u0137\u0001\u0000\u0000\u0000L\u0139\u0001\u0000"+
		"\u0000\u0000NO\u0003\u0002\u0001\u0000OP\u0003\n\u0005\u0000PQ\u0005\u0010"+
		"\u0000\u0000QR\u0003\f\u0006\u0000RS\u0005\u0000\u0000\u0001S\u0001\u0001"+
		"\u0000\u0000\u0000TU\u0005\u0002\u0000\u0000UV\u0003\u0004\u0002\u0000"+
		"V\u0003\u0001\u0000\u0000\u0000WY\u0003\u0016\u000b\u0000XW\u0001\u0000"+
		"\u0000\u0000Y\\\u0001\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000Z[\u0001"+
		"\u0000\u0000\u0000[\u0005\u0001\u0000\u0000\u0000\\Z\u0001\u0000\u0000"+
		"\u0000]^\u0005\u0004\u0000\u0000^_\u0003\b\u0004\u0000_\u0007\u0001\u0000"+
		"\u0000\u0000`a\u0003H$\u0000a\t\u0001\u0000\u0000\u0000bc\u0005\b\u0000"+
		"\u0000cd\u0003\b\u0004\u0000de\u0005\f\u0000\u0000ef\u0005\u0014\u0000"+
		"\u0000fg\u0005\"\u0000\u0000g\u000b\u0001\u0000\u0000\u0000hi\u0005\b"+
		"\u0000\u0000il\u0003\b\u0004\u0000jm\u0003\u0010\b\u0000km\u0003\u0012"+
		"\t\u0000lj\u0001\u0000\u0000\u0000lk\u0001\u0000\u0000\u0000mn\u0001\u0000"+
		"\u0000\u0000no\u0003<\u001e\u0000op\u0003\u0014\n\u0000px\u00030\u0018"+
		"\u0000qr\u0005!\u0000\u0000rs\u0003<\u001e\u0000st\u0003\u0014\n\u0000"+
		"tu\u00030\u0018\u0000uw\u0001\u0000\u0000\u0000vq\u0001\u0000\u0000\u0000"+
		"wz\u0001\u0000\u0000\u0000xv\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000"+
		"\u0000y{\u0001\u0000\u0000\u0000zx\u0001\u0000\u0000\u0000{|\u00038\u001c"+
		"\u0000|}\u0005\"\u0000\u0000}\r\u0001\u0000\u0000\u0000~\u007f\u0005\u0007"+
		"\u0000\u0000\u007f\u0080\u0003H$\u0000\u0080\u000f\u0001\u0000\u0000\u0000"+
		"\u0081\u0082\u0005\t\u0000\u0000\u0082\u0011\u0001\u0000\u0000\u0000\u0083"+
		"\u0084\u0005\n\u0000\u0000\u0084\u0013\u0001\u0000\u0000\u0000\u0085\u0086"+
		"\u0005\u000f\u0000\u0000\u0086\u0015\u0001\u0000\u0000\u0000\u0087\u0088"+
		"\u0003\u0018\f\u0000\u0088\u0089\u0005\"\u0000\u0000\u0089\u0094\u0001"+
		"\u0000\u0000\u0000\u008a\u008b\u0003\u0018\f\u0000\u008b\u008c\u0005$"+
		"\u0000\u0000\u008c\u008d\u0003\u001a\r\u0000\u008d\u008e\u0005\"\u0000"+
		"\u0000\u008e\u0094\u0001\u0000\u0000\u0000\u008f\u0090\u0003\u0018\f\u0000"+
		"\u0090\u0091\u0005\u0003\u0000\u0000\u0091\u0092\u0005\"\u0000\u0000\u0092"+
		"\u0094\u0001\u0000\u0000\u0000\u0093\u0087\u0001\u0000\u0000\u0000\u0093"+
		"\u008a\u0001\u0000\u0000\u0000\u0093\u008f\u0001\u0000\u0000\u0000\u0094"+
		"\u0017\u0001\u0000\u0000\u0000\u0095\u0098\u00030\u0018\u0000\u0096\u0097"+
		"\u0005\u001f\u0000\u0000\u0097\u0099\u0005 \u0000\u0000\u0098\u0096\u0001"+
		"\u0000\u0000\u0000\u0098\u0099\u0001\u0000\u0000\u0000\u0099\u0019\u0001"+
		"\u0000\u0000\u0000\u009a\u009e\u0003\u001c\u000e\u0000\u009b\u009e\u0003"+
		"<\u001e\u0000\u009c\u009e\u0001\u0000\u0000\u0000\u009d\u009a\u0001\u0000"+
		"\u0000\u0000\u009d\u009b\u0001\u0000\u0000\u0000\u009d\u009c\u0001\u0000"+
		"\u0000\u0000\u009e\u001b\u0001\u0000\u0000\u0000\u009f\u00a0\u0005\u001d"+
		"\u0000\u0000\u00a0\u00a5\u0003\u001a\r\u0000\u00a1\u00a2\u0005!\u0000"+
		"\u0000\u00a2\u00a4\u0003\u001a\r\u0000\u00a3\u00a1\u0001\u0000\u0000\u0000"+
		"\u00a4\u00a7\u0001\u0000\u0000\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000"+
		"\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6\u00a8\u0001\u0000\u0000\u0000"+
		"\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005\u001e\u0000\u0000"+
		"\u00a9\u001d\u0001\u0000\u0000\u0000\u00aa\u00b0\u0003 \u0010\u0000\u00ab"+
		"\u00ac\u00053\u0000\u0000\u00ac\u00ad\u0003<\u001e\u0000\u00ad\u00ae\u0005"+
		"#\u0000\u0000\u00ae\u00af\u0003\u001e\u000f\u0000\u00af\u00b1\u0001\u0000"+
		"\u0000\u0000\u00b0\u00ab\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001\u0000"+
		"\u0000\u0000\u00b1\u001f\u0001\u0000\u0000\u0000\u00b2\u00b8\u0003\"\u0011"+
		"\u0000\u00b3\u00b4\u00032\u0019\u0000\u00b4\u00b5\u0003\"\u0011\u0000"+
		"\u00b5\u00b7\u0001\u0000\u0000\u0000\u00b6\u00b3\u0001\u0000\u0000\u0000"+
		"\u00b7\u00ba\u0001\u0000\u0000\u0000\u00b8\u00b6\u0001\u0000\u0000\u0000"+
		"\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9!\u0001\u0000\u0000\u0000\u00ba"+
		"\u00b8\u0001\u0000\u0000\u0000\u00bb\u00c1\u0003&\u0013\u0000\u00bc\u00bd"+
		"\u0003L&\u0000\u00bd\u00be\u0003&\u0013\u0000\u00be\u00c0\u0001\u0000"+
		"\u0000\u0000\u00bf\u00bc\u0001\u0000\u0000\u0000\u00c0\u00c3\u0001\u0000"+
		"\u0000\u0000\u00c1\u00bf\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001\u0000"+
		"\u0000\u0000\u00c2#\u0001\u0000\u0000\u0000\u00c3\u00c1\u0001\u0000\u0000"+
		"\u0000\u00c4\u00c5\u0007\u0000\u0000\u0000\u00c5%\u0001\u0000\u0000\u0000"+
		"\u00c6\u00cc\u0003(\u0014\u0000\u00c7\u00c8\u0003$\u0012\u0000\u00c8\u00c9"+
		"\u0003(\u0014\u0000\u00c9\u00cb\u0001\u0000\u0000\u0000\u00ca\u00c7\u0001"+
		"\u0000\u0000\u0000\u00cb\u00ce\u0001\u0000\u0000\u0000\u00cc\u00ca\u0001"+
		"\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000\u0000\u0000\u00cd\u00d9\u0001"+
		"\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000\u0000\u0000\u00cf\u00d5\u0003"+
		"@ \u0000\u00d0\u00d1\u0003$\u0012\u0000\u00d1\u00d2\u0003(\u0014\u0000"+
		"\u00d2\u00d4\u0001\u0000\u0000\u0000\u00d3\u00d0\u0001\u0000\u0000\u0000"+
		"\u00d4\u00d7\u0001\u0000\u0000\u0000\u00d5\u00d3\u0001\u0000\u0000\u0000"+
		"\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6\u00d9\u0001\u0000\u0000\u0000"+
		"\u00d7\u00d5\u0001\u0000\u0000\u0000\u00d8\u00c6\u0001\u0000\u0000\u0000"+
		"\u00d8\u00cf\u0001\u0000\u0000\u0000\u00d9\'\u0001\u0000\u0000\u0000\u00da"+
		"\u00df\u0003*\u0015\u0000\u00db\u00dc\u0007\u0001\u0000\u0000\u00dc\u00de"+
		"\u0003*\u0015\u0000\u00dd\u00db\u0001\u0000\u0000\u0000\u00de\u00e1\u0001"+
		"\u0000\u0000\u0000\u00df\u00dd\u0001\u0000\u0000\u0000\u00df\u00e0\u0001"+
		"\u0000\u0000\u0000\u00e0\u00e4\u0001\u0000\u0000\u0000\u00e1\u00df\u0001"+
		"\u0000\u0000\u0000\u00e2\u00e4\u0003B!\u0000\u00e3\u00da\u0001\u0000\u0000"+
		"\u0000\u00e3\u00e2\u0001\u0000\u0000\u0000\u00e4)\u0001\u0000\u0000\u0000"+
		"\u00e5\u00ea\u0003,\u0016\u0000\u00e6\u00e7\u0007\u0002\u0000\u0000\u00e7"+
		"\u00e9\u0003,\u0016\u0000\u00e8\u00e6\u0001\u0000\u0000\u0000\u00e9\u00ec"+
		"\u0001\u0000\u0000\u0000\u00ea\u00e8\u0001\u0000\u0000\u0000\u00ea\u00eb"+
		"\u0001\u0000\u0000\u0000\u00eb+\u0001\u0000\u0000\u0000\u00ec\u00ea\u0001"+
		"\u0000\u0000\u0000\u00ed\u00ee\u0005.\u0000\u0000\u00ee\u00f3\u0003,\u0016"+
		"\u0000\u00ef\u00f0\u0005/\u0000\u0000\u00f0\u00f3\u0003,\u0016\u0000\u00f1"+
		"\u00f3\u0003.\u0017\u0000\u00f2\u00ed\u0001\u0000\u0000\u0000\u00f2\u00ef"+
		"\u0001\u0000\u0000\u0000\u00f2\u00f1\u0001\u0000\u0000\u0000\u00f3-\u0001"+
		"\u0000\u0000\u0000\u00f4\u00f8\u00034\u001a\u0000\u00f5\u00f8\u0003>\u001f"+
		"\u0000\u00f6\u00f8\u00030\u0018\u0000\u00f7\u00f4\u0001\u0000\u0000\u0000"+
		"\u00f7\u00f5\u0001\u0000\u0000\u0000\u00f7\u00f6\u0001\u0000\u0000\u0000"+
		"\u00f8/\u0001\u0000\u0000\u0000\u00f9\u00fa\u00057\u0000\u0000\u00fa1"+
		"\u0001\u0000\u0000\u0000\u00fb\u00fc\u0005\u0011\u0000\u0000\u00fc3\u0001"+
		"\u0000\u0000\u0000\u00fd\u00fe\u0005\u001b\u0000\u0000\u00fe\u00ff\u0003"+
		"<\u001e\u0000\u00ff\u0100\u0005\u001c\u0000\u0000\u01005\u0001\u0000\u0000"+
		"\u0000\u0101\u0102\u0005\u001a\u0000\u0000\u0102\u0103\u0005#\u0000\u0000"+
		"\u0103\u0106\u0005\u001a\u0000\u0000\u0104\u0105\u0005#\u0000\u0000\u0105"+
		"\u0107\u0005\u001a\u0000\u0000\u0106\u0104\u0001\u0000\u0000\u0000\u0107"+
		"\u0108\u0001\u0000\u0000\u0000\u0108\u0106\u0001\u0000\u0000\u0000\u0108"+
		"\u0109\u0001\u0000\u0000\u0000\u01097\u0001\u0000\u0000\u0000\u010a\u010d"+
		"\u0005\u000b\u0000\u0000\u010b\u010e\u0005\u000e\u0000\u0000\u010c\u010e"+
		"\u00036\u001b\u0000\u010d\u010b\u0001\u0000\u0000\u0000\u010d\u010c\u0001"+
		"\u0000\u0000\u0000\u010e\u010f\u0001\u0000\u0000\u0000\u010f\u0110\u0005"+
		"\r\u0000\u0000\u01109\u0001\u0000\u0000\u0000\u0111\u0116\u0003<\u001e"+
		"\u0000\u0112\u0113\u0005!\u0000\u0000\u0113\u0115\u0003<\u001e\u0000\u0114"+
		"\u0112\u0001\u0000\u0000\u0000\u0115\u0118\u0001\u0000\u0000\u0000\u0116"+
		"\u0114\u0001\u0000\u0000\u0000\u0116\u0117\u0001\u0000\u0000\u0000\u0117"+
		";\u0001\u0000\u0000\u0000\u0118\u0116\u0001\u0000\u0000\u0000\u0119\u011c"+
		"\u0003\u001e\u000f\u0000\u011a\u011b\u0005$\u0000\u0000\u011b\u011d\u0003"+
		"<\u001e\u0000\u011c\u011a\u0001\u0000\u0000\u0000\u011c\u011d\u0001\u0000"+
		"\u0000\u0000\u011d=\u0001\u0000\u0000\u0000\u011e\u011f\u0007\u0003\u0000"+
		"\u0000\u011f?\u0001\u0000\u0000\u0000\u0120\u0121\u0005\u0005\u0000\u0000"+
		"\u0121\u0122\u0003<\u001e\u0000\u0122A\u0001\u0000\u0000\u0000\u0123\u0124"+
		"\u0005\u0006\u0000\u0000\u0124\u0127\u0005\u0005\u0000\u0000\u0125\u0128"+
		"\u0003\u0016\u000b\u0000\u0126\u0128\u0003<\u001e\u0000\u0127\u0125\u0001"+
		"\u0000\u0000\u0000\u0127\u0126\u0001\u0000\u0000\u0000\u0128C\u0001\u0000"+
		"\u0000\u0000\u0129\u012b\u0003F#\u0000\u012a\u012c\u0005\"\u0000\u0000"+
		"\u012b\u012a\u0001\u0000\u0000\u0000\u012b\u012c\u0001\u0000\u0000\u0000"+
		"\u012cE\u0001\u0000\u0000\u0000\u012d\u012e\u0003<\u001e\u0000\u012eG"+
		"\u0001\u0000\u0000\u0000\u012f\u0131\u0003J%\u0000\u0130\u012f\u0001\u0000"+
		"\u0000\u0000\u0131\u0134\u0001\u0000\u0000\u0000\u0132\u0130\u0001\u0000"+
		"\u0000\u0000\u0132\u0133\u0001\u0000\u0000\u0000\u0133I\u0001\u0000\u0000"+
		"\u0000\u0134\u0132\u0001\u0000\u0000\u0000\u0135\u0138\u0003D\"\u0000"+
		"\u0136\u0138\u0003\u0016\u000b\u0000\u0137\u0135\u0001\u0000\u0000\u0000"+
		"\u0137\u0136\u0001\u0000\u0000\u0000\u0138K\u0001\u0000\u0000\u0000\u0139"+
		"\u013a\u0005\u0001\u0000\u0000\u013aM\u0001\u0000\u0000\u0000\u001aZl"+
		"x\u0093\u0098\u009d\u00a5\u00b0\u00b8\u00c1\u00cc\u00d5\u00d8\u00df\u00e3"+
		"\u00ea\u00f2\u00f7\u0108\u010d\u0116\u011c\u0127\u012b\u0132\u0137";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}