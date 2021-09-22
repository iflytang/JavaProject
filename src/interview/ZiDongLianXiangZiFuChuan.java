package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ZiDongLianXiangZiFuChuan {

    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        String str = null;


//        while ((input = br.readLine()).contains(" ")) {
            String[] words = br.readLine().split(" ");

            while ((str = br.readLine()) != null) {
                String s = str;
                int cnt = 1;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < words.length; i++) {
                    if (words[i].startsWith(s)) {
                        sb.append(cnt).append(".").append(words[i]).append(" ");
                        cnt++;
                    }
                }

                System.out.println(sb.deleteCharAt(sb.length() - 1).toString());
            }

        }

//    }

}
