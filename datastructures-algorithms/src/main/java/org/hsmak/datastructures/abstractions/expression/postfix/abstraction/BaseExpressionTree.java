package org.hsmak.datastructures.abstractions.expression.postfix.abstraction;

import org.hsmak.datastructures.abstractions.expression.postfix.PAREN;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public abstract class BaseExpressionTree {



    public List<String> convertInfixToPostfix(List<String> infixBoolean) {

        Stack<String> infixStack = new Stack<>();
        Stack<String> oprStack = new Stack<>();

        boolean parenFlag = false;
        for (String op : infixBoolean) {

            if (isOperand(op)) {

                infixStack.push(op);

            }else if(isLeftParen(op)){
                oprStack.push(op);
                parenFlag = true;
            }
            else if(isRightParen(op)){

                while(!oprStack.isEmpty() && !isLeftParen(oprStack.peek()))
                    infixStack.push(oprStack.pop());

                oprStack.pop();//Get rid of the left paren
                parenFlag = true;

            }
            else if (isOperator(op)) {

                if (oprStack.isEmpty() || parenFlag) {// first operator is being fetched
                    oprStack.push(op);
                    parenFlag = false;
                    continue;
                }

                String prevOptr = oprStack.peek();

                if (isLessPrecedence(op, prevOptr)) {

                    while (!oprStack.isEmpty())
                        infixStack.push(oprStack.pop());//TODO - abstraction

                    oprStack.push(op);

                } else {
                    oprStack.push(op);
                }
            }
        }

        while (!oprStack.isEmpty())
            infixStack.push(oprStack.pop());//TODO - abstraction

        return Arrays.asList(infixStack.toArray(new String[]{}));
    }

    private boolean isLeftParen(String op){
        return PAREN.isLeftParen(op);
    }

    private boolean isRightParen(String op){
        return PAREN.isRightParen(op);
    }

    private boolean isLessPrecedence(String currentOprtor, String nextOprtr) {
        return (getPrecedence(currentOprtor) < getPrecedence(nextOprtr));
    }

    protected abstract boolean isOperand(String op);

    protected abstract boolean isOperator(String op);

    protected abstract int getPrecedence(String op);

}
