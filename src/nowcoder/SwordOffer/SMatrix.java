package src.nowcoder.SwordOffer;

import java.io.*;
import java.util.*;

public class SMatrix  {
    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);

            int [][] res = new int [n][n];
            int num = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    res[i - j][j] = num;
                    num++;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n - i; j++) {  // 上三角
                    if (res[i][j] != 0) {
                        sb.append(res[i][j]).append(" ");
                    }
                }
                sb.append("\n");
            }

            System.out.println(sb.toString());
        }

    }
}