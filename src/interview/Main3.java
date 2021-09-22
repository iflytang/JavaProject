package src.interview;

class Main3
{
    public static void main (String[] args) throws Exception
    {
        System.out.println("hi");
        get();
    }

    public static void get() {
        int [] num = {45, 155, 14, 5, 8, 7, 78, 15, 14, 4, 0, 35, 9, 7, 894};

        for (int i = 1; i <= 8; i++) {
            if ((i % 2) == 0) {
                num[0] = num[0] + num[i];
            } else {
                num[0] = num[0] - num[i];
            }
        }


        System.out.println(num[0]);
    }
}