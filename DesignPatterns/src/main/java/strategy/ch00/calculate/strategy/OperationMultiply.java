package strategy.ch00.calculate.strategy;

/**
 * Created by hsmak on 4/16/15.
 */
public class OperationMultiply implements IStrategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}
