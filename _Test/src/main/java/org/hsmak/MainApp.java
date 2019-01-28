package org.hsmak;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class MainApp {
    public static void main(String[] args) throws IOException {

        String utf8 = IOUtils.toString(
                new URL("https://s3.amazonaws.com/apache-zeppelin/tutorial/bank/bank.csv"),
                Charset.forName("utf8"));
        System.out.println(utf8);
    }

    public static void test(){
        System.out.println("ttttttttt");
    }

    public void an(){
        test();
    }
}