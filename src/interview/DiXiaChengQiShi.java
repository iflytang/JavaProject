package src.interview;

public class DiXiaChengQiShi {
    public static void main (String [] args) {
        int [][] dungeon = new int[][] {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };
        System.out.println(solution(dungeon));
    }

    public static int solution (int [][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        int [][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[m][n - 1] = 1;
        dp[m - 1][n] = 1;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int minn1 = Math.min(dp[i + 1][j], dp[i][j + 1]);
//                int minn2 = Math.min(dp[i - 1][j], dp[i][j - 1]);
//                int minn = Math.min(minn1, minn2);

                dp[i][j] = Math.max(minn1 - dungeon[i][j], 1);
            }
        }

        return dp[0][0];
    }

}
