package src.interview;

import java.util.Scanner;

/**
 * @author tsf
 * @date 19-4-3
 * @desp
 */

public class Main {
    public static void helper(int[] arr) {
        System.out.println(3);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int len = in.nextInt();
        int[] arr = new int[len];

        for(int i = 0; i < len; i++) {
            arr[i] = in.nextInt();
        }

        helper(arr);
    }
}
