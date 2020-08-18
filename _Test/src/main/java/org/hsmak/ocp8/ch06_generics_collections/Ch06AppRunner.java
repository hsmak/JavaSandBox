package org.hsmak.ocp8.ch06_generics_collections;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hsmak.ocp8._util.Utils;

import java.io.*;
import java.util.*;
import java.util.function.Function;
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

    /*
     * When empty:
     *   - pop()/remove(): throws java.util.NoSuchElementException
     *   - poll(): returns null:
     */
    public static void testArrayDeque() {

        List<Integer> ints = List.of(10, 9, 8, 7, 6, 5);

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        ArrayDeque<Integer> ad3 = new ArrayDeque<>();
        ArrayDeque<Integer> ad4 = new ArrayDeque<>();
        ArrayDeque<Integer> ad5 = new ArrayDeque<>();

        for (int i : ints) {
            ad1.offer(i);// adding on the right (back of the deque)
            ad2.offerFirst(i); // adding on the left (front of the deque)
            ad3.push(i); // adding on the left (front of the deque)
            ad4.add(i); // adding on the right (back of the deque)
            ad5.addFirst(i); // adding on the left (front of the deque)
        }
        List<ArrayDeque<Integer>> ads = List.of(ad1, ad2, ad3, ad4, ad5);

        System.out.println("printing all ArrayDeques: ");
        for (ArrayDeque<Integer> ad : ads) {
            System.out.println(ad);
        }
        System.out.println();

        System.out.println("printing all peek(): ");
        for (ArrayDeque<Integer> ad : ads) {
            System.out.println(ad.peek()); // Returning the element from the head/front/left/ of the Deque without removing
        }
        System.out.println();


    }

}

class SynchronizedCollectionRunner {
    public static void main(String[] args) {

//        Collections.synchronizedCollection();
//        Collections.synchronizedList();
        //etc
    }
}

class NavigableCollRunner {
    public static void main(String[] args) {
        navigateTreeSet();
        navigateTreeMap();
    }


    public static void navigateTreeSet() {
        TreeSet<Integer> intTS = new TreeSet<>(List.of(1205, 1505, 1545, 1830, 1930));
        System.out.print("intTS: ");
        System.out.println(intTS);

        System.out.print("intTS.headSet(1600).last(): ");
        System.out.println(intTS.headSet(1600).last());

        System.out.print("intTS.lower(1600): ");
        System.out.println(intTS.lower(1600));

        System.out.print("intTS.pollFirst(): ");
        System.out.println(intTS.pollFirst());

        System.out.print("intTS, after pollFirst(): ");
        System.out.println(intTS);

        System.out.print("intTS.pollLast(): ");
        System.out.println(intTS.pollLast());

        System.out.print("intTS.tailSet(1600).first(): ");
        System.out.println(intTS.tailSet(1600).first());

        System.out.print("intTS.higher(1600): ");
        System.out.println(intTS.higher(1600));

        System.out.println();
    }


    /**
     * Backed Collection??
     */
    public static void navigateTreeMap() {

    }
}

class BackedCollectionRunner {
    public static void main(String[] args) throws Throwable {
        testSubMap();
        testSubSet();
    }

    private static void testSubSet() {
    }

    public static void testSubMap() throws Throwable {
        TreeMap<String, String> strTM = new TreeMap<>();
        strTM.put("a", "ahmed");

        SortedMap<String, String> strSubTM = strTM.subMap("a", "c");
        System.out.print("strTM.subMap(\"a\", \"c\"): ");
        System.out.println(strSubTM);

        strTM.put("b", "bama");
        System.out.print("strSubTM, after strTM.put(\"b\", \"bama\"): ");
        System.out.println(strSubTM);

        System.out.print("strSubTM: ");
        System.out.println(strSubTM);

        System.out.print("strTM: ");
        System.out.println(strTM);

        System.out.println("--- Adding a new element to the original strTM ---");

        System.out.print("strTM.put(\"d\", \"ada\"): ");
        strTM.put("d", "ada");

        System.out.print("strSubTM: ");
        System.out.println(strSubTM);

        System.out.print("strTM: ");
        System.out.println(strTM);

        System.out.println("--- Adding an out-of-range element to the strSubTM ---");

        System.out.print("strSubTM.put(\"z\", \"zoo\"): ");
        Utils.handleException(() -> strSubTM.put("z", "zoo"), "Pay attention to the range when you created a SubTree via -> strTM.subMap(\"a\", \"c\")");


    }
}

class NaturalOrderingRunner {
    public static void main(String[] args) {
        TreeSet<String> strPQ = new TreeSet<>(List.of(new String[]{">ff<", "> f<", ">f <", ">FF<", "<"}));
        System.out.println(strPQ);
    }
}

/*
 * Lower Bound/Contravariant: <? super Type>
 *  - in Scala [+R]
 * Upper Bound/Covariant: <? extends Type>
 *  - in Scala [-T]
 *
 */
class GenericsRunner {
    public static void main(String[] args) {

        testWildType();
        testLowerBoundWithRetrieving_Consuming();
        testUpperBoundWithInsertion_Producing();
        testPECS();
    }

    private static void testLowerBoundWithRetrieving_Consuming() {

    }

    private static void testUpperBoundWithInsertion_Producing() {

    }

    private static void testWildType() {

    }

    static <T extends Number> void m(List<? extends T> l) {
//        l.set(0, l.get(0)); // Won't compile. Instead use the below helper method to capture the type via <T>
        upperBoundHelper(l, i -> i); //
    }

    /*
     * Lower Bound/Contravariant:   <? super Type>
     * Upper Bound/Covariant:       <? extends Type>
     *
     * PECS: (Producer Extends | Consumer Super)
     * Java's generics notes:
     *  - Covariant:
     *      - class:                        `class MyClass<T extends Type>{}`
     *      - constructors:                 `<T extends Type> Constructor(T t)`
     *      - method's new parameter type:  `<T extends type>` T method(T t){}`
     *      - method's argument:            `void method(List<? extends Type> list){}`
     *  - Contravariant & Wildcard:
     *      - method's argument:            `void method(List<? super Type> list)`
     *      - variable declaration:         `List<? super Number> list;`
     *  Note: `List<?> is exactly same as `List<? extends Object>`
     *
     * Scala's type system is more advanced than Java's. It accepts all variances at the class/trait/method levels.
     * Here's an example:
     * ```
     *      trait Function1[-T, +R]{ def apply(t: T): R }
     * ```
     *
     * link: https://docs.oracle.com/javase/tutorial/java/generics/subtyping.html
     */
    public static void testPECS() {

    }

    /*
     * Wildcard Capture and Helper Methods
     *  Link: https://docs.oracle.com/javase/tutorial/java/generics/capture.html
     */
    private static <T> void upperBoundHelper(List<T> l, Function<? super T, ? extends T> f) {
        l.set(0, f.apply(l.get(0)));
    }


}
