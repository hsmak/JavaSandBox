/**
 * Created by hsmak on 3/2/15.
 */
public class StaticNestedInnerClassExample {

    public static void main(String[] args){
        StaticNestedInnerClassExample.StaticInnerClass s = new StaticNestedInnerClassExample.StaticInnerClass();
        s.callStaticNestedClassMethod();
    }

    static class StaticInnerClass{
        public void callStaticNestedClassMethod(){
            System.out.println("This is a method from Static Nested Class");
        }
    }

    static StaticInnerClass getInnerClassInstance(){
        return new StaticInnerClass();
    }
}
