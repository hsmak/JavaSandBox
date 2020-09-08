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
        // Can we use AtomicReference to create a Singleton? Yes --> https://codereview.stackexchange.com/questions/123139/initialization-lock-for-singleton
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

/*
 * Decouple Tasks from Threads
 */
class ExecutorServiceRunner {
    public static void main(String[] args) throws InterruptedException {
        printClassNameViaStackWalker(1);

        experimentWithExecutors();
        experimentWithScheduledThreadPool();
        submitMyCallable();
    }

    public static void experimentWithExecutors() {
        printMethodNameViaStackWalker(1);
        /*
         * Create as many threads as needed and cache the idle ones.
         * This could overload the system with nonstop thread creation if every new task is never finished.
         * It creates a ThreadPoolExecutor of size 0 and uses "SynchronousQueue"
         * It's preferable to always use FixedThreadPool
         */
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        /*
         * It'll create as many threads as specified by the argument; ThreadPoolExecutor with size n
         * If all threads are occupied by current tasks, new submitted tasks wil be queued in an unbounded queue "LinkedBlockingQueue" till a thread becomes free.
         * This one is preferable to the CachedThreadPool
         * Size can be changed
         */
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(4);
        ThreadPoolExecutor newFixedThreadPoolCasted = (ThreadPoolExecutor) newFixedThreadPool;
        newFixedThreadPoolCasted.setMaximumPoolSize(6); // Reset the MaximumPoolSize first or you will get IllegalArgumentException
        newFixedThreadPoolCasted.setCorePoolSize(6);

        /*
         * This simply will create a ThreadPoolExecutor with size 1
         * Once created, pool size can't be changed
         * Unlike FixedThreadPool, you can't downcast to "ThreadPoolExecutor" and change the size of SingleThreadPool
         */
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        /*ThreadPoolExecutor newSingleThreadExecutorCasted = (ThreadPoolExecutor) newSingleThreadExecutor;
        newSingleThreadExecutorCasted.setMaximumPoolSize(6);
        newSingleThreadExecutorCasted.setCorePoolSize(6);*/
    }

    public static void experimentWithScheduledThreadPool() throws InterruptedException {
        printMethodNameViaStackWalker(1);
        /*
         * Enables tasks to be executed:
         *  - After a delay or
         *  - At repeating intervals
         */
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(4);
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();

        newScheduledThreadPool.schedule(() -> System.out.println("Scheduled - After a Delay"), 5, TimeUnit.SECONDS);
        // Wait for 3 seconds then Execute every 1 seconds

        // Delay is independent on completion of last execution
        newScheduledThreadPool.scheduleAtFixedRate(
                () -> System.out.println("Scheduled - Begin After a Delay - Periodically Independent on completion of last execution"),
                3,
                1,
                TimeUnit.SECONDS);

        // Delay is dependent on completion of last execution
        newScheduledThreadPool.scheduleWithFixedDelay(
                () -> System.out.println("Scheduled - Begin After a Delay - Periodically Dependent on completion of last execution"),
                3,
                1,
                TimeUnit.SECONDS);


        TimeUnit.SECONDS.sleep(10);
        newScheduledThreadPool.shutdown();
    }

    public static void submitMyCallable() throws InterruptedException {
        printMethodNameViaStackWalker(1);
        /*
         * Callable is distinct from Runnable in two ways:
         *  - Return Results
         *  - Throws Checked Exceptions
         */
        class MyCallable implements Callable<Integer> {
            @Override
            public Integer call() throws Exception {
                return ThreadLocalRandom.current().nextInt(10);
            }
        }

        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future<Integer> future = newSingleThreadExecutor.submit(new MyCallable());

        /*
         * Two possible exceptions need to be handled:
         *      - InterruptedException: Thrown when the thread calling the Future’s get() method is interrupted before a result can be returned
         *      - ExecutionException: Thrown when an exception was thrown during the execution of the Callable’s call() method
         */
        try {
            Integer i = future.get();
            System.out.println(i);
        } catch (InterruptedException | ExecutionException e) { // Pay attention to these two Exceptions
            e.printStackTrace();
        } finally {
            TimeUnit.SECONDS.sleep(2);
            newSingleThreadExecutor.shutdown();
        }
    }

    static class MyCommandRunner implements Executor {
        @Override
        public void execute(Runnable command) { // Doesn't create aby thread. It's more like a CommandDelegator
            command.run();
        }
    }

    static class MyThreadExecutor implements Executor {
        @Override
        public void execute(Runnable command) { // Create a Thread per Task
            Thread t = new Thread(command);
            t.start();
        }
    }
}

/*
 * The ForkJoinFramework is actually a specialized ExecutorService. The difference is:
 *      - Other ExecutorServices accepts multiple "independent" tasks
 *          - All threads share the same queue while in ForkjoinPool each thread has its own queue of tasks/subtasks
 *      - While ForkJoinPool accepts one big task to be divided/split up into "dependent" subtasks; recursively
 *          - It's up to the developer to decide how a task is split up into subtasks and to how many
 *          - Considering all system resources and whether tasks are IO/ Intensive or CPU Intensive in nature
 *      - Divide and Conquer
 *          - Initially, one single thread is kicked off.
 *          - This thread will split up the task into two subtasks.
 *          - Fork the 1st one
 *          - Compute the 2nd one
 *          - Let the CurrentThread join forked task.
 *      - Work Stealing
 */
/**
 * Refer to examples at:
 *      {@link concurrency.ch01.forkjoins.RecursiveActionArrayInitializerTask}
 *      {@link concurrency.ch01.forkjoins.RecursiveTaskFindMaxPositionTask}
 */
class ForkJoinFramework {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);

        createForkJoinPool();
    }

    public static void createForkJoinPool() {
        printMethodNameViaStackWalker(1);
        ForkJoinPool forkJoinPool = new ForkJoinPool(); // Default constructor will make use of "Runtime.getRuntime().availableProcessors()"
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println(availableProcessors);
    }
}
