package src.nowcoder.SwordOffer;

import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;

public class FamaCombination {
    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);
            int [] m = new int [n];
            int [] x = new int [n];

            String [] mStr = br.readLine().split(" ");
            String [] xStr = br.readLine().split(" ");

            ArrayList<Integer> nums = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                m[i] = Integer.parseInt(mStr[i]);
                x[i] = Integer.parseInt(xStr[i]);
                for (int j = 0; j < x[i]; j++) {  // 添加砝码的总个数
                    nums.add(m[i]);
                }
            }

            // 排序是剪枝前提
            Collections.sort(nums);

            // 回溯
            HashMap<Integer, Integer> res = new HashMap<>(); // 结果去重，如果是记录路径，这句可不要
            int sum = 0;
            backtrack(nums, res, 0, sum);

            System.out.println(res.size());
        }

    }

    // 回溯法：不同元素组合成的值
    public static void backtrack(ArrayList<Integer> nums, HashMap<Integer, Integer> res, int depth, int sum) {
        // 记录每一个组合值。如果是具体的值，则需要在此处加判断条件
        if (!res.containsKey(sum))
            res.put(sum, 1);

        for (int i = depth; i < nums.size(); i++) {
            // 剪枝：去重相同元素
            if (i > depth && (nums.get(i) == (nums.get(i - 1)))) {
                continue;
            }

            sum += nums.get(i);
            backtrack(nums, res, i + 1, sum);  // 不包括自身
            sum -= nums.get(i);
        }
    }
}