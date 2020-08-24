package visitor.model;

import visitor.visitors.Visitor;

/**
 * Created by hsmak on 1/30/16.
 */
//concrete element

public class Book implements Visitable {

    private double price;

    private double weight;

    //accept the visitor

    public void accept(Visitor visitor) {

        visitor.visit(this);

    }

    public double getPrice() {

        return price;

    }

    public double getWeight() {

        return weight;

    }

}