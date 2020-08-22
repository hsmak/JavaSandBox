package _oo.cloning.com.main;


import _oo.cloning.com.clone.Address;
import _oo.cloning.com.clone.Person;

public class CloneMyObj {

    /**
     * @param args
     */
    public static void main(String[] args) {

        String address1 = "10015 Lake Creek PKWY";
        String address2 = "Apt# 225";
        String state = "TX";
        int zipcode = 78729;
        String country = "US";

        Address add = new Address(address1, address2, state, zipcode, country);

        Person p1 = new Person("Husain", add);

        Person p2 = p1.deepCloning();
//		Person p2 = p1.shallowCloning();
        p2.setName("Another Husain");

        /*
         * setAddress()
         *
         * this will change the Address reference referring to that Address instance to a new one
         * i.e. there are now 2 instances
         * so, changing one of them won't affect each other
         * regardless of ShallowCloning or DeepCloning!
         */
//		p2.setAddress(new Address());



        /*
         * changeAddress()
         *
         * Case1: ShallowCloning
         * 		changing the address will affect the other instance
         *
         * case2: DeepCloning
         * 		changing the address will NOT affect the other instance
         */
        p2.changeAddress("Another address1", "another address2", "another state", 11111, "another country");

        System.out.println(p1);
    }

}
