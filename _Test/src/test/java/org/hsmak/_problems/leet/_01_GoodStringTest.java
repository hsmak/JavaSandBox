package org.hsmak._problems.leet;

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
    public void testMakeGoodFor2Chars() {
        assertThat(gs.makeGood("aA"), is(""));
    }

    @Test
    public void testMakeGoodFor2Chars2() {
        assertThat(gs.makeGood("Aa"), is(""));
    }

    @Test
    public void testMakeGoodForNonEmptyString3Chars() {
        assertThat(gs.makeGood("aAd"), is("d"));
    }

    @Test
    public void testMakeGoodForNonEmptyStringValid4Char() {
        assertThat(gs.makeGood("abcd"), is("abcd"));
    }

    @Test
    public void testMakeGoodForNonEmptyStringValid5Char() {
        assertThat(gs.makeGood("abcde"), is("abcde"));
    }

    @Test
    public void testMakeGoodForNonEmptyStringInvalid5() {
        assertThat(gs.makeGood("aAdbB"), is("d"));
    }

    @Test
    public void testMakeGoodForNonEmptyStringInvalid7() {
        assertThat(gs.makeGood("caAdbBo"), is("cdo"));
    }

    @Test
    public void testMakeGoodForLeetCode() {
        assertThat(gs.makeGood("leEeetcode"), is("leetcode"));
    }

    @Test
    public void testMakeGoodForLeetCode2() {
        assertThat(gs.makeGood("abBAcC"), is(""));
    }

    @Test
    public void testMakeGoodForLeetCode3() {
        assertThat(gs.makeGood("RLlr"), is(""));
    }

}