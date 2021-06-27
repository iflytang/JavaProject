package src.interview;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MultiMachineScheduling {

    public int solution(int machines, int works, int [] time) {

        Arrays.sort(time);

        for (int i = 0; i < works / 2; i++) {
            int tmp = time[i];
            time[i] = time[time.length - 1 - i];
            time[time.length - 1 - i] = tmp;
        }
        
        if (machines >= works) {  // 机器够用，找出最大时间即可
            return time[0];
        } 

        // 机器不够用
        int [] machineTime = new int [machines];
        for (int i = 0; i < works; i++) {  // 每次找最短时间的机器放最长时间的任务
            
            // 找最短的机器
            int minMachineTime = machineTime[0], minIdx = 0;
            for (int j = 1; j < machines; j++) {
                if (minMachineTime > machineTime[j]) {
                    minMachineTime = machineTime[j];
                    minIdx = j;
                }
            }

            // 放最长的任务，time已排序
            machineTime[minIdx] += time[i];
        }

        // 返回最短时间
        int max = machineTime[0];
        for (int i = 1; i < machines; i++) {
            max = Math.max(max, machineTime[i]);
        }

        return max;
    }

    public static void main(String[] args) throws IOException {

        MultiMachineScheduling test = new MultiMachineScheduling();

        int machines = 3;
        int works = 7;
        int [] time = {2, 14, 4, 16, 6, 5, 3};
        int ret = test.solution(machines, works, time);
        System.out.println(ret);
        String x = "123";
        String y = "234";
        System.out.println(x.compareTo(y));

    }
}
