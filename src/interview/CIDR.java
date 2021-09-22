package src.interview;

/**
 * 字节：
 * 从一个CIDR网段中切割一个子网段，返回剩余网段。
 * 例如： 1.1.0.0/16 - 1.1.128.0/17 = 1.1.0.0/17
 * 注意：结果子网可能是多个，结果子网的prefix长度需要最短
 */

public class CIDR {
    public static void main(String[] args) {
        String str = "1.1.0.0/16";
        String dst = "1.1.160.0/19";
        int m = 0;
        int n = 0;
        String a = null, b = null;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '/') {
                m = Integer.valueOf(str.substring(i + 1, str.length()));
                a = str.substring(0, i);
                break;
            }
        }
        for(int i = 0; i < dst.length(); i++) {
            if(dst.charAt(i) == '/') {
                n = Integer.valueOf(dst.substring(i + 1, dst.length()));
                b = dst.substring(0, i);
                break;
            }
        }
        long src_a = ipToLong(a);
        long src_b = ipToLong(b);
        int e = n - m;
        for(int i = 1; i <= e; i++) {
            int k = (m + i);
//            System.out.println("k: " + k);
            long tmp = src_b;
//            System.out.println(" 0: " + longToIP(tmp));
            tmp = tmp - tmp % (1 << (32 - k));
//            System.out.println(" 1: " + longToIP(tmp));

            if(tmp % (1 << (33 - k)) != 0) {
                tmp = tmp - (1 << (32 - k));
//                System.out.println(" 2 1: " + longToIP(tmp));
            } else {
                tmp = tmp + (1 << (32 - k));
//                System.out.println(" 2 0: " + longToIP(tmp));
            }
            System.out.println(longToIP(tmp) + "/" + k);
        }



    }

    public static long ipToLong(String ip) {
        long ans = 0;
        for (String x: ip.split("\\.")) {
            ans = 256 * ans + Integer.valueOf(x);
        }
        return ans;
    }

    private static String longToIP(long x) {
        return String.format("%s.%s.%s.%s",
                x >> 24, (x >> 16) % 256, (x >> 8) % 256, x % 256);
    }
}