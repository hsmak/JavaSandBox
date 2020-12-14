package org.hsmak.random.string;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.EnumSet;
import java.util.stream.Collectors;

import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class AnagramsGrouperTest {

    AnagramsGrouper anagramsGrouper;

    public AnagramsGrouperTest(AnagramsGrouper.StrategyE strategy) {
        this.anagramsGrouper = new AnagramsGrouper(strategy);
    }

    @Parameters(name = "Strategy -> {0}")
    public static EnumSet<AnagramsGrouper.StrategyE> getEnums() {
        return EnumSet.allOf(AnagramsGrouper.StrategyE.class);
    }

    @Test
    public void testAnagrams() {
        Assertions.assertThat(anagramsGrouper.groupAnagrams(new String[]{"cat", "dog", "tac", "god", "act"}))
                .flatExtracting(l -> l.stream().collect(Collectors.toList()))
                .containsExactly("cat", "tac", "act", "dog", "god");
//                .containsExactly(List.of(List.of("cat", "tac", "act"), List.of("dog", "god")));
    }
}