package org.hsmak.random.math;

public class JFibonacci {
    public static void main(String... args){
        System.out.println("Program: Fibonacci");

        System.out.println(fibonacciRecr(50));
        System.out.println(fibonacciIter(50));
    }

    public static long fibonacciRecr(long n){
        return fib(n, 0, 1);
    }

    public static long fib(long n, long prev, long cur){
        if (n == 0)
            return prev;
        else if(n == 1)
            return cur;
        else
            return fib(n - 1, cur, (prev + cur));//Tail Recursive
    }

    public static long fibonacciIter(long n){

        long prev = 0;
        long cur = 1;
        if( n == 0)
            return prev;
        if(n == cur)
            return cur;

        long nxt;
        for(int i = 2; i <= n; i++){
            nxt = prev + cur;
            prev = cur;
            cur = nxt;
        }

        return cur;
    }

}
