package org.hsmak.letit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hsmak.letit.GoodString.StrategyE;


@RunWith(Parameterized.class)
public class GoodStringTest {

    private GoodString gs;

    public GoodStringTest(StrategyE e) {
        gs = new GoodString(e);
    }

    @Parameters(name = "Strategy -> {0}")
    public static EnumSet<StrategyE> getEnums() {
        return EnumSet.allOf(StrategyE.class);
    }

    @Test
    public void testMakeGoodForEmptyBlankStringOrNull() {
        assertThat(gs.makeGood("")).isEqualTo("");
        assertThat(gs.makeGood("     ")).isEqualTo("");
        assertThat(gs.makeGood(null)).isEqualTo("");
    }

    @Test
    public void testMakeGoodForOneCharLower() {
        assertThat(gs.makeGood("a")).isEqualTo("a");
    }

    @Test
    public void testMakeGoodForOneCharUpper() {
        assertThat(gs.makeGood("A")).isEqualTo("A");
    }

    @Test
    public void testMakeGoodForTwoCharsBad() {
        assertThat(gs.makeGood("aA")).isEqualTo("");
    }

    @Test
    public void testMakeGoodForTwoCharsBadSwapped() {
        assertThat(gs.makeGood("Aa")).isEqualTo("");
    }

    @Test
    public void testMakeGoodForThreeCharsBad() {
        assertThat(gs.makeGood("aAd")).isEqualTo("d");
    }

    @Test
    public void testMakeGoodForFourCharsGood() {
        assertThat(gs.makeGood("abcd")).isEqualTo("abcd");
    }

    @Test
    public void testMakeGoodForFourCharsGood2() {
        assertThat(gs.makeGood("aBcD")).isEqualTo("aBcD");
    }

    @Test
    public void testMakeGoodForFiveCharsGood() {
        assertThat(gs.makeGood("abcde")).isEqualTo("abcde");
    }

    @Test
    public void testMakeGoodForFiveCharsBad() {
        assertThat(gs.makeGood("aAdbB")).isEqualTo("d");
    }

    @Test
    public void testMakeGoodForSevenCharsBad() {
        assertThat(gs.makeGood("caAdbBo")).isEqualTo("cdo");
    }

    @Test
    public void testMakeGoodForLeetCode() {
        assertThat(gs.makeGood("leEeetcode")).isEqualTo("leetcode");
    }

    @Test
    public void testMakeGoodForRecursion() {
        assertThat(gs.makeGood("abBAcC")).isEqualTo("");
    }

    @Test
    public void testMakeGoodForLeetCode3() {
        assertThat(gs.makeGood("RLlr")).isEqualTo("");
    }

}