package src.interview;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class ArraySort {
    public static void main (String [] args) throws IOException {
        String [][] students = new String[][] {
                {"1", "Alice", "12"},  // id, name, age
                {"4", "Tom", "16" },
                {"2", "Anna", "12"},
                {"9", "Bob", "15"},
                {"6", "Jack", "15"}
        };

        Arrays.sort(students, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if (Integer.parseInt(o1[2]) == Integer.parseInt(o2[2])) {
                    return o1[1].compareTo(o2[1]);
                } else {
                    return Integer.parseInt(o2[2]) - Integer.parseInt(o1[2]);
                }
            }
        });

        System.out.println(Arrays.deepToString(students));
    }
}
