package org.hsmak.ocp8.ch06_generics_collections;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ch06AppRunner {
    public static void main(String[] args) {

        Map<Integer, String> collect = IntStream.range(0, 5).boxed().collect(Collectors.toMap(i -> i, i -> i.toString().concat("s")));
        System.out.println(collect);
    }
}

class StreamWithBoxedUnboxedRunner{

}