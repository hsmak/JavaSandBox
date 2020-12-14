package org.hsmak.datastructures.abstractions.expression.postfix.main;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Hello world!
 */
public class MainImperative {

    public static void main(String[] args) {
        List<String> infixArithmetic = Arrays.asList("1", "+", "2", "*", "3", "+", "4", "+", "1", "*", "2", "*", "3", "+", "5");

//        List<String> infix = Arrays.asList("1", "+", "2", "*", "3", "+", "4", "/", "2");
        String postfixArithmetic = convertInfixToPostfixArith(infixArithmetic);
        System.out.println(postfixArithmetic);

        String evalArithmetic = evalInfixArith(infixArithmetic);
        System.out.println(evalArithmetic);

        //boolean
        List<String> infixBoolean = Arrays.asList("T", "&", "F", "|", "T", "&", "T", "&", "F", "|", "F", "|", "T", "&", "T");
        String postfixBoolean = convertInfixToPostfixBoolean(infixBoolean);
        System.out.println(postfixBoolean);

        String evalBoolean = evalInfixBoolean(infixBoolean);
        System.out.println(evalBoolean);
    }

    public static String convertInfixToPostfixBoolean(List<String> infixBoolean) {
        Stack<String> infixStack = new Stack<>();
        Stack<String> oprStack = new Stack<>();

        for (String op : infixBoolean) {

            if (isOperand(op)) {

                infixStack.push(op);

            } else if (isOperator(op)) {

                if (oprStack.isEmpty()) {
                    oprStack.push(op);
                    continue;
                }

                String prevOptr = oprStack.peek();

                if (isLessPrecedenceBoolean(op, prevOptr)) {

                    while (!oprStack.isEmpty())
                        infixStack.push(oprStack.pop());

                    oprStack.push(op);

                } else {
                    oprStack.push(op);
                }
            }
        }

        while (!oprStack.isEmpty())
            infixStack.push(oprStack.pop());

        return infixStack.toString();
    }

    public static boolean isOperand(String op) {
        return StringUtils.equalsAny(op, OPRND.T.name(), OPRND.F.name());
    }

    /**
     * TODO - candiadte for abstraction
     *
     * @param op
     * @return
     */
    public static boolean isOperator(String op) {
        return StringUtils.equalsAny(op, OPRTR.AND.getOp(), OPRTR.OR.getOp());
    }

    public static String evalInfixBoolean(List<String> infixBoolean) {
        Stack<String> infixStack = new Stack<>();
        Stack<String> oprStack = new Stack<>();

        for (String op : infixBoolean) {

            if (isOperand(op)) {

                infixStack.push(op);

            } else if (isOperator(op)) {

                if (oprStack.isEmpty()) {
                    oprStack.push(op);
                    continue;
                }

                String prevOptr = oprStack.peek();

                if (isLessPrecedenceBoolean(op, prevOptr)) {

                    while (!oprStack.isEmpty()) {
                        prevOptr = oprStack.pop();
                        boolean rhs = OPRND.valueOf(infixStack.pop()).getBooleanOprnd();
                        boolean lhs = OPRND.valueOf(infixStack.pop()).getBooleanOprnd();
                        boolean result = evalBoolOperands(prevOptr, lhs, rhs);

                        infixStack.push(OPRND.getEnum(result).name());
                    }

                    oprStack.push(op);

                } else {
                    oprStack.push(op);
                }
            }
        }

        while (!oprStack.isEmpty()) {
            String op = oprStack.pop();
            boolean rhs = OPRND.valueOf(infixStack.pop()).getBooleanOprnd();
            boolean lhs = OPRND.valueOf(infixStack.pop()).getBooleanOprnd();
            boolean result = evalBoolOperands(op, lhs, rhs);

            infixStack.push(OPRND.getEnum(result).name());
        }

        return infixStack.toString();
    }

    private static boolean evalBoolOperands(String op, boolean lhs, boolean rhs) {

        if (StringUtils.equals(OPRTR.AND.getOp(), op))
            return lhs && rhs;
        else if (StringUtils.equals(OPRTR.OR.getOp(), op))
            return lhs || rhs;
        else
            return false;
    }



