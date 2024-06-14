package payments.processors.reg;


import com.affaince.payments.grammer.PaymentsBaseListener;
import com.affaince.payments.grammer.PaymentsParser;
import com.affaince.payments.scheme.Scheme;

import java.util.List;

public class PaymentSchemeListener extends PaymentsBaseListener {
    private Scheme scheme;
    private ExpressionBuilder expressionBuilder;
    private AdvancePaymentExpressionBuilder advancePaymentExpressionBuilder;
    private ResidualPaymentExpressionBuilder residualPaymentExpressionBuilder;
    public PaymentSchemeListener(Scheme scheme){
        this.scheme = scheme;
    }
    @Override public void enterScheme(PaymentsParser.SchemeContext ctx) {
        expressionBuilder = new ExpressionBuilder(this.scheme);
        advancePaymentExpressionBuilder = new AdvancePaymentExpressionBuilder(scheme);
        residualPaymentExpressionBuilder = new ResidualPaymentExpressionBuilder(scheme);
    }

    @Override
    public void exitGivenUnit(PaymentsParser.GivenUnitContext ctx) {
        List<PaymentsParser.VariableDeclarationStatementContext> variableDeclarationStatementContexts = ctx.givenBody().variableDeclarationStatement();
        for(PaymentsParser.VariableDeclarationStatementContext context: variableDeclarationStatementContexts){
            scheme.getGivenUnit().addExpression(expressionBuilder.processVariableDeclaration(context));
        }
        System.out.println(" exit variable declaration statement");
    }


 /*   @Override
    public void exitComputeBlock(PaymentsParser.ComputeBlockContext ctx) {
        List<PaymentsParser.BlockStatementContext> blockStatementContexts =ctx.block().blockStatement();
        for(PaymentsParser.BlockStatementContext blockStatementContext:blockStatementContexts){
            scheme.getComputeUnit().addExpression(expressionBuilder.buildExpression(blockStatementContext.statement().statementExpression().expression()));
        }
    }
*/
    @Override
    public void exitAdvancePayUnit(PaymentsParser.AdvancePayUnitContext ctx) {
       List<PaymentsParser.BlockStatementContext> blockStatementContexts =ctx.computeBlock().block().blockStatement();
        for(PaymentsParser.BlockStatementContext blockStatementContext:blockStatementContexts){
            scheme.getAdvancePayUnit().addExpression(expressionBuilder.buildExpression(blockStatementContext.statement().statementExpression().expression()));
        }
        scheme.getAdvancePayUnit().setEventOfAdvancePayment(ctx.ADVANCE_PAYMENT_EVENT().getText());
    }

    @Override
    public void exitResidualPayUnit(PaymentsParser.ResidualPayUnitContext ctx){
       this.scheme.getResidualPayUnit().setResidualPaymentExpression(residualPaymentExpressionBuilder.buildResidualPaymentExpression(ctx));
    }




    public Scheme getScheme() {
        return scheme;
    }
}
