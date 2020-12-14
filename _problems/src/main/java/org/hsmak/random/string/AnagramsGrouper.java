package org.hsmak.random.string;

import java.util.*;
import java.util.function.Function;

public class AnagramsGrouper {

    Function<String[], List<List<String>>> strategy;

    public AnagramsGrouper(Function<String[], List<List<String>>> strategy) {
        this.strategy = strategy;
    }

    public List<List<String>> groupAnagrams(String[] strs) {

        return strategy.apply(strs);
    }

    enum StrategyE implements Function<String[], List<List<String>>> {
        DEFAULT {
            @Override
            public List<List<String>> apply(String[] strs) {
                if (strs.length == 0)
                    return Collections.emptyList();

                Map<String, List<String>> ans = new HashMap<>();

                for (String s : strs) {

                    char[] ca = s.toCharArray();
                    Arrays.sort(ca);
                    String key = String.valueOf(ca);

                    if (!ans.containsKey(key))
                        ans.put(key, new ArrayList());

                    ans.get(key).add(s);
                }

                return new ArrayList(ans.values());
            }
        }
    }
}
