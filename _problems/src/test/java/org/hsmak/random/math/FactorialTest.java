package org.hsmak.random.math;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.EnumSet;

import static org.assertj.core.api.Assertions.*;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class FactorialTest {

    Factorial factorial;

    public FactorialTest(Factorial.StrategyE strategy) {
        this.factorial = new Factorial(strategy);
    }

    @Parameters(name = "Strategy -> {0}")
    public static EnumSet<Factorial.StrategyE> getEnums() {
        return EnumSet.allOf(Factorial.StrategyE.class);
    }

    @Test
    public void testFact(){
        assertThat(factorial.factorial(3)).isEqualTo(6);
        assertThat(factorial.factorial(4)).isEqualTo(24);
    }

}