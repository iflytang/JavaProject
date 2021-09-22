package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FangGe {
    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        int n = 0;
        int m = 0;
        while ((str = br.readLine()) != null) {
            String [] substr = str.split(" ");
            n = Integer.parseInt(substr[0]);
            m = Integer.parseInt(substr[1]);

            int [][] grid = new int[n][m];
            for (int i = 0; i < n; i++) {
                String [] numStr = br.readLine().split(" ");
                for (int j = 0; j < m; j++) {
                    grid[i][j] = Integer.parseInt(numStr[j]);
                }
            }

//             int ret = dfs(n, m);
            int ret = dp(n, m, grid);

            System.out.println(ret);
        }

    }

    public static int dp (int n, int m, int [][] grid) {
        int [][] f = new int[n + 1][m + 1];

        // f[0][0] = 1;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                    if (i == 0 && j == 0 && grid[i][j] == 0) {
                        f[i][j] = 1;
                    } else if (grid[i][j] == 0) {
                        if (i == 0) {
                            f[i][j] = f[i][j - 1];
                        } else if (j == 0) {
                            f[i][j] = f[i - 1][j];
                        } else {
                            f[i][j] = f[i - 1][j] + f[i][j - 1];
                        }
                    } else {
                        f[i][j] = 0;
                    }
            }
        }

        return f[n][m];
    }
}