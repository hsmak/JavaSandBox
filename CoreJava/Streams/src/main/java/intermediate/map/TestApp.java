package intermediate.map;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by hsmak on 5/6/15.
 */
public class TestApp {
    public static void main(String[] args){

        Arrays.asList("a1", "a2", "a3")
                .stream()
                .findFirst()
                .ifPresent(System.out::println);  // a1
        System.out.println();

        Stream.of("a1", "a2", "a3")
                .findFirst()
                .ifPresent(System.out::println);  // a1
        System.out.println();

        IntStream.range(1, 4)
                .forEach(System.out::println);
        System.out.println();

        Arrays.stream(new int[] {1, 2, 3})
                .map(n -> 2 * n + 1)
                .average()
                .ifPresent(System.out::println);  // 5.0
        System.out.println();

        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);  // 3
        System.out.println();

        IntStream.range(1, 4)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);
        System.out.println();

        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);
        System.out.println();



    }
}
