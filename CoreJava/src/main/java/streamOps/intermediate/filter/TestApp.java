package streamOps.intermediate.filter;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by hsmak on 5/6/15.
 * <p>
 * Link:
 * http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 */
public class TestApp {
    public static void main(String[] args) {

        //intermediate operations will only be executed when a terminal operation is present
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                });
        System.out.println();

        /**
         * The order of the result might be surprising.
         * A naive approach would be to execute the operations horizontally one after another on all elements of the stream.
         * But instead each element moves along the chain vertically.
         * The first string "d2" passes filter then forEach, only then the second string "a2" is processed.
         */
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));
        System.out.println();


        /**
         * The operation anyMatch returns true as soon as the predicate applies to the given input element.
         * This is true for the second element passed "A2".
         * Due to the vertical execution of the stream chain, map has only to be executed twice in this case.
         * So instead of mapping all elements of the stream, map will be called as few as possible.
         */
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });
        System.out.println();


        //order matters
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));
        System.out.println();


        /**
         * We can greatly reduce the actual number of executions
         * if we change the order of the operations, moving filter to the beginning of the chain:
         */
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
        System.out.println();

        /**
         * Sorting is a special kind of intermediate operation.
         * It's a so called stateful operation since in order to sort a collection of elements you have to maintain state during ordering
         */
        Stream.of("d2", "a2", "b1", "b3", "c")
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
        System.out.println();


        /**
         * First, the sort operation is executed on the entire input collection.
         * In other words sorted is executed horizontally.
         * So in this case sorted is called eight times for multiple combinations on every element in the input collection.
         *
         * Once again we can optimize the performance by reordering the chain:
         */
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
        System.out.println();

        Stream<String> stream =
                Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        stream.anyMatch(s -> true);    // ok
        //stream.noneMatch(s -> true);   // exception
        System.out.println();


        /**
         * To overcome this limitation we have to to create a new stream chain for every terminal operation we want to execute,
         * e.g. we could create a stream supplier to construct a new stream with all intermediate operations already set up:
         */
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s -> true);   // ok
        streamSupplier.get().noneMatch(s -> true);  // ok
        System.out.println();




    }
}
