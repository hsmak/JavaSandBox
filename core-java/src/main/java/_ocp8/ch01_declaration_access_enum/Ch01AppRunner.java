package _ocp8.ch01_declaration_access_enum;

/*
 * Order of Execution:
 *  - Static Init Blocks:
 *      - Run after class is loaded (when is class loaded? -> Undeterministic?)
 *      - In the order they appear within a class
 *  - Instance Init Blocks:
 *      - Run after calling super(), and after super class's instance init block
 *      - In the order they appear within a class
 */
class ObjectWorkflowOrderRunner {

    public static void main(String[] args) {
        new B();
    }

    static class A {
        static {
            System.out.println("init block - static/class A");
        }

        String name = "default name A";

        {
            System.out.println("init block - instance A: " + name);
        }

        public A() {
            System.out.println(name);
            System.out.println("constructor A");
            name = "another name A";
        }
    }

    static class B extends A {
        static {
            System.out.println("init block - static/class B");
        }

        String name = "default name B";

        {
            System.out.println("init block - instance B: " + name);
        }

        public B() {
            super();
            System.out.println(name);
            System.out.println("constructor B");
            name = "another name B";
            System.out.println(super.name);
        }
    }
}

