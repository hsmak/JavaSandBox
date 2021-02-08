package org.hsmak.letit;

import java.util.*;
import java.util.function.BiFunction;

public class WordBreak {

    BiFunction<String, List<String>, List<String>> strategy;

    public WordBreak(BiFunction<String, List<String>, List<String>> strategy) {
        this.strategy = strategy;
    }

    public static void main(String[] args) {
        new WordBreak(StrategyE.DFS1)
                .wordBreak("catsanddog", List.of("cat", "cats", "and", "sand", "dog"))
                .forEach(System.out::println);
        System.out.println();

        new WordBreak(StrategyE.DFS2)
                .wordBreak("catsanddog", List.of("cat", "cats", "and", "sand", "dog"))
                .forEach(System.out::println);
        System.out.println();

        new WordBreak(StrategyE.DFS_WithMemoization)
                .wordBreak("catsanddog", List.of("cat", "cats", "and", "sand", "dog"))
                .forEach(System.out::println);
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
        DFS1 {
            @Override
            public List<String> apply(String s, List<String> wordDict) {
                Set<String> wordSet = new HashSet<>(wordDict);
                int n = s.length();
                List<String>[] dp = new ArrayList[n + 1];
                return dfs(s, 0, wordSet, dp);
            }

            private List<String> dfs(String s, int i, Set<String> wordSet, List<String>[] dp) {
                if (dp[i] != null) {
                    return dp[i];
                }
                if (i == s.length()) {
                    return Arrays.asList("");
                }
                List<String> res = new ArrayList<>();
                for (int j = i + 1; j <= s.length(); j++) {
                    String cur = s.substring(i, j);
                    if (wordSet.contains(cur)) {
                        List<String> next = dfs(s, j, wordSet, dp); // Recursion
                        if (!next.isEmpty()) {
                            for (String suffix : next) {
                                res.add(cur + (suffix.equals("") ? "" : " ") + suffix);
                            }
                        }
                    }
                }
                dp[i] = new ArrayList<>();
                dp[i] = res;
                return res;
            }
        },
        DFS2 {
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