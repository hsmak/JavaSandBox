package streamOps.intermediate.flatmap;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by hsmak on 2/21/16.
 */
public class ConvertStreamOfList {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("Husain", "Hassan", "Adriana");

        strings.stream()
                .map(w -> w.split(""))//each String from the list
                .flatMap(Arrays::stream)//queue into a single stream; i.e. flattening
                .map(String::toLowerCase)
                .sorted()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((k, v) -> System.out.println("[" + k + ":" + v + "]"));
    }
}
