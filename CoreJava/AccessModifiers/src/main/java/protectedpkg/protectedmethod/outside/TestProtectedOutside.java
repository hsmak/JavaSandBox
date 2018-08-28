package protectedpkg.protectedmethod.outside;

import protectedpkg.protectedmethod.inside.ParentInsidePkg;

/**
 * Created by hsmak on 3/18/15.
 */
public class TestProtectedOutside {
    public static void main(String[] args){
        ParentInsidePkg parentInsidePkg = new ParentInsidePkg();
//        parentInsidePkg.// Protected methods can only be accessed from inside their package or called from a subclass

        ChildOutsidePkg childOutsidePkg = new ChildOutsidePkg();
        childOutsidePkg.protectedMethod();
        childOutsidePkg.callParentProtectedMethod();
    }
}

class ChildOutsidePkg extends ParentInsidePkg{
    @Override
    protected void protectedMethod(){
        System.out.println("I'm an Overridden Protected Method from a child Outside the Package");
    }

    protected void callParentProtectedMethod(){
        super.protectedMethod();
    }
}