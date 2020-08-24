package concurrency.ch01.interrupts;

import java.util.Random;

/**
 * Created by hsmak on 4/16/15.
 */
public class RunnableProcessor {

}

class InterruptableRunnerApp {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting.");

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
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
                        return;
                    }

                    Math.sin(random.nextDouble());
                }
            }
        });

        t.start();
        t.interrupt();//interrupts a sleep call; or check with the method "isInterrupted()"

        t.join();

        System.out.println("Finished");

    }
}