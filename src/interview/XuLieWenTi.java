package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class XuLieWenTi {
    public static void main (String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);

            int [] A = new int[n];
            String [] strs = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                A[i] = Integer.parseInt(strs[i]);
            }

            solution(A, n);
            solution2(A, n);
        }

    }

    public static int solution (int [] A, int n) {
        int[] pre = new int[n];

        for (int i = 0; i < n; i++) {
            int maxVal = 0;
            for (int j = 0; j < i; j++) {
                if (maxVal < A[j] && A[j] < A[i]) {
                    maxVal = A[j];
                }
            }
            pre[i] = maxVal;
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += pre[i] * (i + 1);
        }

        System.out.println(sum);

        return sum;
    }

    public static int solution2 (int [] A, int n) {

        int [][] pre = new int[n][2];
        pre[0][0] = 0;
        pre[0][1] = 1;

        for (int i = 1; i < n; i++) {
            int [][] nums = new int[i][2];

            for (int k = 0; k < i; k++) {
                nums[k][0] = A[k];  // val
                nums[k][1] = k;     // idx
            }

            Arrays.sort(nums, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });
//            System.out.println(Arrays.deepToString(nums));

            int j = i - 1;
            while (j > 0) {
                if (nums[j][0] < A[i]) {
                    break;
                }
                j--;
            }
            pre[i][0] = nums[j][0];  // 当前pre
            pre[i][1] = i;      // 当前idx
        }

//        System.out.println(Arrays.deepToString(pre));

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += pre[i][0] * (pre[i][1] + 1);
        }

        System.out.println(sum);

        return sum;

    }
}