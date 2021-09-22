package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class AliTuSe {
    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            int T = Integer.parseInt(str);

            String [] NM = br.readLine().split(" ");
            int N = Integer.parseInt(NM[0]);
            int M = Integer.parseInt(NM[1]);

            int [][] ab = new int[M][2];
            for (int i = 0; i < M; i++) {
                String [] abStr = br.readLine().split(" ");
                ab[i][0] = Integer.parseInt(abStr[0]);
                ab[i][1] = Integer.parseInt(abStr[1]);
            }

            System.out.println(Arrays.deepToString(ab));

        }
    }


}
