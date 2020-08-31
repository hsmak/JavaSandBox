package org.hsmak.letit;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BitwiseComplement {


    public int complementN(int N) {
        String binN = Integer.toBinaryString(N);
        int d = binN.length();

        String r = Stream.generate(() -> 1).limit(d).map(String::valueOf).collect(Collectors.joining(""));
//        r = "0b"+r;
//        binN = "0b"+binN;

        return Integer.valueOf(String.valueOf(Integer.parseInt(binN) ^ Integer.parseInt(r)), 2);
    }
}
