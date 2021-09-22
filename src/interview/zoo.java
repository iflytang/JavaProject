package src.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * 输入1：
 * [1,2,15]
 * [1,3,7]
 * [3,4,9]
 * 4
 * 1
 *
 * 输出1：
 * 16
 *
 * 输入2：
 * [3,4,9]
 * 4
 * 3
 */

public class zoo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        zoo z = new zoo();

        while (sc.hasNext()) {
            ArrayList<String> edges = new ArrayList<>();
            String edge = new String();
            edge = sc.nextLine();
            while (edge.charAt(0) == '[') {
                edges.add(edge);
                edge = sc.nextLine();
            }
            int N = Integer.parseInt(edge);
            int start = Integer.parseInt(sc.nextLine());
            int[][] zooMap = new int[N + 1][N + 1];
            for (int[] ints : zooMap) {
                Arrays.fill(ints, -1);
            }
            for (String s : edges) {
                edge = s;
                edge = edge.substring(1, edge.length() - 1);
                int src = Integer.parseInt(edge.split(",")[0]);
                int dst = Integer.parseInt(edge.split(",")[1]);
                int len = Integer.parseInt(edge.split(",")[2]);
                zooMap[src][dst] = len;
            }
            System.out.println(z.longestPath(zooMap, N, start));
        }


    }

    int longestPath(int[][] zooMap, int N, int start) {
        int maxPath = -1;
        boolean[] passed = new boolean[N + 1];
        passed[0] = true;
        passed[start] = true;
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        int currentNode = 0;
        int tmpPathLen = 0;
        while (!stack.empty()) {
            currentNode = stack.peek();
            int i = 1;
            while (i <= N && zooMap[currentNode][i] < 0) {
                i++;
            }
            if (i > N) {//说明这个点是没有后续节点。
                maxPath = Math.max(maxPath, tmpPathLen);
                int popped = stack.pop();
                if (!stack.empty())
                    tmpPathLen = tmpPathLen + zooMap[stack.peek()][currentNode];
            } else {
                stack.push(i);
                passed[i] = true;
                tmpPathLen = tmpPathLen + zooMap[currentNode][i];
                zooMap[currentNode][i] = -zooMap[currentNode][i];
            }
        }

        for (boolean b : passed)
            if (!b)
                return -1;
        return maxPath;
    }
}