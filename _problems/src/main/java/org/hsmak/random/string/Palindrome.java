package org.hsmak.random.string;

import java.util.function.Function;
import java.util.stream.IntStream;

public class Palindrome {

    Function<String, Boolean> strategy;

    public Palindrome(Function<String, Boolean> strategy) {
        this.strategy = strategy;
    }

    public boolean isPalindrome(String str) {
        return strategy.apply(str);
    }

    enum StrategyE implements Function<String, Boolean> {
        DEFAULT {
            @Override
            public Boolean apply(String str) {
                int front = 0;
                int back = str.length() - 1;

                while (back > front) {
                    if (str.charAt(front++) != str.charAt(back--))
                        return false;
                }

                return true;
            }
        },
        WithStreams {
            @Override
            public Boolean apply(String str) {
                return IntStream.range(0, str.length() / 2)
                        .noneMatch(i -> str.charAt(i) != str.charAt(str.length() - i - 1));
            }
        },
        ByReversing {
            @Override
            public Boolean apply(String str) {
                StringBuilder sb = new StringBuilder();
                char[] chars = str.toCharArray();
                for (int i = chars.length - 1; i >= 0; i--)
                    sb.append(chars[i]);

                return sb.toString().equals(str);

            }
        }
    }
}
