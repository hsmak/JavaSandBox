package intermediate;

/**
 * Created by hsmak on 5/4/15.
 * Links:
 *      1) http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 *      2) https://github.com/winterbe/java8-tutorial/tree/master/src/com/winterbe/java8/samples/stream
 */
public class Person {
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}
