package org.hsmak.datastructures.abstractions.expression.builder;

import org.hsmak.datastructures.abstractions.expression.builder.model.Token;
import org.hsmak.datastructures.abstractions.expression.builder.model.TokenExpr;
import org.hsmak.datastructures.abstractions.expression.builder.model.TokenOprnd;
import org.hsmak.datastructures.abstractions.expression.builder.model.TokenOprtr;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ArithExprBuilderTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void buildInfix() throws Exception {

        ArithExprBuilder exprBuilderActual = new ArithExprBuilder();
        exprBuilderActual
                .begin("2")
                .add("3")
                .times(new ArithExprBuilder().begin("4"))
                .minus("1")
                .minus(new ArithExprBuilder().begin("5").add("44")
                        .times(new ArithExprBuilder().begin("10").add("15")))
                .divide(new ArithExprBuilder().begin("5").add("44"));


        List<String> InfixStringActual = exprBuilderActual.buildInfix();

        System.out.println(InfixStringActual);
        System.out.println(exprBuilderActual.toString());

        List<Token<?>> exprBuilderExpected = prepareExpectedInfixExpr();

        /**
         * Note: make sure equals() is overridden in the contained objects, i.e. Token class,
         * otherwise Assertion will fail because equals() would fall back to reference checking
         * instead of value checking
         */
        assertThat(exprBuilderActual.getInfix()).isEqualTo(exprBuilderExpected);
        assertThat(exprBuilderActual.getInfix().toString()).isEqualTo(exprBuilderExpected.toString());

    }

    private List<Token<?>> prepareExpectedInfixExpr() {
        List<Token<?>> infix = new ArrayList<>();

        infix.add(new TokenOprnd("2"));
        infix.add(new TokenOprtr("+"));
        infix.add(new TokenOprnd("3"));
        infix.add(new TokenOprtr("*"));
        infix.add(new TokenExpr(Arrays.asList(new TokenOprnd("4"))));
        infix.add(new TokenOprtr("-"));
        infix.add(new TokenOprnd("1"));
        infix.add(new TokenOprtr("-"));
        infix.add(
                new TokenExpr(Arrays.asList(
                        new TokenOprnd("5"),
                        new TokenOprtr("+"),
                        new TokenOprnd("44"),
                        new TokenOprtr("*"),
                        new TokenExpr(Arrays.asList(
                                new TokenOprnd("10"),
                                new TokenOprtr("+"),
                                new TokenOprnd("15"))))
                ));
        infix.add(new TokenOprtr("/"));
        infix.add(new TokenExpr(Arrays.asList(new TokenOprnd("5"),
                new TokenOprtr("+"),
                new TokenOprnd("44"))));

        return infix;
    }

}

