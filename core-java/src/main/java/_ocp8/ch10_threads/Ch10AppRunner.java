package _ocp8.ch10_threads;

import java.util.Arrays;

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

class ThreadStatesAndLifeCycle {
    public static void main(String[] args) {
        printClassNameViaStackWalker(1);
        Arrays.stream(Thread.State.values()).forEach(System.out::println);
    }
}