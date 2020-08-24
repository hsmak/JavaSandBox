package concurrency.ch01.locks.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by u308908 on 4/16/2015.
 */
public class Processor {

    Account account1 = new Account(10000);
    Account account2 = new Account(10000);

    private final static int LIMIT = 10;

    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    Condition condition = lock1.newCondition();

    //to make sure a correct lock orders, use a method to acquire the locks
    private void acquireLocks(Lock lock1, Lock lock2) {

        while (true) {

            //Acquire locks

            boolean isLockAcuired1 = false;
            boolean isLockAcuired2 = false;

            try {

                isLockAcuired1 = lock1.tryLock();
                isLockAcuired2 = lock2.tryLock();

            } finally {

                if (isLockAcuired1 && isLockAcuired2) {
                    return;
                }

                //Locks not acquired
                if (isLockAcuired1) {
                    lock1.unlock();
                }

                if (isLockAcuired2) {
                    lock2.unlock();
                }

            }

        }
    }

    public void transfer1() {

        Random random = new Random();

        for (int i = 0; i < 1000; i++) {

            //deadlock for swapping the order of locks
            lock1.lock();
            lock2.lock();//already acquired by the other thread

            //acquireLocks(lock1, lock2);//maintaining a proper lock ordering avoid deadlock

            try {//always use try/finally to make sure you unlock in case an Exception occurs

                /*while (account1.getBalance() <= 0) {
                    condition.await();
                }*/

                Account.transfer(account1, account2, random.nextInt(100));


            } finally {// cleanup and unlocking. This is useful in case an Exception occurs

                condition.signal();

                lock1.unlock();
                lock2.unlock();

            }
        }
    }

    public void transfer2() {

        Random random = new Random();

        for (int i = 0; i < 1000; i++) {

            //deadlock for swapping the order of locks
            lock2.lock();
            lock1.lock();//already acquired by the other thread

            //acquireLocks(lock1, lock2);//maintaining a proper lock ordering avoid deadlock

            //to avoide deadlocks, always lock in the same order
            /*lock1.lock();
            lock2.lock();*/

            try {//always use try/finally to make sure you unlock in case an Exception occurs

                /*while (account2.getBalance() <= 0) {
                    condition.await();
                }*/

                Account.transfer(account2, account1, random.nextInt(100));

            } finally {// cleanup and unlocking. This is useful in case an Exception occurs

                condition.signal();
                lock2.unlock();
                lock1.unlock();


                /*lock1.unlock();
                lock2.unlock();*/


            }

        }
    }

    public void finished() {
        System.out.println("Account1 Balance: " + account1.getBalance());
        System.out.println("Account2 Balance: " + account2.getBalance());
        System.out.println("Total Balance: " + (account1.getBalance() + account2.getBalance()));
    }
}

class TestApp {

    public static void main(String[] args) throws InterruptedException {

        final Processor processor = new Processor();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
//                try {
                    processor.transfer1();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
//                try {
                    processor.transfer2();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        processor.finished();
    }

}

class Account {
    int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public static void transfer(Account account1, Account account2, int amount) {
        account1.withdraw(amount);
        account2.deposit(amount);
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }
}