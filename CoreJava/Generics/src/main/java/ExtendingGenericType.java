import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsmak on 3/10/15.
 *
 * Links:
 *      1) http://stackoverflow.com/questions/2776975/how-can-i-add-to-list-extends-number-data-structures
 *      2) http://stackoverflow.com/questions/4492950/java-generics-arraylist-initialization
 *      3) http://stackoverflow.com/questions/2723397/java-generics-what-is-pecs
 *
 *
 * Get And Put Principle (From Java Generics and Collections) -> GEPS == PECS

 It states,

 use an extends wildcard when you only get values out of a structure
 use a super wildcard when you only put values into a structure
 and don’t use a wildcard when you both get and put.

 */
public class ExtendingGenericType {
    public static void main(String[] args){

//        List<Number> numbers = new ArrayList<Integer>();// Invalid
        List<? super Integer> numbers = new ArrayList<Integer>();//allowed but for RO operations?? i.e. useful for upcasting, method parameters etc
        List<? extends Number> i = (List<Integer>)numbers;
//        List<? super Number> numbers = new ArrayList<Number>();
//        numbers.add(new Integer(1));
//        numbers.add(new Double(2.2));
//        Integer aa = (Integer)numbers.get(1);

    }

    public static void GetPolymorphicSubTypeFromGEPS(){

    }

    public static void PutPolymorphicSubTypeFromGEPS(){

    }


}
