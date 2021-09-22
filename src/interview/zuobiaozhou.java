package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class zuobiaozhou {

    public static int [] move(String s) {
        int x = 0;
        int y = 0;
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num = (num * 10) + s.charAt(i) - '0';
            } else {
                num = num == 0 ? 1 : num;

                char dir = s.charAt(i);
                if (dir == 'A' || dir == 'a') {
                    x -= num;
                } else if (dir == 'D' || dir == 'd') {
                    x += num;
                } else if (dir == 'S' || dir == 's') {
                    y -= num;
                } else if (dir == 'W' || dir == 'w') {
                    y += num;
                }

                num = 0;
            }
        }

        System.out.println(x + ", " + y);
        return new int [] {x, y};
    }

    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        String str = null;

        while ((str = br.readLine()) != null) {
            System.out.println(Arrays.toString(move(str)));
        }


    }
}
