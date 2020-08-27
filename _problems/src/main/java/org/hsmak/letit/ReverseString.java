package org.hsmak.letit;

import java.util.Objects;
import java.util.function.Function;


public class ReverseString {

    private Function<char[], Void> strategy;

    public ReverseString() {
        strategy = StrategyE.DEFAULT_IN_PLACE;
    }

    public ReverseString(Function<char[], Void> strategy) {
        this.strategy = strategy;
    }

    public void reverseString(char[] s) {
        strategy.apply(s);
    }

    enum StrategyE implements Function<char[], Void> {
        DEFAULT_IN_PLACE {
            @Override
            public Void apply(char[] s) {
                if (Objects.isNull(s) || s.length == 1)
                    return null;

                int n = s.length;

                for (int lower = 0, upper = n - 1;
                     lower < upper;
                     lower++, upper--) {
                    char front = s[lower];
                    s[lower] = s[upper];
                    s[upper] = front;
                }
                return null;
            }
        },
        IN_PLACE_2 {
            @Override
            public Void apply(char[] s) {
                if (Objects.isNull(s) || s.length == 1)
                    return null;

                int n = s.length;

                for (int i = 0; i < n / 2; i++) {
                    char front = s[i];
                    s[i] = s[n - 1 - i];
                    s[n - 1 - i] = front;
                }
                return null;
            }
        }
    }
}
