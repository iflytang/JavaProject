package src.nowcoder.SwordOffer;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 65. 不用加减乘除做加法
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 *
 *
 *
 * 示例:
 *
 * 输入: a = 1, b = 1
 * 输出: 2
 *
 *
 * 提示：
 *
 * a, b 均可能是负数或 0
 * 结果不会溢出 32 位整数
 */

public class Offer65 {

    public int add(int a, int b) {
        int carry; // carry = 进位

        while (b != 0) { // 当进位为 0 时跳出
            carry = (a & b) << 1;
            a = (a ^ b);  // a = 非进位和
            b = carry;
        }

        return a;
    }

    public static void main(String[] args) {

        Offer65 offer65 = new Offer65();

        int a = 1;
        int b = 1;

        int ret = offer65.add(a, b);
        System.out.println(ret);

        System.out.println("==========================");
    }
}





