package org.hsmak.letit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;

//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.junit.Assert.*;

public class GoodStringTest {

    GoodString gs;

    @BeforeClass
    public static void init() {
        EnumSet<GSStrategyE> strategySet = EnumSet.allOf(GSStrategyE.class);
        strategySet.forEach(System.out::println);

//        EnumMap<GSStrategyE, String> enumMap = new EnumMap<>(GSStrategyE.class);
    }

    @Before
    public void setUp() throws Exception {
        gs = new GoodString();
//        gs = new GoodString(GSStrategyE.DEFAULT);
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