package io_nio.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by hsmak on 3/14/15.
 */
public class DeserializeObject {
    public static void main(String[] args) {

        try {

            FileInputStream fileInputStream = new FileInputStream("EmployeeObject.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            EmployeeObject o = (EmployeeObject) objectInputStream.readObject();

            System.out.println(o);

            fileInputStream.close();
            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
