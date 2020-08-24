package _ocp8.ch07_innerclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class LambdaVsAnonymous {
    public static void main(String[] args) {

        LambdaVsAnonymous lambdaVsAnonymous = new LambdaVsAnonymous();

        List<Function<Object, Object>> l = new ArrayList<>();

        // Adding Anonymous Inner Classes in a loop
        for (int i = 0; i < 1000; i++)
            l.add(new Function<Object, Object>() {// compiler will generate LambdaVsAnonymous$1.class
                @Override
                public Object apply(Object o) {
                    return null;
                }
            });

        // Adding Anonymous Inner Classes individually
        l.add(new Function<Object, Object>() { // compiler will generate LambdaVsAnonymous$2.class
            @Override
            public Object apply(Object o) {
                return null;
            }
        });
        l.add(new Function<Object, Object>() {  // compiler will generate LambdaVsAnonymous$3.class
            @Override
            public Object apply(Object o) {
                return null;
            }
        });

        /*
         * In case of lambda, compiler will not generate the anonymous inner classes!
         */
        l.add(s -> null);
        l.add(s -> null);
    }


    public <T, R> void getFun(Function<T, R> fun) {
    }

}

class CreatingInnerFromWithinOuter {
    class MyOuter {
        private int x = 10;

        public void createInner() {
            MyInner myInner = new MyInner();
            myInner.seeOuter();
        }

        class MyInner {
            public void seeOuter() {
                System.out.println(String.format("Outer x is: %d", x));
            }
        }

    }
}

class CreatingInnerFromOutsideTheOuter {
    public void createInnerFromOutsideTheOuterClassV1() {
        MyOuter myOuter = new MyOuter();
        MyOuter.MyInner myInner = myOuter.new MyInner();
        myInner.seeOuter();
    }

    public void createInnerFromOutsideTheOuterClassV2() {
        MyOuter.MyInner myInner = new MyOuter().new MyInner(); // one-liner
        myInner.seeOuter();
    }

    class MyOuter {
        private int x = 10;

        class MyInner {
            public void seeOuter() {
                System.out.println(String.format("Outer x is: %d", x));
            }
        }
    }

}

class ReferencingOuterInInner {

    public static void main(String[] args) {
        ReferencingOuterInInner referencingOuterInInner = new ReferencingOuterInInner();

        MyOuter myOuter = referencingOuterInInner.new MyOuter();
        MyOuter.MyInner inner = myOuter.new MyInner();
        inner.seeReferences();
    }

    class MyOuter {
        private int x = 10;

        class MyInner {
            public void seeReferences() {
                System.out.println(String.format("Inner class reference: %s", this));
                /*
                 * Notice the "this" followed by the Outer class's name.
                 * Don't confuse it with static call. Remember -> you can't use "this" in a static context!!
                 */
                System.out.println(String.format("Outer class reference: %s", MyOuter.this));
            }
        }
    }

}

class CreatingMethodLocalInnerClass {
    public static void main(String[] args) {
        CreatingMethodLocalInnerClass creatingMethodLocalInnerClass = new CreatingMethodLocalInnerClass();

        MyOuter myOuter = creatingMethodLocalInnerClass.new MyOuter();
        myOuter.createInnerClassFromWithingMethod();
    }

    class MyOuter {
        private int x = 10;

        public void createInnerClassFromWithingMethod() {
            final int localVar = 0;
            class MyMethodLocalInner {

                public void seeOuter() {
                    System.out.println(String.format("Outer x is: %d", x));
                }

                /**
                 * Local Variables of a Method: Live on the Stack.
                 * Inner Class Objects: Live on the Heap!!
                 *
                 * The local variables of the method live on the stack and exist only for the lifetime of the method.
                 * The inner class object can’t use them. Unless the local variables are marked final or are effectively final!
                 */
                public void useLocalVar() {
                    System.out.println(String.format("Local Variable localVar is: %d", localVar));
                }
            }
            MyMethodLocalInner myMethodLocalInner = new MyMethodLocalInner();
            myMethodLocalInner.seeOuter();
        }
    }
}

class ReferencingMethodLocalInnerClass {

    //ToDo - How do we access "MyMethodLocalInner#methodLocalInnerVar"? reflection?
    public Object referenceToMethodLocalInstance;

    public static void main(String[] args) {
        ReferencingMethodLocalInnerClass referencingMethodLocalInnerClass = new ReferencingMethodLocalInnerClass();
        ReferencingMethodLocalInnerClass.MyOuter myOuter = referencingMethodLocalInnerClass.new MyOuter();
        myOuter.createInnerClassFromWithingMethod();
        System.out.println(referencingMethodLocalInnerClass.referenceToMethodLocalInstance);

    }

    class MyOuter {
        private int x = 10;

        public void createInnerClassFromWithingMethod() {
            final int localVar = 0;
            class MyMethodLocalInner {

                public int methodLocalInnerVar = 22;

                public void seeOuter() {
                    System.out.println(String.format("Outer x is: %d", x));
                }

                /**
                 * Local Variables of a Method: Live on the Stack.
                 * Inner Class Objects: Live on the Heap!!
                 *
                 * The local variables of the method live on the stack and exist only for the lifetime of the method.
                 * The inner class object can’t use them. Unless the local variables are marked final or are effectively final!
                 */
                public void useLocalVar() {
                    System.out.println(String.format("Local Variable localVar is: %d", localVar));
                }
            }
            MyMethodLocalInner myMethodLocalInner = new MyMethodLocalInner();
            myMethodLocalInner.seeOuter();
            referenceToMethodLocalInstance = myMethodLocalInner;
        }
    }
}

class CreatingStaticNestedClass {

    public static void main(String[] args) {
        // unlike the 2 ways of instantiating inner classes, static nested classes can only be instantiated using this only way:
        CreatingStaticNestedClass.MyStaticNestedClass nested = new CreatingStaticNestedClass.MyStaticNestedClass();
        nested.fromNested();
    }

    static class MyStaticNestedClass {
        public void fromNested() {
            System.out.println("Hello from MyStaticNestedClass");
        }
    }

}


class ReferencingFromWithinStaticNestedClass {
    public static int x_static = 0;
    public int x_instance = 0;

    static class MyStaticNestedClass { // A static member of the enclosing class

        MyStaticNestedClass myStaticNestedClass = new MyStaticNestedClass();

        public void print() {
            System.out.println(x_static);
//            System.out.println(x_instance); // can't reference an instance field from a static context!!
        }
    }
}

class ReplacingAnonymousClassWithLambda {

}