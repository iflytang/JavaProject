package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ZuiShaoCaoZuoCiShu {
    public static void main (String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);

            int [] A = new int[n];
            String [] strs = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                A[i] = Integer.parseInt(strs[i]);
            }




        }

    }
}
