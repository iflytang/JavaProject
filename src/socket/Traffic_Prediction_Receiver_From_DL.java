package src.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author tsf
 * @date 19-4-10
 * @desp
 */
public class Traffic_Prediction_Receiver_From_DL implements Runnable {

    private static final String SERVER_ADDR = "192.168.109.214";
    private static final String CLIENT_ADDR = "192.168.109.209";
    private static final int PORT = 2020;

    private static final short SHORT_LEN = 4;  // in hex str
    private static final short SHORT_OFF = 4;  // in hex str

    private Socket client = null;
    private static int socket_num = 0;

    public Traffic_Prediction_Receiver_From_DL(Socket client) {
        this.client = client;
    }

    public static double bytes2Double(byte[] arr, int k){
        long value=0;
        for(int i=0;i< 8;i++){
            value|=((long)(arr[k]&0xff))<<(8*i);
            k++;
        }
        return Double.longBitsToDouble(value);
    }

    public static int bytes2Int(byte[] arr, int k){
        int value=0;
        for(int i=0;i< 4;i++){
            value|=((arr[k]&0xff))<<(4*i);
            k++;
        }
        return value;
    }

    public static float bytes2float(byte[] b, int index) {
        int l;
        l = b[index + 0];
        l &= 0xff;
        l |= ((long) b[index + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[index + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[index + 3] << 24);
        return Float.intBitsToFloat(l);
    }

    public boolean parse (String msg) {
        short type, len, err_type;
        short msg_off = 0;

        type = Short.parseShort(msg.substring(msg_off, msg_off+SHORT_OFF));
        msg_off += SHORT_LEN;
        len = Short.parseShort(msg.substring(msg_off, msg_off+SHORT_OFF));
        msg_off += SHORT_LEN;

        if (type == 0x0001) {
            err_type = Short.parseShort(msg.substring(msg_off, msg_off+SHORT_OFF));
            System.out.println("type: " + type);
            System.out.println("len: " + len);
            System.out.println("err_type: " + err_type);
        }

        return true;
    }

    @Override
    public void run() {
        try {
            /* connection setups, read data from client. */
            InetAddress inetAddress = null;
            inetAddress = client.getInetAddress();

            InputStream inStrm_sock = client.getInputStream();

            /* received buf. */
            int predict_len = 24;
            int monitor_nodes = 3;
            int float_byte_size = 4;
            byte[] receive = new byte[(monitor_nodes + predict_len) * float_byte_size];

            /* received parsed data. */
            float[] cur_nodes_trace_data = new float[monitor_nodes];
            float[] predict_trace_data = new float[predict_len];

            int i,j;
            while (true) {

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                int len = inStrm_sock.read(receive, 0, receive.length);
                System.out.println(df.format(new Date()) + ", len: " + len);

                for (i = 0, j = 0; i < len; i = i + float_byte_size, j++) {
                    if (j < monitor_nodes) {   // first 'monitor_nodes' points
                        cur_nodes_trace_data[j] = bytes2float(receive, i);
                        System.out.println("cur_nodes_trace_data[" + j + "]: " + cur_nodes_trace_data[j]);
                        continue;
                    }

                    predict_trace_data[j-monitor_nodes] = bytes2float(receive, i);
                    System.out.println("predict_trace_data[" + (j-monitor_nodes) + "]: " + predict_trace_data[j-monitor_nodes]);
                }
                System.out.println();

                if (len < 0) {
                    break;
                }

                Arrays.fill(cur_nodes_trace_data, 0);
                Arrays.fill(predict_trace_data, 0);
                Arrays.fill(receive, (byte) 0);
            }

            inStrm_sock.close();
            client.close();
            socket_num--;
            System.out.println("client<" + inetAddress + "> disconnected! connected_num: " + socket_num);
        } catch (IOException io) {
            io.printStackTrace();
        }

    }

    public static void main (String[] args) {

        try {
            /* server */
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("server socket is waiting to be connected ...");
            System.out.println(serverSocket);
            System.out.println("listening port is " + PORT);

            Socket client = null;
            InetAddress inetAddress = null;
            boolean flag = true;

            while (flag) {
                client = serverSocket.accept();
                inetAddress = client.getInetAddress();
               socket_num++;
                System.out.println("client<" + inetAddress + "> connected! connected_num: " + socket_num);
                new Thread(new Traffic_Prediction_Receiver_From_DL(client)).start();

            }

            serverSocket.close();
            System.out.println("server socket closed.");

        } catch (IOException io) {
            io.printStackTrace();
        }

    }

}
