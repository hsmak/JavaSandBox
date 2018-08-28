package strategy.ch00.animal;

import strategy.ch00.animal.strategy.ItFlys;

/**
 * Created by hsmak on 4/13/15.
 */
public class Bird extends Animal{

    // The constructor initializes all objects

    public Bird(){

        super();

        setSound("Tweet");

        // We set the Flys interface polymorphically
        // This sets the behavior as a non-flying Animal

        flyingType = new ItFlys();

    }

}