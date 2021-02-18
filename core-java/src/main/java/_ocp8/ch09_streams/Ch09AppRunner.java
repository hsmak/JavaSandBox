package _ocp8.ch09_streams;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static _ocp8.Utils.printClassNameViaStackWalker;
import static _ocp8.Utils.printMethodNameViaStackWalker;

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

        printClassNameViaStackWalker(1);

        streamVia_StringChars();
        System.out.println();

        streamVia_CodePoints();
        System.out.println();

        System.out.println();
    }

    public static void streamVia_StringChars() {
        printMethodNameViaStackWalker(1);

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
        printMethodNameViaStackWalker(1);

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
        printClassNameViaStackWalker(1);

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
        printMethodNameViaStackWalker(1);

        Map<Integer, String> collectedMap = IntStream.range(0, 5).boxed().collect(Collectors.toMap(i -> i, i -> i.toString().concat("s")));
        System.out.println(collectedMap);
    }

    /*
     * mapToInt()/mapToDouble()/.../mapToObject()
     *  - convert a Stream of Generic Wrappers to a Stream of their primitives
     */
    public static void CollectAfterUnboxing() {
        printMethodNameViaStackWalker(1);

        int[] intStream = Stream.of(1, 2, 3, 4).mapToInt(i -> i).toArray();
        Arrays.stream(intStream).forEach(System.out::println);
    }
}

class StreamingOverList {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);

        List.of(1, 2, 3, 4).stream().forEach(System.out::println);
    }
}

class StreamingOverArrays {

