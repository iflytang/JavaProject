package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class KuangShi1 {
    public static void main (String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String [] s = br.readLine().split(" ");

        long n = Integer.parseInt(s[0]);
        long m = Integer.parseInt(s[1]);

        ArrayList<Integer> three = new ArrayList<>();
        ArrayList<Integer> seven = new ArrayList<>();
        for (int i = 1; i <= n; i ++) {
            if (isThree(i)) {
                three.add(i);
            }

            if (isSeven(i)) {
                seven.add(i);
            }
        }

        long sum = 0;
        for (int i = 0; i < three.size(); i++) {
            for (int j = 0; j < seven.size(); j++) {
                if (seven.get(j) - three.get(i) >= m - 1) {
                    sum++;
                }
            }
        }

        System.out.println(sum);

    }

    public static boolean isSeven (long num) {
        return num % 7 == 0;
    }

    public static boolean isThree (long num) {
        return num % 3 == 0;
    }

}
