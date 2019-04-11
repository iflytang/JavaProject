package src.socket;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author tsf
 * @date 19-4-10
 * @desp
 */
public class SocketServer implements Runnable {

    private static final String SERVER_ADDR = "192.168.109.214";
    private static final String CLIENT_ADDR = "192.168.109.209";
    private static final int PORT = 2019;

    private static final short SHORT_LEN = 4;  // in hex str
    private static final short SHORT_OFF = 4;  // in hex str

    private Socket client = null;
    private static int socket_num = 0;

    public SocketServer(Socket client) {
        this.client = client;
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
            byte[] buf = new byte[1024];  // received data from client
            int buf_len = -1;             // received data length
            InputStream inputStream = client.getInputStream();

            InetAddress inetAddress = null;
            inetAddress = client.getInetAddress();

            BufferedReader socketIn = new BufferedReader(new InputStreamReader(client.getInputStream()));

            boolean flag = true;
            while (flag) {
                String msg = socketIn.readLine();
                if (msg == null || "".equals(msg)) {
                    break;  // or 'flag = false';
                }

                /*JsonObject object = Json.parse(msg).asObject();
                String name = object.get("name").asString();
                System.out.println(name); */

//                parse(msg);

                System.out.println("server:" + msg);

            }

            socketIn.close();
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
            System.out.println("listening port is " + PORT);

            Socket client = null;
            InetAddress inetAddress = null;
            boolean flag = true;

            while (flag) {
                client = serverSocket.accept();
                inetAddress = client.getInetAddress();
               socket_num++;
                System.out.println("client<" + inetAddress + "> connected! connected_num: " + socket_num);
                new Thread(new SocketServer(client)).start();

            }

            serverSocket.close();
            System.out.println("server socket closed.");

        } catch (IOException io) {
            io.printStackTrace();
        }

    }

}
