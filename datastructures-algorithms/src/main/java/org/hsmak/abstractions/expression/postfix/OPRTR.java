package org.hsmak.abstractions.expression.postfix;

public enum OPRTR {
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
