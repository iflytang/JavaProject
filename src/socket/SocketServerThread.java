package src.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author tsf
 * @date 2020-05-12
 * @desp
 */
public class SocketServerThread implements Runnable {

    private Socket client = null;
//    private static int socket_num = 0;

    public SocketServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        InetAddress inetAddress = null;

        InputStream inputStream = null;   // receive data from client
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        OutputStream outputStream = null;  // send data to client
        PrintWriter printWriter = null;

        try {
            inetAddress = client.getInetAddress();

            inputStream = client.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            outputStream = client.getOutputStream();
            printWriter = new PrintWriter(outputStream);

            String msg = null;
            while ((msg = bufferedReader.readLine()) != null) {

                /* RECEIVE DATA FROM CLIENT */

                /*JsonObject object = Json.parse(msg).asObject();
                String name = object.get("name").asString();
                System.out.println(name); */

                System.out.println("server, from client<" + inetAddress + ">: " + msg);


                /* SEND DATA TO CLIENT */
                printWriter.write("Server: " + msg);
                printWriter.flush();

            }
            client.shutdownInput();   // close input stream

        } catch (IOException e) {
            e.printStackTrace();
        } finally {  // close socket resource
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }

                if (inputStream != null) {
                    inputStream.close();
                }

                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }

                if (client != null) {
                    client.close();
                    SocketServer.socket_num--;
                    System.out.println("client<" + inetAddress + "> disconnected! current connected_num: " + SocketServer.socket_num);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
