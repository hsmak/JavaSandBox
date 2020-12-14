package org.hsmak.datastructures.abstractions.expression.postfix.abstraction;

import org.apache.commons.lang3.StringUtils;
import org.hsmak.datastructures.abstractions.expression.postfix.OPRTR;

public class BoolExprTreeWithANDPrecedence extends BoolExprTree {

    @Override
    public int getPrecedence(String op) {
        if (StringUtils.equals(op, OPRTR.OR.getOp()))
            return 1;
        else if (StringUtils.equals(op, OPRTR.AND.getOp()))
            return 2;
        else
            return 0;
    }
}

