package concurrency.ch01.barriers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by hsmak on 4/15/15.
 */
public class Processor implements Runnable {

    CyclicBarrier barrier;

    List<Integer> list = new ArrayList<>();
    Random random = new Random();

    public Processor(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {

        try {

            for (int i = 0; i < 1000; i++) {
                Thread.sleep(1);
                list.add(random.nextInt(100));
            }

            System.out.println(String.format("%s waiting at barrier", Thread.currentThread().getName()));

            barrier.await(); // Waiting at the barrier

            // The below will continue executing after the Barrier's Runnable finishes running
            System.out.println(String.format("%s continues after Barrier's Runnable finishes running!", Thread.currentThread().getName()));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getList() {
        return list;
    }
}

class CyclicBarrierRunnerApp {
    public static void main(String[] args) throws InterruptedException {

        // This thread will be run after the other threads hit the barrier as many as indicated by count;
        // i.e. will be executed last, however, any work left after "barrier.await()"
        CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println(String.format("%s: BarrierAction executed", Thread.currentThread().getName()));
            }
        });

        //or simply:
        //CyclicBarrier barrier = new CyclicBarrier(2);


        Processor w1 = new Processor(barrier);
        Processor w2 = new Processor(barrier);

        long start = System.currentTimeMillis();

        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        long end = System.currentTimeMillis();

        List<Integer> list1 = w1.getList();
        List<Integer> list2 = w2.getList();


        System.out.println("Time Taken: " + (end - start));
        System.out.println("List1: " + list1.size());
        System.out.println("List2: " + list2.size());
    }
}
