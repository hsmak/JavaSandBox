package com.clone;

public class Address implements Cloneable {

    private String address1;
    private String address2;
    private String state;
    private int zipcode;
    private String country;

    public Address() {
        super();
    }

    public Address(String address1, String address2, String state, int zipcode,
                   String country) {
        this.address1 = address1;
        this.address2 = address2;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
    }

    @Override
    protected Object clone() {
        // protected Object clone() throws CloneNotSupportedException {
        try {
            Address addressInstance = (Address) super.clone();
            return addressInstance;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        boolean validAddres = (address1 != null && address2 != null
                && state != null && zipcode != 0 && country != null);
        if (validAddres)
            return address1 + ", " + address2 + ", " + state + "-" + zipcode
                    + ", " + country;
        else
            return "No Address Found!";
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
