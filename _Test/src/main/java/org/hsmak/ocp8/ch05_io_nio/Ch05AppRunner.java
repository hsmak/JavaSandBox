package org.hsmak.ocp8.ch05_io_nio;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hsmak.ocp8._util.Utils;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.stream.Stream;


/**
 * Character-Oriented I/O
 */
class ReaderWriterRunner {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {

        StreamOverPathElements();
        String basedir = System.getProperty("user.dir");
        createDeleteFile("Testing.txt");


        PrintWriter printWriter = new PrintWriter(System.out);
        printWriter.println(12334564);
        printWriter.flush();
        printWriter.close();


    }

    public static void StreamOverPathElements() {
        String basedir = System.getProperty("user.dir");
//        createDeleteFile("Testing.txt");
        var pathIter = Paths.get(basedir).iterator();
        Stream.generate(() -> pathIter.hasNext()).takeWhile(i -> i != false).map(i -> pathIter.next()).forEach(System.out::println);
    }

    public static void createDeleteFile(String filename) throws IOException, InterruptedException {
        Path filepath = Paths.get("out", filename);
        Files.deleteIfExists(filepath);

        Files.deleteIfExists(Paths.get("out"));
        Path outdir = Files.createDirectory(Paths.get("out"));

        Files.createFile(filepath);
        System.out.println(filepath.getNameCount());

        System.out.println(filepath.toAbsolutePath().getRoot());

//        Paths.get(ClassLoader.getSystemClassLoader().getResource("Testing.txt").toURI());

        new BufferedInputStream(new FileInputStream(filepath.toString()));

        Files.newBufferedReader(filepath);

        Thread.sleep(5000);
        Files.delete(filepath);
        Files.delete(outdir);
    }
}

/**
 * Byte-Oriented I/O
 */
class InputOutputStreamRunner {
    public static void main(String[] args) {
        System.out.println(123);
    }
}

/**
 * Object-Oriented I/O (Serialization/Deserialization)
 * Instead of Character/Byte orientation
 */
class SerDeserRunner {

    static class Car implements Serializable {


        private String name = "Car";
        private String color = "Red";
        private String model = "Ford";
        private Car car = null; // If instantiated here, it'll blow up the stack "StackOverflow"

        public Car() {
        }

        public Car(String name, String color, String model) {
            this.name = name;
            this.color = color;
            this.model = model;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public Car getCar() {
            return car;
        }

        public void setCar(Car car) {
            this.car = car;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("name", name)
                    .append("color", color)
                    .append("model", model)
                    .append("car", car)
                    .toString();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("SerDeserOnDesk:");
        SerDeserOnDesk();
        System.out.println();

        System.out.println("SerDeserInMemory():");
        SerDeserInMemory();
        System.out.println();
    }

    public static void SerDeserOnDesk() throws IOException, ClassNotFoundException {
        ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("car.bin")); //On Desk
        Car carOut = new Car();
        carOut.setCar(new Car("ww","ww","ww"));
        carOut.setName("AnotherName");
        System.out.println(carOut);
        objOut.writeObject(carOut);

        objOut.flush();
        objOut.close();

        ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("car.bin")); // On Desk
        Car carIn = (Car) objIn.readObject();
        System.out.println(carIn);
    }

    public static void SerDeserInMemory() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();// In Memory
        ObjectOutputStream objOut = new ObjectOutputStream(baos);
        Car carOut = new Car();
        carOut.setName("AnotherName");
        System.out.println(carOut);
        objOut.writeObject(carOut);

        objOut.flush();
        objOut.close();


        byte[] baosBuf = baos.toByteArray();

        System.out.println(baosBuf.length);
        ByteArrayInputStream bais = new ByteArrayInputStream(baosBuf);// In Memory
        ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("car.bin"));
        Car carIn = (Car) objIn.readObject();
        System.out.println(carIn);
    }
}

class SocketRunner {

}

class ZipUnzipRunner {

}

class FileAttributes {
    public static void main(String[] args) throws IOException {
        String dirname = "tempDir";
        Utils.deleteDir(dirname);
        Utils.createDir(dirname);

        System.out.println("Reading Attributes before changing: ");
        readAttributesForDir(dirname);

        System.out.println("Setting Attributes...");
        setAttributesForDir(dirname);

        System.out.println("Reading Attributes after changing: ");
        readAttributesForDir(dirname);

        Utils.deleteDir(dirname);
    }

    private static void readAttributesForDir(String dirname) throws IOException {
        Path dirpath = Paths.get(dirname);

        PosixFileAttributes readAttributes = Files.readAttributes(dirpath, PosixFileAttributes.class);

        System.out.println(readAttributes.group());
        System.out.println(readAttributes.permissions());

    }

    public static void setAttributesForDir(String dirname) throws IOException {
        Path dirpath = Paths.get(dirname);

        PosixFileAttributeView attributeView = Files.getFileAttributeView((dirpath), PosixFileAttributeView.class);
        System.out.println(attributeView.getOwner());
//        attributeView.setGroup();
//        attributeView.setOwner();
        attributeView.setPermissions(PosixFilePermissions.fromString("r--r--r--"));
//        attributeView.setTimes();

    }
}

class DirectorySystemRunner {
    public static void main(String[] args) throws IOException {
        System.out.println("Listing all dir:");
        listDir();
        System.out.println();

        System.out.println("Listing all dir with a glob:");
        listDir("[sSdD]*");
        System.out.println();


//        listDirRecur();
    }

    public static void listDir() throws IOException {
        Path homePath = Paths.get(System.getProperty("user.home"));
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(homePath)) {
            for (Path p : dirStream) {
                System.out.println(p);
            }
        }
    }

    public static void listDir(String glob) throws IOException {
        Path homePath = Paths.get(System.getProperty("user.home"));
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(homePath, glob)) {
            for (Path p : dirStream) {
                System.out.println(p);
            }
        }
    }

    ///////////////////////////////////
    public static void listDirRecur() throws IOException {
        Path homePath = Paths.get(System.getProperty("user.home"));
        Files.walkFileTree(homePath, new MyFileVisitor());
    }

    static class MyFileVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            System.out.println(file);
            return FileVisitResult.CONTINUE;
        }
    }
    ////////////////////////////////////////
}

class PathMatcherRunner {
    public static void main(String[] args) throws IOException {

        Path homePath = Paths.get(System.getProperty("user.home"));

        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.???");

        System.out.println("Listing all files matching the glob:");
        DirectoryStream<Path> paths = Files.newDirectoryStream(homePath);
        for (Path p : paths) {
            if (matcher.matches(p))
                System.out.println(p);
        }
    }
}

class WatchServiceRunner {
    public static void main(String[] args){
        Path homePath = Paths.get(System.getProperty("user.home"));

    }
}