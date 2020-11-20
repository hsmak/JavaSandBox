package org.hsmak.letit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class RunningSum {
    Function<int[], int[]> strategy;

    public RunningSum() {
        this.strategy = StrategyE.VIA_REDUCE;
    }

    public RunningSum(Function<int[], int[]> strategy) {
        this.strategy = strategy;
    }

    public static void main(String[] args) {
        Stream.of("scan ", "left ", "too ").
                collect(ArrayList<String>::new,
                        (acc, nxt) -> acc.add(acc.size() > 0 ? acc.get(acc.size() - 1) + nxt : nxt),
                        ArrayList::addAll).forEach(System.out::println);
    }

    public int[] runningSum(int[] nums) {

        return strategy.apply(nums);
    }

    enum StrategyE implements Function<int[], int[]> {
        VIA_COLLECT {
            @Override
            public int[] apply(int[] nums) { // Equivalent to ScanLeft
                return Arrays.stream(nums)
                        .collect(ArrayList<Integer>::new,
                                (acc, nxt) -> acc.add(acc.size() > 0 ? acc.get(acc.size() - 1) + nxt : nxt),
                                ArrayList::addAll)
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
                        .reduce(add(new ArrayList<Integer>(), 0),
                                (acc, nxt) -> add(acc, nxt + acc.get(acc.size() - 1)),
                                (acc1, acc2) -> addAll(acc1, acc2))
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
