package src.nowcoder.SwordOffer;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 46. 把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，
 * 11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 *
 * 示例 1:
 *
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *
 *
 * 提示：
 *
 * 0 <= num < 231
 */

public class Offer46 {

    public int translateNum(int num) {
        char [] chars = String.valueOf(num).toCharArray();

        int preOne = 1;  // dp[0]
        int preTwo = 0;  // dp[-1]
        int cur = preOne + preTwo; // dp[1]
        for (int i = 1; i < chars.length; i++) {
            preTwo = preOne;
            preOne = cur;

            int charNum = 10 * (chars[i - 1] - '0') + (chars[i] - '0');
//            System.out.println(charNum);

            if (charNum >= 10 && charNum <= 25) {
                cur = preOne + preTwo; // dp[i] = dp[i-1] + dp[i-2]
            } // else cur = cur; // dp[i] = dp[i-1]
        }

        return cur;
    }


    public static void main(String[] args) {

        Offer46 offer46 = new Offer46();

        int num = 12258;
        int ret = offer46.translateNum(num);
        System.out.println(ret);
        System.out.println("==========================");

        num = 25;
        ret = offer46.translateNum(num);
        System.out.println(ret);
        System.out.println("==========================");

    }
}





