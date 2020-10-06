package org.hsmak.letit;

import java.util.Stack;
import java.util.function.Function;

public class LongestValidParentheses {
    Function<String, Integer> strategy;

    public LongestValidParentheses(Function<String, Integer> strategy) {
        this.strategy = strategy;
    }

    public int validateParens(String str) {
        return this.strategy.apply(str);
    }

    enum StrategyE implements Function<String, Integer> {
        DEFAULT { // ToDo - still needs fixing

            @Override
            public Integer apply(String str) {
                char[] chars = str.toCharArray();
                Stack<Character> stack = new Stack<>();

                int currentCount = 0;
                int maxCount = 0;

                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] == '(') {

                        stack.push(chars[i]);

                    } else {//ToDo - while loop on stack + 

                        if (!stack.isEmpty()) {
                            stack.pop();
                            currentCount += 2;
                            maxCount = Math.max(maxCount, currentCount);
                        }
                    }
                }
                return maxCount;
            }
        },
        S1 {
            @Override
            public Integer apply(String s) {
                int maxCount = 0;
                Stack<Integer> stack = new Stack<>();
                stack.push(-1);
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '(') {
                        stack.push(i);
                    } else {
                        stack.pop();
                        if (stack.empty()) {
                            stack.push(i);
                        } else {
                            maxCount = Math.max(maxCount, i - stack.peek());
                        }
                    }
                }
                return maxCount;
            }
        },
        S2 {
            @Override
            public Integer apply(String s) {
                int left = 0, right = 0, maxCount = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '(') {
                        left++;
                    } else {
                        right++;
                    }
                    if (left == right) {
                        maxCount = Math.max(maxCount, 2 * right);
                    } else if (right >= left) {
                        left = right = 0;
                    }
                }
                left = right = 0;
                for (int i = s.length() - 1; i >= 0; i--) {
                    if (s.charAt(i) == '(') {
                        left++;
                    } else {
                        right++;
                    }
                    if (left == right) {
                        maxCount = Math.max(maxCount, 2 * left);
                    } else if (left >= right) {
                        left = right = 0;
                    }
                }
                return maxCount;
            }
        },
        S3 {
            @Override
            public Integer apply(String s) {
                int count = 0;
                int max = 0;
                for (int i = 0; i < s.length(); i++) {
                    count = 0;
                    for (int j = i; j < s.length(); j++) {
                        if (s.charAt(j) == '(') {
                            count++;
                        } else {
                            count--;
                        }
                        if (count < 0) {
                            break;

                        }
                        if (count == 0) {
                            if (j - i + 1 > max) {
                                max = j - i + 1;
                            }
                        }
                    }
                }
                return max;
            }
        }
    }
}
