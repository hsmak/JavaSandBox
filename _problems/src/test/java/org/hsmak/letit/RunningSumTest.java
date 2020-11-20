package org.hsmak.letit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class RunningSumTest {
    RunningSum runningSum;

    public RunningSumTest(RunningSum.StrategyE strategy) {
        this.runningSum = new RunningSum(strategy);
    }

    @Parameters(name = "Strategy -> {0}")
    public static EnumSet<RunningSum.StrategyE> getEnums() {
        return EnumSet.allOf(RunningSum.StrategyE.class);
    }

    @Before
    public void setUp() {
        runningSum = new RunningSum();
    }

    @Test
    public void testRunningSum() {
        assertThat(runningSum.runningSum(new int[]{1, 2, 3, 4,})).containsExactly(new int[]{1, 3, 6, 10});
    }

}