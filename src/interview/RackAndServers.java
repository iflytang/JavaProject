package src.interview;

import java.util.*;

/**
 * # rack_space is an int, indicating how many space a rack have, for example 5.
 * # servers is a list of servers, with each server's size, for example [2, 1, 4, 3, 1]
 * # constraints:
 * #   - server's total size in a rack cannot exceed rack space
 * #   - due to power consumption limit, cannot have more than 2 servers in a rack
 * # calculate minimum number of racks we need
 * # example: rack_space = 5, servers = [2, 1, 4, 3, 1]
 * # return: 3
 * def calculate_racks(rack_space, servers):
 *     # Write code here
 */

public class RackAndServers {
    public static int calculateRack(int rackSpace, int [] servers) {
        int [] arr = Arrays.copyOfRange(servers, 0, servers.length);
        Arrays.sort(arr); // 升序

        for (int i = 0; i < servers.length; i++) {  // 降序
            servers[i] = arr[arr.length - 1 - i];
        }

//        Set<Integer> set = new HashSet<>();

        int num = 0;
        boolean [] used = new boolean[servers.length];

        for (int i = 0; i < servers.length; i++) {
            if (!used[i]) {
//                set.add(servers[i]);
                used[i] = true;
                num++;
            } else {
                continue;
            }

            for (int j = i + 1; j < servers.length; j++) {
                if (!used[j] && (servers[j] <= rackSpace - servers[i])) {
//                    set.add(servers[j]);
                    used[j] = true;
                    break;
                }
            }
        }

        return num;
    }

    public static void main (String [] args) {
        int [] servers = {2, 1, 4, 3, 1, 3, 2};
        int rackSpace = 5;

        System.out.println(calculateRack(rackSpace, servers));

    }
}
