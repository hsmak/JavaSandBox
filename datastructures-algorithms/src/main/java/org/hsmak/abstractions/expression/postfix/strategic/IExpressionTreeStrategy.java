package org.hsmak.abstractions.expression.postfix.strategic;

public interface IExpressionTreeStrategy {

    boolean isOperand(String op);

    boolean isOperator(String op);

    int getPrecedence(String op);

}
