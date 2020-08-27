package org.hsmak.letit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hsmak.letit.ReverseStringWithK.StrategyE;

@RunWith(Parameterized.class)
public class ReverseStringWithKTest {

    private ReverseStringWithK reverseStringWithK;

    public ReverseStringWithKTest(StrategyE e) {
        reverseStringWithK = new ReverseStringWithK(e);
    }

    @Parameters(name = "Strategy -> {0}")
    public static EnumSet<StrategyE> getStrategyEnum() {
        return EnumSet.allOf(StrategyE.class);
    }

    @Test
    public void testReverseStringWithK1() {

        assertThat(reverseStringWithK.reverseFirstKEvery2K("abcdefg", 1)).isEqualTo("abcdefg");

        assertThat(reverseStringWithK.reverseFirstKEvery2K("abcdefgh", 2)).isEqualTo("bacdfegh");

        assertThat(reverseStringWithK.reverseFirstKEvery2K("abcdefg", 3)).isEqualTo("cbadefg");

        assertThat(reverseStringWithK.reverseFirstKEvery2K("abcdefg", 7)).isEqualTo("gfedcba");

        assertThat(reverseStringWithK.reverseFirstKEvery2K("abcdefg", 4)).isEqualTo("dcbaefg");

        assertThat(reverseStringWithK.reverseFirstKEvery2K("abcdefg", 40)).isEqualTo("gfedcba");

    }

    @Test
    public void testReverseStringWithK2() {
        assertThat(reverseStringWithK.reverseFirstKEvery2K("abcdefghijklmn", 3)).isEqualTo("cbadefihgjklnm");
    }
}