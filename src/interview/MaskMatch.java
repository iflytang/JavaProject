package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class MaskMatch {

    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            String p1 = str;
            String s1 = br.readLine();
            System.out.println(isMatch2(s1, p1));

            char [] p = p1.toCharArray();  // 注意s和p的顺序
            char [] s = s1.toCharArray();

            System.out.println(isMatch(s, p));
        }
    }

    public static int isMatch(char [] s, char [] p) {
        int m = s.length;
        int n = p.length;

        int [][] dp = new int[m + 1][n + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            if (p[i - 1] == '*') {  // 匹配任意字符0次或任意次
                dp[0][i] = 1;
            } else {
                break;
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p[j - 1] == '?' || s[i - 1] == p[j - 1]) {  // 匹配一次
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p[j - 1] == '#') {
                    dp[i][j] = dp[i - 1][j - 1] | dp[i][j - 1];  // 匹配1次或0次
                } else if (p[j - 1] == '*') {  // 匹配任意多次或0次
                    dp[i][j] = dp[i - 1][j] | dp[i][j - 1];
                }
            }
        }

        return dp[m][n];
    }

    public static int isMatch2(String s, String p) {
        int m = s.length();
        int n = p.length();
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = 1;
            } else {
                break;
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '#') {
                    dp[i][j] = dp[i][j - 1] ^ dp[i - 1][j - 1];}
                else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] ^ dp[i - 1][j];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
