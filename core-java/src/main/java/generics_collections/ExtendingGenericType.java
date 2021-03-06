package generics_collections;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by hsmak on 3/10/15.
 * <p>
 * Links:
 * 1) http://stackoverflow.com/questions/2776975/how-can-i-add-to-list-extends-number-data-structures
 * 2) http://stackoverflow.com/questions/4492950/java-generics-arraylist-initialization
 * 3) http://stackoverflow.com/questions/2723397/java-generics-what-is-pecs
 * <p>
 * <p>
 * Get And Put Principle (From Java Generics and Collections) -> GEPS == PECS
 * <p>
 * It states,
 * <p>
 * use an extends wildcard when you only get values out of a structure
 * use a super wildcard when you only put values into a structure
 * and don’t use a wildcard when you both get and put.
 */

class Employee {
    String firstName;
    String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

class Salaried extends Employee {
    double salary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

public class ExtendingGenericType {


    //ToDo
    public static void printForPredicate(List<? extends Employee> l, Predicate<? super Employee> p) {
        for(Employee e : l)
            System.out.println(p.test(e));

    }

    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
    }


    public static void main(String[] args) {

        printForPredicate(new ArrayList<Employee>(), p -> p.firstName != "");

        addNumbers(new ArrayList<Number>());
        addNumbers(new ArrayList<Integer>());
        addNumbers(new ArrayList<Object>());
//        addNumbers(new ArrayList<Double>()); // compile error


//        List<Number> numbers = new ArrayList<Integer>();// Invalid
        List<? super Integer> numbers = new ArrayList<Integer>();//allowed but for RO operations?? i.e. useful for upcasting, method parameters etc
        List<? extends Number> i = (List<Integer>) numbers; // Downcasting this way makes the compiler issue a warning
//        List<? super Number> numbers = new ArrayList<Number>();
//        numbers.add(new Integer(1));
//        numbers.add(new Double(2.2));
//        Integer aa = (Integer)numbers.get(1);

    }

    public static void GetPolymorphicSubTypeFromGEPS() {

    }

    public static void PutPolymorphicSubTypeFromGEPS() {

    }


}
