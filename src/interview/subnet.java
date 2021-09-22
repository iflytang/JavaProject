package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class subnet {

    public static boolean isSubnet (String ip, String subnet) {

        String [] ipStr = ip.split("\\.");
        String [] subnetStr = subnet.split("\\.");

        int idx = subnetStr[3].indexOf("/");
        int maskVal = Integer.parseInt(subnetStr[3].substring(idx + 1));
        subnetStr[3] = subnetStr[3].substring(0, idx);

        int [] nums1 = new int[4];
        int [] nums2 = new int[4];
        int [] mask = new int[4];

        int n = maskVal / 8;
        int k = maskVal % 8;
        for (int i = 0; i < 4; i++) {
            if (i < n) {
                mask[i] = 0xff;
            } else {
                mask[i] = (0xff << (8 - k));
            }
        }

        for (int i = 0; i < 4; i++) {
            nums1[i] = Integer.parseInt(ipStr[i]);
            nums2[i] = Integer.parseInt(subnetStr[i]);

            if ((nums1[i] & mask[i]) != (nums2[i] & mask[i])) {
                return false;
            }
        }

        return true;

    }

    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            String ip = str;
            String subnet = br.readLine();

            boolean ret = isSubnet(ip, subnet);

            System.out.println(ret);
        }

    }













}
