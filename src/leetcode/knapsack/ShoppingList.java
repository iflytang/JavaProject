package src.leetcode.knapsack;

import java.util.*;
import java.io.*;

public class ShoppingList {
    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            String [] strs = str.split(" ");
            int money = Integer.parseInt(strs[0]);
            int items = Integer.parseInt(strs[1]);

            Good [] goods = new Good[items + 1];

            for (int i = 1; i <= items; i++) {  // 第0行和第0列为全0
                str = br.readLine();
                String [] goodStrs = str.split(" ");
                int v = Integer.parseInt(goodStrs[0]);
                int p = Integer.parseInt(goodStrs[1]);
                int q = Integer.parseInt(goodStrs[2]);

                goods[i] = new Good(v, p, q);

                if (q > 0) {  // 存在主件，且最多存在两个附件
                    if (goods[q].s1 == 0) {
                        goods[q].s1 = i;
                    } else {
                        goods[q].s2 = i;
                    }
                }
            }

            // 开始动态规划
            int [][] dp = new int [items + 1][money + 1];  // 动态规划表格
            for (int i = 1; i <= items; i++) {  // items + 1行，第0行为全0

                int v0 = -1, v1 = -1, v2 = -1, v3 = -1;  // 四种的价格和：主件、主件+附件1、主件+附件2、主件+附件1+附件2
                int p0 = 0, p1 = 0, p2 = 0, p3 = 0;      // 四种的价值和

                v0 = goods[i].v;
                p0 = goods[i].p * goods[i].v;

                if (goods[i].s1 != 0) {
                    v1 = v0 + goods[goods[i].s1].v;
                    p1 = p0 + goods[goods[i].s1].p * goods[goods[i].s1].v;
                }

                if (goods[i].s2 != 0) {
                    v2 = v0 + goods[goods[i].s2].v;
                    p2 = p0 + goods[goods[i].s2].p * goods[goods[i].s2].v;
                }

                if (goods[i].s1 != 0 && goods[i].s2 != 0) {
                    v3 = v1 + v2 - v0;
                    p3 = p1 + p2 - p0;
                }

                // 状态转移过程，列
                for (int j = 1; j <= money; j++) {
                    dp[i][j] = dp[i - 1][j];  // 不能放下去

                    if (goods[i].q == 0) {    // 是主件时。附件在这里一起考虑了
                        if (v0 <= j) {
                            dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v0] + p0);
                        }

                        if (v1 != -1 && v1 <= j) {
                            dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v1] + p1);
                        }

                        if (v2 != -1 && v2 <= j) {
                            dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v2] + p2);
                        }

                        if (v3 != -1 && v3 <= j) {
                            dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v3] + p3);
                        }
                    }
                }
            }

            int best = dp[items][money];
            System.out.println(best);
        }
    }
}

class Good {
    int v;  // 物品的价格，相当于权重
    int p;  // 物品的重要程度，相当于价值
    int q;  // 表示主件ID

    int s1; // 附件1ID
    int s2; // 附件2ID

    Good (int v, int p, int q) {
        this.v = v;
        this.p = p;
        this.q = q;
    }
}
