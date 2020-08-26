package org.hsmak.letit;

//import org.junit.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

class GoodStringWith5Test {

    GoodString gs;

    @BeforeEach
    void setUp() {
        gs = new GoodString();
    }

    @ParameterizedTest
    @EnumSource(GSStrategyE.class)
    public void testMakeGoodForEmptyBlankStringOrNull(GSStrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("")).isEqualTo("");
        assertThat(gs.makeGood("     ")).isEqualTo("");
        assertThat(gs.makeGood(null)).isEqualTo("");
    }

    @ParameterizedTest
    @EnumSource(GSStrategyE.class)
    public void testMakeGoodForOneCharLower(GSStrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("a")).isEqualTo("a");
    }

    @ParameterizedTest
    @EnumSource(GSStrategyE.class)
    public void testMakeGoodForOneCharUpper(GSStrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("A")).isEqualTo("A");
    }

    @ParameterizedTest
    @EnumSource(GSStrategyE.class)
    public void testMakeGoodForTwoCharsBad(GSStrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("aA")).isEqualTo("");
    }

    @ParameterizedTest
    @EnumSource(GSStrategyE.class)
    public void testMakeGoodForTwoCharsBadSwapped(GSStrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("Aa")).isEqualTo("");
    }

    @ParameterizedTest
    @EnumSource(GSStrategyE.class)
    public void testMakeGoodForThreeCharsBad(GSStrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("aAd")).isEqualTo("d");
    }

    @ParameterizedTest
    @EnumSource(GSStrategyE.class)
    public void testMakeGoodForFourCharsGood(GSStrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("abcd")).isEqualTo("abcd");
    }

    @ParameterizedTest
    @EnumSource(GSStrategyE.class)
    public void testMakeGoodForFourCharsGood2(GSStrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("aBcD")).isEqualTo("aBcD");
    }

    @ParameterizedTest
    @EnumSource(GSStrategyE.class)
    public void testMakeGoodForFiveCharsGood(GSStrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("abcde")).isEqualTo("abcde");
    }

    @ParameterizedTest
    @EnumSource(GSStrategyE.class)
    public void testMakeGoodForFiveCharsBad(GSStrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("aAdbB")).isEqualTo("d");
    }

    @ParameterizedTest
    @EnumSource(GSStrategyE.class)
    public void testMakeGoodForSevenCharsBad(GSStrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("caAdbBo")).isEqualTo("cdo");
    }

    @ParameterizedTest
    @EnumSource(GSStrategyE.class)
    public void testMakeGoodForLeetCode(GSStrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("leEeetcode")).isEqualTo("leetcode");
    }

    @ParameterizedTest
    @EnumSource(GSStrategyE.class)
    public void testMakeGoodForRecursion(GSStrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("abBAcC")).isEqualTo("");
    }

    @ParameterizedTest
    @EnumSource(GSStrategyE.class)
    public void testMakeGoodForLeetCode3(GSStrategyE e) {
        gs.setStrategy(e);
        assertThat(gs.makeGood("RLlr")).isEqualTo("");
    }
}