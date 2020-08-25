package _ocp8.ch07_innerclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

class LambdaVsAnonymousInnerClass {
    public static void main(String[] args) {

        LambdaVsAnonymousInnerClass runner = new LambdaVsAnonymousInnerClass();

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
        private final int x = 10;

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
        private final int x = 10;

        class MyInner {
            public void seeOuter() {
                System.out.println(String.format("Outer x is: %d", x));
            }
        }
    }

}

class ReferencingOuterInInner {

    public static void main(String[] args) {
        ReferencingOuterInInner runner = new ReferencingOuterInInner();

        MyOuter myOuter = runner.new MyOuter();
        MyOuter.MyInner inner = myOuter.new MyInner();
        inner.seeReferences();
    }

    class MyOuter {
        private final int x = 10;

        void sayMyOuterHi() {
            System.out.println("Hi from sayMyOuterHi()");
        }

        class MyInner {
            public void seeReferences() {
                System.out.println(String.format("Inner class reference: %s", this));
                /*
                 * Notice the "this" followed by the Outer class's name.
                 * Don't confuse it with static call. Remember -> you can't use "this" in a static context!!
                 */
                System.out.println(String.format("Outer class reference: %s", MyOuter.this));

                MyOuter.this.sayMyOuterHi();
                System.out.println();
            }
        }
    }

}

class CreatingMethodLocalInnerClass {
    public static void main(String[] args) {
        CreatingMethodLocalInnerClass runner = new CreatingMethodLocalInnerClass();

        MyOuter myOuter = runner.new MyOuter();
        myOuter.createInnerClassFromWithingMethod();
    }

    class MyOuter {
        private final int x = 10;

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
        ReferencingMethodLocalInnerClass runner = new ReferencingMethodLocalInnerClass();
        ReferencingMethodLocalInnerClass.MyOuter myOuter = runner.new MyOuter();
        myOuter.createInnerClassFromWithingMethod();
        System.out.println(runner.referenceToMethodLocalInstance);

    }

    class MyOuter {
        private final int x = 10;

