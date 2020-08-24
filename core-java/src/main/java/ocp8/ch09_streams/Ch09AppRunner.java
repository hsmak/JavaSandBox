package ocp8.ch09_streams;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ch09AppRunner {
    public static void main(String[] args) {
        System.out.println(2222);
    }
}

class StreamsRunner {
    public static void main(String[] args) {

        System.out.println("CollectAfterBoxing()");
        CollectAfterBoxing();
        System.out.println();

        System.out.println("CollectAfterUnboxing()");
        CollectAfterUnboxing();
        System.out.println();
    }


    /**
     * boxed()
     * - convert a Stream of primitives to a Stream of their Generic Wrappers
     */
    public static void CollectAfterBoxing() {
        Map<Integer, String> collectedMap = IntStream.range(0, 5).boxed().collect(Collectors.toMap(i -> i, i -> i.toString().concat("s")));
        System.out.println(collectedMap);
    }

    /**
     * mapToInt()/mapToDouble()/.../mapToObject()
     * - convert a Stream of Generic Wrappers to a Stream of their primitives
     */
    public static void CollectAfterUnboxing() {
        int[] intStream = Stream.of(1, 2, 3, 4).mapToInt(i -> i).toArray();
        Arrays.stream(intStream).forEach(System.out::println);
    }

}