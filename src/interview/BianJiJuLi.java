package src.interview;

import java.io.IOException;

public class BianJiJuLi {
    public static void main (String [] args) throws IOException {
        String [] commands = {"help", "version", "status"};
        String input = "statsu";

        System.out.println(didYouMean(commands, input));

        String [] commands2 = {"version", "view"};
        String input2 = "ver";

        System.out.println(didYouMean(commands2, input2));
    }

    public static String didYouMean (String [] commands, String input) {
        int min = Integer.MAX_VALUE;
        int tmp = 0;
        int idx = 0;

        for (int i = 0; i < commands.length; i++) {
            tmp = minDistance(commands[i], input);

            if (tmp < min) {
                min = tmp;
                idx = i;
            }
        }

        return commands[idx];
    }

    public static int minDistance(String word1, String word2) {  // 动态规划

        int m = word1.length();
        int n = word2.length();

        int [][] cost = new int [m + 1][n + 1];

        // 初始状态定义
        for (int i = 0; i <= m; i++) {  // 按行
            cost[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {  // 按列
            cost[0][j] = j;
        }

        // 状态转移
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {  // 无需操作
                    cost[i][j] = cost[i - 1][j - 1];
                } else {  // 比较插入、删除、替换得到最小值加一
                    cost[i][j] = 1 + Math.min(Math.min(cost[i][j - 1], cost[i - 1][j]), cost[i - 1][j - 1]);
                }
            }
        }

        return cost[m][n];
    }

}
