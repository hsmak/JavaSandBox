package org.hsmak.letit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class RunningSum {
    Function<int[], int[]> strategy;

    public RunningSum() {
        this.strategy = StrategyE.VIA_REDUCE;
    }

    public RunningSum(Function<int[], int[]> strategy) {
        this.strategy = strategy;
    }

    public int[] runningSum(int[] nums) {

        return strategy.apply(nums);
    }

    enum StrategyE implements Function<int[], int[]> {
        DECLARATIVE {
            @Override
            public int[] apply(int[] nums) {
                for (int i = 1; i < nums.length; i++) {
                    nums[i] = nums[i - 1] + nums[i];
                }
                return nums;
            }
        },
        VIA_COLLECT {
            @Override
            public int[] apply(int[] nums) { // Equivalent to ScanLeft
                return Arrays.stream(nums)
                        .collect(ArrayList<Integer>::new, // Supplier
                                (acc, nxt) -> acc.add(acc.size() > 0 ? acc.get(acc.size() - 1) + nxt : nxt), // BiConsumer
                                ArrayList::addAll) // BiConsumer
                        .stream()
                        .mapToInt(i -> i)
                        .toArray();
            }
        },
        VIA_REDUCE {
            @Override
            public int[] apply(int[] nums) {
                return Arrays.stream(nums)
                        .mapToObj(i -> i)
                        .reduce(add(new ArrayList<Integer>(), 0), // Identity
                                (acc, nxt) -> add(acc, nxt + acc.get(acc.size() - 1)), // BiFunction
                                (acc1, acc2) -> addAll(acc1, acc2)) // BiFunction
                        .stream()
                        .skip(1)
                        .mapToInt(i -> i)
                        .toArray();
            }

            private <T> List<T> add(List<T> l, T t) {
                l.add(t);
                return l;
            }

            private <T> List<T> addAll(List<T> l1, List<T> l2) {
                l1.addAll(l2);
                return l1;
            }
        }
    }
}
