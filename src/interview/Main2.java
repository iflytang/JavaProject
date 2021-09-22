package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {

    static final int BASE = 100000007;
    static int len;
    static long res;

    public static void main (String [] args) throws IOException {

        /**  输出奇数的回文子序列个数
         * input: aba
         * output: 4，对应a、b、a、aba。
         *
         * input: ababa
         * output: 12
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            long res = solution(str);
            long ret = res % BASE;
            System.out.println(ret);

//            char [] chars = str.toCharArray();
//            len = chars.length;
//            res = 0;
//
//            for (int i = 0; i < len; i++) {
//                solution2(chars, i, i);
//            }
//
//            long ret = res % BASE;
//            System.out.println(ret);
        }

    }

    public static long solution (String str) {
        int len = str.length();

        long [][] dp = new long[len][len];

        for (int j = 0; j < len; j++) {
            dp[j][j] = 1;

            for (int i = j - 1; i >= 0; i--) {
                if (str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] + 1;
//                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1];
//                    System.out.println(dp[i][j]);
                } else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                }

//                System.out.println(dp[i][j]);
            }
        }

//        System.out.println(dp[0][len - 1]);
//        System.out.println(Arrays.deepToString(dp));

        return dp[0][len - 1];

    }

    public static void solution2 (char [] chars, int left, int right) {
        if (chars[left] == chars[right]) {
            res++;
        }

        for (int i = 1; left - i >= 0; i++) {
            for (int j = 1; right + j < len; j++) {
                if (chars[left - i] == chars[right + j]) {
                    solution2(chars, left - i, right + j);
                }
            }
        }

    }



}






















