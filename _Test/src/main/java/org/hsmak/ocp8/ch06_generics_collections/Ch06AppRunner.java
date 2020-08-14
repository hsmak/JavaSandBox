package org.hsmak.ocp8.ch06_generics_collections;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The Contract when overriding equal() and hasCode():
 */
class EqualHashcodeRunner {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        noTransientVarInEqualAndHash();
    }

    public static void noTransientVarInEqualAndHash() throws IOException, ClassNotFoundException {
        Animal a = new Animal();
        a.name = "Cow";

        Map<Animal, String> mapSer = Map.of(a, "my animal");

        System.out.println("Serializing...");
        System.out.println(mapSer.get(new Animal("Cow")));
        //Serialize the Map that as the object of transient var
        ByteArrayOutputStream byteArrOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(byteArrOut);
        objOut.writeObject(mapSer);
        System.out.println();

        System.out.println("Deserializing...");
        //Deserialize
        ObjectInputStream objIn = new ObjectInputStream(new ByteArrayInputStream(byteArrOut.toByteArray()));
        Map<Animal, String> mapDes = (Map<Animal, String>) objIn.readObject();
        System.out.println(mapDes.get(new Animal("Cow"))); // Because name is transient, this will return "null"
        System.out.println();

    }

    static class Animal implements Serializable {
        transient String name;

        public Animal(String name) {
            this.name = name;
        }

        public Animal() {

        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Animal animal = (Animal) o;
            return Objects.equals(name, animal.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("name", name)
                    .toString();
        }
    }
}

class QueueRunner {
    public static void main(String[] args) {
        testPriorityQueue();
        testArrayDeque();
    }


    public static void testPriorityQueue() {
        System.out.println("Setting priority according to String length...");
        Queue<String> strPriQueue = new PriorityQueue<>(Comparator.comparing(String::length));
        strPriQueue.add("aaaaaa");
        strPriQueue.add("bb");
        strPriQueue.add("ccc");

        System.out.println("PriorityQueue doesn't store elements in order..");
        System.out.println(strPriQueue);
        System.out.println();

        System.out.println("Sorting will show up during polling (according to elements' priorities)...");
        Stream.generate(strPriQueue::poll).takeWhile(i -> i != null).forEach(System.out::println);
        System.out.println();
    }

    public static void testArrayDeque() {

    }

}

class SynchronizedCollectionRunner {
    public static void main(String[] args) {

//        Collections.synchronizedCollection();
//        Collections.synchronizedList();
        //etc
    }
}

class StreamsRunner {
    public static void main(String[] args) {

        System.out.println("CollectAfterBoxing()");
        CollectAfterBoxing();
        System.out.println();

        System.out.println("CollectAfterUnboxing()");
        CollectAfterUnboxing();
        System.out.println();
    }


    /**
     * boxed()
     * - convert a Stream of primitives to a Stream of their Generic Wrappers
     */
    public static void CollectAfterBoxing() {
        Map<Integer, String> collectedMap = IntStream.range(0, 5).boxed().collect(Collectors.toMap(i -> i, i -> i.toString().concat("s")));
        System.out.println(collectedMap);
    }

    /**
     * mapToInt()/mapToDouble()/.../mapToObject()
     * - convert a Stream of Generic Wrappers to a Stream of their primitives
     */
    public static void CollectAfterUnboxing() {
        int[] intStream = Stream.of(1, 2, 3, 4).mapToInt(i -> i).toArray();
        Arrays.stream(intStream).forEach(System.out::println);
    }

}
