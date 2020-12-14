package org.hsmak.datastructures.abstractions.expression.postfix;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum PAREN {

    L(Arrays.asList("(", "[", "{")),// left/opening parentheses
    R(Arrays.asList(")", "]", "}")),// right/closing parentheses
    N(Collections.emptyList());// resembles null

    private final List<String> parenList;

    PAREN(List<String> parenList) {
        this.parenList = parenList;
    }

    public static PAREN findParen(String parenSrc) {
        PAREN parenEnum = PAREN.N;

        for (PAREN paren : values()) {
            for (String parenTgt : paren.parenList) {
                if (StringUtils.equals(parenSrc, parenTgt)) {
                    parenEnum = paren;
                }
            }
        }

        return parenEnum;
    }

    public static boolean isParen(String paren) {
        return findParen(paren) != PAREN.N;
    }

    public static boolean isLeftParen(String paren) {
        return findParen(paren) == PAREN.L;
    }

    public static boolean isRightParen(String paren) {
        return findParen(paren) == PAREN.R;
    }

}