package concurrency.ch01.callables;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by hsmak on 4/16/15.
 */
public class Processor implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        Random random = new Random();
        int duration = random.nextInt(4000);

        if(duration > 2000){
            throw new IOException("Sleeping for too long");
        }

        Thread.sleep(duration);
        System.out.println("Finished...");
        return duration;
    }
}

class CallableRunnerApp {
    public static void main(String[] args) {
        ExecutorService ex = Executors.newCachedThreadPool();
        Future<Integer> future = ex.submit(new Processor());

        ex.shutdown();

//        future.cancel(true);

        try {
            System.out.println("Result is: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}