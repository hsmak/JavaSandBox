package infinite;

import java.util.Random;
import java.util.stream.Stream;

/**
 * Created by hsmak on 11/17/16.
 *
 * Stream.generate() is stateless but
 * you can make it statefull by supplying an implementation of Supplier interface that maintains a state,
 * such as the {@link FibonacciMaker}
 *
 * Stream.iterate() is fundamentally stateful!
 * you can't use parallel!
 */
public class StatelessStream {

    public static void main(String[] args){
        createStreamUsingGenerate().limit(10).forEach(System.out::println);
    }

    public static Stream<Integer> createStreamUsingGenerate(){

        Random random = new Random();
        int nextInt = random.nextInt(5);
        System.out.println("Random Seed value: " + nextInt);

        return Stream.generate(() -> nextInt * 2);
    }



}
