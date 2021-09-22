package src.interview;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;

public class Huowu {
    public static void main(String [] args) throws IOException {
        int [] nums = new int[] {1, 2, 3, 4, 1, 4, 4, 4};
        int N = 6;

        Arrays.sort(nums);

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        int cnt = 0;
        boolean [] assigned = new boolean[nums.length];

        while (sum > 0) {
            int tmp = N;
            boolean flag = false;
            for (int i = nums.length - 1; i >= 0; i--) {
                if (!assigned[i] && (tmp > nums[i])) {
                    assigned[i] = true;
                    sum -= nums[i];
                    tmp -= nums[i];
                    flag = true;
                }
            }

            if (flag) {
                cnt++;
            }
        }

        System.out.println(cnt);

    }
}
