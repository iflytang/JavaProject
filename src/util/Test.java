package src.util;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author tsf
 * @date 18-6-23.
 * @desc test toolkit
 */
public class Test {

    public static float bytes2Float(byte[] arr, int k){
        int value=0;
        for(int i=0;i< 4;i++){
            value|=((arr[k]&0xff))<<(4*i);
            k++;
        }
        return Float.intBitsToFloat(value);
    }

    public static byte[] intToBytes2(int n) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (n >> (24 - i * 8));
        }
        return b;
    }

    public static float byte2float(byte[] b, int index) {
        int l;
        l = b[index + 3];
        l &= 0xff;
        l |= ((long) b[index + 2] << 8);
        l &= 0xffff;
        l |= ((long) b[index + 1] << 16);
        l &= 0xffffff;
        l |= ((long) b[index + 0] << 24);
        return Float.intBitsToFloat(l);
    }

    public int MAX_INT(int[] arr) {
        return Arrays.stream(arr).max().getAsInt();
    }

    public static float MAX_floatArry(float[] arr) {
        float[] arrs = arr;
        Arrays.sort(arrs);
        return arrs[arrs.length-1];
    }

    public static float MAX_floatStream(float[] arr) {
        double[] doubles = IntStream.range(0, arr.length).mapToDouble(i -> arr[i]).toArray();
        return (float) Arrays.stream(doubles).max().getAsDouble();
    }

    public static String construct_ocm_conf(double start_freq, double watchWindow, double slice) {
        String ocm_conf = Double.toString(start_freq)+" "+Double.toString(watchWindow)+" "+Double.toString(slice);
        return ocm_conf;
    }


    public static void main(String[] args) {
        ToolKit tools = new ToolKit();

        /* test short_to_hext_str */
//        short short_val = 0x010b;
//        String short_hex = tools.short_to_hex_str(short_val);
//        System.out.println("short: " + short_val + ", short_hex: " + short_hex);

        /* test int_to_hext_str */
//        int int_val = 0x0102030b;
//        String int_hex = tools.int_to_hex_str(int_val);
//        System.out.println("int: " + int_val + ", int_hex: " + int_hex);

        /* test byte_to_hex_str */
//        byte byte_val = 0x01;
//        String byte_hex = tools.byte_to_hex_str(byte_val);
//        System.out.println("byte: " + byte_val + ", byte_hex: " + byte_hex);

        /* test ip_to_hex_str */
        /*String ip = "10.00.10.1728";
        String ip_hex = tools.ip_to_hex_str(ip);
        System.out.println("ip: " + ip + ", ip_hex: " + ip_hex);

        int value = 0x00000001;
        System.out.println("test: " + Float.intBitsToFloat(value));
        System.out.println("test: " + Float.floatToIntBits(1.0f));*/

//        byte[] bytes = intToBytes2(Float.floatToIntBits(1.0f));
        /*byte[] bytes = {'@', 0x00, 0x00, 0x00};
        float f = bytes2Float(bytes, 0);
        float f2 = byte2float(bytes, 0);
        System.out.println(bytes.length);
        System.out.println(f);
        System.out.println(f2);*/

//        float[] num = {1.1f, 1.5f, 1.3f};
////        System.out.println(MAX_floatArry(num));
//        System.out.println(MAX_floatStream(num));
//        System.out.println(Arrays.toString(num));
//
//        float x = 0.70f;
//        System.out.println((int) (x/0.1f));

        double start_freq = 192.575;   // THz, central wavelength
        double watchWindow = 0.05;     // THz
        double severe_level_slice_unit = 0.0003125;  // 0.05 / 0.0003125 = 160 points
        double medium_level_slice_uint = severe_level_slice_unit * 2;    // 80 points, * 2
        double normal_level_slice_uint = severe_level_slice_unit * 8;    // 20 points, * 8
        System.out.println(Double.toString(start_freq) + " " + Double.toString(start_freq).length() + "\n" +
                Double.toString(watchWindow) + " " + Double.toString(watchWindow).length() + "\n" +
                Double.toString(severe_level_slice_unit) + " " + Double.toString(severe_level_slice_unit).length() + "\n" +
                Double.toString(medium_level_slice_uint) + " " + Double.toString(medium_level_slice_uint).length() + "\n" +
                Double.toString(normal_level_slice_uint) + " " + Double.toString(normal_level_slice_uint).length()
        );

        System.out.println(construct_ocm_conf(start_freq, watchWindow, severe_level_slice_unit) + " " + construct_ocm_conf(start_freq, watchWindow, severe_level_slice_unit).length() + "\n" +
                construct_ocm_conf(start_freq, watchWindow, medium_level_slice_uint) + " " + construct_ocm_conf(start_freq, watchWindow, medium_level_slice_uint).length() + "\n" +
                construct_ocm_conf(start_freq, watchWindow, normal_level_slice_uint) + " " + construct_ocm_conf(start_freq, watchWindow, normal_level_slice_uint).length()
        );

    }
}
