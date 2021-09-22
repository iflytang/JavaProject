package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 机器任务调度，P越小优先级越高，T越大越先分配。
 *
 * 输入描述：
 * 第一行输入为N和K，N表示生产车间的贵重仪器数量，K表示需要使用仪器的生产测试数量设备；之后有K行，每一行表示一台生产测试设备要占用仪器的
 * 时间和优先级为[T, P];
 * 1 <= N <= 1000, 1 <= K < 1000, 1 <= T <= 1000, 1 <= P < 1000
 *
 * 输出描述：
 * 输出所有生产测试设备要占用贵重仪器的测试项目都执行完成，所需要的最少时间。
 *
 * 示例1：
 * 输入：
  2 2
  3 1
  4 2
 * 输出：
  4
 *
 *
 * 示例2：
  3 7
  1 1
  4 1
  5 3
  4 2
  2 1
  3 3
  3 1
 * 输出：
 * 1
 */

public class DiaoDu {
    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            String [] NK = str.split(" ");
            int N = Integer.parseInt(NK[0]);
            int K = Integer.parseInt(NK[1]);

            int [][] tasks = new int[K][2];
            for (int i = 0; i < K; i++) {
                String [] taskStr = br.readLine().split(" ");
                tasks[i][0] = Integer.parseInt(taskStr[0]);
                tasks[i][1] = Integer.parseInt(taskStr[1]);
            }

            int res = solution(N, K, tasks);

            System.out.println(res);
        }

    }

    public static int solution (int N, int K, int [][] tasks) {
        // 先按优先级升序排序，如果优先级相同，按时间高低降序排序
        Arrays.sort(tasks, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                } else {
                    return o2[0] - o1[0];
                }
            }
        });

        int [] machineTime = new int[N];  // 每台服务器的时间
        for (int i = 0; i < K; i++) {

            int minTime = machineTime[0];
            int id = 0;  // 机器ID

            // 获得最小时间的机器
            for (int j = 1; j < N; j++) {
                if (minTime > machineTime[j]) {
                    minTime = machineTime[j];
                    id = j;
                }
            }

            // 获得最小时间的机器，将任务分配上去
            machineTime[id] += tasks[i][0];

        }

        // 找最大时间值
        int res = machineTime[0];
        for (int i = 0; i < N; i++) {
            res = Math.max(res, machineTime[i]);
        }

        return res;
    }
}

//public class DiaoDu {
//    public static void main(String[] args) {
//        int N = 3;
//        int K = 7;
//        int [][] test = new int[][] {{1,1},{4,1},{5,3},{4,2},{2,1},{3,3},{2,1}};
//        Arrays.sort(test, new Comparator <int[]>(){ //新的解释器
//            public int compare(int[] a, int[] b){
//                if(a[1] != b[1]){
//                    return a[1] - b[1]; //返回元素的值，所以是int类型
//                }else{
//                    return b[0] - a[0];
//                }}
//        });
//        int res = 0;
//        int [] tmp = new int[test.length];
//        for(int i = 0; i < tmp.length; i++){
//            tmp[i] = test[i][0];
//        }
//        System.out.println(solution(N,tmp));
//
//    }
//    public static int solution(int N, int[] tmp){
//        int[] total = new int[N];
//        for (int i : tmp){
//            int min_time = total[0];
//            int k = 0;
//            for (int j = 1; j < N; j++){
//                if (min_time > total[j]){
//                    k = j;
//                    min_time = total[j];
//                }
//            }
//            total[k] += i;
//        }
//        int ans = 0;
//        for (int i = 0; i < total.length; i++){
//            if (total[i] > ans){
//                ans = total[i];
//            }
//        }
//        return ans;
//    }
//
//}
