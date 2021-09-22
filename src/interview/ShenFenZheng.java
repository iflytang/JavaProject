package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShenFenZheng {

    static long res = 0;
    static StringBuilder path = new StringBuilder();

    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {

            int N = Integer.parseInt(str);

            for (int i = 0; i < N; i++) {
                res = 0;
                path = new StringBuilder();
                validIDs("34088119480815*663", 0, 0);
                System.out.println(res);

                res = 0;
                path = new StringBuilder();
                validIDs("340881194808156*63", 0, 0);
                System.out.println(res);

                res = 0;
                path = new StringBuilder();
                validIDs("520123200501169**4", 0, 0);
                System.out.println(res);

                res = 0;
                path = new StringBuilder();
                validIDs("520123200501168**4", 0, 0);
                System.out.println(res);

                res = 0;
                path = new StringBuilder();
                validIDs("52012320050116*9*4", 0, 0);
                System.out.println(res);

                res = 0;
                path = new StringBuilder();
                validIDs("52012320050116*5*4", 0, 0);
                System.out.println(res);

                res = 0;
                path = new StringBuilder();
                validIDs("52012320050116***4", 0, 0);
                System.out.println(res);
//
//                res = 0;
//                path = new StringBuilder();
//                validIDs(br.readLine(), 0, 0);
//                System.out.println(res);
            }

        }

    }

    static long [] factor = {
      7, 9, 10, 5, 8,
      4, 2, 1, 6, 3,
      7, 9, 10, 5, 8,
      4, 2
    };

    static char [] remain = {
      '1', '0', 'X', '9', '8',
      '7', '6', '5', '4', '3',
      '2'
    };



    public static void validIDs(String str, long sum, int start) {
        char [] nums = str.toCharArray();

        for (int i = start; i < 18; i++) {

            if (i == 17) {  // 18位
                int idx = (int) (sum % 11);
                if (remain[idx] == nums[nums.length - 1] && path.length() >= 17) {  // 如果余数相等
                    res++;
//                    System.out.println(sum + " " + path.toString() + nums[nums.length - 1]);
                }
                return;
            }

            if (nums[i] >= '0' && nums[i] <= '9') { // 1~17位
                sum += (nums[i] - '0') * factor[i];
                path.append(nums[i]);
            } else if (nums[i] == '*') {
                for (int j = 0; j < 10; j++) {
                    path.append(j);
                    validIDs(str, sum + j * factor[i], i + 1);
                    path.deleteCharAt(path.length() - 1);
                }
            }

        }
    }
}
