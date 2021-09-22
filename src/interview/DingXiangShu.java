package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DingXiangShu {

    public static void main (String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int n = Integer.parseInt(str);

        String [] numStr = br.readLine().split(" ");
        int [] nums = new int[numStr.length];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(numStr[i]);
        }

        int [] dp = new int[31];
        int res = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < nums[i]; j++) {
                res += dp[j];
            }
            dp[nums[i]] = 1;
        }

        System.out.println(res);

    }
}
