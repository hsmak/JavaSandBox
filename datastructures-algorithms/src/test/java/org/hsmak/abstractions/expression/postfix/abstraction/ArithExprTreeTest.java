package org.hsmak.abstractions.expression.postfix.abstraction;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ArithExprTreeTest {


    private ArithExprTree arithExprTree;

    @Before
    public void setUp() throws Exception {
        arithExprTree = new ArithExprTree();
    }

    @Test
    public void convertInfixToPostfixWithNoParens() throws Exception {
        List<String> infixArithmetic = Arrays.asList("1", "+", "2", "*", "3", "+", "4");
        List<String> infixToPostfixActual = arithExprTree.convertInfixToPostfix(infixArithmetic);
        System.out.println(infixToPostfixActual);

        List<String> infixToPostfixExpected = Arrays.asList("1", "2", "3", "*", "+", "4", "+");

        assertThat(infixToPostfixActual).isEqualTo(infixToPostfixExpected);
    }

    @Test
    public void convertInfixToPostfixWithParens() throws Exception {

//        List<String> infixArithmetic = Arrays.asList("(","1", "+", "2", "*", "3", "+", "4", ")");// TODO - non working case. fix

        List<String> infixArithmetic = Arrays.asList("1", "+", "(", "2", "*", "3", ")", "+", "4");
        List<String> infixToPostfixActual = arithExprTree.convertInfixToPostfix(infixArithmetic);
        System.out.println(infixToPostfixActual);
        List<String> infixToPostfixExpected = Arrays.asList("1", "2", "3", "*", "4", "+", "+");

        assertThat(infixToPostfixActual).isEqualTo(infixToPostfixExpected);

        //Another Test

        infixArithmetic = Arrays.asList("2", "*", "(", "1", "+", "3", "*", "(", "4", "-", "2", ")", "-", "1", ")", "-", "2");
        infixToPostfixActual = arithExprTree.convertInfixToPostfix(infixArithmetic);
        System.out.println(infixToPostfixActual);
        infixToPostfixExpected = Arrays.asList("2", "1", "3", "4", "2", "-", "1", "-", "*", "+", "2", "-", "*");

        assertThat(infixToPostfixActual).isEqualTo(infixToPostfixExpected);
    }

}