        public void createInnerClassFromWithingMethod() {
            final int localVar = 0;
            class MyMethodLocalInner {

                public final int methodLocalInnerVar = 22;

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

/*
 * - Link: https://stackoverflow.com/questions/27738935/why-is-an-anonymous-class-in-a-static-context-valid
 * -
 */
class CreatingAnonymousInnerClass {


    public static void main(String[] args) {


        CreatingAnonymousInnerClass runner = new CreatingAnonymousInnerClass();

        runner.createAnonymousInterfaceImplementer();
        runner.createAnonymousInnerClass_ViaNoArgConstruct();
        runner.createAnonymousInnerClass_ViaArgConstruct();

        runner.createInterfaceImplementerOfInnerInterface();

    }

    /*
     * Error: non-static variable this cannot be referenced from a static context
     */
    public static void createAnonymousInnerClass_FromStaticContext() {
     /*   MyClass myClass = new MyClass() {

            @Override
            void overrideMe() {
            }
        };*/
    }

    /*
     * However, Anonymous Interface Implementer works just fine from within a static context.
     * That's because there's no relationship between the interface and the enclosing class.
     */
    public static void createAnonymousInterfaceImplementer_FromStaticContext() {
        MyInterface myInterface = new MyInterface() {
            @Override
            public void overrideMe() {
                super.toString();
            }
        };
    }

    // This works as well from "static" context
    public void createInterfaceImplementerOfInnerInterface() {
        MyInterface.InnerInterface innerInterface = new MyInterface.InnerInterface() {
            @Override
            public void overrideInner() {
                System.out.println("--- Creating an Anonymous Interface implementer of an Inner Interface ---");
                System.out.println(getClass().getEnclosingMethod().getName());
            }
        };
        innerInterface.overrideInner();
    }

    /*
     * Can't declare an Anonymous Inner Class of an Inner Class as "static"!! Though it's allowed in the case of the Interface Implementer!
     * This is because of the enclosing class; i.e. the outer class. The inner class needs to maintain a reference to it.
     * If this needs to be declared "static" then the inner class needs to be a "Static Top-Level Nested Class" instead!!
     *
     * However, it's allowed to declare an Anonymous Inner Class as "static" as long as it's not a "regular" Inner Class itself!!
     */
    /*static MyClass myClass = new MyClass() {

        @Override
        void overrideMe() {
        }
    };*/

    public void createLambdaOfInnerInterface() {
        MyInterface.InnerInterface innerInterface = () -> System.out.println(getClass().getEnclosingMethod().getName());
    }

    // Flavor One
    public void createAnonymousInterfaceImplementer() {
        MyInterface myInterface = new MyInterface() {
            @Override
            public void overrideMe() {
                System.out.println(String.format("%s@overrideMe() printing..", getClass().getEnclosingMethod().getName()));
                System.out.println();
            }
        };
        myInterface.overrideMe();
    }

    // Flavor Two -- A
    public void createAnonymousInnerClass_ViaNoArgConstruct() {
        MyStaticClass myStaticClass = new MyStaticClass() {

            {
//                System.out.println(super.getClass() == this.getClass());
            }

            @Override
            void overrideMe() {
                System.out.println(String.format("%s@overrideMe() printing super.x: %s", getClass().getEnclosingMethod().getName(), super.str));
                System.out.println();
            }
        };
        myStaticClass.overrideMe();
    }

    // Flavor Two -- B
    public void createAnonymousInnerClass_ViaArgConstruct() {
        MyStaticClass myStaticClass = new MyStaticClass("Passed From Super Class") {

            String str = "From Anonymous Inner Class";

            @Override
            void overrideMe() {
                System.out.println(String.format("%s@overrideMe() printing this.x: %s", getClass().getEnclosingMethod().getName(), this.str));
                System.out.println(String.format("%s@overrideMe() printing super.x: %s", getClass().getEnclosingMethod().getName(), super.str));
                System.out.println();
            }
        };
        myStaticClass.overrideMe();
    }


    interface MyInterface {
        void overrideMe();

        /*
         * Nested interfaces are static by default!!
         * This inner interface is similar to the Static Top-Level Nested Class. Be default, it's "static"!
         * It's more of a way to provide a namespace.
         */
        interface InnerInterface {
            void overrideInner();
        }

    }

    abstract static class MyStaticClass {
        String str = "Hard Coded in Super";

        public MyStaticClass() {

        }

        public MyStaticClass(String str) {
            this.str = str;
        }

        abstract void overrideMe();
    }

    abstract class MyClass {
        String str = "Hard Coded in Super";

        public MyClass() {

        }

        public MyClass(String str) {
            this.str = str;
        }

        abstract void overrideMe();
    }
}

class AnonymousInnerClassAndInterfaceAsFields {

    /*
     * Unlike Anonymous Inner Class of an Inner Class, it's allowed to declare an Anonymous Interface Implementer as a "static".
     * However, it's also allowed to declare an Anonymous Inner Class as "static" as long as it's not a "regular" Inner Class itself!!
     */
    //Class Field
    static CreatingAnonymousInnerClass.MyInterface myInterface = new CreatingAnonymousInnerClass.MyInterface() {


        {
            System.out.print("calling overrideMe() from a block: ");
            overrideMe(); // returns null!! Why?
            System.out.println();
        }

        @Override
        public void overrideMe() {
            System.out.println("--- calling MyInterface.overrideMe() ---");
            System.out.println(this.getClass().getEnclosingMethod());// returns null!! Why? because the Anonymous Inner Class is not being called from a method
            System.out.println(super.getClass().getEnclosingMethod());// returns null!! Why? because the Anonymous Inner Class is not being called from a method
            System.out.println();
        }
    };
    /*
     * However, it's allowed to declare an Anonymous Inner Class as "static" as long as it's not an Inner Class itself!!
     */
    //Class Field
    static CreatingAnonymousInnerClass.MyStaticClass myStaticClass = new CreatingAnonymousInnerClass.MyStaticClass() {
        @Override
        void overrideMe() {
            System.out.println("--- myStaticClass.toString() ---");
            System.out.println(getClass().getEnclosingMethod()); // returns null! Why? because the Anonymous Inner Class is not being called from a method
            System.out.println();
        }
    };
    // Instance Field
    CreatingAnonymousInnerClass.MyInterface myInterface2 = new CreatingAnonymousInnerClass.MyInterface() {


        {
            /*System.out.print("calling overrideMe() from a block: ");
            overrideMe(); // returns null!! Why?
            System.out.println();*/
        }

        @Override
        public void overrideMe() {
            System.out.println("--- calling MyInterface2.overrideMe() ---");
            System.out.println(this.getClass().getEnclosingMethod());// returns null!! Why? because the Anonymous Inner Class is not being called from a method
            System.out.println(super.getClass().getEnclosingMethod());// returns null!! Why? because the Anonymous Inner Class is not being called from a method
            System.out.println();
        }
    };
    // Instance Field
    CreatingAnonymousInnerClass.MyStaticClass myStaticClass2 = new CreatingAnonymousInnerClass.MyStaticClass() {
        @Override
        void overrideMe() {
            System.out.println("--- myStaticClass2.toString() ---");
            System.out.println(getClass().getEnclosingMethod()); // returns null! Why? because the Anonymous Inner Class is not being called from a method
            System.out.println();
        }
    };

    public static void main(String[] args) {
        AnonymousInnerClassAndInterfaceAsFields anonymousInnerClassAndInterfaceAsFields = new AnonymousInnerClassAndInterfaceAsFields();
        myInterface.overrideMe();
        anonymousInnerClassAndInterfaceAsFields.myInterface2.overrideMe();
        myStaticClass.overrideMe();
        anonymousInnerClassAndInterfaceAsFields.myStaticClass2.overrideMe();

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

class AbstractOuterWithAbstractInner {
    public static void main(String[] args) {
        AbstractOuterWithAbstractInner runner = new AbstractOuterWithAbstractInner();
        runner.createAbstractClasses();
    }

    public void createAbstractClasses() {
        AbstractOuter abstractOuter = new AbstractOuter() {
            @Override
            public int getNum() {
                return 22;
            }
        };

        AbstractOuter.AbstractInner abstractInner = abstractOuter.new AbstractInner() {
            @Override
            public int getNum() {
                return 57;
            }
        };

        System.out.println(String.format("%d %d", abstractInner.getNum(), abstractOuter.getNum()));
    }

    public abstract class AbstractOuter {
        public int getNum() {
            return 45;
        }

        public abstract class AbstractInner {
            public int getNum() {
                return 38;
            }

        }
    }
}