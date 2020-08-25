package org.hsmak.letit;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.junit.Assert.*;

public class ReverseStringTest {

    ReverseString reverseString;

    @Before
    public void setUp() throws Exception {
        reverseString = new ReverseString();
    }

    @Test
    public void testReverseStringForNull() {
        char[] str = null;
        reverseString.reverseString(str);
        assertThat(str).isNull();
    }

    @Test
    public void testReverseStringForOneChar() {
        String str = "H";
        char[] chars = str.toCharArray();
        reverseString.reverseString(chars);
        assertThat(String.valueOf(chars)).isEqualTo(new StringBuilder(str).reverse().toString());
    }

    @Test
    public void testReverseStringOdd() {
        String str = "Hello";
        char[] chars = str.toCharArray();
        reverseString.reverseString(chars);
        assertThat(String.valueOf(chars)).isEqualTo(new StringBuilder(str).reverse().toString());
    }


    @Test
    public void testReverseStringEven() {
        String str = "Hanna";
        char[] chars = str.toCharArray();
        reverseString.reverseString(chars);
        assertThat(String.valueOf(chars)).isEqualTo(new StringBuilder(str).reverse().toString());
    }

    @Test
    public void testReverseStringUnrepeatedOdd() {
        String str = "abcd";
        char[] chars = str.toCharArray();
        reverseString.reverseString(chars);
        assertThat(String.valueOf(chars)).isEqualTo(new StringBuilder(str).reverse().toString());
    }


    @Test
    public void testReverseStringUnrepeatedEven() {
        String str = "abcde";
        char[] chars = str.toCharArray();
        reverseString.reverseString(chars);
        assertThat(String.valueOf(chars)).isEqualTo(new StringBuilder(str).reverse().toString());
    }


}