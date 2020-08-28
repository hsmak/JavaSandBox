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
        /*DEFAULT { // Doesn't count for multiple whitespaces!!
            @Override
            public String apply(String s) {
                String[] words = s.split("\\s+");
                StringBuilder reversedWordsBuilder = new StringBuilder();
                for (String word : words)
                    reversedWordsBuilder.append(new StringBuilder(word).reverse()).append(" ");
                return reversedWordsBuilder.toString().trim();
            }
        },*/
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
                    } else {
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
                    } else {
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
        };

        void reverseChars(char[] chars, int beginIndex, int endIndex) {
            if (beginIndex < 0 || endIndex >= chars.length)
                throw new IllegalArgumentException(
                        String.format("Index out of range -> beginIndex{%d} endIndex{%d}", beginIndex, endIndex));

            for (int lower = beginIndex, upper = endIndex; lower < upper; lower++, upper--) {
                char temp = chars[lower];
                chars[lower] = chars[upper];
                chars[upper] = temp;
            }
        }

    }
}
