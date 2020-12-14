package org.hsmak.random.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JMostFrequentWord {

    public static void main(String... args) {
        List<String> words = Arrays.asList("dog", "dog", "elephant", "cat", "cat", "cat");
        String result = findMostFrequentWord(words);
        System.out.println(result);
    }

    public static String findMostFrequentWord(List<String> words) {

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
