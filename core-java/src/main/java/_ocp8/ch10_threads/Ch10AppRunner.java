package _ocp8.ch10_threads;

import java.util.*;
import java.util.concurrent.TimeUnit;
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
        synchronized (MyClassImpMyI.class) { //ToDo - Will this affect classes implementing this interface? Most likely NO!
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

class StaticAndNonStaticMethodsAccessingSameField { // They will not block each other on the same lock object!!! There will be two locks involved!!

}

class DeadlocksVsLivelocks {

    static Lock lockA_DeadLock = new ReentrantLock();
    static Lock lockB_DeadLock = new ReentrantLock();

    static Lock lockA_LiveLock = new ReentrantLock();
    static Lock lockB_LiveLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        printClassNameViaStackWalker(1);

        deadLockThem();
        liveLockThem();

        System.out.println("Sleeping for 20 sec in the main thread before forcing the JVM to shutdown!");
        TimeUnit.SECONDS.sleep(20);
        System.exit(0);
    }

    private static void liveLockThem() {
        printMethodNameViaStackWalker(1);

        new Thread(() -> {
            try {
                liveLockMeOnLockA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                liveLockMeOnLockB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public static void deadLockThem() {
        printMethodNameViaStackWalker(1);

        new Thread(() -> {
            try {
                deadLockMeOnLockA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                deadLockMeOnLockB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void deadLockMeOnLockB() throws InterruptedException {
        printMethodNameViaStackWalker(1);

        while (true) {
            synchronized (lockA_DeadLock) {
                Thread.sleep(100); // Sleep to encourage the scheduler to place this thread back in the queue and dispatch the other one
                synchronized (lockB_DeadLock) { // deadlock on lockB
                    System.out.println("Inside - deadLockMeOnLockB: This might never be reached!");
                }
                System.out.println("Outside - 0: This might never be reached!");
            }
            System.out.println("Outside - 1: This might never be reached!");
        }
    }

    public static void deadLockMeOnLockA() throws InterruptedException {
        printMethodNameViaStackWalker(1);

        while (true) {
            synchronized (lockB_DeadLock) {
                Thread.sleep(100); // Sleep to encourage the scheduler to place this thread back in the queue and dispatch the other one
                synchronized (lockA_DeadLock) { // deadlock on lockA
                    System.out.println("Inside - deadLockMeOnLockA: This might never be reached!");
                }
                System.out.println("Outside - 0: This might never be reached!");
            }
            System.out.println("Outside - 1: This might never be reached!");
        }
    }

    public static void liveLockMeOnLockB() throws InterruptedException {
        printMethodNameViaStackWalker(1);

        while (true) {
            boolean a = lockA_LiveLock.tryLock();
            Thread.sleep(100); // Encourage the scheduler to place this thread back in the queue and dispatch the other one
            boolean b = lockB_LiveLock.tryLock();
            if (a && b)
                System.out.println("Inside - liveLockMeOnLockB: This might never be reached! However, because TryLock are nonblocking there's higher chance of success!");
            if (a)
                lockA_LiveLock.unlock();
            if (b)
                lockB_LiveLock.unlock();
        }

    }

    public static void liveLockMeOnLockA() throws InterruptedException {
        printMethodNameViaStackWalker(1);

        while (true) {
            boolean b = lockB_LiveLock.tryLock();
            Thread.sleep(100); // Encourage the scheduler to place this thread back in the queue and dispatch the other one
            boolean a = lockA_LiveLock.tryLock();
            if (a && b)
                System.out.println("Inside - liveLockMeOnLockA: This might never be reached! However, because TryLock are nonblocking there's higher chance of success!");
            if (a)
                lockA_LiveLock.unlock();
            if (b)
                lockB_LiveLock.unlock();
        }
    }
}

class ThreadCommunication {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);

        ProducerConsumer producerConsumer = new ProducerConsumer();

        //Start 2 Producers
        startNThreads(2, () -> {
            try {
                producerConsumer.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Start 2 Consumers
        startNThreads(2, () -> {
            try {
                producerConsumer.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    static void startNThreads(int n, Runnable r) {
        Stream.generate(() -> new Thread(r)).limit(n).forEach(Thread::start);
    }

    static class ProducerConsumer {

        private final static int LIMIT = 10;
        LinkedList<Integer> list = new LinkedList<>();
        Object lock = new Object();

        public void produce() throws InterruptedException {
            int value = 0;
            Random random = new Random();
            while (true) {
                synchronized (lock) {// in order to use lock.wait()/lock.notify() on the lock object, this thread must 1st own the lock on this object via synchronized(lock)
                    while (list.size() == LIMIT) {
                        lock.wait();
                    }
                    list.add(value++);
                    lock.notifyAll();
                }
                Thread.sleep(random.nextInt(200));
            }
        }

        public void consume() throws InterruptedException {

            Random random = new Random();

            while (true) {
                synchronized (lock) { // in order to use lock.wait()/lock.notify() on the lock object, this thread must 1st own the lock on this object via synchronized(lock)

                    while (list.isEmpty()) {
                        lock.wait();
                    }

                    System.out.print("List size is: " + list.size());
                    int value = list.removeFirst();
                    System.out.println("; the value is: " + value);
                    lock.notifyAll();
                }

                Thread.sleep(random.nextInt(200));
            }
        }
    }
}

// Synchronized + Volatile
class SingletonInstance {

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
