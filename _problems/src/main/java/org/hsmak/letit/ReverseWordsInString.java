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
        PRESERVE_MULTIPLE_SPACES_IN_PLACE { // ToDo - optimize it

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

            private void reverseChars(char[] chars, int beginIndex, int endIndex) {
                if (beginIndex < 0 || endIndex >= chars.length)
                    throw new IllegalArgumentException(
                            String.format("Index out of range -> beginIndex{%d} endIndex{%d}", beginIndex, endIndex));

                for (int lower = beginIndex, upper = endIndex; lower < upper; lower++, upper--) {
                    char temp = chars[lower];
                    chars[lower] = chars[upper];
                    chars[upper] = temp;
                }
            }
        },
        STRATEGY_01 {
            @Override
            public String apply(String s) {
                if (s == null || s.length() == 0) {
                    return s;
                }

                StringBuilder sb = new StringBuilder();
                char[] words = s.toCharArray();

                for (int i = 0; i < words.length; i++) {
                    if (words[i] == ' ') {
                        continue;
                    }
                    int j = i;
                    while (j < words.length && words[j] != ' ') {
                        j++;
                    }
                    reverse(words, i, j - 1);
                    i = j;
                }

                return sb.append(words).toString();
            }

            private void reverse(char[] s, int i, int j) {
                while (i < j) {
                    char tmp = s[i];
                    s[i] = s[j];
                    s[j] = tmp;
                    i++;
                    j--;
                }
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
                    while (j < chars.length && chars[j] != ' ') {
                        j++;
                    }
                    reverse(chars, i, j - 1);
                    while (j < chars.length && chars[j] == ' ') {
                        j++;
                    }
                }
                return new String(chars);
            }

            private void reverse(char[] chars, int i, int j) {
                while (i < j) {
                    char tem = chars[i];
                    chars[i++] = chars[j];
                    chars[j--] = tem;
                }
            }
        }
    }
}
