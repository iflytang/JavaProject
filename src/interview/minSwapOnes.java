package src.interview;

import java.io.IOException;

class minSwapOnes {
    // 1的最少交换次数
    public static int minSwaps(int[] data) {
        int n = 0;
        for (int d : data) {
            if (d == 1) n++;
        }
        int ones = 0, maxOnes = 0;
        for (int j = 0; j < data.length; j++) {
            if (data[j] == 1) {
                ones++;
            }
            if (j >= n && data[j-n] == 1) {
                ones--;
            }
            maxOnes = Math.max(ones, maxOnes);
        }
        return n - maxOnes;
    }

    public static void main (String [] args) throws IOException {

        int [] arr = {1, 0, 1, 0, 1};
        System.out.println(minSwaps(arr));
    }
}
