package _ocp8.ch08_lambda_functional_interfaces;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

class DeferredCallWithFunctionalInterfaces {
    /*
     * Supplier's Lambda won't be constructed until it's called
     */
    static {
        Logger logger = Logger.getAnonymousLogger();
        logger.setLevel(Level.OFF);
//        logger.setLevel(Level.SEVERE);
        logger.log(Level.SEVERE, () -> { //Example of deferred call
            try {
                System.out.println("Construct me when you need me!");
                throw new Exception("Exception Message");
            } catch (Exception e) {
                return e.getMessage();
            }
        });
    }

    public static void main(String[] args) {

    }
}

class ClosuresAndEffectiveFinalVariablesInLambdas {

    public static void main(String[] args) {

        final int closure = 0;

        IntStream.range(0, 5).forEach(i -> {
//            closure++; // outside variables/closures must be FINAL, or effectively FINAL!!

            System.out.println(closure);
        });
    }
}

class ChainingFunctionalInterfaces {
    //using andThen

    public static void main(String[] args) {
        ChainingFunctionalInterfaces runner = new ChainingFunctionalInterfaces();
        runner.chainFunctions();
    }

    void chainFunctions() { //andThen compose() identity()
        Function<Integer, Integer> multiplyBy2 = i -> i * 2;
        Function<Integer, Integer> add3 = i -> i + 3;
        Function<Integer, Integer> multiplyBy2_andThen_Add3 = multiplyBy2.andThen(add3);

        System.out.println("--- Chain Functions ---");
        ChainingFunctionalInterfaces runner = new ChainingFunctionalInterfaces();
        System.out.print("runner.mThenA.apply: ");
        System.out.println(multiplyBy2_andThen_Add3.apply(5));
        System.out.println();
    }

    void chainPredicates() {// using or() and() negate()

    }

}

class IsEqualOfPredicate { // Similar to equals() method!

    public static void main(String[] args) {
        Predicate<String> isLanguageScala = p -> p.toLowerCase().equals("scala");

        System.out.println(isLanguageScala.test("Scala"));
        System.out.println(isLanguageScala.test("Java"));
    }
}

class GenericFunctionalInterfaces {

    private Supplier<Integer> integerSupplierViaAnonymousInnerClass = new Supplier<Integer>() {
        @Override
        public Integer get() {
            return null; // Can return null beside ints
        }
    };
    private Supplier<Integer> getIntegerSupplierViaLambda = () -> null;

}

class PrimitiveFunctionalInterfaces {
    private IntSupplier intSupplierViaAnonymousInnerClass = new IntSupplier() {
        @Override
        public int getAsInt() { // Generic Supplier<R> interface has "get()"
            return 10; // Can't return null!!
        }
    };

    private IntSupplier intSupplierViaLambda = () -> 10;
}

class BinaryFunctionalInterfaces {

}

class UnaryOperator { // there's only one UnaryOperator

}

class FunctionBasedTransformersInCollection{
    public static void main(String[] args) {
        Map<String, String> m = new TreeMap<>();
//        m.compute();
//        m.computeIfAbsent();
//        m.computeIfPresent();
//        m.replaceAll();
    }

}
class HigherOrderFunctions {

}

class Currying {

}