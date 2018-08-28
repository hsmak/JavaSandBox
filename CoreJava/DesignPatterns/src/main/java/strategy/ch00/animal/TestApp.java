package strategy.ch00.animal;

import strategy.ch00.animal.strategy.ItFlys;

/**
 * Created by hsmak on 4/13/15.
 */
public class TestApp{

    public static void main(String[] args){

        Animal sparky = new Dog();
        Animal tweety = new Bird();

        System.out.println("Dog: " + sparky.tryToFly());

        System.out.println("Bird: " + tweety.tryToFly());

        // This allows dynamic changes for flyingType

        sparky.setFlyingAbility(new ItFlys());

        System.out.println("Dog: " + sparky.tryToFly());

    }

}