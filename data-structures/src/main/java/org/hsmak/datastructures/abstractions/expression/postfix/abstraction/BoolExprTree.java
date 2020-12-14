package org.hsmak.datastructures.abstractions.expression.postfix.abstraction;

import org.apache.commons.lang3.StringUtils;
import org.hsmak.datastructures.abstractions.expression.postfix.OPRND;
import org.hsmak.datastructures.abstractions.expression.postfix.OPRTR;

public class BoolExprTree extends BaseExpressionTree {

    @Override
    public boolean isOperand(String op) {
        return StringUtils.equalsAny(op, OPRND.T.name(), OPRND.F.name());
    }

    @Override
    public boolean isOperator(String op) {
        return StringUtils.equalsAny(op, OPRTR.AND.getOp(), OPRTR.OR.getOp());
    }

    @Override
    public int getPrecedence(String op) {
        if (StringUtils.equals(op, OPRTR.AND.getOp()))
            return 1;
        else if (StringUtils.equals(op, OPRTR.OR.getOp()))
            return 2;
        else
            return 0;
    }
}

