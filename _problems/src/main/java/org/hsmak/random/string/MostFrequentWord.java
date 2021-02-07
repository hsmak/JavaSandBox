package org.hsmak.random.string;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class MostFrequentWord {

    Function<List<String>, String> strategy;

    public MostFrequentWord(Function<List<String>, String> strategy) {
        this.strategy = strategy;
    }

    public String findMostFrequentWord(List<String> words) {
        return strategy.apply(words);

    }

    enum StrategyE implements Function<List<String>, String> {
        DEFAULT {
            @Override
            public String apply(List<String> words) {
                String result = "";
                Map<String, Integer> map = new HashMap<>();

                int max = Integer.MIN_VALUE;

                for (String word : words) {
                    if (map.containsKey(word)) {

                        map.put(word, map.get(word) + 1);

                        if (max < map.get(word)) {
                            max = map.get(word);
                            result = word;
                        }

                    } else
                        map.put(word, 1);
                }
                return result;
            }
        }
    }
}
