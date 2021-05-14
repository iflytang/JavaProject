package src.nowcoder.SwordOffer;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 64. 求1+2+…+n
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 *
 *
 * 示例 1：
 *
 * 输入: n = 3
 * 输出: 6
 * 示例 2：
 *
 * 输入: n = 9
 * 输出: 45
 */

public class Offer64 {

    int res = 0;
    public int sumNums(int n) { // 运算符的短路效应
        boolean x = (n > 1) && (sumNums(n - 1) > 0);
        res += n;

        return res;
    }

    public static void main(String[] args) {

        Offer64 offer64 = new Offer64();

        int n = 9;
        int ret = offer64.sumNums(n);
        System.out.println(ret);

        System.out.println("==========================");
    }
}





