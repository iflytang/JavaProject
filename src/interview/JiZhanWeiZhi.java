package src.interview;

import java.io.*;
import java.util.*;

public class JiZhanWeiZhi {
    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            String [] NK = str.split(" ");
            int n = Integer.parseInt(NK[0]);
            int k = Integer.parseInt(NK[1]);

            String [] strs = br.readLine().split(" ");

            int maxIdx = Integer.parseInt(strs[strs.length - 1]);
            if (n == maxIdx) {
                System.out.println("1");
                continue;
            }

            int [] idx = new int[n];
            int [] loc = new int[maxIdx];
            for (int i = 0; i < n; i++) {
                idx[i] = Integer.parseInt(strs[i]);
                loc[Integer.parseInt(strs[i]) - 1] = 1;   // 当前已占的位置
            }

            int maxVal = -1;
            for (int i = 0; i < n - 1; i++) {
                maxVal = Math.max(maxVal, idx[i + 1] - idx[i]);
            }

            for (int i = 0; i < k; i++) {  // 个数

                int curIdx = 0;
                int curLen = 0;
                for (int j = 0; j < maxIdx; j++) {
                    if (loc[j] == 0) {
                        int left = j;
                        int right = j;

                        while (loc[left] != 1 && left >= 0) {
                            left--;
                        }

                        while (loc[right] != 1 && right < maxIdx) {
                            right++;
                        }

                        curLen = Math.max(right - j, j - left);

                        if (curLen < maxVal) {
                            curIdx = j;
                            maxVal = curLen;
                        }
                    }
                }
                loc[curIdx] = 1;
            }

            int t = 0;
            for (int i = 0; i < maxIdx; i++) {  // check
                if (loc[i] == 0) {
                    i++;
                } else {
                    t = i;
                    break;
                }
            }

            for (int i = t; i < maxIdx; i++) {
                if (loc[i] == 0) {
                    continue;
                }

                maxVal = Math.max(maxVal, Math.abs(i - t));
                t = i;
            }

            System.out.println(maxVal);

        }
    }
}
