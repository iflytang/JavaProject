package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Ali1 {

    public static void main (String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);

            String [] substr = br.readLine().split(" ");
            int [] nums = new int[substr.length];

            for (int i = 0; i < nums.length; i++) {
                nums[i] = Integer.parseInt(substr[i]);
            }

            int ret = solution(nums);

            System.out.println(ret);

        }

    }

    public static int solution (int [] nums) {

        List<Integer> res = new ArrayList<>();

        for (int i = 1; i < nums.length - 1; i++) {
            int max = Math.max(Math.max(nums[i - 1], nums[i]), nums[i + 1]);
            int min = Math.min(Math.min(nums[i - 1], nums[i]), nums[i + 1]);

            if (nums[i] != max && nums[i] != min) {
                res.add(nums[i]);
            }

        }

        return res.size();

    }

}






















