package intermediate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hsmak on 4/30/15.
 * <p>
 * Link: http://tutorials.jenkov.com/java-collections/streams.html
 */
public class TestApp {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("apple", "elephant", "ant", "bubble");

        long count = list.stream()
                .filter(item -> item.startsWith("a"))
                .count();
        System.out.println("Count: " + count);
        System.out.println("==========================");

        System.out.println("Testing ForEach:");
        list.stream()
                .filter(item -> item.startsWith("a"))
//                .forEach(e -> System.out.println(e));
                .forEach(System.out::println);
        System.out.println("==========================");

        String max = list.stream()
                .max((o1, o2) -> o1.compareTo(o2)).get();
        System.out.println("Max: " + max);
        System.out.println("==========================");

        String min = list.stream()
                .min(Comparator.comparing(item -> item)).get();
        System.out.println("Min: " + min);
        System.out.println("==========================");

        list.stream()
                .filter(item -> item.startsWith("a"))
                .map(item -> item.toUpperCase())//String::toUpperCase
                .forEach(item -> System.out.println(item));
        System.out.println("==========================");

        List<String> collected = list.stream()
                .filter(item -> item.startsWith("a"))
                .collect(Collectors.toList());
        System.out.println(collected);
        System.out.println("==========================");

        String reduced = list.stream()
                .filter(item -> item.startsWith("a"))
                .reduce("", (acc, item) -> acc + " " + item);
        System.out.println(reduced);
        System.out.println("==========================");

        /**
         * Link: www.oracle.com/technetwork/articles/java/architect-streams-pt2-2227132.html
         */
        List<String> stringList = Arrays.asList("a b c d a c a f g f z q x z", "a b c d a c a f g f z q x z");
        Stream<String> s = stringList.stream();
//        Stream<String> s = Stream.of("a b c d a c a f g f z q x z");
        s.map(line -> line.split(" "))
                .flatMap(Arrays::stream)//((array) -> Arrays.stream(array))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((k, v) -> System.out.println("[" + k + ":" + v + "]"));
        System.out.println("==========================");
//        System.out.println(s);
        //StringBuffer sb = new StringBuffer(s);


    }
}
