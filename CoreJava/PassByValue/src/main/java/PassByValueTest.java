/**
 * Created by hsmak on 3/8/15.
 */
public class PassByValueTest {
    public static void main(String[] args) {
        Integer x = 1;
        Integer y = 2;
        System.out.println("Swapping immutable Integer objects:");
        System.out.println(x + " " + y);
        swapImmutableWrapperInteger(x, y);//won't work because it's immutable
        System.out.println(x + " " + y);


        int[] xArr = {1};
        int[] yArr = {2};
        System.out.println("Swapping int[] objects:");
        System.out.println(xArr[0] + " " + yArr[0]);
        swapIntValuesByArrays(xArr, yArr);
        System.out.println(xArr[0] + " " + yArr[0]);
    }

    //we can't swap immutable objects
    public static void swapImmutableWrapperInteger(Integer x, Integer y) {
        Integer temp = x;
        x = y;
        y = temp;
    }

    public static void swapIntValuesByArrays(int[] x, int[] y) {
        int temp = x[0];
        x[0] = y[0];
        y[0] = temp;
    }
}
