package src.nowcoder.SwordOffer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 *
 * 剑指 Offer 29. 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 *
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 *
 * 限制：
 *
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 * 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/
 *
 */

public class Offer29 {

    public ArrayList<Integer> printMatrix(int [][] matrix) {

        if (matrix.length == 0) { // 边界条件
            return new ArrayList<>();
        }

        // 上下左右定义
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;

        ArrayList<Integer> res = new ArrayList<>(matrix.length * matrix[0].length);

        while (true) {
            /* 左 -> 右 */
            for (int i = left; i <= right; i++) {  // 左到右
                res.add(matrix[top][i]);
            }
            top++;  // 下移一行

            if (top > bottom) {  // 判断违反条件
                break;
            }

            /* 上 -> 下 */
            for (int i = top; i <= bottom; i++) { // 上到下
                res.add(matrix[i][right]);
            }
            right--;  // 左移一列

            if (left > right) {
                break;
            }

            /* 右 -> 左 */
            for (int i = right; i >= left; i--) {  // 右到左
                res.add(matrix[bottom][i]);
            }
            bottom--;  // 上移一行

            if (top > bottom) {
                break;
            }

            /* 下 -> 上 */
            for (int i = bottom; i >= top; i--) {  // 下到上
                res.add(matrix[i][left]);
            }
            left++;  // 右移一列

            if (left > right) {
                break;
            }
        }

        return res;
    }


    public static void main(String[] args) {

        Offer29 offer29 = new Offer29();

        int [][] input = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        ArrayList<Integer> ret = offer29.printMatrix(input);
        System.out.println(Arrays.toString(ret.toArray()));


    }
}



