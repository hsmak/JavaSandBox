package _oo.cloning.com.clone;

public class Person implements Cloneable {

    private String name;
    private Address address;

    public Person() {

    }

    public Person(String name) {
        super();
        this.name = name;
    }

    public Person(String name, Address address) {
        this(name);
        this.address = address;
    }

    @Override
    protected Object clone() {
        try {
            Person p = (Person) super.clone();
//			p.address = (Address)p.address.clone();			
            return p;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Person shallowCloning() {
        Person p = (Person) this.clone();
        return p;
    }

    public Person deepCloning() {
        Person p = (Person) this.clone();
        p.address = (Address) p.address.clone();
        return p;
    }

    @Override
    public String toString() {
        if (this.address != null)
            return "Name: " + this.name + "\nAddress: " + this.address.toString();
        else
            return "Name: " + this.name + "\nAddress: No Address Entered!";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    /*
     * setAddress()
     * change the variable reference to a new instance
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /*
     * changeAddres()
     * modify the current Address instance
     * it doesn't create a new instance compared to setAddress()
     */
    public void changeAddress(String address1, String address2, String state, int zipcode,
                              String country) {
        this.address.setAddress1(address1);
        this.address.setAddress2(address2);
        this.address.setState(state);
        this.address.setZipcode(zipcode);
        this.address.setCountry(country);
    }

    public void changeAddress(Address address) {
        this.address.setAddress1(address.getAddress1());
        this.address.setAddress2(address.getAddress2());
        this.address.setState(address.getState());
        this.address.setZipcode(address.getZipcode());
        this.address.setCountry(address.getCountry());
    }


}
