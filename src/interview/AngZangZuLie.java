package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AngZangZuLie {

    static long res = 0;

    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        int T = 0;
        while ((str = br.readLine()) != null) {
            T = Integer.parseInt(str);

            int [] grid = new int[6];
            for (int i = 0; i < T; i++) {
                String [] numStr = br.readLine().split(" ");
                for (int j = 0; j < 6; j++) {
                    grid[j] = Integer.parseInt(numStr[j]);
                }

                res = 0;
                solution(grid);
                System.out.println(res);
            }



        }
    }

    public static void solution (int [] grid) {

    }

}
