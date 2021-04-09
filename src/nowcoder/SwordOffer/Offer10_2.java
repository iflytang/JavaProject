package src.nowcoder.SwordOffer;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：21
 * 示例 3：
 *
 * 输入：n = 0
 * 输出：1
 * 提示：
 *
 * 0 <= n <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


public class Offer10_2 {

    public int jumpFloor(int target) {

        if (target <= 1) {  // 0, 1
            return 1;
        }

        int[] fArray = new int[target + 1];
        fArray[0] = 1;
        fArray[1] = 1;

        for (int i = 2; i <= target; i++) {
            fArray[i] = fArray[i - 1] + fArray[i - 2];
        }

        return fArray[target];
    }

    /**
     * 进阶版：一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
     *        求该青蛙跳上一个n级的台阶总共有多少种跳法。
    * */
    public int jumpFloorII(int target) {

        if (target <= 1) {
            return 1;
        }

        int fN1 = 1;
        int fN2 = 1;

        int fSum = fN1 + fN2;

        for (int i = 3; i <= target; i++) {
            fN2 = fN1;
            fN1 = fN1 + fN2;

            fSum += fN1;
        }

        return fSum;
    }


    public static void main(String[] args) {

        Offer10_2 offer10 = new Offer10_2();

        int ret = offer10.jumpFloor(2);
        System.out.println(ret);

        ret = offer10.jumpFloor(7);
        System.out.println(ret);

        ret = offer10.jumpFloor(4);
        System.out.println(ret);

        ret = offer10.jumpFloorII(4);
        System.out.println(ret);

    }
}
