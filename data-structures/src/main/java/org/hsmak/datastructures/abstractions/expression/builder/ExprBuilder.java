package org.hsmak.datastructures.abstractions.expression.builder;

import org.apache.commons.collections4.CollectionUtils;
import org.hsmak.datastructures.abstractions.expression.builder.model.Token;
import org.hsmak.datastructures.abstractions.expression.builder.model.TokenExpr;
import org.hsmak.datastructures.abstractions.expression.builder.model.TokenOprnd;
import org.hsmak.datastructures.abstractions.expression.builder.model.TokenOprtr;

import java.util.ArrayList;
import java.util.List;

public class ExprBuilder {

    private List<Token<?>> infix = new ArrayList<>();

    /**
     * TODO - get rid of downcasting and instanceof
     * possible Steps:
     *      have one Token class with different constructors
     *      replace generic type with List
     *      call addAll() and no need for if-else fragments
     *      for recursive call, check the type of the Token
     *
     * @return
     */
    protected List<String> buildInfix() {
        List<String> extractedInfix = new ArrayList<>();

        //TODO - Refactor this code to eliminate the duplicate code
        for (Token token : infix) {
            if (token instanceof TokenOprnd)
                extractedInfix.add(((TokenOprnd) token).getValue());
            else if (token instanceof TokenOprtr)
                extractedInfix.add(((TokenOprtr) token).getValue());
            else
                extractedInfix.addAll(buildInfix(((TokenExpr) token).getValue()));
        }
        return extractedInfix;
    }

    /**
     * Recursive method - considering the use of stacks instead
     * TODO - get rid of downcasting and instanceof
     * @param infix
     * @return
     */
    private List<String> buildInfix(List<Token<?>> infix) {
        List<String> extractedInfix = new ArrayList<>();

        extractedInfix.add("(");//TODO - replace with abstract method getOpenParen()

        for (Token token : infix) {

            if (token instanceof TokenOprnd)
                extractedInfix.add(((TokenOprnd) token).getValue());
            else if (token instanceof TokenOprtr)
                extractedInfix.add(((TokenOprtr) token).getValue());
            else
                extractedInfix.addAll(buildInfix(((TokenExpr) token).getValue()));// recursive call

        }

        extractedInfix.add(")");//TODO - replace with abstract method getCloseParen()

        return extractedInfix;
    }

    //Operations

    protected ExprBuilder begin(String oprnd) {

        if (!CollectionUtils.isEmpty(infix))
            return this;

        infix.add(new TokenOprnd(oprnd));
        return this;
    }

    protected ExprBuilder begin(ExprBuilder exprBuilder) {

        if (!CollectionUtils.isEmpty(infix))
            return this;

        infix.add(new TokenExpr(exprBuilder.getInfix()));
        return this;
    }

    protected ExprBuilder operation(String oprtr, String oprnd) {

        if (CollectionUtils.isEmpty(infix))
            return this;

        infix.add(new TokenOprtr(oprtr));
        infix.add(new TokenOprnd(oprnd));
        return this;
    }

    protected ExprBuilder operation(String oprnd, ExprBuilder exprBuilder) {

        if (CollectionUtils.isEmpty(infix))
            return this;

        infix.add(new TokenOprtr(oprnd));
        infix.add(new TokenExpr(exprBuilder.getInfix()));
        return this;
    }

    protected List<Token<?>> getInfix() {
        return infix;
    }

    protected void setInfix(List<Token<?>> infix) {
        this.infix = infix;
    }

    @Override
    public String toString() {
        return "ExprBuilder{" +
                "infix=" + infix +
                '}';
    }
}
