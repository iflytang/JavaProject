package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ZuiXiaoZhanLi {

    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);

            long [][] nums = new long [n][2];
            for (int i = 0; i < n; i++) {
                String [] substr = br.readLine().split(" ");
                nums[i][0] = Long.parseLong(substr[0]);
                nums[i][1] = Long.parseLong(substr[1]);
            }

            Arrays.sort(nums, new Comparator<long[]>() {
                @Override
                public int compare(long[] o1, long[] o2) {
                    return (int) (o1[0] - o2[0]);   // 从大到小
                }
            });

            long start = nums[0][0];
            while (true) {
                long add = solution(start, nums);

                if (add == 0) {
                    System.out.println(start);
                    break;
                } else {
                    start += add;
                }
            }
        }
    }


    public static long solution (long start, long [][] nums) {
        long len = nums.length;

        ArrayList<Long> list = new ArrayList<>();
        long power = start;

        int i = 0;
        for (; i < len; i++) {
            if (power >= nums[i][0]) {
                power += nums[i][1];
            } else {
                list.add(nums[i][0]);
                break;
            }
        }

        return (list.size() == 0) ? 0L : (nums[i][0] - power);
    }
}
