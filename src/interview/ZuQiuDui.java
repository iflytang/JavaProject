package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ZuQiuDui {

    public static long sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);

            int [][] ab = new int[n][2];

            for (int i = 0; i < n; i++) {
                String [] substr = br.readLine().split(" ");
                ab[i][0] = Integer.parseInt(substr[0]);
                ab[i][1] = Integer.parseInt(substr[1]);
            }

            sum = 0;
            solution(n, ab, 0, 0, 0, 0, 0);
            System.out.println(sum);
        }
    }

    public static void solution (int n, int [][] ab, int k,
                                 long basketball, int n1,
                                 long soccer, int n2) {
        if (k == n) {
            if ((basketball + soccer > sum) && (n1 == n2)) {
                sum = basketball + soccer;
            }

            return;
        }

        solution(n, ab, k + 1, basketball + ab[k][0], n1 + 1, soccer, n2);
        solution(n, ab, k + 1, basketball, n1, soccer  + ab[k][1], n2 + 1);
    }
}
