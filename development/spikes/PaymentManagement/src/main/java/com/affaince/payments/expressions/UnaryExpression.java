package com.affaince.payments.expressions;


public class UnaryExpression extends Expression{
    private Object value;
    private UnaryType type;
    public UnaryExpression() { super();}
    public UnaryExpression(Object value,UnaryType type) {
        super(null,null,null);
        this.value=value;
        this.type=type;
    }


    @Override
    public Object apply() {
        return this.getValue();
    }
    public Object getValue() {
        return value;
    }

    public UnaryType getType() {
        return type;
    }

    public static UnaryType obtainUnaryType(Class type){
        if(type.equals(Integer.class) || type.equals(Double.class)){
            return UnaryType.NUMBER;
        }
        if(type.equals(Character.class)){
            return UnaryType.CHARACTER ;
        }
        if( type.equals(String.class)){
            return UnaryType.STRING;
        }
        if(type.equals(Boolean.class)){
            return UnaryType.BOOLEAN;
        }
        if( type.equals(Object.class)){
            return UnaryType.OBJECT ;
        }
        return UnaryType.NULL;
    }
}
