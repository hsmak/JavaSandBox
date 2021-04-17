package org.hsmak.letit;

import java.util.*;
import java.util.stream.Collectors;

public class UniqueOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i : arr) {
            if (m.containsKey(i))
                m.put(i, m.get(i) + 1);
            else {
                m.put(i, 0);
            }
        }
        Collection<Integer> l = m.values();//m.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
        Set<Integer> s = new HashSet<>(l);
        return l.size() == s.size();
    }
}
