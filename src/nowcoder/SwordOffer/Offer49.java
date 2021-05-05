package src.nowcoder.SwordOffer;

import java.util.HashMap;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 49. 丑数
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 *
 * 示例:
 *
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:
 *
 * 1 是丑数。
 * n 不超过1690。
 * 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/
 */

public class Offer49 {

    public int nthUglyNumber(int n) {  // 动态规划, dp[i]代表第 i + 1个丑数
        int [] uglyNumberArray = new int[n];  // 使用dp数组来存储丑数序列
        uglyNumberArray[0] = 1;  // dp[0]已知为1

        int a = 0, b = 0, c = 0;  // 下个应该通过乘2来获得新丑数的数据是第a个， 同理b, c

        for (int i = 1; i < n; i++) {
            // 第a丑数个数需要通过乘2来得到下个丑数，第b丑数个数需要通过乘2来得到下个丑数，同理第c个数
            int n2 = uglyNumberArray[a] * 2;
            int n3 = uglyNumberArray[b] * 3;
            int n5 = uglyNumberArray[c] * 5;

            uglyNumberArray[i] = Math.min(Math.min(n2, n3), n5);

            if (n2 == uglyNumberArray[i]) {
                // 第a个数已经通过乘2得到了一个新的丑数，那下个需要通过乘2得到一个新的丑数的数应该是第(a+1)个数
                a++;
            }

            if (n3 == uglyNumberArray[i]) {
                // 第b个数已经通过乘3得到了一个新的丑数，那下个需要通过乘3得到一个新的丑数的数应该是第(b+1)个数
                b++;
            }

            if (n5 == uglyNumberArray[i]) {
                // 第c个数已经通过乘5得到了一个新的丑数，那下个需要通过乘5得到一个新的丑数的数应该是第(c+1)个数
                c++;
            }
        }

        return uglyNumberArray[n - 1];
    }


    public static void main(String[] args) {

        Offer49 offer49 = new Offer49();

        int inputs = 10;
        int ret = offer49.nthUglyNumber(inputs);
        System.out.println(ret);
        System.out.println("==========================");

    }
}





