package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ABChuan {
    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);

            long ret = solution(n);

            System.out.println(ret);
        }
    }

    public static long solution(int n) {
        long [][] dp = new long[n][2];

        dp[0][0] = 0;
        dp[0][1] = 2;

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][1] % 998244353;
            dp[i][1] = (dp[i - 1][0] % 998244353) + (dp[i - 1][1] % 998244353);
        }

        return ((dp[n - 1][0] % 998244353) + (dp[n - 1][1] % 998244353)) % 998244353;
    }
}
