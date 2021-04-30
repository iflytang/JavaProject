package src.nowcoder.SwordOffer;

import java.util.Arrays;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 47. 礼物的最大价值
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
 * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 *
 *
 * 提示：
 *
 * 0 < grid.length <= 200
 * 0 < grid[0].length <= 200
 */

public class Offer47 {

    public int maxValue(int[][] grid) {

        if (grid.length == 0) {
            return 0;
        }

        // dynamic programming
        int rows = grid.length;
        int cols = grid[0].length;
        int [][] values = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 边界条件，状态定义
                if (i == 0 && j == 0) {
                    values[i][j] = grid[i][j];
                    continue;
                }

                if (i == 0) {  // 只能右移
                    values[i][j] = values[i][j - 1] + grid[i][j];
                    continue;
                }

                if (j == 0) {  // 只能下移
                    values[i][j] = values[i - 1][j] + grid[i][j];
                    continue;
                }
                
                // 状态转移方程：values[i][j] = max(values[i - 1][j], values[i][j - 1]) + grid[i][j]
                int downStep = values[i - 1][j] + grid[i][j];
                int rightStep = values[i][j - 1] + grid[i][j];
                values[i][j] = Math.max(downStep, rightStep);
            }
        }

        System.out.println(Arrays.deepToString(values));

        return values[rows-1][cols-1];
    }

    public static void main(String[] args) {

        Offer47 offer47 = new Offer47();

        int [][] inputs = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        int ret = offer47.maxValue(inputs);
        System.out.println(ret);
        System.out.println("==========================");

    }
}





