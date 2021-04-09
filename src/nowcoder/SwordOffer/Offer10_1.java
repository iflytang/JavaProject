package src.nowcoder.SwordOffer;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 10- I. 斐波那契数列
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：1
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：5
 *
 *
 * 提示：
 *
 * 0 <= n <= 100
 */


public class Offer10_1 {

    public int Fibonacci2(int n) {
        // 使用循环
        if (n == 0 || n == 1) {
            return n;
        }

        int[] fibArray = new int[n + 1];
        fibArray[0] = 0;
        fibArray[1] = 1;

        for (int i = 2; i <= n; i++) {
            fibArray[i] = fibArray[i - 1] + fibArray[i - 2];
        }

        return fibArray[n];
    }

    // 递归
    public int Fibonacci(int n) {

        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        return Fibonacci(n - 1) + Fibonacci(n - 2);  // n > 1
    }

    public static void main(String[] args) {

        Offer10_1 offer10 = new Offer10_1();

        int ret = offer10.Fibonacci(2);
        System.out.println(ret);

        ret = offer10.Fibonacci(10);
        System.out.println(ret);
    }
}
