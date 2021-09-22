package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SLAfuwu {

    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        int num = 0;  // 记录N和起点

        while ((str = br.readLine()) != null) {
            if (!str.contains("[")) {
                num = 0;
                String [] substr = str.split(",");
                int s = Integer.parseInt(substr[0].substring(1));
                int d = Integer.parseInt(substr[1]);
                int dis = Integer.parseInt(substr[2].substring(0, substr[2].length()));

            } else {
                num++;
            }
        }


    }


}
