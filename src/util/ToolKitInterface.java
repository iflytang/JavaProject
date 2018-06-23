package src.util;

/**
 * @author tsf
 * @date 18-6-23.
 * @desc
 */
public interface ToolKitInterface {

    // convert byte[] into hex_str
    String bytes_to_hex_str(byte[] b);

    // convert
    String short_to_hex_str(short val);

    // convert
    String int_to_hex_str(int val);
}
