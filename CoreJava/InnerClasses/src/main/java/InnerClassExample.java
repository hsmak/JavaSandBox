/**
 * Created by hsmak on 3/2/15.
 */
public class InnerClassExample {

    public static void main(String[] args){

        InnerClassExample outer = new InnerClassExample();

        // new MyInnerClass(); // compile error

        InnerClassExample.MyInnerClass inner = new InnerClassExample().new MyInnerClass();
        inner.callInnerClassMethod();

    }

    class MyInnerClass{

        public void callInnerClassMethod(){
            System.out.println("This is a method from Inner Class");
        }

    }
}
