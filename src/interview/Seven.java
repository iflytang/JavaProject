package src.interview;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
输出7和7的倍数，还有包含7的数字例如（17，27，37...70，71，72，73...）
输入
一个整数N。(N不大于30000)29999
输出
统计出不大于N的与7有关的数字的个数。如20以内与7有关的数为7、14、17共3个。
*/

public class Seven { // 含7或7的倍数的总个数

    public boolean contains(int num) {
        boolean contains = false;

        while (num > 0) {
            if (num % 10 == 7) {
                contains = true;
            }
            num /= 10;
        }

        return contains;
    }

    public int solution(int num) {

        int k = 0;

        for (int i = 7; i <= num; i++) {
            if (i % 7 == 0) {
//                System.out.println("i: " + i + ", case: " + "times");
                k++;
            } else if (contains(i)) {
//                System.out.println("i: " + i + ", case: " + "contains");
                k++;
            }
        }

        return k;
    }

    public boolean isSeven(int num) {

        if (num % 7 == 0) {  // 7的倍数
            return true;
        }

        while (num > 0) {  // 包含7
            if (num % 10 == 7) {
                return true;
            }
            num /= 10;
        }

        return false;
    }

    // 比特位翻转，十六进制，翻转4位
    public static char reverseBit(char dChar) {
        int num = Integer.parseInt(dChar + "", 16);  // 16进制
        int target = 0;

        int bitmask = 0x01;
        for (int i = 0; i < 4; i++) {
            if ((num & bitmask) != 0) {
                target |= (0x01 << (3 - i));
            }
            bitmask = (bitmask << 1);
        }

        char res = Character.forDigit(target, 16); // 16进制

//        return res;

         return target < 10 ? res : (char) (res - 'a' + 'A');
    }

    public static void main(String[] args) throws IOException {

        Seven test = new Seven();
        int num = 105140;
        int ret = test.solution(num);
        System.out.println(ret);
        System.out.println(test.isSeven(num));

        int [] nums = new int[] {

        };

        System.out.println(nums.length);

        HashSet<Integer> set = new HashSet<>();
        set.add(5);
        System.out.println(Arrays.toString(set.toArray()));
        set.add(1);
        System.out.println(Arrays.toString(set.toArray()));
        set.add(4);
        System.out.println(Arrays.toString(set.toArray()));
        set.add(2);
        System.out.println(Arrays.toString(set.toArray()));

    }
}
