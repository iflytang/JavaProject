package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ChiHuaJuZhen {

    static int res = 0;

    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        int n = 0;
        while ((str = br.readLine()) != null) {
            n = Integer.parseInt(str);

            int [][] grid = new int[n][n];
            for (int i = 0; i < n; i++) {
                String [] numStr = br.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    grid[i][j] = Integer.parseInt(numStr[j]);
                }
            }

            res = 0;
            solution(grid);

            System.out.println(res);
        }
    }

    public static void solution (int [][] grid) {
        int n = grid.length;

        if (n == 1) {
            res = grid[0][0];
            return;
        }

        int [][] matrix = new int[n/2][n/2];
        int row = 0;
        int col = 0;
        for (int i = 0; i < n; i = i + 2) {
            for (int j = 0; j < n; j = j + 2) {
                int [] tmp = new int[] {grid[i][j], grid[i][j + 1], grid[i + 1][j], grid[i + 1][j + 1]};
                Arrays.sort(tmp);
                matrix[row][col++] = tmp[2];
            }
            row++;
            col = 0;
        }

        solution(matrix);
    }
}
