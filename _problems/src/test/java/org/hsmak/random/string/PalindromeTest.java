package org.hsmak.random.string;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.EnumSet;

import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PalindromeTest {

    Palindrome palindrome;

    public PalindromeTest(Palindrome.StrategyE strategy) {
        this.palindrome = new Palindrome(strategy);
    }

    @Parameters(name = "Strategy -> {0}")
    public static EnumSet<Palindrome.StrategyE> getEnums() {
        return EnumSet.allOf(Palindrome.StrategyE.class);
    }

    @Test
    public void testPalindrome() {
        Assertions.assertThat(palindrome.isPalindrome("abccba")).isTrue();
    }
}