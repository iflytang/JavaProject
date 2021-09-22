package src.interview;

import java.io.IOException;
import java.util.Arrays;

public class RoundRobinPrint {

    static int [] weightsBK = new int[] {1, 2, 3, 4};  // 初始状态
    static int [] weights = new int[] {1, 2, 3, 4};

    static char [] chars = new char[] {'A', 'B', 'C', 'D'};


    public static void print() {
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] != 0) {
                System.out.print(chars[i]);
                weights[i]--;
            }
        }

        System.out.println();

        // 如果最后一个权重值也为0，重置为初始状态
        if (weights[weights.length - 1] == 0) {
            weights = Arrays.copyOfRange(weightsBK, 0, weightsBK.length);
        }
    }

    public static void main (String [] args) throws IOException {
        for (int i = 0; i < 12; i++) {
            print();
        }
    }
}
