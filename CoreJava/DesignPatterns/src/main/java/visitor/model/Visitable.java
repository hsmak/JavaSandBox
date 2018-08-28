package visitor.model;

import visitor.visitors.Visitor;

/**
 * Created by hsmak on 1/30/16.
 */
//Element interface
public interface Visitable{

    public void accept(Visitor visitor);

}