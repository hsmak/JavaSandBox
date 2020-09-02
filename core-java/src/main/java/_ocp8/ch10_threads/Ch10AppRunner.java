package _ocp8.ch10_threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static _ocp8.Utils.printClassNameViaStackWalker;
import static _ocp8.Utils.printMethodNameViaStackWalker;

/*
 * In Java, Thread means two different things:
 *  - An instance of class java.lang.Thread:
 *      An instance of Thread is just an object that has variables and methods, and it lives and dies on the heap.
 *  - A thread of execution:
 *      an individual process (a “lightweight” process) that has its own call stack; one call stack per thread.
 */
class CreatingThreads {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);

        System.out.printf("main()'s thread's name: %s\n", Thread.currentThread().getName());
        System.out.println();

        createByExplicitlyExtendingThreadClass();
        createByAnonymouslyExtendingThreadClass();
        createByImplementingRunnable();

    }

    public static void createByExplicitlyExtendingThreadClass() {
        printMethodNameViaStackWalker(1);

        class MyCustomThread extends Thread { // not recommended unless a specialized thread-specific behaviour is intended

            public MyCustomThread() {
            }

            public MyCustomThread(Runnable target) {
                super(target);
            }

            public
            @Override
            void run() {
                printMethodNameViaStackWalker(1);
                System.out.println("1. Before calling super.run()");
                super.run();
                System.out.println("3. After calling super.run()");
                System.out.println(Thread.currentThread().getName());
            }
        }
        new MyCustomThread().start();
        new Thread(new MyCustomThread()).start(); // Ridiculous, but possible!!
        new MyCustomThread(() -> {
            printMethodNameViaStackWalker(1);
            System.out.println("2. Supplying Runnable lambda to custom Thread");
            System.out.println(Thread.currentThread().getName());
        }).start();
        System.out.println();

    }

    public static void createByAnonymouslyExtendingThreadClass() {
        printMethodNameViaStackWalker(1);

        new Thread() { // Thread's job is non-Reusable! Supply a Runnable instead!
            @Override
            public void run() {
                printMethodNameViaStackWalker(1);
                System.out.println(Thread.currentThread().getName());
            }
        }.start();
        System.out.println();
    }

    public static void createByImplementingRunnable() {
        printMethodNameViaStackWalker(1);

        Runnable r = () -> {
            printMethodNameViaStackWalker(1);
            System.out.println(Thread.currentThread().getName());
        };
        new Thread(r).start();
        System.out.println();
        new Thread(() -> {
            printMethodNameViaStackWalker(1);
            System.out.println(Thread.currentThread().getName());
        }).start();
    }
}

class ThreadStatesPrioritiesAndLifeCycle {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);

        String states = Arrays.stream(Thread.State.values()).map(Enum::name).collect(Collectors.joining(" | "));
        System.out.println(states);
        System.out.println();

        String priorities = Stream.of(
                Thread.MIN_PRIORITY,
                Thread.NORM_PRIORITY,
                Thread.MAX_PRIORITY)
                .map(String::valueOf)
                .collect(Collectors.joining(" | "));
        System.out.println(priorities);
        System.out.println();
    }
}

class Synchronization {

    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        printClassNameViaStackWalker(1);
    }

    synchronized public static void synchronizeStaticMethod() {
        printMethodNameViaStackWalker(1);
    }

    public static void synchronizeStaticBlock() {

        synchronized (Synchronization.class) {
            printMethodNameViaStackWalker(1);
            synchronized (Synchronization.class) { // unnecessary but possible. It does makes sense to synchronize on another Class level lock
            }
        }
        synchronized (MyInterfaceLock.class) { //ToDo - Will this affect classes implementing this interface? Most likely NO!
        }
        synchronized (MyClassLock.class) {
        }
    }

    synchronized public void synchronizeInstanceMethod() {
        printMethodNameViaStackWalker(1);
    }

    public void synchronizeInstanceBlock() {
        synchronized (this) {
            printMethodNameViaStackWalker(1);
            synchronized (this) { // unnecessary but possible. It does makes sense to synchronize on another Object level lock

            }
            synchronized (lock) { // This means this method is synchronizing on two locks

            }
        }
        synchronized (this) {

        }
    }

    interface MyInterfaceLock {
    }

    class MyClassImpMyI implements MyInterfaceLock {
    }

    class MyClassLock {
    }
}

class StaticAndNonStaticMethodsAccessingSameField { // They will not block each other!!!

}

class LockingMechanisms {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);
    }
}

class DeadlocksLivelocksStarvationRaceConditions {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);
    }
}

class AtomicsAndVolatile {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);
    }
}

/*
 * Links:
 *  - https://docs.oracle.com/javase/specs/jls/se11/html/jls-17.html#jls-17.4.5[Java 11's Language Specification]
 *  - https://medium.com/@kasunpdh/handling-java-memory-consistency-with-happens-before-relationship-95ddc837ab13[Handling Java Memory Consistency with happens-before relationship]
 *  - https://javarevisited.blogspot.com/2020/01/what-is-happens-before-in-java-concurrency.html[What is happens-before in Java Concurrency?]
 */
class HappensBeforeRelationship {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);
    }
}

/*
 * Still you can't solely rely on Synchronized Collections if there are other ops that need to act as a whole unit around synchronized individual Collection's methods.
 */
class SynchronizedList {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<Integer>());
        synchronizedList.addAll(Arrays.asList(1, 2, 3, 4));
    }
}