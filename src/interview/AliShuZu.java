package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class AliShuZu {
    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
          int n = Integer.parseInt(str);

          String [] substr = br.readLine().split(" ");
          long [] a = new long[n];
          for (int i = 0; i < n; i++) {
              a[i] = Long.parseLong(substr[i]);
          }

          System.out.println(solution(a, n));

        }

    }

    public static long solution (long [] a, int n) {

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                a[j] = a[j + 1] - a[j];
//                System.out.println(a[j]);
            }
//            System.out.println(Arrays.toString(a));
        }

        return a[0] % (1000000007);
    }

}
