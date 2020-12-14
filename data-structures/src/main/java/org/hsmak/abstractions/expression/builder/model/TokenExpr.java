package org.hsmak.abstractions.expression.builder.model;

import java.util.List;

public class TokenExpr extends Token<List<Token<?>>>{

    public TokenExpr() {
    }

    public TokenExpr(List<Token<?>> value) {
        super(value);
    }
}
