package streamOps.methodreference;


import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * Created by hsmak on 2/11/16.
 */
public class MethodReference {

    public static void main(String[] args) {

        ArrayList<Person> persons = new ArrayList<>();
        Person p1 = new Person("Husain", 31, "4407 Hoposon Rd");
        Person p2 = new Person("Ingrid", 32, "4407 Hoposon Rd");
        persons.add(p1);
        persons.add(p2);

        System.out.println("Method Reference");
        for (Person p : persons) {
            filterPerson(p, MethodReference::equalToPersonHusain);
        }

        for (Person p : persons) {
            p.filter(MethodReference::equalToStringHusain);
        }

        for (Person p : persons) {
            filter(p, MethodReference::equalToPersonHusain);
        }

        filter(persons, MethodReference::equalToPersonHusain);

        /*
         ***************************
         */
        System.out.println("Lambda");
        /*
         ***************************
         */

        for (Person p : persons) {
            filterPerson(p, (Person pp) -> "Husain".equalsIgnoreCase(pp.getName()));
        }

        for (Person p : persons) {
            p.filter((String s) -> "Husain".equalsIgnoreCase(s));
        }

        for (Person p : persons) {
            filter(p, (Person pp) -> "Husain".equalsIgnoreCase(pp.getName()));
        }

        filter(persons, (Person pp) -> "Husain".equalsIgnoreCase(pp.getName()));
    }

    public static void filterPerson(Person person, Predicate<Person> predicate) {
        if (predicate.test(person))
            System.out.println(person);
    }

    public static <T> void filter(T t, Predicate<T> predicate) {
        if (predicate.test(t))
            System.out.println(t);
    }

    public static <T> void filter(Collection<T> elements, Predicate<T> predicate) {
        for (T element : elements) {
            if (predicate.test(element))
                System.out.println(element);
        }
    }

    /**
     * This is a Referenced Method
     * If this is used only in one place then use the Anonymous Function style, lambda
     *
     * @param p
     * @return
     */
    public static boolean equalToPersonHusain(Person p) {
        return "Husain".equalsIgnoreCase(p.getName());
    }

    /**
     * This is a Referenced Method
     *
     * @param p
     * @return
     */
    public static boolean equalToStringHusain(String p) {
        return "Husain".equalsIgnoreCase(p);
    }
}


class Person {
    private String name;
    private Integer age;
    private String address;

    public Person(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * A Referenced Method
     *
     * @param predicate
     */
    public void filter(Predicate<String> predicate) {
        if (predicate.test(name))
            System.out.println(this);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}