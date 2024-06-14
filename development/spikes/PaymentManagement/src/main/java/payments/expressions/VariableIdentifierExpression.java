package payments.expressions;


public class VariableIdentifierExpression extends Expression {
    private String identifier;
    public VariableIdentifierExpression(){super();}
    public VariableIdentifierExpression(String identifier) {
        super(null,null,null);
        this.identifier=identifier;
    }

    @Override
    public Object apply() {
        //return  (scheme)-> scheme.getGivenUnit().searchVariableExpression(identifier).apply();
        return this.getIdentifier();
    }

    public String getIdentifier() {
        return identifier;
    }
}
