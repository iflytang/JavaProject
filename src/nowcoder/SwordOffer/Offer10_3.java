package src.nowcoder.SwordOffer;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 */


public class Offer10_3 {

    public int rectCover(int target) {

        if (target <= 1) { // f(0) = 1; f(1) = 1
            return 1;
        }

        int fN1 = 1;
        int fN2 = 1;
        int fSum = 0;

        for (int i = 2; i <= target; i++) {
            fSum = fN1 + fN2;

            fN2 = fN1;
            fN1 = fSum;
        }

        return fSum;
    }


    public static void main(String[] args) {

        Offer10_3 offer10 = new Offer10_3();

        int ret = offer10.rectCover(4);
        System.out.println(ret);

    }
}
