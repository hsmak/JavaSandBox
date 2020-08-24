package ocp8._util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {
    public static void main(String[] args) throws Throwable {
        String userHome = System.getProperty("user.home");
        System.out.println(userHome);
        printSystemProperties();

        System.out.println(System.getProperty("user.dir"));

        handleException(() -> new IllegalAccessException("For the heck of it"));


    }

    public static void listFiles(String userHome) throws IOException {
        Files.newDirectoryStream(Paths.get(userHome))
                .forEach(i -> System.out.println(i));
    }

    public static void printSystemProperties() {
        System.getProperties()
                .entrySet()
                .forEach(ent -> System.out.println(ent.getKey() + " " + ent.getValue()));
    }

    public static Path createDir(String dirname) throws IOException {
        return Files.createDirectory(Paths.get(dirname));
    }

    public static boolean deleteDir(String dirname) throws IOException {
        return Files.deleteIfExists(Paths.get(dirname));
    }

    public static <T> void handleException(Supplier<T> s, String... optionalMsgs) throws Throwable {
        try {
            s.get();
        } catch (Throwable t) {
            System.err.println(t);
            if (optionalMsgs.length != 0) {
                String joinedMsgs = Stream.of(optionalMsgs).map(i -> Optional.ofNullable(i)).filter(i -> !i.isEmpty()).map(Optional::get).collect(Collectors.joining(" | "));
                System.err.println(joinedMsgs);

                /*
                // notice the casting in "Optional.ofNullable((String) null)"
                Stream.of(Optional.ofNullable("aa"), Optional.ofNullable((String) null), Optional.ofNullable("bb"))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(
                        Collectors.joining(" | ")
                );

                 */
            }
        }
    }
}
