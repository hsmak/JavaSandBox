package org.hsmak.random.math;

public class JFibonacci {
    public static void main(String... args){
        System.out.println("Program: Fibonacci");

        System.out.println(fibonacciRecr(50));
        System.out.println(fibonacciIter(50));
    }

    public static long fibonacciRecr(long nth){
        return fib(nth, 0, 1);
    }

    public static long fib(long nth, long prev, long cur){
        if (nth == 0)
            return prev;
        else if(nth == 1)
            return cur;
        else
            return fib(nth - 1, cur, (prev + cur));//Tail Recursive
    }

    public static long fibonacciIter(long nth){

        long prev = 0;
        long cur = 1;
        if( nth == 0)
            return prev;
        if(nth == cur)
            return cur;

        long nxt;
        for(int i = 2; i <= nth; i++){
            nxt = prev + cur;
            prev = cur;
            cur = nxt;
        }

        return cur;
    }

}
