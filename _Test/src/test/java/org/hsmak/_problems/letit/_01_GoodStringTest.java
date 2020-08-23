package org.hsmak._problems.letit;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
//import static org.junit.Assert.*;

public class _01_GoodStringTest {

    _01_GoodString gs;

    @Before
    public void setUp() throws Exception {
        gs = new _01_GoodString();
    }

    @Test
    public void testMakeGoodForEmptyBlankStringOrNull() {
        assertThat(gs.makeGood(""), is(""));
        assertThat(gs.makeGood("     "), is(""));
        assertThat(gs.makeGood(null), is(""));
    }

    @Test
    public void testMakeGoodForOneCharLower() {
        assertThat(gs.makeGood("a"), is("a"));
    }

    @Test
    public void testMakeGoodForOneCharUpper() {
        assertThat(gs.makeGood("A"), is("A"));
    }

    @Test
    public void testMakeGoodForTwoCharsBad() {
        assertThat(gs.makeGood("aA"), is(""));
    }

    @Test
    public void testMakeGoodForTwoCharsBadSwapped() {
        assertThat(gs.makeGood("Aa"), is(""));
    }

    @Test
    public void testMakeGoodForThreeCharsBad() {
        assertThat(gs.makeGood("aAd"), is("d"));
    }

    @Test
    public void testMakeGoodForFourCharsGood() {
        assertThat(gs.makeGood("abcd"), is("abcd"));
    }

    @Test
    public void testMakeGoodForFourCharsGood2() {
        assertThat(gs.makeGood("aBcD"), is("aBcD"));
    }

    @Test
    public void testMakeGoodForFiveCharsGood() {
        assertThat(gs.makeGood("abcde"), is("abcde"));
    }

    @Test
    public void testMakeGoodForFiveCharsBad() {
        assertThat(gs.makeGood("aAdbB"), is("d"));
    }

    @Test
    public void testMakeGoodForSevenCharsBad() {
        assertThat(gs.makeGood("caAdbBo"), is("cdo"));
    }

    @Test
    public void testMakeGoodForLeetCode() {
        assertThat(gs.makeGood("leEeetcode"), is("leetcode"));
    }

    @Test
    public void testMakeGoodForRecursion() {
        assertThat(gs.makeGood("abBAcC"), is(""));
    }

    @Test
    public void testMakeGoodForLeetCode3() {
        assertThat(gs.makeGood("RLlr"), is(""));
    }

}