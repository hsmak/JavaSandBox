package streamOps.intermediate.reduce;


import streamOps.intermediate.Person;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hsmak on 5/6/15.
 * Link:
 *      http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 */
public class TestApp {
    public static void main(String[] args){
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));
        System.out.println();

        /**
         * The reduction operation combines all elements of the stream into a single result.
         * Java 8 supports three different kind of reduce methods. The first one reduces a stream of elements to exactly one element of the stream.
         * Let's see how we can use this method to determine the oldest person:
         */
        persons
                .stream()
                .reduce((p1, p2) -> p1.age > p2.age
                                    ? p1
                                    : p2)
                .ifPresent(System.out::println);    // Pamela
        System.out.println();


        /**
         * The second reduce method accepts both an identity value and a BinaryOperator accumulator.
         * This method can be utilized to construct a new Person with the aggregated names and ages from all other persons in the stream:
         */
        Person result =
                persons
                        .stream()
                        .reduce(new Person("", 0), (p1, p2) -> {
                            p1.age += p2.age;
                            p1.name += p2.name;
                            return p1;
                        });
        System.out.format("name=%s; age=%s", result.name, result.age);
        System.out.println();
        // name=MaxPeterPamelaDavid; age=76


        /**
         * The third reduce method accepts three parameters:
         * an identity value, a BiFunction accumulator and a combiner function of type BinaryOperator.
         * Since the identity values type is not restricted to the Person type,
         * we can utilize this reduction to determine the sum of ages from all persons:
         */
        Integer ageSum = persons
                .stream()
                .reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);
        System.out.println(ageSum);  // 76
        System.out.println();


        /**
         * The combiner never gets called? Executing the same stream in parallel will lift the secret:
         */
        ageSum = persons
                .stream()
                .reduce(0,
                        (sum, p) -> {
                            System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
                            return sum += p.age;
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
                            return sum1 + sum2;
                        });
        System.out.println();

    }
}
