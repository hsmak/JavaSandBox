package org.hsmak.random;

public class JFibonacci {
    public static void main(String... args){
        System.out.println("Program: Fibonacci");

        System.out.println(fibonacci(5));
    }

    public static long fibonacci(long n){
        return fib(n, 0, 1);
    }

    public static long fib(long n, long prev, long next){
        if (n == 0)
            return prev;
        else if(n == 1)
            return next;
        else
            return fib(n - 1, next, (prev + next));//Tail Recursive
    }


}
