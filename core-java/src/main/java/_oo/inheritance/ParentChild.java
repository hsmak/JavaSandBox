package _oo.inheritance;

/**
 * Created by hsmak on 3/14/15.
 */
public class ParentChild {
    public static void main(String[] args){
        C c = new C();
        c.a();
        c.b();
        c.c();
    }
}

class A{
    int a;
    void a(){
        System.out.println("a");
    }
}

class B extends A{
    void b(){
        System.out.println("b");
    }
}

class C extends B{
    void c(){
        System.out.println("c");
        super.a();
        int c = a;
    }
}
