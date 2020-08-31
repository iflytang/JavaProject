package src.ML_INT_OCM;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tsf
 * @date 20-7-18
 * @desp collector ctrl thread reconfigure the 'ocm_conf', then this thread send the updated 'ocm_conf'
 *       to OCM collector, finally ocm_recv_thread receive the requested data according to new 'ocm_conf'.
 */

public class Ocm_Monitor_Send_Thread extends Thread {
    /* ocm client socket for communicating with OCM. */
    private Socket OCMSock;
    private InputStream inStrm_OCM;
    private InputStreamReader inStrmRd_OCM;
    private BufferedInputStream bufInput_OCM;
    private BufferedReader bufRd_OCM;
    private OutputStream outStrm_OCM;
    private PrintWriter prtwt_OCM;

    /* ocm_conf parameters, these can be tuned. */
    private volatile double start_freq;   // THz, central wavelength
    private volatile double watchWindow;  // THz
    private volatile double slice;
    private volatile int sleep_ms;
    private volatile String ocm_conf;     // "start_freq watchWindow slice"
    private volatile Boolean should_send = false;

    Ocm_Monitor_Send_Thread(double start_freq, double watchWindow, double slice,  int sleep_ms) {
        OCMSock = Ocm_Monitor_Collector_Ctrl.getOCMSock();
        inStrm_OCM = Ocm_Monitor_Collector_Ctrl.getInStrm_OCM();
        inStrmRd_OCM = Ocm_Monitor_Collector_Ctrl.getInStrmRd_OCM();
        bufInput_OCM = Ocm_Monitor_Collector_Ctrl.getBufInput_OCM();
        bufRd_OCM = Ocm_Monitor_Collector_Ctrl.getBufRd_OCM();
        outStrm_OCM = Ocm_Monitor_Collector_Ctrl.getOutStrm_OCM();
        prtwt_OCM = Ocm_Monitor_Collector_Ctrl.getPrtwt_OCM();

        this.start_freq = start_freq;
        this.watchWindow = watchWindow;
        this.slice = slice;
        this.sleep_ms = sleep_ms;
        this.ocm_conf = OCM_util.construct_ocm_conf(start_freq, watchWindow, slice);
    }

    public void setSleep_ms(int sleep_ms) {
        this.sleep_ms = sleep_ms;
    }

    public void setSlice(double slice) {
        this.slice = slice;
    }

    public void setWatchWindow(double watchWindow) {
        this.watchWindow = watchWindow;
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

    public void setOcm_conf(String ocm_conf) {
        this.ocm_conf = ocm_conf;
    }

    public void setShould_send(Boolean should_send) {
        this.should_send = should_send;
    }

    public void setOCMSock(Socket OCMSock) {
        this.OCMSock = OCMSock;
    }

    public void setStart_freq(double start_freq) {
        this.start_freq = start_freq;
    }


    @Override
    public void run() {
        try {
            while (true) {

                if ("end".equals(ocm_conf)) {
                    break;
                }

                if (should_send) {
                    prtwt_OCM.println(ocm_conf);
                    prtwt_OCM.flush();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    System.out.println("\ntime: " + df.format(new Date()));
                    System.out.println("OCM Send (ocm_conf): " + ocm_conf);

//                    Thread.sleep(sleep_ms);

                    should_send = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
