package _ocp8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Utils {
    public static void main(String[] args) {
        String userHome = System.getProperty("user.home");
        System.out.println(userHome);
//        printSystemProperties();

        System.out.println(System.getProperty("user.dir"));

        handleException(() -> {
            throw new IllegalArgumentException("For the heck of it");
        }, "msg1", null, "msg2", null);


    }

    public static Stream<String> getSlidingStream(char[] chars, int size, int step) {
        return IntStream.range(0, (int) Math.ceil(chars.length / (double)step))
                .map(z -> z * step)
                .mapToObj(z -> Arrays.copyOfRange(chars, z, Math.min(z + size, chars.length)))
                .map(cc -> String.valueOf(cc));
    }

    public static void printClassNameViaStackWalker(int skip) {
        String className = StackWalker.getInstance().walk(stream -> stream.skip(skip).findFirst()).get().getClassName();
        System.out.println(String.format("--- Class: %s ---", className));
        System.out.println();
    }

    public static void printMethodNameViaStackWalker(int skip) {
        String methodName = StackWalker.getInstance().walk(stream -> stream.skip(skip).findFirst()).get().getMethodName();
        System.out.println(String.format("--- %s() ---", methodName));
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

    public static <T> void handleException(Supplier<T> s, String... optionalMsgs) {
        try {
            s.get();
        } catch (Throwable t) {
            System.err.println(t);
            if (optionalMsgs.length != 0) {

//                String joinedMsgs = Stream.of(optionalMsgs).map(i -> Optional.ofNullable(i)).filter(i -> !i.isEmpty()).map(Optional::get).collect(Collectors.joining(" | "));

                /*
                 * In Java,
                 *      - Optional is a MONAD!! Just as a Stream is a MONAD!!
                 *      - It has flatMap(); which will filter out the empty Optionals automatically
                 *      - Because Monads have the identity
                 *      - Optional.empty is the identity for the Optional Monad
                 */
                String joinedMsgs = Stream.of(optionalMsgs).map(i -> Optional.ofNullable(i)).flatMap(Optional::stream).collect(Collectors.joining(" | "));
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
