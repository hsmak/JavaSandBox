package strategy.ch00.calculate;

import strategy.ch00.calculate.strategy.OperationAdd;
import strategy.ch00.calculate.strategy.OperationMultiply;
import strategy.ch00.calculate.strategy.OperationSubstract;

/**
 * Created by hsmak on 4/16/15.
 */
public class TestApp {

    public static void main(String[] args) {

        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubstract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));

    }
}