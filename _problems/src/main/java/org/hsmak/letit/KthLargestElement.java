package org.hsmak.letit;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.function.BiFunction;

public class KthLargestElement {

    BiFunction<int[], Integer, Integer> strategy;

    public KthLargestElement() {
        strategy = StrategyE.VIA_MIN_HEAP;
    }

    public int findKthLargest(int[] nums, int k) {

        return strategy.apply(nums, k);
    }

    enum StrategyE implements BiFunction<int[], Integer, Integer> {
        VIA_SORTING {
            @Override
            public Integer apply(int[] nums, Integer k) {
                Arrays.sort(nums);
                return nums[nums.length - k];
            }
        },
        VIA_MIN_HEAP {
            @Override
            public Integer apply(int[] nums, Integer k) {
                PriorityQueue<Integer> minHeap = new PriorityQueue<>();
                for (int i : nums) {
                    minHeap.add(i);
                    if (minHeap.size() > k)
                        minHeap.remove();
                }
                return minHeap.remove();
            }
        }
    }
}
