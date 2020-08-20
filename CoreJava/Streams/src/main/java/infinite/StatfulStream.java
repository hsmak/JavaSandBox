package infinite;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by hsmak on 11/17/16.
 *
 * Stream.generate() is stateless but
 * you can make it statefull by supplying an implementation of Supplier interface that maintains a state,
 * such as the {@link FibonacciMaker}
 *
 * Stream.iterate() is fundamentally stateful!
 * you can't take advantage of parallel() feature!
 */
public class StatfulStream {

    public static void main(String[] args){
        createFibStream().limit(10).forEach(System.out::println);
        createStreamUsingIterate().limit(10).forEach(System.out::println);
    }

    /*
     * Instead of lambda expression we created an implementation of a supplier
     * FibonaccieMaker class maintains state in the method get()
     */
    public static Stream<Long> createFibStream() {
        return Stream.generate(new FibonacciMaker());
    }

    /*
     * Stream.iterate() is fundamentally stateful!
     * you can't use parallel!
     */
    public static Stream<Integer> createStreamUsingIterate() {

        Random random = new Random();
        int nextInt = random.nextInt(5);
        System.out.println("Random Seed value: " + nextInt);

        return
                Stream
                        .iterate(nextInt, i -> i * 2 );

    }

}

/*
 * Stateful Supplier; i.e it maintains state
 * get() is changing instance variables
 */
class FibonacciMaker implements Supplier<Long> {

    private long previous = 0;
    private long current = 1;


    @Override
    public Long get() {

        long next = previous + current;

        previous = current;
        current = next;

        //previous will be current for next iteration
        return previous;
    }
}