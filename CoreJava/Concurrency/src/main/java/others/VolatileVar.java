package others; /**
 * Created by hsmak on 3/3/15.
 * Link:
 *      1) http://java.dzone.com/articles/java-volatile-keyword-0
 *      2) http://en.wikipedia.org/wiki/Singleton_pattern#Lazy_initialization
 *      3) http://stackoverflow.com/questions/21129018/java-volatile-and-cache-coherence
 *      5) http://javarevisited.blogspot.com/2011/06/volatile-keyword-java-example-tutorial.html
 *      4) http://javarevisited.blogspot.com/2011/04/synchronization-in-java-synchronized.html
 */

/**
 * to understand how Volatile works on variables, we have to understand how Main Memory and CPU Memory work together.
 * Main Memory: this the RAM
 * CPU Memory: this is the Cache. it's local to threads | Stack
 * A thread always read/write from/to its local memory; CPU Cache
 * <p>
 * Volatile is always confused to be same as Synchronization; which is not true.
 * Volatile: means read from main memory to CPU cache, and when writing back then write it to the main memory as well.
 * if a variable is not Volatile, the read/write from/to main memory won't happen; Dirty Cache!
 * <p>
 * You can have both static synchronized method and non static synchronized method and synchronized blocks in Java
 * but we can not have synchronized variable in java.
 * Using synchronized keyword with variable is illegal and will result in compilation error.
 * Instead of synchronized variable in Java, you can have java volatile variable,
 * which will instruct JVM threads to read value of volatile variable from main memory and don’t cache it locally
 * <p>
 * Read more: http://javarevisited.blogspot.com/2011/04/synchronization-in-java-synchronized.html#ixzz3TRS87hUM
 */

public class VolatileVar {

    private static volatile int MY_INT = 0;


    public static void main(String[] args) {
        new ChangeListener().start();
        new ChangeMaker().start();
    }

    static class ChangeListener extends Thread {
        @Override
        public void run() {

            int local_value = MY_INT;

            // without declaring MY_INT as Volatile, this Thread won't see the update from the other Thread.
            // Hence infinite looping!
            while (local_value < 5) {// this is almost a busy loop
                if (local_value != MY_INT) {
                    System.out.printf("Got Change for MY_INT : %d\n", MY_INT);
                    local_value = MY_INT;
                }
            }
        }
    }

    static class ChangeMaker extends Thread {
        @Override
        public void run() {

            int local_value = MY_INT;

            // Without declaring MY_INT as Volatile, this Thread won't update MY_INT in the main memory.
            // It will be updated thread-local memory only, instead!
            // Hence, the other thread won't see the update!
            while (MY_INT < 5) {
                System.out.printf("Incrementing MY_INT to %d\n", local_value + 1);
                MY_INT = ++local_value;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}