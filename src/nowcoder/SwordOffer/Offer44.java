package src.nowcoder.SwordOffer;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 44. 数字序列中某一位的数字
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。
 * 在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 *
 * 请写一个函数，求任意第n位对应的数字。
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：3
 * 示例 2：
 *
 * 输入：n = 11
 * 输出：0
 *
 *
 * 限制：
 *
 * 0 <= n < 2^31
 * 注意：本题与主站 400 题相同：https://leetcode-cn.com/problems/nth-digit/
 */

public class Offer44 {

    public int findNthDigit(int n) {

        int digit = 1;  //数字位数(例如三位数digit=3)
        long start = 1;  //digit位数的第一个数字，注意long
        long count = 9;  //所有digit位数所占的位数，注意long

        while (n > count) { //1.确定n所在的数字的位数digit
            n -= count;

            digit += 1;
            start *= 10;
            count = 9 * start * digit;
        }

        //2. 确定n所在的数字num
        //此时n的值是相对于start的位置
        long num = start + (n - 1) / digit; //n-1是因为start是第0个数字

        //3. 确定n是num的第几位
        int bitLoc = (n - 1) % digit;

        return Long.toString(num).charAt(bitLoc) - '0';
    }

    public int findNthDigit2(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) { // 1.
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit; // 2.

//        return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3.
        return String.valueOf(num).charAt((n - 1) % digit) - '0'; // 3.
    }


    public static void main(String[] args) {

        Offer44 offer44 = new Offer44();

        int n = 1000000000;
        int ret = offer44.findNthDigit(n);
        System.out.println(ret);
        System.out.println("==========================");

        ret = offer44.findNthDigit2(n);
        System.out.println(ret);
        System.out.println("==========================");

    }
}





