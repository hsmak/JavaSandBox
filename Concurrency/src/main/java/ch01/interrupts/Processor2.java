package ch01.interrupts;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by hsmak on 4/16/15.
 */
public class Processor2 {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting.");


        ExecutorService ex = Executors.newCachedThreadPool();
        Future<?> future = ex.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {

                Random random = new Random();

                for(int i = 0; i<1E8; i++){

                    /*try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.println("Sleep has been interrupted!");
                        return;
                    }*/

                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("We've been interrupted!");
                        return null;
                    }

                    Math.sin(random.nextDouble());
                }

                return null;
            }
        });

        ex.shutdown();

//        Thread.sleep(500);

//        ex.shutdownNow();
        future.cancel(true);

        ex.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("Finished");
    }

}
