/**
 * Created by hsmak on 3/14/15.
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializeObject {
    public static void main(String[] args) {
        EmployeeObject emp = new EmployeeObject();

        emp.setAge(30);
        emp.setGender("Male");
        emp.setName("Husain");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("EmployeeObject.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            //write object to file
            objectOutputStream.writeObject(emp);
            System.out.println("Done");
            //closing resources
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}



class EmployeeObject implements Serializable {

    private static final long serialVersionUID = -299482035708790407L;

    private String name;
    private String gender;
    private int age;
    private String role;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Employee:: Name=" + this.name + " Age=" + this.age + " Gender=" + this.gender +
                " Role=" + this.role;
    }

}
