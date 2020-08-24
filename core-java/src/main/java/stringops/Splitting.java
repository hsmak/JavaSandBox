package stringops;

/**
 * Created by hsmak on 3/21/15.
 */

public class Splitting {
    public static void main(String[] args){

        String[] splits = "192.168.10.28".split("[^\\d]");
        for(String s : splits)
            System.out.print(s);

    }
}
