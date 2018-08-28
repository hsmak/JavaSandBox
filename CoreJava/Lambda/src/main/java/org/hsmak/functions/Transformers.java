package org.hsmak.functions;

import java.util.List;
import java.util.function.Function;

/**
 * Created by hsmak on 11/12/16.
 */
public class Transformers {

    //what is the effect if declared static?
    private final static Function<String, Integer> wordCount = w -> w.length();

    public <T, R> R mapSum(T word, Function<T, R> f){
        return f.apply(word);
    }

    public static void main(String[] args){
        Transformers t = new Transformers();
        t.test();
        System.out.println(t.mapSum("husain", wordCount));
    }

    public void test(){
        Transformers t = new Transformers();
        System.out.println(t.mapSum("husain", wordCount));
    }
}
