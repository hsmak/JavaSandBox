package org.hsmak.letit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hsmak.letit.TwoSum.*;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class TwoSumTest {

    TwoSum twoSum;

    public TwoSumTest(StrategyE e) {
        this.twoSum = new TwoSum(e);
    }

    @Parameters(name = "Strategy -> {0}")
    public static EnumSet<StrategyE> getStrategies(){
        return EnumSet.allOf(StrategyE.class);
    }

    @Test
    public void twoSum() {

        assertThat(twoSum.findTwoIndicesSumUpToTarget(new int[]{2,7,11,15}, 9)).isEqualTo(new int[]{0, 1});
        assertThat(twoSum.findTwoIndicesSumUpToTarget(new int[]{3,2,4}, 6)).isEqualTo(new int[]{1, 2});
        assertThat(twoSum.findTwoIndicesSumUpToTarget(new int[]{3,3}, 6)).isEqualTo(new int[]{0, 1});
        assertThat(twoSum.findTwoIndicesSumUpToTarget(new int[]{2,5,5,11}, 10)).isEqualTo(new int[]{1, 2});
    }
}