    public static void main(String[] args) {
        printClassNameViaStackWalker(1);
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
        printClassNameViaStackWalker(1);

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
        printClassNameViaStackWalker(1);

        Map<Integer, Integer> map = Map.of(
                1, 100,
                2, 22,
                3, 30,
                4, 14);
        // Streaming
        map.entrySet().stream().forEach(System.out::println);

        System.out.println("--- Sorting ---");
        System.out.println("-- 1.1 ByValue - Ascending --");
        map.entrySet().stream().sorted((e1, e2) -> e1.getValue() - e2.getValue()).forEach(System.out::println);
        System.out.println();
        System.out.println("-- 1.2 ByValue - Ascending --");
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
        printClassNameViaStackWalker(1);

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
        printClassNameViaStackWalker(1);

        Optional<String> reduceStr = List.of(1, 2, 3, 4).stream().map(String::valueOf).reduce((a, b) -> a + " " + b);
        System.out.println(reduceStr.get());

        System.out.println("--- Primitive ---");
        Stream<Integer> integerStream = List.of(1, 2, 3, 4).stream(); // This is a Generic Stream<Integer>
        IntStream intStream = integerStream.mapToInt(i -> i); // convert to Primitive IntStream
        OptionalInt optionalInt = intStream.reduce(Integer::max); // Optional of Primitive OptionalInt
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
 * Observation:
 *  - Collect() is a ReduceOp
 *  - Collectors work with Generic collections not primitive construct
 *      - Therefore, Collectors won't work with IntStream, IntPredicate, DoubleStream, etc
 *
 * As you collect:
 *  - you can Group values together based on a Function (returns a Map)                 -> Collectors.groupingBy()
 *  - Partition values into true/false partitions based on a Predicate (returns a Map)  -> Collectors.partitioningBy()
 *  - Map values into other values using a Function (returns a Collector)               -> Collectors.mapping()
 *  - Join values into a String                                                         -> Collectors.joining()
 *  - Count values as you collect                                                       -> Collectors.counting()
 */
class CollectingOps {

    public static final List<Person> PERSONS = List.of(
            new Person(20, "Alice"),
            new Person(20, "Tim"),
            new Person(30, "Tiff"),
            new Person(30, "Bob"),
            new Person(25, "Jess"),
            new Person(25, "John"));

    public static void main(String[] args) {
        printClassNameViaStackWalker(1);

        collectViaCollectors_ToList_ToSet_ToMap();

        collectViaCollectors_GroupingBy();
        collectViaCollectors_PartitioningBy();
        collectViaCollectors_Mapping();
        collectViaCollectors_Joining_MaxBy_MinBy();
        collectViaCollectors_Counting();
    }

    private static void collectViaCollectors_ToList_ToSet_ToMap() {
        printMethodNameViaStackWalker(1);

        findAllPrimesViaPrimitiveStreams(20);
        findAllPrimesViaGenericStreams(20);

        collectToList();
        collectToSet();
        collectToArrayListViaToCollection();
        collectToArrayListViaToCollection2();
        collectToMap();

        System.out.println();
    }

    private static void collectToMap() {
        printMethodNameViaStackWalker(1);

        Map<String, Integer> CollectedToMap = PERSONS.stream().collect(Collectors.toMap(Person::getName, Person::getAge)); // Key must be unique
        System.out.println(CollectedToMap);
    }

    private static void collectToArrayListViaToCollection2() {
        printMethodNameViaStackWalker(1);

        Stream<Integer> numsLessThan20 = Stream.of(1, 2, 3, 4, 5).map(i -> i * 5).filter(i -> i < 20);
        ArrayList<Integer> collectArrayList2Primes = numsLessThan20.collect(Collectors.toCollection(ArrayList::new)); // via constructor reference
        System.out.println(collectArrayList2Primes);
    }

    private static void collectToArrayListViaToCollection() {
        printMethodNameViaStackWalker(1);

        Stream<Integer> numsLessThan20 = Stream.of(1, 2, 3, 4, 5).map(i -> i * 5).filter(i -> i < 20);
        ArrayList<Integer> collectArrayListPrimes = numsLessThan20.collect(Collectors.toCollection(() -> new ArrayList<Integer>())); // via regular supplier
        System.out.println(collectArrayListPrimes);
    }

    private static void collectToSet() {
        printMethodNameViaStackWalker(1);

        Stream<Integer> numsLessThan20 = Stream.of(1, 2, 3, 4, 5).map(i -> i * 5).filter(i -> i < 20);
        Set<Integer> collectSetPrimes = numsLessThan20.collect(Collectors.toSet()); // Collect into a set
        System.out.println(collectSetPrimes);
    }

    private static void collectToList() {
        printMethodNameViaStackWalker(1);

        Stream<Integer> numsLessThan20 = Stream.of(1, 2, 3, 4, 5).map(i -> i * 5).filter(i -> i < 20);
        List<Integer> collectListPrimes = numsLessThan20.collect(Collectors.toList()); // there is no guarantee this will generate an ArrayList
        System.out.println(collectListPrimes);
    }

    private static void findAllPrimesViaPrimitiveStreams(int num) {
        printMethodNameViaStackWalker(1);

        // Primitive Predicate and Stream
        IntPredicate isPrime = n -> IntStream
                .iterate(n - 1, i -> i - 1)
                .limit(n - 2).filter(i -> n % i == 0)
                .findAny().isEmpty();
        IntStream intStream = IntStream.range(2, num).filter(isPrime); // Must start from 2
//        intStream.collect(Collectors.toList());// Won't work because Collectors work only with Generic Streams
        int[] ints = intStream.toArray(); // Will emit primitive int[] array
        List<Integer> collectPrimes = Arrays.stream(ints).boxed().collect(Collectors.toList()); // Converting a Primitive stream to a Generic one for the heck of it
        System.out.println(collectPrimes);

        System.out.println();
    }

    private static void findAllPrimesViaGenericStreams(int num) {
        printMethodNameViaStackWalker(1);

        // Generic Predicate and Stream
        Predicate<Integer> isPrime = n -> Stream
                .iterate(n - 1, i -> i - 1)
                .limit(n - 2)
                .filter(i -> n % i == 0)
                .findAny().isEmpty();
        Stream<Integer> allPrimes = Stream.iterate(2, i -> i + 1).limit(num).filter(isPrime); // Must start from 2
        List<Integer> collectPrimes = allPrimes.collect(Collectors.toList());
        System.out.println(collectPrimes);

        System.out.println();
    }

    public static void collectViaCollectors_GroupingBy() {
        printMethodNameViaStackWalker(1);

        Map<Integer, List<Person>> groupingByAge = PERSONS.stream()
                .collect(Collectors.groupingBy(Person::getAge));
        System.out.println(groupingByAge);
        System.out.println();

        Map<Integer, List<Person>> groupingByAge2 = PERSONS.stream()
                .collect(Collectors.groupingBy(p -> p.getAge()));
        System.out.println(groupingByAge2);
        System.out.println();

        Map<Integer, Long> groupingByAgeAndCounting = PERSONS.stream()
                .collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
        System.out.println(groupingByAgeAndCounting);
        System.out.println();

        Map<Integer, List<Person>> groupingByAgeAndExplicitList = PERSONS.stream()
                .collect(Collectors.groupingBy(p -> p.getAge(), Collectors.toList()));
        System.out.println(groupingByAgeAndExplicitList);
        System.out.println();

    }

    /*
     * Specialized form of GroupingBy()
     */
    public static void collectViaCollectors_PartitioningBy() {
        printMethodNameViaStackWalker(1);

        Map<Boolean, List<Person>> partitioningAroundAge25 = PERSONS.stream()
                .collect(Collectors.partitioningBy(p -> p.age < 25));
        System.out.println(partitioningAroundAge25);
        System.out.println();

        Map<Boolean, List<String>> partitioningAroundAge25ThenMappingByName = PERSONS.stream()
                .collect(Collectors.partitioningBy(
                        p -> p.age < 25,
                        Collectors.mapping(
                                Person::getName,
                                Collectors.toList())));// Collectors.mapping() always takes a downstream collector
        System.out.println(partitioningAroundAge25ThenMappingByName);
        System.out.println();


        /*
         * A Function that acts as a predicate. It's really transforming a Person object to a Boolean true/false based on some condition
         * Similar to what Collectors.partitioning() is doing. Though Collectors.partitioning() is taking a Predicate not a Function!
         */
        Function<Person, Boolean> isLessThan25 = p -> {
            if (p.getAge() < 25)
                return true;
            return false;
        };
        Map<Boolean, List<String>> partitioningAroundAge25ViaGroupingThenMappingByName = PERSONS.stream()
                .collect(
                        Collectors.groupingBy(
                                isLessThan25, // passing the Predicate Function
                                Collectors.mapping(
                                        Person::getName,
                                        Collectors.toList()))); // Collectors.mapping() always takes a downstream collector
        System.out.println(partitioningAroundAge25ViaGroupingThenMappingByName);
        System.out.println();


        System.out.println();
    }

    /*
     * Specialized form of GroupingBy()
     */
    public static void collectViaCollectors_Mapping() {
        printMethodNameViaStackWalker(1);

        Map<Integer, List<String>> groupingByAgeThenMappingByName = PERSONS.stream()
                .collect(Collectors
                        .groupingBy(
                                Person::getAge, // Group by Age
                                Collectors.mapping(
                                        Person::getName,// Collect Names (instead of the whole Person object) into a list
                                        Collectors.toList())));// Collectors.mapping() always takes a downstream collector
        System.out.println(groupingByAgeThenMappingByName);
        System.out.println();

        System.out.println();
    }

    /*
     * Collectors.joining() works only with String
     */
    public static void collectViaCollectors_Joining_MaxBy_MinBy() {
        printMethodNameViaStackWalker(1);

        System.out.println(PERSONS.stream().map(Person::getName)
                .collect(
                        Collectors.joining(", ")));
        System.out.println();

        Optional<Person> maxAge = PERSONS.stream().collect(Collectors.maxBy((p1, p2) -> p1.getAge() - p2.getAge()));
        System.out.println(maxAge);
        System.out.println();

        Optional<Person> maxAge2 = PERSONS.stream().collect(Collectors.maxBy(Comparator.comparingInt(Person::getAge)));
        System.out.println(maxAge2);
        System.out.println();

        System.out.println(PERSONS);

        System.out.println();
    }

    public static void collectViaCollectors_Counting() {
        printMethodNameViaStackWalker(1);

        System.out.println(PERSONS.stream()
                .collect(
                        Collectors.counting()));
        System.out.println();

        System.out.println(PERSONS.stream()
                .collect(
                        Collectors.partitioningBy(
                                p -> p.getAge() < 25,
                                Collectors.counting())));
        System.out.println();

        System.out.println();
    }

    static class Person {
        int age;
        String name;

        Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age &&
                    name == person.name;
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, name);
        }

        @Override
        public String toString() {
            return String.format("%s is %d years old", name, age);
        }
    }
}

class OptionalOps {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);

        Optional.ofNullable((String) null).orElse("te");

        wrapValuesAroundOptionalAndEliminateNulls();
        wrapValuesAroundOptionalAndEliminateNullsViaFlatMap();

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
        printMethodNameViaStackWalker(1);

        String[] optionalMsgs = {null, "msg1", null, "msg2", null};
        String joinedMsgs = Stream.of(optionalMsgs)
                .map(i -> Optional.ofNullable(i))
                .filter(i -> !i.isEmpty())
                .map(Optional::get)
                .collect(Collectors.joining(" | "));
        System.out.println(joinedMsgs);
        System.out.println();
    }

    public static void wrapValuesAroundOptionalAndEliminateNullsViaFlatMap() {
        printMethodNameViaStackWalker(1);

        String[] optionalMsgs = {null, "msg1", null, "msg2", null};
        String joinedMsgs = Stream.of(optionalMsgs)
                .map(i -> Optional.ofNullable(i))
//                .filter(i -> !i.isEmpty())
//                .map(Optional::get)
                .flatMap(Optional::stream)// using flatMap() instead
                .collect(Collectors.joining(" | "));
        System.out.println(joinedMsgs);
        System.out.println();
    }

}

class ParallelStreaming {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);
    }
}