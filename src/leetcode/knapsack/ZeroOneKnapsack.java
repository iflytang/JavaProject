package src.leetcode.knapsack;

import src.nowcoder.SwordOffer.Main;

import java.io.IOException;
import java.util.List;

public class ZeroOneKnapsack {

    public int knapsack(int capacity, int[] weight, int[] value) {

        int num = weight.length;

        int [][] totalValue = new int[num][capacity + 1];

        for (int j = 0; j <= capacity; j++) {
            totalValue[0][j] = (weight[0] <= j ? value[0] : 0);
        }

        for (int i = 1; i < num; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (weight[i] <= j) {
                    totalValue[i][j] = Math.max(totalValue[i - 1][j], totalValue[i - 1][j - weight[i]] + value[i]);
                } else {
                    totalValue[i][j] = totalValue[i - 1][j];
                }
            }
        }


        return totalValue[num - 1][capacity];
    }

    public int knapsack2(int capacity, int[] weight, int[] value) {

        int num = weight.length;

        int [][] totalValue = new int[num + 1][capacity + 1];

        // 构建二维数组，num = 0 || capacity = 0时（即第一行和第一列），值都为0
        for (int i = 1; i <= num; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (weight[i - 1] <= j) {
                    totalValue[i][j] = Math.max(totalValue[i - 1][j], totalValue[i - 1][j - weight[i - 1]] + value[i - 1]);
                } else {
                    totalValue[i][j] = totalValue[i - 1][j];
                }
            }
        }

        int N = num;
        int C = capacity;
        while (N != 0) {
            if (totalValue[N][C] != totalValue[N-1][C]) {
                System.out.println("item: " + N + ", weight: " + weight[N-1] + ", value: " + value[N-1]);
                C = C - weight[N-1];
            }
            N--;
        }

        return totalValue[num][capacity];
    }

    public static void main(String[] args) throws IOException {

        ZeroOneKnapsack knapsackTest = new ZeroOneKnapsack();

        int capacity = 5;
        int [] weight = {1, 2, 3};
        int [] value = {6, 10, 22};

        int ret = knapsackTest.knapsack(capacity, weight, value);
        System.out.println(ret);

        int capacity2 = 11;
        int [] weight2 = {1, 2, 5, 6, 7};
        int [] value2 = {1, 6, 18, 22, 28};
        int ret2 = knapsackTest.knapsack2(capacity2, weight2, value2);
        System.out.println(ret2);

    }
}
