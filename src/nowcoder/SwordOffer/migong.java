package src.nowcoder.SwordOffer;

import java.io.*;
import java.util.*;

public class migong {
    static ArrayList<int []> ret = new ArrayList<>();  // 结果只有一个
    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            String [] matLen = str.split(" ");
            int m = Integer.parseInt(matLen[0]);
            int n = Integer.parseInt(matLen[1]);

            int [][] mat = new int[m][n];
            for (int i = 0; i < m; i++) {
                String [] element = br.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    mat[i][j] = Integer.parseInt(element[j]);
                }
            }

            ArrayList<int []> path = new ArrayList<>();
            ArrayList<int []> res = new ArrayList<>();  // 结果只有一个
            ArrayList<ArrayList<int []>> test = new ArrayList<>();

            backtrack(mat, test, path, 0, 0);

            StringBuilder sb = new StringBuilder();
//            res = ret;
            res = test.get(0);
            for (int i = 0; i < res.size(); i++) {
                int [] xy = res.get(i);
                sb.append('(').append(xy[0]).append(',').append(xy[1]).append(")\n");
            }

            System.out.print(sb.toString());

        }

    }

    // 递归回溯
//    public static void backtrack(int [][] mat, ArrayList<int []> res, ArrayList<int []> path, int x, int y) {
    public static void backtrack(int [][] mat, ArrayList<ArrayList<int []>>  res, ArrayList<int []> path, int x, int y) {
        if (x < 0 || x >= mat.length ||
                y < 0 || y >= mat[0].length ||
                mat[x][y] != 0 ) {
            return;
        }

        if (x == (mat.length - 1) && y == (mat[0].length -1 )) {
            path.add(new int [] {x, y});
            res.add(new ArrayList<>(path));
//            ret = res;
//            path.remove(path.size() - 1);
            return;
        }



        mat[x][y] = 2;  // 标记为访问过
        path.add(new int [] {x, y});

        backtrack(mat, res, path, x, y - 1);   // 向上
        backtrack(mat, res, path, x, y + 1);   // 向下
        backtrack(mat, res, path, x - 1, y);   // 向左
        backtrack(mat, res, path, x + 1, y);   // 向右

        path.remove(path.size() - 1);
        mat[x][y] = 0;  // 回溯
    }
}