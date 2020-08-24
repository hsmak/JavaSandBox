package concurrency.ch01.executers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by hsmak on 4/15/15.
 */
public class WorkerThreadPoolExecutor {

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();
    private Random random = new Random();

    public void stageOne() {

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        list1.add(random.nextInt(100));

    }

    public void stageTwo() {

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        list2.add(random.nextInt(100));

    }

    public void process() throws InterruptedException {

        long start = System.currentTimeMillis();

        //ExecutorService e = new ThreadPoolExecutor(2, 2, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2));
        ExecutorService e = Executors.newFixedThreadPool(2);
        e.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    stageOne();
                }
            }
        });
        e.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    stageTwo();
                }
            }
        });

        e.shutdown();
        e.awaitTermination(10, TimeUnit.SECONDS);

        long end = System.currentTimeMillis();


        System.out.println("Time Taken: " + (end - start));
        System.out.println("List1: " + list1.size());
        System.out.println("List2: " + list2.size());
    }

    public static void main(String[] args) throws InterruptedException {
        new WorkerThreadPoolExecutor().process();
    }
}
