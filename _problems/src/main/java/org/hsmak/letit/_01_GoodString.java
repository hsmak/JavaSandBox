package org.hsmak.letit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.function.Function;


enum GSStrategyE implements Function<String, String> {
    DEFAULT {
        @Override
        public String apply(String s) {
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
            return sb.length() == s.length() ? sb.toString() : this.apply(sb.toString()); //Recursive function call
        }

        private boolean isOneUpperTheOtherLower(char prev, char cur) {
            return Objects.equals(Character.toLowerCase(prev), Character.toLowerCase(cur)) && !(Character.isLowerCase(prev) ^ Character.isUpperCase(cur));
        }
    },
    STRATEGY_02 {
        @Override
        public String apply(String s) {
            String[] h = s.split("");
            ArrayList<String> H = new ArrayList<String>();
            for (int i = 0; i < h.length; i++)
                H.add(h[i]);
            for (int i = 0; i < H.size() - 1; i++) {
                if (H.get(i).equals(H.get(i + 1)))
                    continue;
                else if (H.get(i).toLowerCase().equals(H.get(i + 1)) || H.get(i).toUpperCase().equals(H.get(i + 1))) {
                    H.remove(i);
                    H.remove(i);
                    if (i == 0)
                        i--;
                    else
                        i -= 2;
                }
            }
            s = "";
            for (int i = 0; i < H.size(); i++)
                s += H.get(i);
            return s;
        }
    },
    STRATEGY_03 {
        @Override
        public String apply(String s) {
            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                boolean di = false;
                if (!stack.empty() && Math.abs(c - stack.peek()) == Math.abs('A' - 'a')) {
                    //System.out.println(stack);
                    stack.pop();
                    di = true;
                }

                if (!di) stack.push(c);
            }

            StringBuilder sb = new StringBuilder();

            while (!stack.empty()) {
                System.out.println(stack);
                sb.append(stack.peek());
                stack.pop();
            }

            sb.reverse();
            String ans = sb.toString();
            //ans = ans.reverse();
            return ans;
        }
    },
    STRATEGY_04 {
        @Override
        public String apply(String s) {
            Stack<Character> stack = new Stack<Character>();
            for (int i = 0; i < s.length(); i++) {

                if (!stack.isEmpty() && Math.abs(stack.peek() - s.charAt(i)) == 32) stack.pop();
                else stack.push(s.charAt(i));
            }
            System.out.println(stack);
            char[] ch = new char[stack.size()];
            for (int i = stack.size() - 1; i >= 0; i--) {
                ch[i] = stack.pop();
            }
            String str = "";
            for (int i = 0; i < ch.length; i++) {
                str += ch[i];
            }
            return str;
        }
    },
    STRATEGY_05 {
        @Override
        public String apply(String s) {
            boolean check = true;
            if (s == null) {
                return "";
            }
            List<Character> list = new ArrayList<Character>();
            for (int i = 0; i < s.length(); i++) {
                list.add(s.charAt(i));
            }

            while (check) {
                check = false;
                for (int i = 0; i < list.size() - 1; i++) {
                    if (Math.abs(list.get(i) - list.get(i + 1)) == 32) {
                        list.remove(i + 1);
                        list.remove(i);
                        check = true;
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for (Character sa : list) {
                sb.append(sa);
            }

            return sb.toString();
        }
    },
    STRATEGY_06 {
        @Override
        public String apply(String s) {
            Stack<Character> st = new Stack<>();

            for (int i = 0; i < s.length(); i++) {

                if (!st.isEmpty() && Math.abs(st.peek() - s.charAt(i)) == 32) {
                    st.pop();
                } else {
                    st.push(s.charAt(i));
                }
            }
            StringBuilder sb = new StringBuilder();
            while (!st.isEmpty()) {
                sb.append(st.pop());
            }
            return sb.reverse().toString();
        }
    },
    STRATEGY_07 {
        @Override
        public String apply(String s) {
            Stack<Character> stack = new Stack();
            for (int i = 0; i < s.length(); i++) {
                if (!stack.empty() && Math.abs(stack.peek() - s.charAt(i)) == 32) {
                    stack.pop();
                } else
                    stack.push(s.charAt(i));
            }
            char[] res = new char[stack.size()];
            int i = stack.size() - 1;
            while (!stack.isEmpty()) {
                res[i--] = stack.pop();
            }

            return new String(res);
        }
    },
    STRATEGY_08 {
        @Override
        public String apply(String s) {
            Stack<Character> stack = new Stack();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (!stack.isEmpty() && Math.abs(stack.peek() - ch) == 32)
                    stack.pop();
                else
                    stack.push(ch);
            }
            char[] result = new char[stack.size()];
            int index = result.length - 1;
            while (!stack.isEmpty())
                result[index--] = stack.pop();
            return new String(result);
        }
    },
    STRATEGY_09 {
        @Override
        public String apply(String s) {
            if (s.isEmpty()) return s;
            char[] arr = s.toCharArray();
            Stack<Character> stack = new Stack<>();

            for (char c : arr) {
                if (stack.isEmpty() || (c + 32 != stack.peek() && c - 32 != stack.peek())) {
                    stack.push(c);
                } else {
                    stack.pop();
                }
            }

            StringBuilder b = new StringBuilder();
            for (char c : stack) b.append(c);
            return b.toString();
        }
    },
    STRATEGY_IN_PLACE_01 {
        @Override
        public String apply(String s) {
            StringBuilder sb = new StringBuilder();
            char[] characters = s.toCharArray();
            for (char c : characters) {
                if (sb.length() > 0 && Math.abs(c - sb.charAt(sb.length() - 1)) == 'a' - 'A') {
                    sb.deleteCharAt(sb.length() - 1);
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
    },
    STRATEGY_IN_PLACE_02 {
        @Override
        public String apply(String s) {
            if (s.length() <= 1)
                return s;
            int i = 0, j = 1;
            StringBuilder sb = new StringBuilder(s);
            while (j < sb.length() && sb.length() > 1) {
                if (Math.abs(sb.charAt(i) - sb.charAt(j)) == 32) {
                    sb.deleteCharAt(i);
                    sb.deleteCharAt(i);
                    if (i > 0)
                        i = i - 1;
                    j = i + 1;
                    continue;
                }
                i++;
                j++;
            }
            return sb.toString();
        }
    }
}

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
