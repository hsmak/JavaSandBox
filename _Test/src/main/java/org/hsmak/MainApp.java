package org.hsmak;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

    public static void main(String[] args) throws IOException {


        MainApp ma = new MainApp();
        MainApp.Inner in = ma.new Inner();
        in.printMe();
        StatIn s = new MainApp.StatIn();
        s.printMe();

        /*String bankCSV = IOUtils.toString(
                new URL("https://s3.amazonaws.com/apache-zeppelin/tutorial/bank/bank.csv"),
                Charset.forName("utf8"));
        System.out.println(bankCSV);

        testArray();

        FileWriter fileWriter = new FileWriter("");*/

        Map<Integer, String> m = Map.of(2, "2", 0, "0", 3, "3", 1, "1");
//        m.forEach((k, v) -> System.out.println());
        System.out.println(m);
        Map<Integer, String> collect = m.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(collect);
        Path f = Paths.get("./test.txt");
        Files.deleteIfExists(f);
        Path file = Files.createFile(f);
        OutputStream outputStream = Files.newOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        bufferedOutputStream.write("test".getBytes());
        bufferedOutputStream.write("\n".getBytes());
//        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        System.out.println(Files.readAttributes(f, PosixFileAttributes.class).isOther());

        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(1205);
        ts.add(1505);
        ts.add(1545);
        ts.add(1830);
        ts.add(1930);
        System.out.print("ts.headSet(1600).last(): ");
        System.out.println(ts.headSet(1600).last());
        System.out.print("ts.lower(1600): ");
        System.out.println(ts.lower(1600));

        System.out.println(ts.pollFirst());
        System.out.println(ts.tailSet(1600).first());
        System.out.println(ts.higher(1600));

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

        List<? extends Number> l = new ArrayList<>();
        List<Integer> ll = (List<Integer>) l;
        ll.add(123);
        l.forEach(i -> System.out.println(i));

        List<? super A> lll = new ArrayList<>();
        lll.add(new B());
        Comparator<Integer> comparing = Comparator.comparing(i -> i);

        Stream<Integer> empty1 = Stream.<Integer>empty();
        Stream.of(1, 2, 3).filter(i -> i > 4).mapToInt(i -> i).sum();

        Runnable r = () -> {
            Stream.iterate(1, i -> i + 1)
                    .limit(3)
                    .forEach(i -> System.out.println(Thread.currentThread().getName() + ", x is " + i));
        };
        new Thread(r, "Fred").start();
        new Thread(r, "Lucy").start();
        new Thread(r, "Ricky").start();

        Optional.ofNullable((String)null).orElse("te");

//        new Thread(() -> {
//            System.out.println("another thread: " + Thread.currentThread().getName());
//            throw new IllegalArgumentException();
//        }).start();
//        an();

    }

    public static void test() {
        System.out.println("ttttttttt");
        throw new NumberFormatException();
    }

    public static void an() {
        test();

    }

    public static void testArray() {
        String[] is = new String[1];
        int[] is2;
        System.out.println(is[0]);
    }
}

class A {

}

class B extends A {

}

class Test<C, T>{
    public <T, R extends C> R method(T t){
        return null;
    }
}