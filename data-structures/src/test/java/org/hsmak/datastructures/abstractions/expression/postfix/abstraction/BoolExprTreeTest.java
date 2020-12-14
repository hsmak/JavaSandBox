package org.hsmak.datastructures.abstractions.expression.postfix.abstraction;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BoolExprTreeTest {

    private BoolExprTree boolExprTree;

    @Before
    public void setUp() throws Exception {
        boolExprTree = new BoolExprTree();
    }

    @Test
    public void convertInfixToPostfix() throws Exception {
        List<String> infixBoolean = Arrays.asList("T", "&", "F", "|", "T", "&", "T", "&", "F", "|", "F", "|", "T", "&", "T");
        List<String> infixToPostfixActual = boolExprTree.convertInfixToPostfix(infixBoolean);

        List<String> infixToPostfixExpected = Arrays.asList("T", "F", "T", "|", "&", "T", "F", "F", "T", "|", "|", "&", "&", "T", "&");

        System.out.println(infixToPostfixActual);

        assertThat(infixToPostfixActual).isEqualTo(infixToPostfixExpected);
    }

}