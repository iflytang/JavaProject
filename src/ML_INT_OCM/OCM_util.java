package src.ML_INT_OCM;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class OCM_util {
    public static byte[] charArr2ByteArr(char[] chars) {
        Charset cs = Charset.forName("UTF-8");
        CharBuffer cb = CharBuffer.allocate(chars.length);
        cb.put(chars);
        cb.flip();
        ByteBuffer bb = cs.encode(cb);
        return bb.array();
    }

    public static double bytes2Double(byte[] arr, int k){
        long value=0;
        for(int i=0;i< 8;i++){
            value|=((long)(arr[k]&0xff))<<(8*i);
            k++;
        }
        return Double.longBitsToDouble(value);
    }
}
