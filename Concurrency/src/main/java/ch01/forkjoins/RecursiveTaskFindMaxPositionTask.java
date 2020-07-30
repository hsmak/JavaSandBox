package ch01.forkjoins;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RecursiveTaskFindMaxPositionTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 50;
    private int[] data;
    private int start;
    private int end;

    public RecursiveTaskFindMaxPositionTask(int[] data, int start, int end) {
        this.data = data;
        this.start = start; // where does our section begin at?
        this.end = end; // how large is this section?
    }

    @Override
    protected Integer compute() { // return type matches <generic> type
        if (end - start <= THRESHOLD) { // is it a manageable amount of work?

            int position = 0; // if all values are equal,
            // return position 0

            for (int i = start; i < end; i++) {
                if (data[i] > data[position])
                    position = i;
            }

            return position;

        } else { // task too big, split it
            int halfWay = ((end - start) / 2) + start;
            RecursiveTaskFindMaxPositionTask t1 = new RecursiveTaskFindMaxPositionTask(data, start, halfWay);
            t1.fork(); // queue half left of task
            RecursiveTaskFindMaxPositionTask t2 = new RecursiveTaskFindMaxPositionTask(data, halfWay, end);
            int position2 = t2.compute();
            int position1 = t1.join();
            if (data[position1] >= data[position2])
                return position1;
            else
                return position2;
        }
    }

    public static void main(String[] args) {
        /*int[] data = new int[20_000];

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        RecursiveActionArrayInitializerTask initArrayAction = new RecursiveActionArrayInitializerTask(data, 0, data.length);
        forkJoinPool.invoke(initArrayAction);*/

        // invokeAll can save some typing by replacing the previous 2 lines
        //invokeAll(initArrayAction);
        //Arrays.stream(data).forEach(System.out::println);

        //**************************************//

        //prepare the data
        int[] data = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(0, 9)).limit(20).toArray();
        IntStream.range(0, data.length).forEach(i -> System.out.println(String.format("index %d: %d", i, data[i])));

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        RecursiveTaskFindMaxPositionTask findMaxIndexTask = new RecursiveTaskFindMaxPositionTask(data, 0, data.length);
//        invokeAll(findMaxIndexTask); // It works but useless since we're expecting a value to be returned.
        Integer index = forkJoinPool.invoke(findMaxIndexTask);
        System.out.println(index);

    }
}