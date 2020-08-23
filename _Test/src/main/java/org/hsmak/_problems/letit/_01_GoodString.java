package org.hsmak._problems.letit;

import java.util.Objects;
import java.util.function.Function;

public class _01_GoodString {

    private Function<String, String> strategy;
    private Function<String, String> defaultStrategy = s -> {
        if (Objects.isNull(s) || s.isBlank())
            return "";

        if (s.length() == 1)
            return s;

        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char cur = chars[i];
            if (i == chars.length - 1) {
                sb.append(cur);
                break;
            }

            char nxt = chars[i + 1];
            if (isOneUpperTheOtherLower(cur, nxt)) {//NOR; the negation of XOR
                i++;
                continue;
            }
            sb.append(cur);
        }
        return sb.length() == s.length() ? sb.toString() : this.defaultStrategy.apply(sb.toString()); //Recursive function call
    };

    _01_GoodString() {
        this.strategy = defaultStrategy;
    }

    _01_GoodString(Function<String, String> strategy) {
        this.strategy = strategy;
    }

    public String makeGood(String s) {
        return strategy.apply(s);
    }

    private boolean isOneUpperTheOtherLower(char prev, char cur) {
        return Objects.equals(Character.toLowerCase(prev), Character.toLowerCase(cur)) && !(Character.isLowerCase(prev) ^ Character.isUpperCase(cur));
    }

}
