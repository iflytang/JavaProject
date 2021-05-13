package src.nowcoder.SwordOffer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 60. n个骰子的点数
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 *
 *
 *
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 *
 *
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 * 示例 2:
 *
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
 *
 *
 * 限制：
 *
 * 1 <= n <= 11
 */

public class Offer60 {

    public double[] dicesProbability(int n) {
        // n 个骰子，和为 6*n 的次数, dp[0]不使用
        int [][] dp = new int[n + 1][6 * n + 1];

        // 初始化
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }

        // 状态转移
        for (int i = 2; i <= n; i++) { // 骰子
            for (int j = i; j <= 6 * i; j++) {  // 骰子数字，注意 6 * i， 当前最大值 = 6 * 骰子个数
                for (int k = 1; k <= 6 && k <= j; k++) {  // 动态规划状态转移方程：f(n,s) = \sum_{k=1}^{k=6} f(n-1, s-k)
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }

        // 结果计算
        double [] ans = new double [6 * n - n + 1];     // 点数和的范围
        double powVal = Math.pow(6, n);
        for (int i = n; i <= 6 * n; i++) {
            ans[i - n] = dp[n][i] / powVal;
        }

        return ans;

    }

    public static void main(String[] args) {

        Offer60 offer60 = new Offer60();

        double [] ret = offer60.dicesProbability(2);
        System.out.println(Arrays.toString(ret));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    }
}





