package src.nowcoder.SwordOffer;

import java.util.Arrays;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 66. 构建乘积数组
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
 * 其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积,
 * 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 *
 * 示例:
 *
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 *
 *
 * 提示：
 *
 * 所有元素乘积之和不会溢出 32 位整数
 * a.length <= 100000
 */

public class Offer66 {

    // 对称乘法，分成左、右两部分相乘，但不包括自己
    public int[] constructArr(int[] a) {

        int [] res = new int [a.length];

        // 左到右遍历
        for (int i = 0, left = 1; i < a.length; i++) {
            res[i] = left;  // 不包括自身
            left *= a[i];   // left是a[1: 1: i-1] 的累计乘积
        }

        // 右到左遍历
        for (int i = a.length - 1, right = 1; i >= 0; i--) {
            res[i] *= right;  // 不包括自身
            right *= a[i];    // right是a[n-1 : -1 : i+1]的累计乘积
        }

        return res;
    }

    // 下三角、上三角两次循环乘法，对角线都是1
    public int[] constructArr2(int[] a) {
        // 边界检查
        if (a.length == 0) {
            return new int[0];
        }

        int [] res = new int [a.length];

        // 下三角，不计算对角线
        res[0] = 1;  // res[0]
        for (int i = 1; i <= a.length - 1; i++) {
            res[i] = res[i - 1] * a[i - 1];
        }

        // 上三角，不计算对角线
        int bn_1 = 1;  // res[n-1]
        for (int i = a.length - 2; i >= 0; i--) {
            bn_1 *= a[i + 1];  // 第n-1行开始，不包括自身
            res[i] *= bn_1;  // 乘上上三角元素
        }

        return res;
    }

    public static void main(String[] args) {

        Offer66 offer66 = new Offer66();

        int [] input = {1, 2, 3, 4, 5};
        int [] ret = offer66.constructArr(input);
        System.out.println(Arrays.toString(ret));

        System.out.println("==========================");

        ret = offer66.constructArr2(input);
        System.out.println(Arrays.toString(ret));
    }
}





