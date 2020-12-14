package org.hsmak.datastructures.abstractions.expression.builder;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hsmak.datastructures.abstractions.expression.builder.model.Token;

import java.util.List;

public class ArithExprBuilder extends ExprBuilder{

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

    public ArithExprBuilder begin(String oprnd) {
        exprBuilder.begin(oprnd);
        return this;
    }

    public ArithExprBuilder begin(ArithExprBuilder arithExprBuilder) {

        exprBuilder.begin(arithExprBuilder);
        return this;
    }

    public ArithExprBuilder add(String oprnd) {

        exprBuilder.operation("+", oprnd);
        return this;
    }

    public ArithExprBuilder add(ArithExprBuilder arithExprBuilder) {

        exprBuilder.operation("+", arithExprBuilder);
        return this;
    }

    public ArithExprBuilder minus(ArithExprBuilder arithExprBuilder) {

        exprBuilder.operation("-", arithExprBuilder);
        return this;
    }

    public ArithExprBuilder minus(String oprnd) {

        exprBuilder.operation("-", oprnd);
        return this;
    }

    public ArithExprBuilder times(String oprnd) {

        exprBuilder.operation("*", oprnd);
        return this;
    }

    public ArithExprBuilder times(ArithExprBuilder arithExprBuilder) {

        exprBuilder.operation("*", arithExprBuilder);
        return this;
    }

    public ArithExprBuilder divide(String oprnd) {

        exprBuilder.operation("/", oprnd);
        return this;
    }

    public ArithExprBuilder divide(ArithExprBuilder arithExprBuilder) {

        exprBuilder.operation("/", arithExprBuilder);
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
