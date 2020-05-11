package src.homomorphicencryption.LinearHash;


import java.util.*;
public class Test3 {

    public static void main(String[] args) {
        int min;
        int max;

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个数：");
        min = sc.nextInt();
        System.out.println("请再输入一个数：");
        max = sc.nextInt();

        if (max < min) {
            int temp = min;
            min = max;
            max = temp;
        }
        System.out.println("最大公约数为：" + Gcd(min, max));
        System.out.println("最小公倍数为：" + Lcm(min, max));
    }


    public static int Gcd(int min, int max) {

        while (max % min != 0) {
            int k = max % min;
            max = min;
            min = k;
        }
        return min;
    }

    public static int Lcm(int min, int max) {
        return min * max / Gcd(min, max);
    }
}
