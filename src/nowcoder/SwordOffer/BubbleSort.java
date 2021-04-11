package src.nowcoder.SwordOffer;

import java.util.Arrays;

public class BubbleSort {

    public int [] myBubbleSort(int [] unsorted) {

        int tmp = 0;
        for (int i = 0; i < unsorted.length - 1; i++) {
            for (int j = 0; j < unsorted.length - 1 - i; j++) {
                if (unsorted[j] > unsorted[j + 1]) {  // ascending order
                    tmp = unsorted[j + 1];
                    unsorted[j + 1] = unsorted[j];
                    unsorted[j] = tmp;
                }
            }
        }

        return unsorted;
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();

        int [] input = {3, 4, 8, 10, 2};
        int [] ret = bubbleSort.myBubbleSort(input);
        System.out.println(Arrays.toString(ret));


    }
}
