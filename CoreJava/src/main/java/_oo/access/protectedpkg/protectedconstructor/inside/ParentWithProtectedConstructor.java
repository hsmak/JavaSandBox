package _oo.access.protectedpkg.protectedconstructor.inside;

/**
 * Created by hsmak on 3/18/15.
 */
public class ParentWithProtectedConstructor {
    protected ParentWithProtectedConstructor(){
        System.out.println("I'm a Protected Constructor");
    }
}

class TestProtectedConstructor {
    public static void main(String[] args){
        ParentWithProtectedConstructor parentWithProtectedConstructor = new ParentWithProtectedConstructor();
    }
}