package src.nowcoder.SwordOffer;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 62. 圆圈中最后剩下的数字
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字
 * （删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
 *
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，
 * 则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 *
 *
 *
 * 示例 1：
 *
 * 输入: n = 5, m = 3
 * 输出: 3
 * 示例 2：
 *
 * 输入: n = 10, m = 17
 * 输出: 2
 *
 *
 * 限制：
 *
 * 1 <= n <= 10^5
 * 1 <= m <= 10^6
 */

public class Offer62 {

    public int lastRemaining(int n, int m) {
        int f = 0;

        for (int i = 2; i <= n; i++) {  //  “约瑟夫环” 问题，dp[i]=(dp[i−1]+m)%i
            f = (f + m) % i;
        }

        return f;
    }

    public static void main(String[] args) {

        Offer62 offer62 = new Offer62();

        int n = 5;
        int m = 3;
        int ret = offer62.lastRemaining(n, m);
        System.out.println(ret);

        System.out.println("==========================");
    }
}





