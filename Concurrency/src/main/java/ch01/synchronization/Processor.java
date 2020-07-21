package ch01.synchronization;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by u308908 on 4/16/2015.
 */
public class Processor {

    LinkedList<Integer> list = new LinkedList<>();

    private final static int LIMIT = 10;

    Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;
        Random random = new Random();
        while (true) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
            Thread.sleep(random.nextInt(200));
        }
    }

    public void consume() throws InterruptedException {

        Random random = new Random();

        while (true) {
            synchronized (lock) {

                while (list.isEmpty()) {
                    lock.wait();
                }

                System.out.print("List size is: " + list.size());
                int value = list.removeFirst();
                System.out.println("; the value is: " + value);
                lock.notify();
            }

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