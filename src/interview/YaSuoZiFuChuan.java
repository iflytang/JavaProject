package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class YaSuoZiFuChuan {
    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            char [] chars = str.toCharArray();
            StringBuilder sb = new StringBuilder();

            char pre = chars[0];
            int num = 1;
            for (int i = 1; i < chars.length; i++) {
                if (pre == chars[i]) {
                    num++;
                } else {
                    sb.append(pre);
                    if (num > 1) {
                        sb.append(num);
                    }

                    pre = chars[i];
                    num = 1;
                }
            }

            sb.append(pre);
            if (num > 1) {
                sb.append(num);
            }

            System.out.println(sb.toString());
        }
    }
}
