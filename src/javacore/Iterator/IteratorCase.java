package javacore.Iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tsf on 17-11-30.
 */


public class IteratorCase {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        Iterator lsIterator = list.iterator();
        for(; lsIterator.hasNext(); ){
            System.out.println(lsIterator.next());
        }
    }
}
