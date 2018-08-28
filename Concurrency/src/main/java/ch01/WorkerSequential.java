package ch01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hsmak on 4/15/15.
 */
public class WorkerSequential {

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();
    private Random random = new Random();

    public void stageOne() {

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        list1.add(random.nextInt(100));

    }

    public void stageTwo() {

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        list2.add(random.nextInt(100));

    }

    public void process(){
        long start = System.currentTimeMillis();

        for(int i=0; i<1000; i++){
            stageOne();
            stageTwo();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time Taken: " + (end-start));
        System.out.println("List1: " + list1.size());
        System.out.println("List2: " + list2.size());
    }

    public static void main(String[] args){
        new WorkerSequential().process();
    }
}
