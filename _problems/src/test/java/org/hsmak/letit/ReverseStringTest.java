package org.hsmak.letit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hsmak.letit.ReverseString.*;

@RunWith(Parameterized.class)
public class ReverseStringTest {

    private ReverseString reverseString;

    public ReverseStringTest(StrategyE e) {
        this.reverseString = new ReverseString(e);
    }

    @Parameterized.Parameters(name = "Strategy -> {0}")
    public static EnumSet<StrategyE> getEnums() {
        return EnumSet.allOf(StrategyE.class);
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