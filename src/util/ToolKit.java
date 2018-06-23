package src.util;

import java.nio.ByteBuffer;

/**
 * @author tsf
 * @date 18-6-23.
 * @desc some small useful methods as tool kit.
 */
public class ToolKit implements ToolKitInterface {

    @Override
    public String short_to_hex_str(short val) {
        StringBuilder hex_str = new StringBuilder();
        byte[] b = new byte[2];
        b[1] = (byte) (val & 0xff);
        b[0] = (byte) ((val >> 8) & 0xff);

        return bytes_to_hex_str(b);
    }

    @Override
    public String int_to_hex_str(int val) {

        byte[] b = new byte[4];
        ByteBuffer buffer = ByteBuffer.allocate(b.length);
        buffer.putInt(val);

        return bytes_to_hex_str(buffer.array());
    }

    @Override
    public String bytes_to_hex_str(byte[] b) {
        StringBuilder hex_str = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xff);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hex_str.append(hex);
        }
        return hex_str.toString();
    }
}
