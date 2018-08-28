package intermediate.flatmap;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hsmak on 2/21/16.
 */
public class CombineTwoStreams {
    public static void main(String[] args){
        List<Integer> integerList01 = Arrays.asList(1, 2, 3);
        List<Integer> integerList02 = Arrays.asList(4, 5);

        integerList01.stream()
                .flatMap(i -> integerList02.stream()
                                            .map(j -> new int[]{i, j}))
                .forEach(in -> System.out.println("("+in[0] + "," + in[1]+")"));
    }
}
