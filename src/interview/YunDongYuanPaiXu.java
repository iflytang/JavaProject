package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class YunDongYuanPaiXu {
    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            int T = Integer.parseInt(str);

            for (int i = 0; i < T; i++) {
                String [] strs = br.readLine().split(" ");
                int N = Integer.parseInt(strs[0]);  // 运动员
                int M = Integer.parseInt(strs[1]);  // 观众



            }

        }

    }

}
