package _oo.access.protectedpkg.protectedconstructor.outside;

import _oo.access.protectedpkg.protectedconstructor.inside.ParentWithProtectedConstructor;

/**
 * Created by hsmak on 3/18/15.
 */
public class ChildFromOutside extends ParentWithProtectedConstructor{
    ChildFromOutside(){
        super();//OK
    }

    void constructParentObj(){
//        new ParentWithProtectedConstructor();// Error: Constructor is protected
    }
}
