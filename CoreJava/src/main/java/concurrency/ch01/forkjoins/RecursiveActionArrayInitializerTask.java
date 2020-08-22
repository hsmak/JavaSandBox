package concurrency.ch01.forkjoins;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class RecursiveActionArrayInitializerTask extends RecursiveAction {
    private static final int THRESHOLD = 5000;
    private int[] data;
    private int start;
    private int end;

    public RecursiveActionArrayInitializerTask(int[] data, int start, int end) {
        this.data = data;
        this.start = start; // where does our section begin at?
        this.end = end; // how large is this section?
    }

    @Override
    protected void compute() {
        if (end - start <= THRESHOLD) { // is it a manageable amount of work?
            // do the task
            /*for (int i = start; i < end; i++)
            {
                data[i] = ThreadLocalRandom.current().nextInt();
            }*/

            // Initialize the array using Streams
            IntStream indicesStrm = IntStream.iterate(start, i -> i + 1).limit(end - start);
            indicesStrm.forEach(i -> data[i] = ThreadLocalRandom.current().nextInt());

            // Unnecessary here, but we can opt for parallel stream within this thread as well
            //indicesStrm.parallel().forEach(i -> data[i] = ThreadLocalRandom.current().nextInt());


        } else { // task too big, split it
            int halfWay = ((end - start) / 2) + start;
            RecursiveActionArrayInitializerTask a1 = new RecursiveActionArrayInitializerTask(data, start, halfWay);
            a1.fork(); // queue left half of task
            RecursiveActionArrayInitializerTask a2 = new RecursiveActionArrayInitializerTask(data, halfWay, end);
            a2.compute(); // work on right half of task
            a1.join(); // wait for queued task to be complete
        }
    }

    public static void main(String[] args) {
        int[] data = new int[20_000];
        RecursiveActionArrayInitializerTask action = new RecursiveActionArrayInitializerTask(data, 0, data.length);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(action);

        // invokeAll can save some typing by replacing the previous 2 lines
        //invokeAll(action);

        Arrays.stream(data).forEach(System.out::println);
    }
}