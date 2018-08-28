package visitor.visitors;

import visitor.model.Book;

/**
 * Created by hsmak on 1/30/16.
 */
public interface Visitor{

    public void visit(Book book);



    //visit other concrete items

//    public void visit(CD cd);

//    public void visit(DVD dvd);

}