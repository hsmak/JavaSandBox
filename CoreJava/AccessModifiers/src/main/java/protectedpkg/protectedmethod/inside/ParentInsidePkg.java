package protectedpkg.protectedmethod.inside;

/**
 * Created by hsmak on 3/18/15.
 */
class TestProtectedInside {

    public static void main(String[] args){
        ParentInsidePkg parentInsidePkg = new ParentInsidePkg();
        parentInsidePkg.protectedMethod();

        ChildInsidePkg childInsidePkg = new ChildInsidePkg();
        childInsidePkg.protectedMethod();
        childInsidePkg.callParentProtectedMethod();
    }

}

public class ParentInsidePkg {
    protected void protectedMethod(){
        System.out.println("I'm a Protected Method from a Parent inside a Package");
    }
}

class ChildInsidePkg extends ParentInsidePkg {
    @Override
    protected void protectedMethod(){
        System.out.println("I'm an Overriden Protected Method from a Child inside a Package");
    }

    protected void callParentProtectedMethod(){
        super.protectedMethod();
    }
}