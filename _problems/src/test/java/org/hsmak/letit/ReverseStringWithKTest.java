package org.hsmak.letit;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReverseStringWithKTest {

    ReverseStringWithK reverseStringWithK;
    @Before
    public void setUp() throws Exception {
        reverseStringWithK = new ReverseStringWithK();
    }

    @Test
    public void reverseStr() {

        assertThat(reverseStringWithK.reverseFirstKEvery2K("abcdefg", 2)).isEqualTo("bacdfeg");

        assertThat(reverseStringWithK.reverseFirstKEvery2K("abcdefg", 1)).isEqualTo("abcdefg");

        assertThat(reverseStringWithK.reverseFirstKEvery2K("abcdefg", 3)).isEqualTo("cbadefg");

        assertThat(reverseStringWithK.reverseFirstKEvery2K("abcdefg", 7)).isEqualTo("gfedcba");

        assertThat(reverseStringWithK.reverseFirstKEvery2K("abcdefg", 4)).isEqualTo("dcbaefg");

        assertThat(reverseStringWithK.reverseFirstKEvery2K("abcdefg", 40)).isEqualTo("gfedcba");

    }
}