package org.hsmak;

import org.apache.commons.io.IOUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MainApp {
    public static void main(String[] args) throws IOException {

        /*String bankCSV = IOUtils.toString(
                new URL("https://s3.amazonaws.com/apache-zeppelin/tutorial/bank/bank.csv"),
                Charset.forName("utf8"));
        System.out.println(bankCSV);

        testArray();

        FileWriter fileWriter = new FileWriter("");*/

        Map<Integer, String> m = Map.of(2,"2", 0, "0", 3, "3", 1, "1");
//        m.forEach((k, v) -> System.out.println());
        System.out.println(m);
        Map<Integer, String> collect = m.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(collect);
    }

    public static void test() {
        System.out.println("ttttttttt");
    }

    public void an() {
        test();

    }

    public static void testArray() {
        String[] is = new String[1];
        int[] is2;
        System.out.println(is[0]);
    }
}

class A {

}

abstract class B extends A {

}