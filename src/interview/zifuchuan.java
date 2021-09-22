package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class zifuchuan {
    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            int size = 0;  // 记录转义长度
            char [] chars = str.toCharArray();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < chars.length; i++) {
                if (chars[i] >= 'A' && chars[i] <= 'Z') {
                    sb.append((char) (chars[i] - 'A' + 'a'));
                    size++;
                } else if (chars[i] >= 'a' && chars[i] <= 'z') {
                    sb.append((char) (chars[i] - 'a' + 'A'));
                    size++;
                } else if (chars[i] == '*') {
                    if (size > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                        size--;
                    }
                } else if (chars[i] == '#') {
                    if (size > 0) {
                        sb.append(sb.charAt(sb.length() - 1));
                        size++;
                    }
                } else {
                    sb.append(chars[i]);
                    size++;
                }
            }

            System.out.println(sb.toString());
        }
    }
}
