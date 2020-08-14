package org.hsmak.ocp8.ch02_oo;

class OverridingRunner {
    interface T {
        static int m() {
            return 1;
        }

        default int d() {
            return m();
        }
    }

    interface TT extends T {
        default int mm() {
            return d();
        }

        @Override
        default int d() {
            return 0;
        }
    }

    static class Vehicle {
        String name = "Vehicle";

        String getName() {
            return ("This is a Vehicle");
        }

        static int g() {
            return 1;
        }
    }

    static class Bike extends Vehicle implements T {
        String name = "Bike";

        String getName() {
            return ("This is a Bike");
        }
    }

    public static void main(String[] args) {
        Vehicle v = new Bike();
        System.out.println(v.name);//instance variables are NOT overridden
        System.out.println(v.getName());
    }
}
class DefaultMethodsRunner {
    interface IF1 {
        default void defaultMethod() {
            System.out.println();
        }

        private void privateMethod() {

        }

        void abstractMethod();
    }

    interface IF2 {
        default void defaultMethod() {
            System.out.println();
        }

        private void privateMethod() {

        }

        void abstractMethod() throws RuntimeException;
    }

    interface IF3 extends IF2 {

    }

    class D implements IF2 {
        public void W() {

        }

        @Override
        public void defaultMethod() {
            IF2.super.defaultMethod();
        }

        @Override
        public void abstractMethod() throws RuntimeException {

        }
    }
}