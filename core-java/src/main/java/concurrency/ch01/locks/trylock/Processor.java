package concurrency.ch01.locks.trylock;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ${user.name} on 4/16/2015.
 */
public class Processor {

    LinkedList<Integer> list = new LinkedList<Integer>();

    private final static int LIMIT = 10;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    public void produce() throws InterruptedException {

        int value = 0;
        Random random = new Random();

        while (true) {// However, busy loop is CPU intensive!!

            if (lock.tryLock()) {
                try {//always use try/finally to make sure you unlock in case an Exception occurs

                    while (list.size() == LIMIT) {
                        condition.await();
                    }

                    list.add(value++);

                } finally {// cleanup and unlocking. This is useful in case an Exception occurs

                    condition.signal();
                    lock.unlock();

                }
            }

            /**
             * This sleep is necessary to avoid taking CPU resources due to the busy loop
             *  - since tryLock is non-blocking
             *
             * Also, can be used to control the rate at which producer is producing. Shall we separate them into 2 sleeps?
             */
            Thread.sleep(random.nextInt(200));
        }
    }

    public void consume() throws InterruptedException {

        Random random = new Random();

        while (true) {

            if (lock.tryLock()) {
                try {//always use try/finally to make sure you unlock in case an Exception occurs

                    while (list.isEmpty()) {
                        condition.await();
                    }

                    System.out.print("List size is: " + list.size());
                    int value = list.removeFirst();
                    System.out.println("; the value is: " + value);

                } finally {// cleanup and unlocking. This is useful in case an Exception occurs

                    condition.signal();
                    lock.unlock();

                }
            }

            /**
             * This sleep is necessary to avoid taking CPU resources due to the busy loop
             *  - since tryLock is non-blocking
             *
             * Also, can be used to control the rate at which consumer is consuming. Shall we separate them into 2 sleeps?
             */
            Thread.sleep(random.nextInt(200));
        }
    }
}

class TestApp {

    public static void main(String[] args) throws InterruptedException {

        final Processor processor = new Processor();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}