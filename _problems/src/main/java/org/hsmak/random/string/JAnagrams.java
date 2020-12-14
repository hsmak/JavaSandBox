package org.hsmak.random.string;

import java.util.*;

public class JAnagrams {
    public static void main(String[] args) {

        List<List<String>> anagrams = groupAnagrams(new String[]{"cat", "dog", "tac", "god", "act"});
        System.out.println(anagrams);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {

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
