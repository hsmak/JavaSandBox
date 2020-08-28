package org.hsmak;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainApp {
    private int x = 10;

    class Inner {
        private void printMe() {
            System.out.println("hhhhhh: " + x);
        }
    }

    static class StatIn {
        private void printMe() {
            System.out.println("dddddd: " + 2);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {


        MainApp ma = new MainApp();
        MainApp.Inner in = ma.new Inner();
        in.printMe();
        StatIn s = new MainApp.StatIn();
        s.printMe();

        /*String bankCSV = IOUtils.toString(
                new URL("https://s3.amazonaws.com/apache-zeppelin/tutorial/bank/bank.csv"),
                Charset.forName("utf8"));
        System.out.println(bankCSV);*/

        Map<Integer, String> m = Map.of(2, "2", 0, "0", 3, "3", 1, "1");
//        m.forEach((k, v) -> System.out.println());
        System.out.println(m);
        Map<Integer, String> collect = m.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(collect);


        TreeMap<String, String> tm = new TreeMap<>();
        tm.put("a", "ahmed");
        SortedMap<String, String> x = tm.subMap("a", "c");
        System.out.println(x);
        tm.put("b", "bama");
        System.out.println(x);
//        x.put("d", "ada");
        System.out.println(x);
        System.out.println(tm);

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        pq.offer(1);
        pq.add(5);
        pq.add(3);
        System.out.println(pq.poll());
        System.out.println(pq.peek());

        List<? extends Number> l = new ArrayList<Integer>();
        List<Integer> ll = (List<Integer>) l;
        ll.add(123);
        l.forEach(i -> System.out.println(i));

        /*
         * - Super is used in method's arguments and variable/reference declaration
         * - Which means <? super Type> allows to add only this Type and its subtypes; YES subtypes not super types!
         * - The benefit lies at preventing passing a List<Cat> to a method's argument of List<Animal> then adding Dog to it.
         *
         */
        List<? super A> lll = new ArrayList<>();
        lll.add(new B());
        lll.add(new A());
//        lll.add(new Object()); // Which means <? super Type> allows to add only this Type and its subtypes; YES subtypes not super types!

        Comparator<Integer> comparing = Comparator.comparing(i -> i);

        Stream<Integer> empty1 = Stream.<Integer>empty();
        Stream.of(1, 2, 3).filter(i -> i > 4).mapToInt(i -> i).sum();



//        testThreading();
        testParallelStreamWithCustomForkJoinPool();


    }


    public static void testThreading() {
        Runnable r = () -> {
            Stream.iterate(1, i -> i + 1)
                    .limit(3)
                    .forEach(i -> System.out.println(Thread.currentThread().getName() + ", x is " + i));
        };
        new Thread(r, "Fred").start();
        new Thread(r, "Lucy").start();
        new Thread(r, "Ricky").start();

        throwNumberFormatException();
        new Thread(() -> {
            System.out.println("another thread: " + Thread.currentThread().getName());
            throw new IllegalArgumentException();
        }).start();

    }

    public static void testParallelStreamWithCustomForkJoinPool() throws InterruptedException, ExecutionException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        Stream<Integer> parallelPeek = Stream.generate(new Random()::nextInt)
                .parallel()
                .peek(i -> System.out.println(Thread.currentThread().getName()));

        ForkJoinTask<List<Integer>> submit = forkJoinPool.submit(() -> parallelPeek.limit(100).collect(Collectors.toList()));
        submit.get();
//        Thread.sleep(1000);
    }


    public static void throwNumberFormatException() {
        throw new NumberFormatException();

    }

}

class A {

}

class B extends A {

}

class Test<C, T> {
    public <T, R extends C> R method(T t) {
        return null;
    }
}