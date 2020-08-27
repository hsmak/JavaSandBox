package org.hsmak.letit;

//import org.junit.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hsmak.letit.GoodString.StrategyE;

class GoodStringWith5Test {

    GoodString gs;

    @BeforeEach
    void setUp() {
        gs = new GoodString();
    }

    @ParameterizedTest
    @EnumSource(StrategyE.class)
    public void testMakeGoodForEmptyBlankStringOrNull(StrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("")).isEqualTo("");
        assertThat(gs.makeGood("     ")).isEqualTo("");
        assertThat(gs.makeGood(null)).isEqualTo("");
    }

    @ParameterizedTest
    @EnumSource(StrategyE.class)
    public void testMakeGoodForOneCharLower(StrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("a")).isEqualTo("a");
    }

    @ParameterizedTest
    @EnumSource(StrategyE.class)
    public void testMakeGoodForOneCharUpper(StrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("A")).isEqualTo("A");
    }

    @ParameterizedTest
    @EnumSource(StrategyE.class)
    public void testMakeGoodForTwoCharsBad(StrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("aA")).isEqualTo("");
    }

    @ParameterizedTest
    @EnumSource(StrategyE.class)
    public void testMakeGoodForTwoCharsBadSwapped(StrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("Aa")).isEqualTo("");
    }

    @ParameterizedTest
    @EnumSource(StrategyE.class)
    public void testMakeGoodForThreeCharsBad(StrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("aAd")).isEqualTo("d");
    }

    @ParameterizedTest
    @EnumSource(StrategyE.class)
    public void testMakeGoodForFourCharsGood(StrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("abcd")).isEqualTo("abcd");
    }

    @ParameterizedTest
    @EnumSource(StrategyE.class)
    public void testMakeGoodForFourCharsGood2(StrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("aBcD")).isEqualTo("aBcD");
    }

    @ParameterizedTest
    @EnumSource(StrategyE.class)
    public void testMakeGoodForFiveCharsGood(StrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("abcde")).isEqualTo("abcde");
    }

    @ParameterizedTest
    @EnumSource(StrategyE.class)
    public void testMakeGoodForFiveCharsBad(StrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("aAdbB")).isEqualTo("d");
    }

    @ParameterizedTest
    @EnumSource(StrategyE.class)
    public void testMakeGoodForSevenCharsBad(StrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("caAdbBo")).isEqualTo("cdo");
    }

    @ParameterizedTest
    @EnumSource(StrategyE.class)
    public void testMakeGoodForLeetCode(StrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("leEeetcode")).isEqualTo("leetcode");
    }

    @ParameterizedTest
    @EnumSource(StrategyE.class)
    public void testMakeGoodForRecursion(StrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("abBAcC")).isEqualTo("");
    }

    @ParameterizedTest
    @EnumSource(StrategyE.class)
    public void testMakeGoodForLeetCode3(StrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("RLlr")).isEqualTo("");
    }
}