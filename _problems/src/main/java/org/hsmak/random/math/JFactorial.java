package org.hsmak.random.math;

public class JFactorial {
    public static void main(String... args) {
        System.out.println("Program: Factorial");

        /**
         * Use BigInteger
         *
         * Note: In Scala; BigInt as an implicit
         */
        System.out.println(factorial(5));
    }

    public static long factorial(long n) {
        return fact(n, 1);
    }

    private static long fact(long n, long acc) {
        if (n == 0)
            return acc;
        else
            return fact(n - 1, acc * n);//Tail Recursive
    }
}
