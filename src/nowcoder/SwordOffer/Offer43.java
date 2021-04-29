package src.nowcoder.SwordOffer;

import java.util.Arrays;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 43. 1～n 整数中 1 出现的次数
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 *
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 12
 * 输出：5
 * 示例 2：
 *
 * 输入：n = 13
 * 输出：6
 *
 *
 * 限制：
 *
 * 1 <= n < 2^31
 * 注意：本题与主站 233 题相同：https://leetcode-cn.com/problems/number-of-digit-one/
 */

public class Offer43 {

    public int countDigitOne(int n) {

        int digit = 1;

        int high = n / 10;
        int cur = n % 10;
        int low = 0;

        int ones = 0;

        while (high != 0 || cur != 0) {  // 当 high 和 cur 同时为 0 时，说明已经越过最高位，因此跳出
            if (cur == 0) {
                ones += (high * digit);
            } else if (cur == 1) {
                ones += (high * digit + low + 1);
            } else {
                ones += ((high + 1) * digit);
            }

            low += cur * digit;  // 将 cur 加入 low ，组成下轮 low
            cur = high % 10;  // 下轮 cur 是本轮 high 的最低位

            high /= 10;  //  将本轮 high 最低位删除，得到下轮 high
            digit *= 10;  // 位因子每轮 × 10
        }

        return ones;
    }


    public static void main(String[] args) {

        Offer43 offer43 = new Offer43();

        int n = 13;
        int ret = offer43.countDigitOne(n);
        System.out.println(ret);
        System.out.println("==========================");

    }
}





