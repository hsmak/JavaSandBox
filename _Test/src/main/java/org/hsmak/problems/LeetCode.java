package org.hsmak.problems;

/*
 * ToDo:
 *      - Refactor into its own packages and classes
 *      - Use JUnit to test all cases
 */
public class LeetCode {

    public static void main(String[] args) {
        System.out.println(makeGood("aAd"));
        System.out.println(makeGood("aAdbB"));
        System.out.println(makeGood("caAdbBo"));
    }

    public static String makeGood(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {

            char cur = chars[i];
            if (i + 1 >= chars.length)
                return sb.append(cur).toString();

            char nxt = chars[i + 1];

            if (!(Character.isLowerCase(cur) ^ Character.isUpperCase(nxt))) {//NOR; the negation of XOR
                i++;
                continue;
            }
            sb.append(cur);
        }
        return sb.toString();
    }
}
