package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 示例一
//    5 5
//    3 5 4 2 3
//    4 5 3 4 3
//    4 3 5 3 2
//    2 5 3 3 5
//    5 3 4 4 6

// 实例二
// 3 3
// 2 3 2
// 5 1 1
// 4 5 5

public class ZuiDuanLuJingShiJian {

    static List<Integer> res;

    public static void main (String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            String [] str1 = str.split(" ");
            int row = Integer.parseInt(str1[0]);
            int col = Integer.parseInt(str1[1]);

            int [][] grid = new int[row][col];

            for (int i = 0; i < row; i++) {
                String [] substrs = br.readLine().split(" ");
                for (int j = 0; j < col; j++) {
                    grid[i][j] = Integer.parseInt(substrs[j]);
                }
            }

            // 如果出口时间小于行数，则不能出来
            if (grid[row - 1][col - 1] < grid.length) {
                System.out.println(-1);
                continue;
            }

            res = new ArrayList<>();
            solution(grid, 0, 0, 0);  // 开始回溯

//            System.out.println(Arrays.toString(res.toArray()));

            if (res.size() == 0) {  // 如果是空，返回-1
                System.out.println(-1);
                continue;
            }

            int ret = Integer.MAX_VALUE;
            for (int i = 0; i < res.size(); i++) {  // 找所有可能路径的最小值
                ret = Math.min(ret, res.get(i));
            }
            System.out.println(ret);
        }
    }



    public static void solution (int [][] grid, int i, int j, int cnt) {

        if ((i < 0) || (i >= grid.length) ||     // 行约束
            (j < 0) || (j >= grid[0].length) ||  // 列约束
            (grid[i][j] <= 0)) {               // 值约束
            return;
        }

        // 递归出口
        if ((i == grid.length - 1 ) && (j == grid[0].length - 1)) {
            res.add(cnt);
        }

        dec(grid);
        solution(grid, i - 1, j, cnt + 1);  // 左
        solution(grid, i + 1, j, cnt + 1);  // 右
        solution(grid, i, j - 1, cnt + 1);  // 上
        solution(grid, i, j + 1, cnt + 1);  // 下
        inc(grid);

    }

    public static void inc (int [][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j]++;
            }
        }
    }

    public static void dec (int [][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j]--;
            }
        }
    }

}




























