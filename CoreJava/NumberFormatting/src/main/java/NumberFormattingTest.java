import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by hsmak on 3/8/15.
 */
public class NumberFormattingTest {
    public static void main(String[] args){

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        Double d = 12345678915.126;
        String doubleStr = decimalFormat.format(d);

        double d1 = 2.15;
        double d2 = 1.05;
        System.out.println(d1-d2);
        System.out.println(new DecimalFormat("#0.00").format(d1-d2));
        System.out.println(new BigDecimal(d1-d2));

        System.out.println(doubleStr);
    }
}
