package src.nowcoder.SwordOffer;

import java.io.*;
import java.util.*;

public class IPConvertion {
    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            String ip = str;
            int num = Integer.parseUnsignedInt(br.readLine());

            System.out.println(ipToNum(ip));
            System.out.println(numToIp(num));
        }
    }

    public static long ipToNum (String ip) {
        long ipNum = 0;

        String [] ipStr = ip.split("\\.");
        for (int i = 0; i < ipStr.length; i++) {
            int num = Integer.parseInt(ipStr[i]);
            ipNum |= ((long) num << 8 * (3 - i));
        }

        return ipNum;
    }

    public static String numToIp (int num) {

        StringBuilder sb = new StringBuilder();

        int mask = 0xff;
        for (int i = 0; i < 4; i++) {
            // int byteNum = ((num & (mask << 8 * (3 - i))));
            long byteNum = (num >>> (8 * (3 - i))) & mask;
            sb.append(byteNum).append(".");
        }

        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}