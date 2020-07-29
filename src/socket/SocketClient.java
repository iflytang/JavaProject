package src.socket;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author tsf
 * @date 19-4-10
 * @desp
 */

public class SocketClient {

    private static final String SERVER_ADDR = "192.168.108.126";
    private static final String CLIENT_ADDR = "192.168.109.209";
    private static final int PORT = 9999;

    public static void main (String[] args) {

        try {
            Socket client = new Socket(SERVER_ADDR, PORT);
            System.out.println("server:" + SERVER_ADDR + ", port:" + PORT);

            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader recv = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintStream send = new PrintStream(client.getOutputStream());

            boolean flag = true;
            while (true) {
                String str = input.readLine();

                if ("end".equals(str)) {
                    break;
                }
                send.println(str);
            }

            input.close();
            recv.close();
            send.close();
            client.close();
        } catch (IOException io) {
            io.printStackTrace();
        }

    }
}
