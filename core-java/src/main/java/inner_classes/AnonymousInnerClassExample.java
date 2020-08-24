package inner_classes;

/**
 * Created by hsmak on 3/2/15.
 *
 * Anonymous Inner Classes either:
 *                  > extend a class, or
 *                  > implement an interface
 */
public class AnonymousInnerClassExample {

    public static void main(String[] args) {

        AnonymousInnerClassExample a = new AnonymousInnerClassExample() {//this is an anonymous inner class

            /**
             * This is the Overriden method
             * Because of polymorphism, only overriden methods can be invoked
             */
            @Override
            public void outerMethod() {
                System.out.println("This method from Anonymous Inner Class is overriding the outer method outerMethod()");
            }

            /**
             * This is a new method added to the inner class
             * Newly added methods can never be invoked because:
             *      > the new class is anonymous, and
             *      > it's assigned to the polymorphic type; i.e. the parent type
             */
            public void innerMethod() {
                System.out.println("This method can't be call because it's not in the parent class");
            }
        };

        a.outerMethod();
        //a.innerMethod(); // compile-time error

    }

    public void outerMethod() {
        System.out.println("This method will be overriden by the Anonymous Inner Class");
    }
}
