package org.hsmak.letit;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.EnumSet;

import static org.assertj.core.api.Assertions.*;
import static org.hsmak.letit.LongestValidParentheses.*;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class LongestValidParenthesesTest {

    LongestValidParentheses longestValidParentheses;
    public LongestValidParenthesesTest(StrategyE e) {
        longestValidParentheses = new LongestValidParentheses(e);
    }

    @Parameters(name = "Strategy -> {0}")
    public static EnumSet<StrategyE> getStrategies(){
        return EnumSet.allOf(StrategyE.class);
    }
    @Test
    public void validateParensWithValidParens01() {
        assertThat(longestValidParentheses.validateParens("()()()()")).isEqualTo(8);
        assertThat(longestValidParentheses.validateParens("(())")).isEqualTo(4);
        assertThat(longestValidParentheses.validateParens("(())")).isEqualTo(4);
        assertThat(longestValidParentheses.validateParens("()()()()")).isEqualTo(8);
        assertThat(longestValidParentheses.validateParens("(())()()()")).isEqualTo(10);
//        assertThat(longestValidParentheses.validateParens("(())()")).isEqualTo(4);
        assertThat(longestValidParentheses.validateParens("(())(())")).isEqualTo(8);
        assertThat(longestValidParentheses.validateParens("(())((()))")).isEqualTo(10);
        assertThat(longestValidParentheses.validateParens("((()))(())")).isEqualTo(10);
        assertThat(longestValidParentheses.validateParens("((()))((()))")).isEqualTo(12);
        assertThat(longestValidParentheses.validateParens("(((()))((())))")).isEqualTo(14);
    }


    @Test
    public void validateParensWithValidParens02() {
        assertThat(longestValidParentheses.validateParens("((())")).isEqualTo(4);
        assertThat(longestValidParentheses.validateParens("))(())")).isEqualTo(4);
    }

    @Test
    public void validateParensWithValidParens03() {
        assertThat(longestValidParentheses.validateParens("(()))")).isEqualTo(4);
        assertThat(longestValidParentheses.validateParens("(())((")).isEqualTo(4);
        assertThat(longestValidParentheses.validateParens("()((()(((())))")).isEqualTo(10);
        assertThat(longestValidParentheses.validateParens("()(((())))((()")).isEqualTo(10);
        assertThat(longestValidParentheses.validateParens("(((())))()(((())))")).isEqualTo(18);
    }

    @Test
    public void validateParensWithInvalidParens01() {
        assertThat(longestValidParentheses.validateParens("((")).isEqualTo(0);
    }

    @Test
    public void validateParensWithInvalidParens02() {
        assertThat(longestValidParentheses.validateParens("))")).isEqualTo(0);
    }

    @Test
    public void validateParensWithInvalidParens03() {
        assertThat(longestValidParentheses.validateParens(")()())")).isEqualTo(4);
        assertThat(longestValidParentheses.validateParens("()(()")).isEqualTo(2);
    }

}