package org.hsmak.letit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Observations:
 * - use the wordDict to recursively find the next Substring in the original String
 */
public class WordBreak {

    BiFunction<String, List<String>, List<String>> strategy;

    public WordBreak(BiFunction<String, List<String>, List<String>> strategy) {
        this.strategy = strategy;
    }

    public static void main(String[] args) {
        new WordBreak(StrategyE.DFS_WithMemoization)
                .wordBreak("catsanddog", List.of("cat", "cats", "and", "sand", "dog"))
                .forEach(System.out::println);
        System.out.println();

        new WordBreak(StrategyE.DFS)
                .wordBreak("catsanddog", List.of("cat", "cats", "and", "sand", "dog"))
                .forEach(System.out::println);
        System.out.println();
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        return strategy.apply(s, wordDict);
    }

    enum StrategyE implements BiFunction<String, List<String>, List<String>> {
        DFS_WithMemoization {
            @Override
            public List<String> apply(String s, List<String> wordDict) {
                return backtrack(s, wordDict, new HashMap<>());
            }

            private List<String> backtrack(String s, List<String> wordDict, Map<String, List<String>> memoiz) {
                if (memoiz.containsKey(s))
                    return memoiz.get(s);

                List<String> sentences = new ArrayList<>();

                for (String word : wordDict) {
                    if (word.equals(s)) {
                        sentences.add(word);
                    } else if (s.startsWith(word)) {
                        for (String sentence : backtrack(s.substring(word.length()), wordDict, memoiz)) { // Recursion
                            sentences.add(word.concat(" ").concat(sentence));
                        }
                    }
                }
                memoiz.put(s, sentences);
                return sentences;
            }
        },
        DFS {
            @Override
            public List<String> apply(String s, List<String> wordDict) {
                return dfs(s, wordDict, new HashMap<>());
            }

            public List<String> dfs(String s, List<String> wordDict, Map<String, List<String>> memoiz) {

                if (memoiz.containsKey(s))
                    return memoiz.get(s);

                List<String> sentences = new ArrayList<>();
                if (s.length() == 0) {
                    sentences.add("");
                    return sentences;
                }

                for (String word : wordDict) {
                    if (s.startsWith(word)) {
                        List<String> sublist = dfs(s.substring(word.length()), wordDict, memoiz); // Recursion
                        for (String sub : sublist) {
                            sentences.add(word + (sub.isEmpty() ? "" : " ") + sub);
                        }
                    }
                }
                memoiz.put(s, sentences);
                return sentences;
            }
        }
    }
}