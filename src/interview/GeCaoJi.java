package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GeCaoJi {

    public static void main (String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        str = br.readLine();
//        while ((str = br.readLine()) != null) {
            String [] nm = str.split(" ");

            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);

            int k = Integer.parseInt(br.readLine());

            int [] x = new int[k];
            int [] y = new int[k];
            int [] r = new int[k];

            for (int i = 0; i < k; i++) {
                String [] xyr = br.readLine().split(" ");
                x[i] = Integer.parseInt(xyr[0]);
                y[i] = Integer.parseInt(xyr[1]);
                r[i] = Integer.parseInt(xyr[2]);
            }

            long ret = solution(n, m, k, x, y, r);

            System.out.println(ret);

//        }

    }

    public static void add (int [][] square) {
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square[0].length; j++) {
                square[i][j]++;
            }
        }
    }

    public static long remained (int [][] square) {
        long remained = 0;
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square[0].length; j++) {
                remained += square[i][j];
            }
        }

        return remained;
    }

    public static long solution (int n, int m, int k, int [] x, int [] y, int [] r) {
        double area = (double) n * m * k;
        int [][] square = new int[n][m];

        long remained = 0;
        for (int i = 0; i < k; i++) {
            add(square);
            for (int x0 = 0; x0 <= r[i]; x0++) {
                for (int y0 = 0; y0 <= r[i]; y0++) {

                    if (x0 * x0 + y0 * y0 <= r[i] * r[i]) {
                        square[x[i] - 1 - x0][y[i] - 1 - y0] = 0;
                    }

                    if (x0 * x0 + y0 * y0 <= r[i] * r[i]) {
                        square[x[i] - 1 - x0][y[i] - 1 + y0] = 0;
                    }

                    if (x0 * x0 + y0 * y0 <= r[i] * r[i]) {
                        square[x[i] - 1 + x0][y[i] - 1 + y0] = 0;
                    }

                    if (x0 * x0 + y0 * y0 <= r[i] * r[i]) {
                        square[x[i] - 1 + x0][y[i] - 1 - y0] = 0;
                    }
                }
            }
            remained = remained(square);
//            System.out.println(remained);
        }

        return remained;
    }

}
