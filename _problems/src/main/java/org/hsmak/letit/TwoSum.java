package org.hsmak.letit;

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
        DEFAULT {
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
        }
    }
}
