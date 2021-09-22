package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BuXuAC {

    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);
            String ac = br.readLine();

            long ret = solution2(n, ac);  // 变成ccc...aaa...

            System.out.println(ret);
        }
    }

    public static long solution (int n, String str) {
        long cnt = 0;

        char [] chars = str.toCharArray();
        int cIdx = 0;
        int cDistance = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'c') {
                cDistance = i - cIdx;
                cnt += cDistance;

                cIdx = i;
            }
        }

        return cnt;
    }

    public static long solution2 (int n, String str) {
        StringBuilder sb = new StringBuilder();
        char [] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'c') {
                sb.insert(0, 'c');
            } else {
                sb.append('a');
            }
        }

//        String target = sb.toString();
//        System.out.println(target);

        long ans = 0;
        // a是target, b是num
        StringBuilder a = sb;
        String b = str;
        for (int i = n - 1; i >= 0; i--) {
            int pos = b.lastIndexOf(a.charAt(a.length() - 1));  // 找出a的最后一个字符在b中的最末位出现位置
            ans += (b.length() - pos - 1);
            a.deleteCharAt(a.length() - 1);  // 删除最后一个
            b = b.substring(0, pos) + b.substring(pos + 1);  // 删除pos字符
        }

        return ans;
    }
}
