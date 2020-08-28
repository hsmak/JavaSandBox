package _ocp8.ch09_streams;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static _ocp8.Utils.printClassNameWithStackWalker;
import static _ocp8.Utils.printMethodNameWithStackWalker;

/*
 * Observations:
 *  - Primitive "char" is "int"
 *  - "stream of char" is actually a "stream of int"
 *  - "String#chars()" and "String#codePoints()" return "IntStream" type
 *  - Use the Generic mapToObj() to convert "int" into the wrapper Character.
 *      - In order to operate on "char" as "char", always transform via mapToObj() and cast the "int" to "char/Character"
 */
class StreamingOverChars {
    public static void main(String[] args) {
        System.out.println("--- Class: StreamingChars ---");

        streamVia_StringChars();
        System.out.println();

        streamVia_CodePoints();
        System.out.println();

        System.out.println();
    }

    public static void streamVia_StringChars() {
        System.out.println("-- streamVia_StringChars --");

        String str = "This String is to be streamed";
        IntStream charsIntStream = str.chars(); // will return IntStream object

        // 1. Implicit Boxing into Stream<Character>

        // 1.1. Via method reference
        str.chars()
                .mapToObj(Character::toChars)
                .forEach(System.out::print);
        System.out.println();

        // 1.2. Via lambda expressions by casting int to char

        str.chars()
                .mapToObj(i -> Character.valueOf((char) i))
                .forEach(System.out::print);
        System.out.println();

        str.chars()
                .mapToObj(i -> (char) i)
                .forEach(System.out::print);
        System.out.println();

        str.chars()
                .mapToObj(i -> new StringBuilder().appendCodePoint(i))
                .forEach(System.out::print);
        System.out.println();
    }

    public static void streamVia_CodePoints() {
        System.out.println("-- streamVia_CodePoints --");

        String str = "This String is to be streamed";
        IntStream codePointsIntStream = str.codePoints(); // will return IntStream object

        // 1. Implicit Boxing into Stream<Character>

        // 1.1. Via method reference
        str.codePoints()
                .mapToObj(Character::toChars)
                .forEach(System.out::print);
        System.out.println();

        // 1.2. Via lambda expressions by casting int to char

        str.codePoints()
                .mapToObj(i -> Character.valueOf((char) i))
                .forEach(System.out::print);
        System.out.println();

        str.codePoints()
                .mapToObj(i -> (char) i)
                .forEach(System.out::print);
        System.out.println();

        str.codePoints()
                .mapToObj(i -> new StringBuilder().appendCodePoint(i))
                .forEach(System.out::print);
        System.out.println();
    }
}

class StreamBoxingUnboxing {
    public static void main(String[] args) {
        printClassNameWithStackWalker(1);

        CollectAfterBoxing();
        System.out.println();

        CollectAfterUnboxing();
        System.out.println();
    }

    /*
     * boxed()
     *  - convert a Stream of primitives to a Stream of their Generic Wrappers
     */
    public static void CollectAfterBoxing() {
        printMethodNameWithStackWalker(1);

        Map<Integer, String> collectedMap = IntStream.range(0, 5).boxed().collect(Collectors.toMap(i -> i, i -> i.toString().concat("s")));
        System.out.println(collectedMap);
    }

    /*
     * mapToInt()/mapToDouble()/.../mapToObject()
     *  - convert a Stream of Generic Wrappers to a Stream of their primitives
     */
    public static void CollectAfterUnboxing() {
        printMethodNameWithStackWalker(1);

        int[] intStream = Stream.of(1, 2, 3, 4).mapToInt(i -> i).toArray();
        Arrays.stream(intStream).forEach(System.out::println);
    }
}

class StreamingOverCollections {
    public static void main(String[] args) {
        printClassNameWithStackWalker(1);

        List.of(1, 2, 3, 4).stream().forEach(System.out::println);
    }
}

class StreamingOverArrays {

    public static void main(String[] args) {
        printClassNameWithStackWalker(1);
        System.out.println("--- StreamingOverArrays ---");

        System.out.println("--- Primitive ---");
        Arrays.stream(new int[]{1, 2, 3}).forEach(System.out::println);// same as IntStream.of()
        System.out.println();

        System.out.println("--- Generic ---");
        Arrays.stream(new Integer[]{1, 2, 3}).forEach(System.out::println); // same as Stream.of()
        System.out.println();
    }
}

