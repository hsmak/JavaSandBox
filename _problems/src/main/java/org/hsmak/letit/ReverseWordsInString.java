package org.hsmak.letit;

import java.util.function.Function;

public class ReverseWordsInString {

    Function<String, String> strategy;

    public ReverseWordsInString(Function<String, String> strategy) {
        this.strategy = strategy;
    }

    public String reverseWords(String s) {
        return this.strategy.apply(s);
    }

    enum StrategyE implements Function<String, String> {
        PRESERVE_MULTIPLE_SPACES {
            @Override
            public String apply(String str) {

                char[] chars = str.toCharArray();
                StringBuilder sb = new StringBuilder();
                int i = 0;
                while (i < chars.length) {

                    if (Character.isWhitespace(chars[i])) {
                        sb.append(chars[i]);
                        while (++i < chars.length) {
                            if (Character.isWhitespace(chars[i]))
                                sb.append(chars[i]);
                            else
                                break;
                        }
                    } else { // may use a StringBuilder to accumulate and reverse non-space chars
                        StringBuilder reversedBuilder = new StringBuilder();
                        reversedBuilder.append(chars[i]);
                        while (++i < chars.length) {
                            if (!Character.isWhitespace(chars[i]))
                                reversedBuilder.append(chars[i]);
                            else
                                break;
                        }
                        sb.append(reversedBuilder.reverse());
                    }
                }
                return sb.toString();
            }
        },
        PRESERVE_MULTIPLE_SPACES_IN_PLACE {
            @Override
            public String apply(String s) {
                char[] chars = s.toCharArray();
                int i = 0;

                while (i < chars.length) {
                    if (Character.isWhitespace(chars[i])) {
                        while (++i < chars.length) {
                            if (Character.isWhitespace(chars[i]))
                                continue;
                            else
                                break;
                        }
                    } else { // Alternative to StringBuilder, preserve the index of the first occurrence of non-space char
                        int nonSpaceIndex = i;
                        while (++i < chars.length) {
                            if (!Character.isWhitespace(chars[i]))
                                continue;
                            else
                                break;
                        }
                        reverseChars(chars, nonSpaceIndex, i - 1);
                    }
                }
                return String.valueOf(chars);
            }

        },
        PRESERVE_MULTIPLE_SPACES_IN_PLACE_2 {
            @Override
            public String apply(String s) {
                char[] chars = s.toCharArray();
//                int i = 0;

                for (int i = 0; i < chars.length; i++) {
                    if (Character.isWhitespace(chars[i])) {
                        continue;
                    }
                    int nonSpaceIndex = i;
                    while (i < chars.length && !Character.isWhitespace(chars[i])) {
                        i++;
                    }
                    reverseChars(chars, nonSpaceIndex, i - 1);
                }
                return String.valueOf(chars);
            }
        },
        STRATEGY_01 {
            @Override
            public String apply(String s) {
                if (s == null || s.length() == 0) {
                    return s;
                }

                StringBuilder sb = new StringBuilder();
                char[] chars = s.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    if (Character.isWhitespace(chars[i])) {
                        continue;
                    }
                    int j = i;
                    while (j < chars.length && !Character.isWhitespace(chars[j])) {
                        j++;
                    }
                    reverseChars(chars, i, j - 1);
                    i = j;
                }

                return sb.append(chars).toString();
            }
        },
        STRATEGY_02 {
            @Override
            public String apply(String s) {
                if (s == null || s.length() == 0) {
                    return s;
                }
                char[] chars = s.toCharArray();
                int j = 0;
                while (j < chars.length) {
                    int i = j;
                    while (j < chars.length && !Character.isWhitespace(chars[j])) {
                        j++;
                    }
                    reverseChars(chars, i, j - 1);
                    while (j < chars.length && Character.isWhitespace(chars[j])) {
                        j++;
                    }
                }
                return new String(chars);
            }
        }; // End of Enums

        /**
         * Common method used in some of the Enums
         *
         * @param chars
         * @param beginIndex
         * @param endIndex
         */
        protected void reverseChars(char[] chars, int beginIndex, int endIndex) {
            if (beginIndex < 0 || endIndex >= chars.length)
                throw new IllegalArgumentException(
                        String.format("Index out of range -> beginIndex{%d} endIndex{%d}", beginIndex, endIndex));

            for (int lower = beginIndex, upper = endIndex; lower < upper; lower++, upper--) {
                char tmp = chars[lower];
                chars[lower] = chars[upper];
                chars[upper] = tmp;
            }
        }
    }


}
