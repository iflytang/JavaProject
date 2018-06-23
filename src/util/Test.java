package src.util;

/**
 * @author tsf
 * @date 18-6-23.
 * @desc test toolkit
 */
public class Test {

    public static void main(String[] args) {
        ToolKit tools = new ToolKit();

        //test short_to_hext_str
        short short_val = 0x010b;
        String short_hex = tools.short_to_hex_str(short_val);
        System.out.println("short: " + short_val + ", short_hex: " + short_hex);

        //test int_to_hext_str
        int int_val = 0x0102030b;
        String int_hex = tools.int_to_hex_str(int_val);
        System.out.println("int: " + int_val + ", int_hex: " + int_hex);

        // test ip_to_hex_str
        String ip = "10.00.10.172";
        String ip_hex = tools.ip_to_hex_str(ip);
        System.out.println("ip: " + ip + ", ip_hex: " + ip_hex);

    }
}
