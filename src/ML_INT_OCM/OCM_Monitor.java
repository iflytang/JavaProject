package src.ML_INT_OCM;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author tsf
 * @date 20-7-7
 * @desp dynamically adjustable periodical request OCM data.
 */

public class OCM_Monitor {
    /* server socket info <ip_addr, port> */
    private static final String SER_ADDR = "192.168.108.126";
    private static final int SER_PORT = 9999;

    /* client socket for communicating with OCM. */
    private static Socket OCM;
    private static InputStream inStrm_OCM;
    private static InputStreamReader inStrmRd_OCM;
    private static BufferedInputStream bufInput_OCM;
    private static BufferedReader bufRd_OCM;
    private static OutputStream outStrm_OCM;
    private static PrintWriter prtwt_OCM;


    public static String getSerAddr() {
        return SER_ADDR;
    }

    public static int getSerPort() {
        return SER_PORT;
    }

    public static Socket getOCM() {
        return OCM;
    }

    public static InputStream getInStrm_OCM() {
        return inStrm_OCM;
    }

    public static InputStreamReader getInStrmRd_OCM() {
        return inStrmRd_OCM;
    }

    public static BufferedInputStream getBufInput_OCM() {
        return bufInput_OCM;
    }

    public static BufferedReader getBufRd_OCM() {
        return bufRd_OCM;
    }

    public static OutputStream getOutStrm_OCM() {
        return outStrm_OCM;
    }

    public static PrintWriter getPrtwt_OCM() {
        return prtwt_OCM;
    }


    private static void setupSocket(){
        try{
            OCM = new Socket(SER_ADDR, SER_PORT);  // build socket object and setup connection
            inStrm_OCM = OCM.getInputStream();
            inStrmRd_OCM = new InputStreamReader(inStrm_OCM);
            bufInput_OCM = new BufferedInputStream(inStrm_OCM);
            bufRd_OCM = new BufferedReader(inStrmRd_OCM);
            outStrm_OCM = OCM.getOutputStream();
            prtwt_OCM = new PrintWriter(outStrm_OCM);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void teardownSocket() {
        try {
            bufRd_OCM.close();
            inStrm_OCM.close();
            prtwt_OCM.close();
            OCM.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String construct_ocm_conf(double start_freq, double watchWindow, double slice) {
       String ocm_conf = Double.toString(start_freq)+" "+Double.toString(watchWindow)+" "+Double.toString(slice);
       return ocm_conf;
    }

    public static void main (String[] args) {
        double start_freq = 192.475;   // THz, central wavelength
        double watchWindow = 0.05;     // THz
//        double bandwidth = watchWindow/1000.0;   // THz
        double min_slice = 0.0003125;
        double max_slice = 0.05;
        double slice = max_slice;  // 0.0003125 0.05

        String ocm_conf = Double.toString(start_freq)+" "+Double.toString(watchWindow)+" "+Double.toString(slice);

        int cnt = 0;

        setupSocket();

        try {
            Socket client = new Socket(SER_ADDR, SER_PORT);
            System.out.println("server:" + SER_ADDR + ", port:" + SER_PORT);
            OcmMonitorThread ocm_thread = new OcmMonitorThread(watchWindow, slice);
            ocm_thread.start();

            while (true) {

                System.out.println(ocm_conf);

                if(cnt == 3) {
                    ocm_thread.setSlice(0.01);
                    ocm_conf = Double.toString(start_freq)+" "+Double.toString(watchWindow)+" "+Double.toString(0.01);
                }

                if(cnt == 6) {
                    ocm_thread.setSlice(0.0003125);
                    ocm_conf = Double.toString(start_freq)+" "+Double.toString(watchWindow)+" "+Double.toString(0.0003125);
                }

                if(cnt == 9) {
                    ocm_thread.setSlice(0.05);
                    ocm_conf = Double.toString(start_freq)+" "+Double.toString(watchWindow)+" "+Double.toString(0.05);
                }


                if ("end".equals(ocm_conf)) {
                    break;
                }
                prtwt_OCM.println(ocm_conf);
                prtwt_OCM.flush();

                cnt += 1;

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e);
                }
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                System.out.println("time: " + df.format(new Date()));
            }

            teardownSocket();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}


class OcmMonitorThread extends Thread {
    private volatile String write_file;
    private volatile double watchWindow;
    private volatile double slice;

    private final int DOUBLE_FIELD_SIZE = 8;

    private Socket OCM;
    private InputStream inStrm_OCM;
    private InputStreamReader inStrmRd_OCM;
    private BufferedInputStream bufInput_OCM;
    private BufferedReader bufRd_OCM;
    private OutputStream outStrm_OCM;
    private PrintWriter prtwt_OCM;

    OcmMonitorThread(double watchWindow, double slice) {
        this.watchWindow = watchWindow;
        this.slice = slice;

        OCM = OCM_Monitor.getOCM();
        inStrm_OCM = OCM_Monitor.getInStrm_OCM();
        inStrmRd_OCM = OCM_Monitor.getInStrmRd_OCM();
        bufInput_OCM = OCM_Monitor.getBufInput_OCM();
        bufRd_OCM = OCM_Monitor.getBufRd_OCM();
        outStrm_OCM = OCM_Monitor.getOutStrm_OCM();
        prtwt_OCM = OCM_Monitor.getPrtwt_OCM();

    }

    public void setFileName(String write_file) {
        this.write_file = write_file;
    }

    public void setOCM(Socket OCM) {
        this.OCM = OCM;
    }

    public void setInStrm_OCM(InputStream inStrm_OCM) {
        this.inStrm_OCM = inStrm_OCM;
    }

    public void setInStrmRd_OCM(InputStreamReader inStrmRd_OCM) {
        this.inStrmRd_OCM = inStrmRd_OCM;
    }

    public void setBufInput_OCM(BufferedInputStream bufInput_OCM) {
        this.bufInput_OCM = bufInput_OCM;
    }

    public void setBufRd_OCM(BufferedReader bufRd_OCM) {
        this.bufRd_OCM = bufRd_OCM;
    }

    public void setOutStrm_OCM(OutputStream outStrm_OCM) {
        this.outStrm_OCM = outStrm_OCM;
    }

    public void setPrtwt_OCM(PrintWriter prtwt_OCM) {
        this.prtwt_OCM = prtwt_OCM;
    }

    public void setWrite_file(String write_file) {
        this.write_file = write_file;
    }

    public void setWatchWindow(double watchWindow) {
        this.watchWindow = watchWindow;
    }

    public void setSlice(double slice) {
        this.slice = slice;
    }

    @Override
    public void run() {

        try {
            while (true) {

                int data_num = (int) Math.ceil(watchWindow / slice);
                byte[] receive = new byte[data_num * DOUBLE_FIELD_SIZE];
                double[] ocm_data = new double[data_num];
                int recv_width = data_num * DOUBLE_FIELD_SIZE;

                int position = 0;
                int len = 0;
                while (position < recv_width) {
                    len = inStrm_OCM.read(receive, position, recv_width - position);
                    position += len;
                }

                len = position;

//                double[] ocm_data = new double[len / DOUBLE_FIELD_SIZE];
                for (int i = 0, j = 0; i < len; i = i + DOUBLE_FIELD_SIZE, j++) {
                    ocm_data[j] = OCM_util.bytes2Double(receive, i);
                    System.out.println("slice[" + j + "]: " + ocm_data[j]);
//                    ocm_thread.setNames(Double.toString(value[j]));
                }
                Arrays.fill(ocm_data, 0);
                Arrays.fill(receive, (byte) 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
