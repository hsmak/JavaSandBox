package org.hsmak.ocp8._util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils{
    public static void main(String[] args){
        String userHome = System.getProperty("user.home");
        System.out.println(userHome);
        printSystemProperties();

        System.out.println(System.getProperty("user.dir"));


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
}
