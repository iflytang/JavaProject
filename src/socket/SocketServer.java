package src.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author tsf
 * @date 2020-05-12
 * @desp
 */
public class SocketServer  {


    private static final String SERVER_ADDR = "192.168.109.214";
    private static final int PORT = 2022;

    static int socket_num = 0;

    public static void main (String[] args) {

        try {  // server
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("server<" + SERVER_ADDR + "> socket is waiting to be connected ...");
            System.out.println("listening port is " + PORT);

            Socket client = null;
            InetAddress inetAddress = null;

            while (true) {
                // client connection
                client = serverSocket.accept();
                inetAddress = client.getInetAddress();
                Thread serverThread = new Thread(new SocketServerThread(client));
                serverThread.start();

                // client statistics
                socket_num++;
                System.out.println("client<" + inetAddress + "> connected! current connected_num: " + socket_num);

                if (!serverThread.isAlive()) {
                    socket_num--;
                    System.out.println("client<" + inetAddress + "> disconnected! current connected_num: " + socket_num);
                }
            }

        } catch (IOException io) {
            io.printStackTrace();
        }

    }

}
