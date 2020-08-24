package concurrency.ch01.latches;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by hsmak on 4/15/15.
 */
public class WorkerWithCountDownLatch implements Runnable {

    CountDownLatch latch;

    List<Integer> list = new ArrayList<>();
    Random random = new Random();

    public WorkerWithCountDownLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {

        try {
            for (int i = 0; i < 1000; i++) {
                Thread.sleep(1);
                list.add(random.nextInt(100));
            }

            System.out.println(Thread.currentThread().getName() +
                    " Decrementing CountDownLatch: " + latch.getCount());


            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public List<Integer> getList() {
        return list;
    }
}

class Waiter implements Runnable{

    CountDownLatch latch;

    public Waiter(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {

        try {
            System.out.println(Thread.currentThread().getName() + " Waiting for CountDownLatch... " + latch.getCount());
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class CountDownLatchRunnerApp {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(2);
        Waiter waiter = new Waiter(latch);
        Thread t = new Thread(waiter);


        //or simply:
        //CyclicBarrier barrier = new CyclicBarrier(2);


        WorkerWithCountDownLatch w1 = new WorkerWithCountDownLatch(latch);
        WorkerWithCountDownLatch w2 = new WorkerWithCountDownLatch(latch);

        long start = System.currentTimeMillis();

        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w2);

        t.start();
        t1.start();
        t2.start();

        t.join();
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
