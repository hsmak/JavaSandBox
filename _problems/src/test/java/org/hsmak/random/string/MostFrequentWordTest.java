package org.hsmak.random.string;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.EnumSet;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(Parameterized.class)
public class MostFrequentWordTest {

    MostFrequentWord mostFrequentWord;

    public MostFrequentWordTest(MostFrequentWord.StrategyE strategy) {
        this.mostFrequentWord = new MostFrequentWord(strategy);
    }

    @Parameterized.Parameters(name = "Strategy -> {0}")
    public static EnumSet<MostFrequentWord.StrategyE> getEnums() {
        return EnumSet.allOf(MostFrequentWord.StrategyE.class);
    }

    @Test
    public void testForMostFrequentWord() {
        assertThat(mostFrequentWord.findMostFrequentWord(List.of("dog", "dog", "elephant", "cat", "cat", "cat")))
                .isEqualTo("cat");
    }
}