package generics_collections.collections;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by hsmak on 3/12/15.
 */
public class Sorting {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.<Integer>asList(1, 4, 2, 7, 8, 4, 5, 99, 22, 11);
        Collections.sort(integerList);
        System.out.println(integerList);

        Integer[] i = integerList.toArray(new Integer[]{});
        System.out.println(ArrayUtils.toString(i));
    }
}
