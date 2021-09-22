package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TiaoFangGe {

    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            String [] substr = str.split(",");
            int [] nums = new int[substr.length];

            if (substr.length == 1) {
                nums[0] = Integer.parseInt(substr[0].substring(1, substr[0].length() - 1));
                int ret = jump(nums);
                System.out.println(ret);
                continue;
            }

            for (int i = 0; i < substr.length; i++) {
                if (i == 0) {
                    nums[i] = Integer.parseInt(substr[i].substring(1));
                } else if (i == substr.length - 1) {
                    nums[i] = Integer.parseInt(substr[i].substring(0, substr[i].length() - 1));
                } else {
                    nums[i] = Integer.parseInt(substr[i]);
                }
            }

            int ret = jump(nums);
            System.out.println(ret);

        }
    }

    public static int jump(int[] nums) {
        int position = nums.length - 1;
        int steps = 0;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }
}
