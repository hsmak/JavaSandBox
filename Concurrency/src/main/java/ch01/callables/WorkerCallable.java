package ch01.callables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by hsmak on 4/15/15.
 */
public class WorkerCallable implements Callable<List<Integer>>{

    List<Integer> list = new ArrayList<>();
    Random random = new Random();

    @Override
    public List<Integer> call() throws Exception {

        System.out.println(Thread.currentThread().getName() +
                " inside call()");
        for(int i=0; i<1000; i++){
            Thread.sleep(1);
            list.add(random.nextInt(100));
        }

        return list;
    }
}

class WorkerCallableRunnerApp {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService es = Executors.newFixedThreadPool(2);

        List<WorkerCallable> workerCallableList = new ArrayList<>();

        workerCallableList.add(new WorkerCallable());
        workerCallableList.add(new WorkerCallable());


        long start = System.currentTimeMillis();

        List<Future<List<Integer>>> futures = es.invokeAll(workerCallableList);
        es.shutdown();

        long end = System.currentTimeMillis();

        List<Integer> list1 = futures.get(0).get();
        List<Integer> list2 = futures.get(1).get();


        System.out.println("Time Taken: " + (end-start));
        System.out.println("List1: " + list1.size());
        System.out.println("List2: " + list2.size());
    }
}