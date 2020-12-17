package org.hsmak.random.math;

import java.util.function.Function;

public class Factorial {

    Function<Long, Long> strategy;

    public Factorial(Function<Long, Long> strategy) {
        this.strategy = strategy;
    }

    public long factorial(long n) {
        return strategy.apply(n);
    }



    /*
     * ToDo:Use BigInteger
     *
     * Note: In Scala; BigInt as an implicit
     */
    enum StrategyE implements Function<Long, Long>{
        FactRecr{
            @Override
            public Long apply(Long n) {
                return fact(n, 1);
            }
            private Long fact(long n, long acc) {
                if (n == 0)
                    return acc;
                else
                    return fact(n - 1, acc * n);//Tail Recursive
            }
        },
        FactIter{
            @Override
            public Long apply(Long aLong) {
                long acc = 1;
                for(long i = 1; i <= aLong; i++)
                    acc *= i;
                return acc;
            }
        }
    }
}
