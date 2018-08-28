package wildcard;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by hsmak on 3/10/15.
 * <p/>
 * PECS
 * <? extends T>:   Upper bound
 * <? super T>:     Lower Bound
 */
public class UpperBound {
    public static void main(String[] args) {

        List<Integer> ints = Arrays.asList(1, 2, 3);
        System.out.println(sum(ints));
        assert sum(ints) == 6.0;

        List<Double> doubles = Arrays.asList(2.78, 3.14);
        System.out.println(sum(doubles));
        assert sum(doubles) == 5.92;

        List<Number> nums = Arrays.<Number>asList(1, 2, 2.78, 3.14);
        System.out.println(sum(nums));
        assert sum(nums) == 8.92;

    }

    public static double sum(Collection<? extends Number> nums) {

        double s = 0.0;
        for (Number num : nums)
            s += num.doubleValue();
        return s;

    }
}
