package src.nowcoder.SwordOffer;

import java.io.*;
import java.util.*;

public class Main3 {
    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            int N = Integer.parseInt(str);

            str = br.readLine();
            String [] substrs = str.split(" ");
            int [] height = new int [N];
            for (int i = 0; i < height.length; i++) {
                height[i] = Integer.parseInt(substrs[i]);
            }

            /* 最长上升子序列，左右各查找一次 */
            int [] numL = new int [N];
            for (int i = 0; i < N; i++) {  // 左到右
                numL[i] = 1; // 初始状态

                for (int j = 0; j < i; j++) {
                    if (height[j] < height[i]) {
                        numL[i] = Math.max(numL[i], numL[j] + 1);
                    }
                }

            }

            // 从右到左，升序
            int [] numR = new int [N];
            for (int i = N - 1; i >= 0; i--) {
                numR[i] = 1; // 初始状态
                for (int j = N - 1; j > i; j--) {

                    if (height[j] < height[i]) {
                        numR[i] = Math.max(numR[i], numR[j] + 1);
                    }

                }
            }

            int maxLen = 0;
            for (int i = 0; i < N; i++) {
                int curLen = numL[i] + numR[i];
                maxLen = Math.max(maxLen, curLen);
            }

            System.out.println(maxLen - 1);  // 两次相加，自身多算了一次

            String [] num = {"16", "26", "4"};
            Arrays.sort(num);
            System.out.println(Arrays.toString(num));

            Map<Integer, Integer> map = new LinkedHashMap<>();
            map.put(0, 0);
            System.out.println(map.size());
        }
    }
}
