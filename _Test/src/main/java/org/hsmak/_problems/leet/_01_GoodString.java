package org.hsmak._problems.leet;

import java.util.Objects;

public class _01_GoodString {

    public String makeGood(String s) {
        if (Objects.isNull(s) || s.isBlank())
            return "";

        if (s.length() == 1)
            return s;

        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char cur = chars[i];
            if (i == chars.length - 1)
                return sb.append(cur).toString();

            char nxt = chars[i + 1];
            if (isOneUpperTheOtherLower(cur, nxt)) {//NOR; the negation of XOR
                i++;
                continue;
            }
            sb.append(cur);
        }
        return makeGood(sb.toString()); //Recursive Call
    }

    private boolean isOneUpperTheOtherLower(char prev, char cur) {
        return Objects.equals(Character.toLowerCase(prev), Character.toLowerCase(cur)) && !(Character.isLowerCase(prev) ^ Character.isUpperCase(cur));
    }
}
