package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class memoryBinAlloc {

    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            int N = Integer.parseInt(str);

            ArrayList<Integer> cards = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                String [] substr = br.readLine().split(" ");
                for (int j = 0; j < Integer.parseInt(substr[0]); j++) {
                    cards.add(Integer.parseInt(substr[1]));
                }
            }
            Collections.sort(cards);

            int M = Integer.parseInt(br.readLine());
            String [] reqMem = br.readLine().split(" ");
            int [] req = new int[M];
            for (int i = 0; i < M; i++) {
                req[i] = Integer.parseInt(reqMem[i]);
            }
            Arrays.sort(req);

            // first fit
            int used = 0;
            for (int i = M - 1; i >= 0; i--) {
                for (int j = cards.size() - 1; j >= 0; j--) {
                    if (req[i] < cards.get(j)) {
                        cards.set(j, cards.get(j) - req[i]);
                        used = j;
                        break;
                    }
                }
            }

            int sum = 0;
            for (int i = used; i >= 0; i--) {
                sum += cards.get(i);
            }

            System.out.println(sum);
        }

    }

}
