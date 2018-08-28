package strategy.ch00.calculate;

import strategy.ch00.calculate.strategy.IStrategy;

/**
 * Created by hsmak on 4/16/15.
 */
public class Context {
    private IStrategy strategy;

    public Context(IStrategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}