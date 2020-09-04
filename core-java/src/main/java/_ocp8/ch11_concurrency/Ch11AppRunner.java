package _ocp8.ch11_concurrency;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static _ocp8.Utils.printClassNameViaStackWalker;
import static _ocp8.Utils.printMethodNameViaStackWalker;

/*
 * CAS: Compare And Swap
 */
class AtomicsAndVolatile {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);

        createAtomicVariables();
    }

    public static void createAtomicVariables() {
        printMethodNameViaStackWalker(1);

        /*
         * ToDo:
         *  - Create multiple threads that modify the Atomic var.
         */
        AtomicInteger atomicInteger = new AtomicInteger(10);
        System.out.println(atomicInteger);
        atomicInteger.set(5);
        System.out.println(atomicInteger);                   //returns 5
        System.out.println(atomicInteger.getAndDecrement()); //returns 5 | becomes 4
        System.out.println(atomicInteger.getAndIncrement()); //returns 4 | becomes 5
        System.out.println(atomicInteger.incrementAndGet()); //returns 6 | becomes 6
        System.out.println(atomicInteger.decrementAndGet()); //returns 5 | becomes 5
        /*AtomicBoolean atomicBoolean = new AtomicBoolean();
        AtomicLong atomicLong = new AtomicLong();
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{1,2,3});
        AtomicReference<String> stringAtomicReference = new AtomicReference<>();*/
    }
}


class GranularLockingMechanisms {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);

        reentrantLock();
        reentrantTryLock();
        reentrantLockAndCondition_SignalAndAwait();
        reentrantReadWriteLock();
    }

    public static void reentrantLock() {
        printMethodNameViaStackWalker(1);

    }

    /*
     * Observations:
     *  - Nonblocking Lock
     *  - CPU intensive
     *  - Could lead to Livelock
     */
    public static void reentrantTryLock() {
        printMethodNameViaStackWalker(1);

    }

    /*
     * - Equivalent to the traditional wait, notify, and notifyAll
     * - An advantage of a Condition over the traditional wait/notify operations -> multiple Conditions can exist for each Lock
     * - A Condition is effectively a waiting/blocking pool for threads.
     */
    public static void reentrantLockAndCondition_SignalAndAwait() {
        printMethodNameViaStackWalker(1);

    }

    /*
     * - A ReentrantReadWriteLock is not actually a Lock; it implements the ReadWriteLock interface.
     * - ReentrantReadWriteLock produces two specialized Lock instances:
     *      - Read lock
     *      - Write lock
     */
    public static void reentrantReadWriteLock() {
        printMethodNameViaStackWalker(1);

        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock(); // use it to write/modify; write threads block other threads
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock(); // use it to read/fetch; read threads don't block each other
    }
}

/*
 * Still you can't solely rely on Synchronized Collections if there are other ops that need to act as a whole unit around synchronized individual Collection's methods.
 */
class SynchronizedCollections {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);
        createSynchronizedList();

        Collections.synchronizedMap(Map.of(1, 1, 2, 2));
        Collections.synchronizedCollection(List.of(1, 2, 3));


    }


    public static void createSynchronizedList() {
        printMethodNameViaStackWalker(1);

        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<Integer>());
        synchronizedList.addAll(Arrays.asList(1, 2, 3, 4));
    }
}

class CopyOnWriteCollections {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);

        creatCopyOnWriteArrayList();
        creatCopyOnWriteArraySet();
    }

    public static void creatCopyOnWriteArrayList() {
        printMethodNameViaStackWalker(1);

        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        CopyOnWriteArraySet<Integer> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
        copyOnWriteArrayList.add(1);
        copyOnWriteArrayList.set(0, 2);
        copyOnWriteArrayList.listIterator().add(2);
    }

    public static void creatCopyOnWriteArraySet() {
        printMethodNameViaStackWalker(1);

        CopyOnWriteArraySet<Integer> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
    }
}

/*
 *
 * Note that:
 *  - Iterator for a concurrent collection is weakly consistent;
 *  - it can return elements from the point in time the Iterator was created or later.
 */
class ConcurrentCollections {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);

        new ConcurrentHashMap<>();
        new ConcurrentLinkedDeque<>();
        new ConcurrentLinkedQueue<>();

        // these two are sorted.. So elements require to be mutually Comparable
        new ConcurrentSkipListMap<>();
        new ConcurrentSkipListSet<>();

    }

}

/*
 * Bounded Vs. Unbounded
 * Blocking vs. Nonblocking methods
 */
class BlockingQueues {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);

        // Below BlockingQueue implementations:

        //Bounded
        new ArrayBlockingQueue<Integer>(10);

        //Optionally Bounded, depending on the used constructor
        new LinkedBlockingDeque<Integer>();
        new LinkedBlockingQueue<Integer>();

        new PriorityBlockingQueue<Integer>();

        // Elements of a DelayQueue can only be consumed once their delay has expired
//        new DelayQueue<Delayed???>();


        /*
         * Unbounded - A superset of ConcurrentLinkedQueue, SynchronousQueue, and LinkedBlockingQueue
         *
         * Use a LinkedTransferQueue (added in Java 7) instead of another comparable queue type.
         * The other java.util.concurrent queues (introduced in Java 5) are less efficient than LinkedTransferQueue.
         */
        new LinkedTransferQueue<Integer>();

        // Bounded - Has a capacity of ZERO. Does it mean only one element can be exchanged at a time?
        new SynchronousQueue<Integer>();
    }


}

/*
 * You can think of CyclicBarrier as a Master thread waiting for other Worker threads to finish their work.
 * Once their done, Master thread can continue its main task.
 */
class CyclicBarrierRunner {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);
    }
}

class ExecutorServiceRunner {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);
    }
}
