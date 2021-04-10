package src.nowcoder.SwordOffer;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 实现pow(x,n)，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
 *
 * 示例 1：
 *
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 *
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 *
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 *
 * 提示：
 *
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * -10^4 <= x^n <= 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


public class Offer16 {

    /* 暴力求解 */
    public double Power(double base, int exponent) {

        if (exponent == 0) {   // 牛客网的案例0^0=0，不符合定义
            return 1.0;
        }

        double powVal = 1.0;

        // exponent > 0
        int loop = exponent > 0? exponent : -exponent;
        for (int i = 0; i < loop; i++) {
            powVal *= base;
        }

        if (exponent < 0) {
            powVal = 1.0 / powVal;
        }

        return powVal;
        
    }

    /* 递归解法 */
    public double Power_2(double base, int exponent) {
        if (exponent < 0) {
            base = 1.0 / base;
            exponent = -exponent;
        }

        return myPower(base, exponent);
    }

    public double myPower(double base, int exponent) {
        
        if (exponent == 0) {  // 偶数
            return 1.0;
        }

        if (exponent == 1) {  // 奇数
            return base;
        }

        /* 如果是偶数 */
        double powVal = Power_2(base, exponent >> 1);
        powVal *= powVal;

        /* 如果是奇数，还要再乘一次base */
        if ((exponent & 0x1) == 0x1) {
            powVal *= base;
        }

        return powVal;
    }

    /* 非递归的快速幂 */
    public double Power_fast(double base, int exponent) {
        boolean flag = true;
        if (exponent < 0) {
//             base = 1.0 / base;
            exponent = -exponent;
            flag = false;
        }
        
        /* 开始快速幂，利用二进制思想 */
        double powVal = 1.0;
        double xBase = base;
        while (exponent > 0) {
            if ((exponent & 0x1) == 0x1) { //最后一位二进制值为1
                powVal *= xBase;
            }
            
            /* 右移 */
            xBase *= xBase;
            exponent = (exponent >>> 1);
        }
        
        return flag ? powVal : 1.0 / powVal;
    }

    public static void main(String[] args) {

        Offer16 offer16 = new Offer16();

        double base = 0;
        int exponent = 0;
        double ret = offer16.Power(base, exponent);
        System.out.println(ret + ", " + Math.pow(base, exponent));
        System.out.println();

        base = 0.0;
        exponent = 3;
        ret = offer16.Power(base, exponent);
        System.out.println(ret + ", " + Math.pow(base, exponent));
        ret = offer16.Power_2(base, exponent);
        System.out.println(ret + ", " + Math.pow(base, exponent));
        ret = offer16.Power_fast(base, exponent);
        System.out.println(ret + ", " + Math.pow(base, exponent));
        System.out.println();

        base = 2.0;
        exponent = 3;
        ret = offer16.Power(base, exponent);
        System.out.println(ret + ", " + Math.pow(base, exponent));
        ret = offer16.Power_2(base, exponent);
        System.out.println(ret + ", " + Math.pow(base, exponent));
        ret = offer16.Power_fast(base, exponent);
        System.out.println(ret + ", " + Math.pow(base, exponent));
        System.out.println();

        base = 2.0;
        exponent = -3;
        ret = offer16.Power(base, exponent);
        System.out.println(ret + ", " + Math.pow(base, exponent));
        ret = offer16.Power_2(base, exponent);
        System.out.println(ret + ", " + Math.pow(base, exponent));
        ret = offer16.Power_fast(base, exponent);
        System.out.println(ret + ", " + Math.pow(base, exponent));
        System.out.println();


    }
}
