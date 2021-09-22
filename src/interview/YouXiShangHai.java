package src.interview;

import java.util.*;

/**
输入：
3
3 2
0 2
0 3
0 4
3 2
0 4
0 4
1 4
5 6
0 4
0 4
0 4
0 4
0 4

输出：
1060000
1092727
930393309
 */

public class YouXiShangHai {

    private static int[][] aw;
    private static int N;
    private static int K;
    private static long max_ans;
    private static final int C = 1000000007;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        long[] ans = new long[T];
        for(int i = 0; i < T; i++) {
            N = in.nextInt();
            K = in.nextInt();
            aw = new int[N][2];
            for(int j = 0; j < N; j++) {
                aw[j][0] = in.nextInt();
                aw[j][1] = in.nextInt();
            }
            max_ans = 0;
            dfs(K);
            ans[i] = max_ans;
        }
        for(int i = 0; i < T; i++) {
            System.out.println(ans[i]);
        }
    }

    public static void dfs(int dep) {
        if(dep == 0) {
            long tmp = cal_e();
            max_ans = Math.max(max_ans, tmp);
            return;
        }
        for(int i = 0; i < N; i++) {
            aw[i][0]++;
            dfs(dep - 1);
            aw[i][0]--;
        }
    }

    public static long cal_e() {
        long res = 1;
        for(int i = 0; i < N; i++) {
            res *= (100 - aw[i][0] + aw[i][0] * aw[i][1]);
            res %= C;
        }
        return res;
    }
}
