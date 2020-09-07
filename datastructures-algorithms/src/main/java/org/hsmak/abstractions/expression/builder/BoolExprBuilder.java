package org.hsmak.abstractions.expression.builder;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hsmak.abstractions.expression.builder.model.Token;

import java.util.List;

public class BoolExprBuilder extends ExprBuilder {

    private ExprBuilder exprBuilder = new ExprBuilder();

    /**
     * TODO - get rid of downcasting and instanceof
     * possible Steps:
     * have one Token class with different constructors
     * replace generic type with List
     * call addAll() and no need for if-else fragments
     * for recursive call, check the type of the Token
     *
     * @return
     */
    public List<String> buildInfix() {
        return exprBuilder.buildInfix();
    }

    //Operations

    public BoolExprBuilder begin(String oprnd) {
        exprBuilder.begin(oprnd);
        return this;
    }

    public BoolExprBuilder begin(BoolExprBuilder boolExprBuilder) {

        exprBuilder.begin(boolExprBuilder);
        return this;
    }

    public BoolExprBuilder and(String oprnd) {

        exprBuilder.operation("AND", oprnd);
        return this;
    }

    public BoolExprBuilder and(BoolExprBuilder boolExprBuilder) {

        exprBuilder.operation("AND", boolExprBuilder);
        return this;
    }

    public BoolExprBuilder or(BoolExprBuilder boolExprBuilder) {

        exprBuilder.operation("OR", boolExprBuilder);
        return this;
    }

    public BoolExprBuilder or(String oprnd) {

        exprBuilder.operation("OR", oprnd);
        return this;
    }

    public BoolExprBuilder not(String oprnd) {

        exprBuilder.operation("NOT", oprnd);
        return this;
    }

    public BoolExprBuilder not(BoolExprBuilder boolExprBuilder) {

        exprBuilder.operation("NOT", boolExprBuilder);
        return this;
    }

    public List<Token<?>> getInfix() {
        return exprBuilder.getInfix();
    }

    public void setInfix(List<Token<?>> infix) {
        this.exprBuilder.setInfix(infix);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("exprBuilder", exprBuilder)
                .toString();
    }
}