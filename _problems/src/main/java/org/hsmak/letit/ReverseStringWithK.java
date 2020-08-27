package org.hsmak.letit;

import java.util.function.Function;


public class ReverseStringWithK {

    private Function<String, Function<Integer, String>> strategy;

    public ReverseStringWithK() {
        this.strategy = StrategyE.SIMILAR;
    }

    public ReverseStringWithK(Function<String, Function<Integer, String>> strategy) {
        this.strategy = strategy;
    }

    public String reverseFirstKEvery2K(String s, int k) {
        return strategy.apply(s).apply(k);
    }

    /*
     * Function<String, Function<Integer, String>> -
     *      - Similar to currying in Scala
     *      - Though it needs to be explicit; the ugly side!
     *      - We could use BiFunction<String, Integer, String> ?
     *          - what if we have more than 2 params? We still can do our own TriFunction and so one
     */
    enum StrategyE implements Function<String, Function<Integer, String>> {
        DEFAULT {
            @Override
            public Function<Integer, String> apply(String s) {
                return k -> {
                    char[] chars = s.toCharArray();
                    int n = chars.length;
                    for (int step = 0; step < n; step += 2 * k) {//Every 2*K -> multiple of Ks

                        for (int lowerBound = step, upperBound = Math.min(k + step - 1, n - 1);
                             lowerBound < upperBound;
                             lowerBound++, upperBound--) {

                            char front = chars[lowerBound];
                            chars[lowerBound] = chars[upperBound];
                            chars[upperBound] = front;
                        }
                    }
                    return String.valueOf(chars);
                };
            }


        },
        SIMILAR {
            @Override
            public Function<Integer, String> apply(String s) {
                return k -> {
                    char[] chars = s.toCharArray();
                    for (int i = 0; i < s.length(); i += 2 * k) { //Every 2*K -> multiple of Ks
                        int j = i, end = Math.min(i + k - 1, chars.length - 1);
                        while (j < end) {
                            char temp = chars[j];
                            chars[j++] = chars[end];
                            chars[end--] = temp;
                        }
                    }
                    return String.valueOf(chars);
                };
            }
        },
        SIMILAR2 {
            @Override
            public Function<Integer, String> apply(String s) {
                return k -> {
                    char[] chars = s.toCharArray();
                    for (int step = 0; step < s.length(); step += 2 * k) {
                        for(int lowerBound = step,
                            upperBound = Math.min(k + step - 1, s.length() - 1);
                            lowerBound < upperBound;
                            lowerBound++, upperBound--){
                            char temp = chars[lowerBound];
                            chars[lowerBound] = chars[upperBound];
                            chars[upperBound] = temp;
                        }
                    }
                    return String.valueOf(chars);
                };
            }
        }
    }
}
