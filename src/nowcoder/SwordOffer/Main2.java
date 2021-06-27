package src.nowcoder.SwordOffer;
import java.util.*;
import java.io.*;

public class Main2 {
    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            int [] cnt = new int [26]; // 26个小写字母
            int min = Integer.MAX_VALUE;
            char [] chars = str.toCharArray();

            // 计数
            for (int i = 0; i < chars.length; i++) {
                cnt[chars[i] - 'a']++;
                min = Math.min(min, cnt[chars[i] - 'a']);
            }

            // 删除最少的
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                if (cnt[chars[i] - 'a'] == min) {
                    continue;
                }
                sb.append(chars[i]);
            }

            System.out.println(sb.toString());
        }
    }
}