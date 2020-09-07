package org.hsmak.abstractions.expression.postfix.strategic;

import org.apache.commons.lang3.StringUtils;

public class ArithmeticStrategy implements IExpressionTreeStrategy{
    @Override
    public boolean isOperand(String op) {
        return StringUtils.isNumeric(op);
    }

    @Override
    public boolean isOperator(String op) {
        return !StringUtils.isAlphanumeric(op);
    }

    @Override
    public int getPrecedence(String op) {
        if (StringUtils.equalsAny(op, "+", "-"))
            return 1;
        else if (StringUtils.equalsAny(op, "*", "/"))
            return 2;
        else if (StringUtils.equalsAny(op, "^"))
            return 3;
        else
            return 0;
    }
}
