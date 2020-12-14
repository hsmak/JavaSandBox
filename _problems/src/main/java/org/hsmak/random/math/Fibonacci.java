package org.hsmak.random.math;

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
        }
    }
}
