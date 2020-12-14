package org.hsmak.datastructures.abstractions.expression.postfix.main;

import org.hsmak.datastructures.abstractions.expression.postfix.abstraction.ArithExprTree;
import org.hsmak.datastructures.abstractions.expression.postfix.abstraction.BaseExpressionTree;
import org.hsmak.datastructures.abstractions.expression.postfix.abstraction.BoolExprTree;

import java.util.Arrays;
import java.util.List;

public class MainDeclarative {

    public static void main(String[] args){
        List<String> infixArithmetic = Arrays.asList("1", "+", "2", "*", "3", "+", "4", "+", "1", "*", "2", "*", "3", "+", "5");
        testExpressionTree(new ArithExprTree(), infixArithmetic);

        List<String> infixBoolean = Arrays.asList("T", "&", "F", "|", "T", "&", "T", "&", "F", "|", "F", "|", "T", "&", "T");
        testExpressionTree(new BoolExprTree(), infixBoolean);

    }

    public static void testExpressionTree(BaseExpressionTree expressionTree, List<String> infix){
        List<String> infixToPostfix = expressionTree.convertInfixToPostfix(infix);
        System.out.println(infixToPostfix);
    }
}
