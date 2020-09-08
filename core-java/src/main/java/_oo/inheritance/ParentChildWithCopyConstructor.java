package _oo.inheritance;

import java.util.stream.Stream;

import static java.lang.System.out;

/**
 * Created by hsmak on 3/4/17.
 */
public class ParentChildWithCopyConstructor {

    public static void main(String[] args) {

        Car car1 = new Car(2015, new Engine());
        Car car2 = new Car(2016, new TurboEngine());
        Car car3 = new Car(car2);
        Stream.of(car1, car2, car2).forEach(out::println);

    }
}

class Car {
    private int year;
    private Engine engine;

    public Car() {
    }

    public Car(int year, Engine engine) {
        this.year = year;
//        this.engine = new Engine(engine);// non-polymorphic way. what if this Engine is of type TurboEngine!!
        this.engine = engine.copy();//Polymorphic method
    }

    protected Car(Car otherCar) {
//        this.engine = new Engine(otherCar.getEngine()); non-polymorphic way. what if otherCar has a TurboEngine!!
        this.engine = otherCar.getEngine().copy();//polymorphic method which is good
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car{" +
                "year='" + year + '\'' +
                ", engine=" + engine +
                '}';
    }
}

class Engine {
    public Engine() {
    }

    /**
     * copy-constructor
     *
     * @param otherEngine
     */
    protected Engine(Engine otherEngine) {
    }

    /**
     * to be overridden so it's a Polymorphic method
     *
     * @return
     */
    public Engine copy() {
        return new Engine(this);
    }


    public String toString() {
        return this.getClass().getName() + ":" + hashCode();
    }
}

class TurboEngine extends Engine {
    public TurboEngine() {
        super();
    }

    /**
     * copy-constructore
     *
     * @param turboEngine
     */
    protected TurboEngine(TurboEngine turboEngine) {

    }

    /**
     * Polymorphic method
     *
     * @return
     */
    @Override
    public Engine copy() {
        return new TurboEngine(this);
    }
}