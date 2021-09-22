package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class camelCase {
    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            char [] chars = str.toCharArray();
            StringBuilder sb = new StringBuilder();

            int i = 0;
            int n = chars.length;
            boolean first = false;
            while (i < n) {
                while ((i < n) && !(Character.isDigit(chars[i]) || Character.isLetter(chars[i]))) {
                    i++;
                    first = true;
                }

                if (i < n) {
                    if (first) {
                        if (chars[i] >= 'a' && chars[i] <= 'z') {
                            chars[i] = (char) (chars[i] - 'a' + 'A');
                        }
                        sb.append(chars[i]);  // 大写或数字
                        first = false;
                    } else {
                        if (chars[i] >= 'A' && chars[i] <= 'Z') {
                            chars[i] = (char) (chars[i] - 'A' + 'a');
                        }
                        sb.append(chars[i]);  // 小写或数字
                    }
                    i++;
                }
            }

            if (sb.charAt(0) >= 'A' && sb.charAt(0) <= 'Z') {
//                sb.insert(0, (char) (sb.charAt(0) - 'A' + 'a'));
//                sb.deleteCharAt(1);
                sb.setCharAt(0, (char) (sb.charAt(0) - 'A' + 'a'));
            }

            System.out.println(sb.toString());
        }
    }
}
