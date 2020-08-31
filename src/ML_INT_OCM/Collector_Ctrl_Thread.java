package src.ML_INT_OCM;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tsf
 * @date 20-7-15
 * @desp collector ctrl thread: receive BER data from collector and reconfigure the 'ocm_conf'.
 */

public class Collector_Ctrl_Thread extends Thread {

    /* collector client socket for communicating with collector. */
    private Socket DASock;
    private InputStream inStrm_DA;
    private InputStreamReader inStrmRd_DA;
    private BufferedInputStream bufInput_DA;
    private BufferedReader bufRd_DA;
    private OutputStream outStrm_DA;
    private PrintWriter prtwt_DA;

    /* OCMSock send fun. */
    private volatile PrintWriter prtwt_OCM;

    /* Ocm_Monitor_Recv_Thread. */
    private volatile Ocm_Monitor_Send_Thread ocm_monitor_send_thread;
    private volatile Ocm_Monitor_Recv_Thread ocm_monitor_recv_thread;
    private volatile int sleep_ms;

    Collector_Ctrl_Thread( Ocm_Monitor_Send_Thread ocm_monitor_send_thread, Ocm_Monitor_Recv_Thread ocm_monitor_recv_thread) {
        DASock = Ocm_Monitor_Collector_Ctrl.getDASock();
        inStrm_DA = Ocm_Monitor_Collector_Ctrl.getInStrm_DA();
        inStrmRd_DA = Ocm_Monitor_Collector_Ctrl.getInStrmRd_DA();
        bufInput_DA = Ocm_Monitor_Collector_Ctrl.getBufInput_DA();
        bufRd_DA = Ocm_Monitor_Collector_Ctrl.getBufRd_DA();
        outStrm_DA = Ocm_Monitor_Collector_Ctrl.getOutStrm_DA();
        prtwt_DA = Ocm_Monitor_Collector_Ctrl.getPrtwt_DA();

        /* send ocm_conf to ocm. */
        prtwt_OCM = Ocm_Monitor_Collector_Ctrl.getPrtwt_OCM();
        this.sleep_ms = 1000;

        /* call ocm_monitor_recv_thread set_fun(). */
        this.ocm_monitor_send_thread = ocm_monitor_send_thread;
        this.ocm_monitor_recv_thread = ocm_monitor_recv_thread;
    }

    public void setPrtwt_OCM(PrintWriter prtwt_OCM) {
        this.prtwt_OCM = prtwt_OCM;
    }

    public void setBufInput_DA(BufferedInputStream bufInput_DA) {
        this.bufInput_DA = bufInput_DA;
    }

    public void setBufRd_DA(BufferedReader bufRd_DA) {
        this.bufRd_DA = bufRd_DA;
    }

    public void setDASock(Socket DASock) {
        this.DASock = DASock;
    }

    public void setInStrm_DA(InputStream inStrm_DA) {
        this.inStrm_DA = inStrm_DA;
    }

    public void setInStrmRd_DA(InputStreamReader inStrmRd_DA) {
        this.inStrmRd_DA = inStrmRd_DA;
    }

    public void setOcm_monitor_thread(Ocm_Monitor_Recv_Thread ocm_monitor_recv_thread) {
        this.ocm_monitor_recv_thread = ocm_monitor_recv_thread;
    }

    public void setOutStrm_DA(OutputStream outStrm_DA) {
        this.outStrm_DA = outStrm_DA;
    }

    public void setPrtwt_DA(PrintWriter prtwt_DA) {
        this.prtwt_DA = prtwt_DA;
    }

    public void setSleep_ms(int sleep_ms) {
        this.sleep_ms = sleep_ms;
    }

    @Override
    public void run() {
        int cnt = 0;

        double start_freq = 192.475;   // THz, central wavelength
        double watchWindow = 0.05;     // THz

        double min_slice = 0.0003125;
        double max_slice = 0.05;
        double slice = max_slice;  // 0.0003125 0.05

        String ocm_conf = OCM_util.construct_ocm_conf(start_freq, watchWindow, slice);

        boolean run_once =  true;
        try {
            while (true) {
                byte[] receive = new byte[100];
                int len = inStrm_DA.read(receive, 0, receive.length);

                if (run_once) {
                    ocm_monitor_send_thread.setShould_send(true);
                    run_once = false;
                }

                if (len < 0) {
                    break;
                }
                System.out.println("cnt: " + cnt);

                if(cnt == 2) {
                    slice = 0.01;
                    ocm_monitor_recv_thread.setSlice(slice);
                    ocm_conf = OCM_util.construct_ocm_conf(start_freq, watchWindow, slice);
//                    ocm_monitor_recv_thread.setRecvData(watchWindow, slice);
                    ocm_monitor_send_thread.setOcm_conf(ocm_conf);
                }

                if(cnt == 4) {
                    slice = 0.0003125;
                    ocm_monitor_recv_thread.setSlice(slice);
                    ocm_conf = OCM_util.construct_ocm_conf(start_freq, watchWindow, slice);
//                    ocm_monitor_recv_thread.setRecvData(watchWindow, slice);
                    ocm_monitor_send_thread.setOcm_conf(ocm_conf);
                }

                if(cnt == 6) {
                    slice = 0.05;
                    ocm_monitor_recv_thread.setSlice(slice);
                    ocm_conf = OCM_util.construct_ocm_conf(start_freq, watchWindow, slice);
//                    ocm_monitor_recv_thread.setRecvData(watchWindow, slice);
                    ocm_monitor_send_thread.setOcm_conf(ocm_conf);
                }

                if(cnt == 8) {
                    sleep_ms = 2000;
                }


                if ("end".equals(ocm_conf)) {
                    break;
                }
                prtwt_OCM.println(ocm_conf);
                prtwt_OCM.flush();

                cnt += 1;

                try {
                    Thread.sleep(sleep_ms);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println("time: " + df.format(new Date()));
                System.out.println("DA: " + cnt + ", " + OCM_util.bytes2Double(receive, 0));
                System.out.println("OCM: " + cnt + ", " + ocm_conf);
                System.out.println("\n");
            }

            Ocm_Monitor_Collector_Ctrl.teardownOcmSocket();
            Ocm_Monitor_Collector_Ctrl.teardownDaSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
