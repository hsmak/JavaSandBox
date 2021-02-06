package org.hsmak.random.math;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class FibonacciTest {

    Fibonacci fibonacci;

    public FibonacciTest(Fibonacci.StrategyE strategy) {
        this.fibonacci = new Fibonacci(strategy);
    }

    @Parameters(name = "Strategy -> {0}")
    public static EnumSet<Fibonacci.StrategyE> getEnums() {
        return EnumSet.allOf(Fibonacci.StrategyE.class);
    }

    @Test
    public void testFib() {
        assertThat(fibonacci.fib(3)).isEqualTo(2);
        assertThat(fibonacci.fib(9)).isEqualTo(34);
        assertThat(fibonacci.fib(20)).isEqualTo(6765);
    }
}