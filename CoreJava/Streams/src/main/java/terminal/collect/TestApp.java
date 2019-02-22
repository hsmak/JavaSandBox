package terminal.collect;

import intermediate.Person;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

/**
 * Created by hsmak on 5/6/15.
 */
public class TestApp {
    public static void main(String[] args) {

        // Simple comparison
        Stream.of("A", "B").map(String::new).collect(Collectors.toList()).forEach(out::println);
        Stream.of("A", "B").map(String::new).collect(ArrayList::new, ArrayList::add, ArrayList::addAll).forEach(out::println);

        //advanced
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));
        System.out.println();

        /**
         * Collect is an extremely useful terminal operation to transform the elements of the stream into a different kind of result, e.g. a List, Set or Map.
         * Collect accepts a Collector which consists of four different operations: a supplier, an accumulator, a combiner and a finisher
         */
        List<Person> filtered =
                persons.stream()
                        .filter(p -> p.name.startsWith("P"))
                        .collect(Collectors.toList());
        System.out.println(filtered);    // [Peter, Pamela]
        System.out.println();


        //The next example groups all persons by age:
        Map<Integer, List<Person>> personsByAge =
                persons.stream()
                        .collect(Collectors.groupingBy(p -> p.age));

        personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));

        System.out.println();


        /**
         * Collectors are extremely versatile.
         * You can also create aggregations on the elements of the stream, e.g. determining the average age of all persons
         */
        Double averageAge =
                persons.stream()
                        .collect(Collectors.averagingInt(p -> p.age));
        System.out.println(averageAge);     // 19.0
        System.out.println();


        //The next example joins all persons into a single string:
        String phrase = persons
                .stream()
                .filter(p -> p.age >= 18)
                .map(p -> p.name)
                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));
        System.out.println(phrase);
        System.out.println();
        // In Germany Max and Peter and Pamela are of legal age.


        /**
         * In order to transform the stream elements into a map, we have to specify how both the keys and the values should be mapped.
         * Keep in mind that the mapped keys must be unique, otherwise an IllegalStateException is thrown.
         * You can optionally pass a merge function as an additional parameter to bypass the exception
         */
        Map<Integer, String> map = persons
                .stream()
                .collect(Collectors.toMap(
                        p -> p.age,
                        p -> p.name,
                        (name1, name2) -> name1 + ";" + name2));
        System.out.println(map);
        System.out.println();
        // {18=Max, 23=Peter;Pamela, 12=David}


        /**
         * Now that we know some of the most powerful built-in collectors, let's try to build our own special collector.
         * We want to transform all persons of the stream into a single string
         * consisting of all names in upper letters separated by the | pipe character.
         * In order to achieve this we create a new collector via Collector.of().
         * We have to pass the four ingredients of a collector: a supplier, an accumulator, a combiner and a finisher.
         */
        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner(" | "),          // supplier
                        (j, p) -> j.add(p.name.toUpperCase()),  // accumulator
                        (j1, j2) -> j1.merge(j2),               // combiner
                        StringJoiner::toString);                // finisher
        String names = persons
                .stream()
                .collect(personNameCollector);
        System.out.println(names);  // MAX | PETER | PAMELA | DAVID
        System.out.println();


    }
}
