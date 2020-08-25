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
        String str = "abcdefg";
        String reversedStr = reverseStringWithK.reverseFirstKEvery2K(str, 2);
        assertThat(reversedStr).isEqualTo("bacdefg");

    }
}