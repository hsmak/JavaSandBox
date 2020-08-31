package org.hsmak.letit;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class TwoSum {

    BiFunction<int[], Integer, int[]> strategy;

    public TwoSum(BiFunction<int[], Integer, int[]> strategy) {
        this.strategy = strategy;
    }

    public int[] findTwoIndicesSumUpToTarget(int[] nums, int target) {
        return this.strategy.apply(nums, target);
    }

    enum StrategyE implements BiFunction<int[], Integer, int[]> {
        /*
         * Time Complexity:     O(n^2)
         * Space Complexity:    O(1)
         */
        BRUTE_FORCE_IN_PLACE {
            @Override
            public int[] apply(int[] nums, Integer target) {
                for (int i = 0; i < nums.length - 1; i++) {
                    for (int j = 1 + i; j < nums.length; j++) {
                        if (nums[i] + nums[j] == target)
                            return new int[]{i, j};
                    }
                }
                return new int[]{};
            }
        },
        HASH_MAP_1_PASS {
            @Override
            public int[] apply(int[] nums, Integer target) { // Consider target a radix "r"
                Map<Integer, Integer> map = new HashMap<>();
                for (int i = 0; i < nums.length; i++) {
                    if (map.containsKey(nums[i])) {
                        return new int[]{map.get(nums[i]), i};
                    }
                    int complement = target - nums[i]; // Calculate the r's complement of nums[i]
                    map.put(complement, i);
                }
                return new int[]{};
            }
        },
        HASH_MAP_2_PASS {
            @Override
            public int[] apply(int[] nums, Integer target) { // Consider target a radix "r"
                Map<Integer, Integer> map = new HashMap<>();
                for (int i = 0; i < nums.length; i++) {
                    map.put(nums[i], i);
                }
                for (int i = 0; i < nums.length; i++) {
                    int complement = target - nums[i]; // Calculate the r's complement of nums[i]
                    if (map.containsKey(complement) && map.get(complement) != i) {
                        return new int[]{i, map.get(complement)};
                    }
                }
                return new int[]{};
            }
        }
    }
}
