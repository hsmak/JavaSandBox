package iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by hsmak on 3/5/15.
 */
public class Iterator {
    public static void main(String[] args){
        Integer[] a = new Integer[]{1,2,3,4,5};
        ArrayList<Integer> integers = new ArrayList<Integer>(Arrays.asList(a));

        integers.iterator();
        System.out.println(integers.toString());

        HashMap b = new HashMap();

        HashSet shortSet = new HashSet();
        for (short i = 0; i < 100; i++) {
            shortSet.add(i);
            shortSet.remove(i - 1);
        }
        System.out.println(shortSet.size());
    }

}
