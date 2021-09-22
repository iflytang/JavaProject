package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class longestDengCha {
    public static void main (String [] args) throws IOException {
        solution();
    }

    public static void solution() {
        int [] s1 = {3, 1, 2, 3, 4, 5};
        int [] s2 = {7, 8, 9, 10, 11, 12};

        int m = s1.length;
        int n = s2.length;

        int [] ds1 = new int[m - 1];
        int [] ds2 = new int[n - 1];
        for (int i = 0; i < m - 1; i++) {
            ds1[i] = s1[i + 1] - s1[i];
        }

        for (int j = 0; j < n - 1; j++) {
            ds2[j] = s2[j + 1] - s2[j];
        }

        int [][] dp = new int[m][n];
        int start1 = 0, start2 = 0;
        int maxLen = 0;
        for (int i = 1; i <= m - 1; i++) {
            for (int j = 1; j <= n - 1; j++) {

                if (ds1[i - 1] == ds2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        start1 = i - maxLen;
                        start2 = j - maxLen;
                    }
                }
            }
        }

        int [] ret1 = Arrays.copyOfRange(s1, start1, start1 + maxLen + 1);  // 等差，需加1
        int [] ret2 = Arrays.copyOfRange(s2, start2, start2 + maxLen + 1);  // 等差，需加1

        System.out.println(start1 + " " + start2 + " " + maxLen);
        System.out.println(Arrays.toString(ret1));
        System.out.println(Arrays.toString(ret2));

    }
}
