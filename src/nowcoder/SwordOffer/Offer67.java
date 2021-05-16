package src.nowcoder.SwordOffer;

import java.util.Arrays;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 67. 把字符串转换成整数
 * 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
 *
 *
 *
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 *
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，
 * 作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 *
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 *
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 *
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 *
 * 说明：
 *
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。
 * 如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *
 * 示例 1:
 *
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 *
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 *
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 *
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 *      因此无法执行有效的转换。
 * 示例 5:
 *
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 *      因此返回 INT_MIN (−231) 。
 *
 *
 * 注意：本题与主站 8 题相同：https://leetcode-cn.com/problems/string-to-integer-atoi/
 */

public class Offer67 {

    public int strToInt(String str) {
        int MAX = 0x7fffffff;
        int MIN = 0x80000000;
        
        // 字符串数组
        char [] chars = str.toCharArray();

        // 去除前面无用的0
        int i;
        for (i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                break;
            }
        }

        // 判断首字母是否符合要求
        boolean negative = false;
        if (!isDigit(chars[i]) && chars[i] != '+' && chars[i] != '-') { // 首字母不是数字或正负号
            return 0;
        }

        // 判断正负号
        if (chars[i] == '-') {
            negative = true;
        }

        if (chars[i] == '-' || chars[i] == '+') {
            i++;
        }

        // 字符转换数字
        long res = 0;
        while ((i < chars.length) && isDigit(chars[i])) {
            res = res * 10 + (chars[i] - '0');
//            System.out.println(res);

            // 判断溢出
           if (!negative && (res > MAX)) {
               return MAX;
           }

           if (negative && (-res < MIN)) {
               return MIN;
           }

            i++;
        }

        int ret = (int) res; // long截断为int，但截断就不满足题目中要求的32位存储

        return negative ? -ret: ret;
    }

    public boolean isDigit(char c) {  // '0'~'9'内
        return c >= '0' && c <= '9';
    }


    public int strToInt2(String str) {

        // 字符串数组
        char [] chars = str.toCharArray();

        if (chars.length == 0) {
            return 0;
        }

        // 去除前面无用的0
        int i;
        for (i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                break;
            }
        }

        if (i == chars.length) { // 如果全是空格
            return 0;
        }

        // 判断首字母是否符合要求
        boolean negative = false;
        if (!isDigit(chars[i]) && chars[i] != '+' && chars[i] != '-') { // 首字母不是数字或正负号
            return 0;
        }

        // 判断正负号
        if (chars[i] == '-') {
            negative = true;
        }

        if (chars[i] == '-' || chars[i] == '+') {
            i++;
        }

        // 字符转换数字
        int res = 0;
        while ((i < chars.length) && isDigit(chars[i])) {
            int digit = chars[i] - '0';
//            System.out.println(res);

            // 判断溢出
            if (res > (Integer.MIN_VALUE - digit) / 10) {
                // 本来应该是 ans * 10 + digit > Integer.MAX_VALUE
                // 但是 *10 和 + digit 都有可能越界，所有都移动到右边去就可以了。
                return negative ? Integer.MIN_VALUE : Integer.MIN_VALUE;
            }

            res = res * 10 + digit;

            i++;
        }

        return negative ? -res: res;
    }

    public static void main(String[] args) {

        Offer67 offer67 = new Offer67();

        String str = "42x";
        int ret = offer67.strToInt(str);
        System.out.println(ret);

        System.out.println("==========================");

        str = "     -42";
        ret = offer67.strToInt(str);
        System.out.println(ret);

        System.out.println("==========================");

        str = "words and 987";
        ret = offer67.strToInt(str);
        System.out.println(ret);

        System.out.println("==========================");

        str = "4193 with words";
        ret = offer67.strToInt(str);
        System.out.println(ret);

        System.out.println("==========================");

        str = "-91283472332";
        ret = offer67.strToInt(str);
        System.out.println(ret);

        System.out.println("==========================");

        str = "-91283472332";
        ret = offer67.strToInt2(str);
        System.out.println(ret);

        System.out.println("==========================");

        str = "+1";
        ret = offer67.strToInt(str);
        System.out.println(ret);

        System.out.println("==========================");

        str = "-";
        ret = offer67.strToInt2(str);
        System.out.println(ret);

        System.out.println("==========================");

        str = " ";
        ret = offer67.strToInt2(str);
        System.out.println(ret);

        System.out.println("==========================");

        char [] chars = new char[] {' ', 'i', ' ', 'a', 'm'};
        System.out.println(String.valueOf(chars).trim() + ", " + Math.pow(27, 1/3.0));

    }
}





