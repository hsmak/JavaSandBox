package org.hsmak.letit;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReverseWordsInString {

    Function<String, String> strategy;

    public ReverseWordsInString(Function<String, String> strategy) {
        this.strategy = strategy;
    }

    public String reverseWords(String s) {
        return this.strategy.apply(s);
    }

    enum StrategyE implements Function<String, String> {
        DEFAULT {
            @Override
            public String apply(String s) {
                String[] words = s.split("\\s+");
                StringBuilder reversedWordsBuilder = new StringBuilder();
                for (String word : words)
                    reversedWordsBuilder.append(new StringBuilder(word).reverse()).append(" ");
                return reversedWordsBuilder.toString().trim();
            }
        },
        PRESERVE_MULTIPLE_SPACES { // ToDo
            @Override
            public String apply(String s) {

//                StringBuilder words
//                int[] whiteSpaces = s.chars().filter(c -> Character.isWhitespace(c)).toArray();

                String[] words = s.split("\\s+");
                StringBuilder reversedWordsBuilder = new StringBuilder();
                for (String word : words)
                    reversedWordsBuilder.append(new StringBuilder(word).reverse()).append(" ");
                return reversedWordsBuilder.toString().trim();
            }
        }
    }
}
