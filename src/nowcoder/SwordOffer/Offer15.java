package src.nowcoder.SwordOffer;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 15. 二进制中1的个数
 * 请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
 *
 *
 *
 * 示例 1：
 *
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 * 示例 2：
 *
 * 输入：00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 * 示例 3：
 *
 * 输入：11111111111111111111111111111101
 * 输出：31
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 *
 *
 * 提示：
 *
 * 输入必须是长度为 32 的 二进制串 。
 */


public class Offer15 {

    public int NumberOf1(int n) {

        int base = 1;

        int num = 0;

        int loop = 32;
        for (int i = 0; i < loop; i++) {
            num += (n & base);
            n = (n >>> base);  // 负数时>>右移，可能会死循环；使用无符号右移：>>>
        }

        return num;
    }

    public int NumberOf1_v2(int n) {
        int count = 0;
        
        while (n != 0) {
            count++;
            
            n = n & (n - 1);  // 循环含1的次数
        }
        
        return count;
    }

    public static void main(String[] args) {

        Offer15 offer15 = new Offer15();

        int n = 8;
        int ret = offer15.NumberOf1(n);
        System.out.println(ret);

        n = 10;
        ret = offer15.NumberOf1(n);
        System.out.println(ret);

        n = -2147483648;
//        n = 2;
        ret = offer15.NumberOf1(n);
        System.out.printf("0x%08x\t", n);
        System.out.println(n + " " + ~n + " " + ret);
    }
}