    public static String convertInfixToPostfixArith(List<String> infixExpr) {

        Stack<String> infixStack = new Stack<>();
        Stack<String> oprStack = new Stack<>();

        for (String op : infixExpr) {

            if (StringUtils.isNumeric(op)) {

                infixStack.push(op);

            } else if (!StringUtils.isAlphanumeric(op)) {

                if (oprStack.isEmpty()) {
                    oprStack.push(op);
                    continue;
                }

                String prevOptr = oprStack.peek();

                if (isLessPrecedence(op, prevOptr)) {

                    while (!oprStack.isEmpty())
                        infixStack.push(oprStack.pop());

                    oprStack.push(op);

                } else {
                    oprStack.push(op);
                }
            }
        }

        while (!oprStack.isEmpty())
            infixStack.push(oprStack.pop());

        return infixStack.toString();
    }

    public static String evalInfixArith(List<String> infixExpr) {

        Stack<String> infixStack = new Stack<>();
        Stack<String> optrsStack = new Stack<>();

        for (String op : infixExpr) {

            if (StringUtils.isNumeric(op)) {

                infixStack.push(op);

            } else if (!StringUtils.isAlphanumeric(op)) {

                if (optrsStack.isEmpty()) {
                    optrsStack.push(op);
                    continue;
                }

                String prevOptr = optrsStack.peek();

                if (isLessPrecedence(op, prevOptr)) {

                    while (!optrsStack.isEmpty()) {

                        prevOptr = optrsStack.pop();
                        Double rhs = Double.parseDouble(infixStack.pop());
                        Double lhs = Double.parseDouble(infixStack.pop());
                        Double result = evalOperands(prevOptr, lhs, rhs);

                        infixStack.push(Double.toString(result));

                    }

                    optrsStack.push(op);

                } else {
                    optrsStack.push(op);
                }
            }
        }

        while (!optrsStack.isEmpty()) {
            String op = optrsStack.pop();
            Double rhs = Double.parseDouble(infixStack.pop());
            Double lhs = Double.parseDouble(infixStack.pop());
            Double result = evalOperands(op, lhs, rhs);

            infixStack.push(Double.toString(result));
        }

        return infixStack.toString();
    }

    public static Double evalOperands(String op, Double lhs, Double rhs) {
        switch (op) {
            case "+":
                return lhs + rhs;
            case "-":
                return lhs - rhs;
            case "*":
                return lhs * rhs;
            case "/":
                return lhs / rhs;
            case "^":
                return Math.pow(lhs, rhs);
            default:
                return 0.0;
        }
    }

    /**
     * TODO - Candidate for abstraction
     *
     * @param op
     * @param prevOptr
     * @return
     */
    public static boolean isLessPrecedence(String op, String prevOptr) {

        return (getPrecedence(op) < getPrecedence(prevOptr));
    }

    /**
     * TODO - Candidate for abstraction
     *
     * @param op
     * @return
     */
    public static int getPrecedence(String op) {
        if (StringUtils.equalsAny(op, "+", "-"))
            return 1;
        else if (StringUtils.equalsAny(op, "*", "/"))
            return 2;
        else if (StringUtils.equalsAny(op, "^"))
            return 3;
        else
            return 0;
    }

    /**
     * TODO - Candidate for abstraction
     *
     * @param op
     * @param prevOptr
     * @return
     */
    public static boolean isLessPrecedenceBoolean(String op, String prevOptr) {

        return (getPrecedenceBoolean(op) < getPrecedenceBoolean(prevOptr));
    }

    /**
     * TODO - Candidate for abstraction
     *
     * @param op
     * @return
     */
    public static int getPrecedenceBoolean(String op) {
        if (StringUtils.equals(op, OPRTR.AND.getOp()))
            return 1;
        else if (StringUtils.equals(op, OPRTR.OR.getOp()))
            return 2;
        else
            return 0;
    }
}

enum OPRTR {
    AND("&"),
    OR("|");

    private String op;

    OPRTR(String op) {
        this.op = op;
    }

    public String getOp() {
        return op;
    }
}

enum OPRND {
    T(true),
    F(false),
    N(null);

    private Boolean oprnd;

    OPRND(Boolean oprnd) {
        this.oprnd = oprnd;
    }

    public Boolean getBooleanOprnd() {
        return oprnd;
    }

    public static OPRND getEnum(boolean oprnd) {
        for (OPRND v : values()) {
            if (v.getBooleanOprnd() == oprnd)
                return v;
        }

        return OPRND.N;
    }
}