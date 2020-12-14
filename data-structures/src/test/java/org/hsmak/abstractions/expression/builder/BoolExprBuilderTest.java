package org.hsmak.abstractions.expression.builder;

import org.hsmak.abstractions.expression.builder.model.Token;
import org.hsmak.abstractions.expression.builder.model.TokenExpr;
import org.hsmak.abstractions.expression.builder.model.TokenOprnd;
import org.hsmak.abstractions.expression.builder.model.TokenOprtr;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BoolExprBuilderTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void buildInfix() throws Exception {

        BoolExprBuilder boolExprBuilderActual = new BoolExprBuilder();
        boolExprBuilderActual
                .begin("T")
                .and("T")
                .and(new BoolExprBuilder().begin("T"))
                .and("T")
                .and(new BoolExprBuilder().begin("T").and("T")
                        .and(new BoolExprBuilder().begin("T").and("T")))
                .and(new BoolExprBuilder().begin("T").and("T"));


        List<String> InfixStringActual = boolExprBuilderActual.buildInfix();

        System.out.println(InfixStringActual);
        System.out.println(boolExprBuilderActual);

        List<Token<?>> exprBuilderExpected = prepareExpectedInfixExpr();

        /**
         * Note: make sure equals() is overridden in the contained objects, i.e. Token class,
         * otherwise Assertion will fail because equals() would fall back to reference checking
         * instead of value checking
         */
        assertThat(boolExprBuilderActual.getInfix()).isEqualTo(exprBuilderExpected);
        assertThat(boolExprBuilderActual.getInfix().toString()).isEqualTo(exprBuilderExpected.toString());

    }

    private List<Token<?>> prepareExpectedInfixExpr() {
        List<Token<?>> infix = new ArrayList<>();

        infix.add(new TokenOprnd("T"));
        infix.add(new TokenOprtr("AND"));
        infix.add(new TokenOprnd("T"));
        infix.add(new TokenOprtr("AND"));
        infix.add(new TokenExpr(Arrays.asList(new TokenOprnd("T"))));
        infix.add(new TokenOprtr("AND"));
        infix.add(new TokenOprnd("T"));
        infix.add(new TokenOprtr("AND"));
        infix.add(
                new TokenExpr(Arrays.asList(
                        new TokenOprnd("T"),
                        new TokenOprtr("AND"),
                        new TokenOprnd("T"),
                        new TokenOprtr("AND"),
                        new TokenExpr(Arrays.asList(
                                new TokenOprnd("T"),
                                new TokenOprtr("AND"),
                                new TokenOprnd("T"))))
                ));
        infix.add(new TokenOprtr("AND"));
        infix.add(new TokenExpr(Arrays.asList(new TokenOprnd("T"),
                new TokenOprtr("AND"),
                new TokenOprnd("T"))));

        return infix;
    }

}

