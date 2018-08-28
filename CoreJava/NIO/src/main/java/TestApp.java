import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by hsmak on 5/2/15.
 */
public class TestApp {
    public static void main(String[] args) throws URISyntaxException, IOException {
        Path p = Paths.get(TestApp.class.getResource("test.txt").toURI());
        Files.lines(p)
//                .parallel()
                .map(line -> line.split(" "))
                .flatMap(Arrays::stream)
//                .filter(wd -> wd.startsWith("h") || wd.startsWith("H"))
                .map(wd -> (wd.startsWith("h") || wd.startsWith("H")) ? wd.toUpperCase() : wd)
                .forEach(System.out::println);
    }
}
