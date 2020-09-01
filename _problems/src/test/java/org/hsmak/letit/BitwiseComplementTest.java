package org.hsmak.letit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hsmak.letit.BitwiseComplement.StrategyE;

@RunWith(Parameterized.class)
public class BitwiseComplementTest {

    BitwiseComplement bitwiseComplement;

    public BitwiseComplementTest(StrategyE e) {
        this.bitwiseComplement = new BitwiseComplement(e);
    }

    @Parameterized.Parameters(name = "Strategy -> {0}")
    public static EnumSet<StrategyE> getStrategies() {
        return EnumSet.allOf(StrategyE.class);
    }

    @Test
    public void complementN() {
        assertThat(bitwiseComplement.onesComplementOfN(5)).isEqualTo(2);
        assertThat(bitwiseComplement.onesComplementOfN(7)).isEqualTo(0);
        assertThat(bitwiseComplement.onesComplementOfN(10)).isEqualTo(5);
    }
}