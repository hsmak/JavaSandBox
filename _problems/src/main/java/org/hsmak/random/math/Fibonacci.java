package org.hsmak.random.math;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Fibonacci {

    Function<Long, Long> strategy;

    public Fibonacci(Function<Long, Long> strategy) {
        this.strategy = strategy;
    }

    public long fib(long nth) {
        return strategy.apply(nth);
    }

    enum StrategyE implements Function<Long, Long> {
        Fib_Recur {
            @Override
            public Long apply(Long nth) {
                return fibRecr(nth, 0, 1);
            }

            private long fibRecr(long nth, long prev, long cur) {
                if (nth == 0)
                    return prev;
                else if (nth == 1)
                    return cur;
                else
                    return fibRecr(nth - 1, cur, (prev + cur));//Tail Recursive
            }
        },
        Fib_Iter {
            @Override
            public Long apply(Long nth) {
                long prev = 0;
                long cur = 1;
                if (nth == 0)
                    return prev;
                if (nth == cur)
                    return cur;

                long nxt;
                for (int i = 2; i <= nth; i++) {
                    nxt = prev + cur;
                    prev = cur;
                    cur = nxt;
                }

                return cur;
            }
        },
        Fib_Memoization{
            Map<Long, Long> memFib = new HashMap<>();
            @Override
            public Long apply(Long n) {
                memFib.put(0L, 0L);
                memFib.put(1L, 1L);

                return recurWithMem(n);
            }

            private long recurWithMem(long nth) {
                if(memFib.containsKey(nth))
                    return memFib.get(nth);
                else {
                    long nxtFib = recurWithMem(nth - 2) + recurWithMem(nth-1);
                    memFib.put(nth, nxtFib);

                    return nxtFib;
                }
            }
        },
        Fib_DynamicProgramming{
            @Override
            public Long apply(Long n) {
                Long[] dpFib = new Long[n.intValue()+1]; // space complexity is O(n)

                dpFib[0] = 0L;
                dpFib[1] = 1L;

                for(int i = 2; i <= n; i++) {
                    dpFib[i] = dpFib[i-2] + dpFib[i-1];
                }
                return dpFib[n.intValue()];
            }
        },
        Fib_DynamicProgrammingImp{
            @Override
            public Long apply(Long n) {
                Long[] dpFib = new Long[3];// Space complexity O(1)

                dpFib[0] = 0L;
                dpFib[1] = 1L;
                dpFib[2] = 1L;

                /*
                 * So it's similar to the Iterative approach but
                 * instead of maintaining separate variables, we maintain the values in an array
                 */
                for(int i = 2; i < n; i++) {
                    dpFib[0] = dpFib[1];
                    dpFib[1] = dpFib[2];
                    dpFib[2] = dpFib[0] + dpFib[1];
                }
                return dpFib[2];
            }
        }
    }
}
