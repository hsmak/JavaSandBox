package io_nio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by hsmak on 5/2/15.
 */
public class TestApp {
    public static void main(String[] args) throws URISyntaxException, IOException {
        URL resource = ClassLoader.getSystemClassLoader().getResource("test.txt");
//        URL resource = TestApp.class.getResource("test.txt");
        System.out.println(resource);
        Path p = Paths.get(resource.toURI());

        Files.lines(p)
//                .parallel()
                .map(line -> line.split(" "))
                .flatMap(Arrays::stream)
//                .filter(wd -> wd.startsWith("h") || wd.startsWith("H"))
                .map(wd -> (wd.startsWith("h") || wd.startsWith("H")) ? wd.toUpperCase() : wd)
                .forEach(System.out::println);
    }
}
