/**
 * Created by hsmak on 3/2/15.
 */
class MethodLocalInnerClassExample {

    public static void main(String[] args) {

        Object[] o = new Object[2];
        testInnerClass(o);
        System.out.println(o[0]);

    }

    public static void testInnerClass(Object[] o) {

        class MyInnerClass {

            void callLocalInnerClassMethod() {
                System.out.println("This is a method from a Method-Local Inner Class");
            }

            @Override
            public String toString(){
                return "This is the overriden toString() method from a Method-Local Inner Class";
            }

        }

        MyInnerClass m = new MyInnerClass();
        m.callLocalInnerClassMethod();

        o[0] = m;
    }
}
