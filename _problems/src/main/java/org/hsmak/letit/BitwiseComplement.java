package org.hsmak.letit;

import java.util.function.IntFunction;

/*
 * Refresher on radix r Complement:
 *  - https://www.youtube.com/watch?v=ldFofOqKcPs
 *  - https://www.youtube.com/watch?v=LP5of3ODA3I
 *
 * Refresher on Number Systems:
 *  - https://learning.oreilly.com/library/view/digital-logic-design/9780750645829/xhtml/B9780750645829500020.htm#cesectitle8
 */
public class BitwiseComplement {

    IntFunction<Integer> strategy;

    public BitwiseComplement(IntFunction<Integer> strategy) {
        this.strategy = strategy;
    }

    public int onesComplementOfN(int n) {
        return this.strategy.apply(n);
    }


    enum StrategyE implements IntFunction<Integer> {
        S1 {
            @Override
            public Integer apply(int n) {
                char[] bins = Integer.toBinaryString(n).toCharArray();
                flip(bins);
                return Integer.parseInt(String.valueOf(bins), 2);
            }

            private void flip(char[] bins) {
                for (int j = 0; j < bins.length; j++)
                    if (bins[j] == '0')
                        bins[j] = '1';
                    else
                        bins[j] = '0';
            }
        },
        S2 {
            @Override
            public Integer apply(int n) {
                if (n == 0) {
                    return 1;
                }

                int ans = 0;
                int off = 0;
                while (n > 0) {
                    int bit = ((n & 1) + 1) % 2;
                    ans = (bit << off) | ans;
                    n >>= 1;
                    off++;
                }

                return ans;
            }
        },
        S3 {
            @Override
            public Integer apply(int n) {
                if (n == 0) {
                    return 1;
                }
                long ans = (long) Math.pow(2, Math.ceil(Math.log(n + 1) / Math.log(2))) - 1 - n;
                return (int) ans;
            }
        },
        S4 {
            @Override
            public Integer apply(int n) {
                if (n == 0) return 1;
                if (n == 1) return 0;

                int ans = 0;
                int nC = ((int) (Math.log(n) / Math.log(2))) + 1;
                ans = ((1 << nC) - 1) ^ n;
                return ans;
            }
        },
        S5 {
            @Override
            public Integer apply(int n) {
                String bin = Integer.toBinaryString(n);
                return n ^= ((1 << bin.length()) - 1);
            }
        },
        S6 {
            @Override
            public Integer apply(int n) {
                int x = 1;
                while (n > x) x = x * 2 + 1;
                return x - n;
            }
        },
        S7 {
            @Override
            public Integer apply(int n) {
                int base = 2;
                while (base <= n) {
                    base = base * 2;
                }
                return base - 1 - n;
            }
        },
        S8 {
            /*
             *
             * Approach:
             *  - XOR N's binary with an array of 1's of the same length.
             *  - Find length of n (via while loop.... 2^y=n).
             *  - Subtract 1 from the resulted number. The final outcome is an array of 1's in binary.
             *      - if x=2^8, then x-1 in binary is "1111 1111"
             */
            @Override
            public Integer apply(int n) {
                if (n == 0) return 1;
                if (n == 1) return 0;
                int x = 1;
                while (x <= n) {
                    x = x << 1;  // equivalent to x *= 2;
                }
                return n ^ (x - 1);
            }
        },
        S9 {
            @Override
            public Integer apply(int n) {
                int p = (1 << Integer.toBinaryString(n).length() - 1);
                return Math.abs((p + p - 1) ^ n);
            }
        }
    }
}
