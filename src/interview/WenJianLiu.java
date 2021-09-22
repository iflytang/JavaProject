package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WenJianLiu {
    public static void main (String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String a = br.readLine();

        long res = solution(s, a);
        System.out.println(res);

    }

    public static long solution (String s, String a) {
        int n = s.length();
        int m = a.length();

        for (int i = 0; i < m; i++) {
            if (!s.contains(a.charAt(i) + "")) {
                return -1;
            }
        }

        int i = 0;
        int j = 0;
        long res = 0;

        while (j < m) {
            if (s.charAt(i) != a.charAt(j)) {
                res++;
            } else {
                if (j == m - 1) {
                    return res;
                }
                j++;
            }

            if (i != n - 1) {
                i++;
            } else {
                i = 0;
            }
        }

        return res;

    }
}