class CreatingStreamViaFactoryMethod_Of {
    public static void main(String[] args) {
        System.out.println("--- CreatingStreamViaFactoryMethod_Of ---");
        System.out.println("--- Primitive ---");
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        IntStream.of(1, 2, 3).forEach(System.out::println);
        System.out.println();

        DoubleStream.of(new double[]{1.1, 2.3}).forEach(System.out::println);
        DoubleStream.of(1.1, 2.3).forEach(System.out::println);
        System.out.println();

        System.out.println("--- Generic ---");
        Stream.of(1, 2, 3).forEach(System.out::println);
        Stream.of(new Integer[]{1, 2, 3}).forEach(System.out::println);
        System.out.println();
    }
}

/*
 * Observations:
 *  - You can't stream over a "Map" because Map is not under "Collection interface"
 *  - You have to retrieve a set of the "Map.Entry" via "map#entrySet()"
 */
class StreamingOverMaps {
    public static void main(String[] args) {
        Map<Integer, Integer> map = Map.of(
                1, 100,
                2, 22,
                3, 30,
                4, 14);
        // Streaming
        map.entrySet().stream().forEach(System.out::println);

        System.out.println("--- Sorting ---");
        System.out.println("-- 1. ByValue - Ascending --");
        map.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
        System.out.println();

        System.out.println("-- 2.1 ByValue - Reverse Sorting --");
        map.entrySet().stream().sorted(Map.Entry.comparingByValue((v1, v2) -> v2 - v1)).forEach(System.out::println);
        System.out.println();

        System.out.println("-- 2.2 ByValue - Reverse Sorting --");
        map.entrySet().stream().sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed()).forEach(System.out::println);
        System.out.println();
    }
}

class StreamWithNIO2 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        System.out.println("--- Class: StreamWithNIO2 ---");
        URL txtFileURL = ClassLoader.getSystemResource("test.txt");
        Path txtFilePath = Paths.get(txtFileURL.toURI());
        System.out.println("--- File Lines Streaming ---");
        Stream<String> linesStream = Files.lines(txtFilePath);
        linesStream.forEach(System.out::println);
        System.out.println();
    }
}

class TerminalOperationReduce {

    public static void main(String[] args) {
        printClassNameWithStackWalker(1);

        Optional<String> reduceStr = List.of(1, 2, 3, 4).stream().map(String::valueOf).reduce((a, b) -> a + " " + b);
        System.out.println(reduceStr.get());

        System.out.println("--- Primitive ---");
        Stream<Integer> integerStream = List.of(1, 2, 3, 4).stream();
        IntStream intStream = integerStream.mapToInt(i -> i);
        OptionalInt optionalInt = intStream.reduce(Integer::max); // optional of Primitiv
        optionalInt.ifPresent(System.out::println);
        System.out.println();

        System.out.println("--- Generic ---");
        Stream<Integer> integerStream1 = List.of(1, 2, 3, 4).stream();
        Optional<Integer> optionalInteger = integerStream1.reduce(Integer::max); // Optional of Generic
        optionalInteger.ifPresent(System.out::println);
        System.out.println();

    }
}

/*
 * As you collect:
 *  - you can Group values together based on a Function (returns a Map)                 -> Collectors.groupingBy()
 *  - Partition values into true/false partitions based on a Predicate (returns a Map)  -> Collectors.partitioningBy()
 *  - Map values into other values using a Function (returns a Collector)               -> Collectors.mapping()
 *  - Join values into a String                                                         -> Collectors.joining()
 *  - Count values as you collect                                                       -> Collectors.counting()
 */
class CollectingOps {
    public static void main(String[] args) {

    }
}

class OptionalOps {
    public static void main(String[] args) {
        printClassNameWithStackWalker(1);

        Optional.ofNullable((String) null).orElse("te");

        wrapValuesAroundOptionalAndEliminateNulls();

        System.out.println("--- Primitive ---");
        OptionalInt optionalInt = OptionalInt.of(1);
        IntStream intStream = optionalInt.stream();
        intStream.forEach(System.out::println);

        System.out.println("-- Generic ---");
        Optional<Integer> optionalInteger = Optional.of(1);
        Stream<Integer> integerStream = optionalInteger.stream();
        integerStream.forEach(System.out::println);
    }

    public static void wrapValuesAroundOptionalAndEliminateNulls() {
        printMethodNameWithStackWalker(1);

        String[] optionalMsgs = {null, "msg1", null, "msg2", null};
        String joinedMsgs = Stream.of(optionalMsgs)
                .map(i -> Optional.ofNullable(i))
                .filter(i -> !i.isEmpty())
                .map(Optional::get)
                .collect(Collectors.joining(" | "));
        System.out.println(joinedMsgs);
        System.out.println();
    }
}