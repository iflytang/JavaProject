package src.ML_INT_OCM;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author tsf
 * @date 20-7-15
 * @desp monitor thread: recv ocm data and write to file.
 */


public class OcmMonitorCollectorCtrlThread extends Thread {
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

    private File wrt_fd;
    private BufferedWriter buf_wrt;

    OcmMonitorCollectorCtrlThread(String write_file, double watchWindow, double slice) {
        this.write_file = write_file;
        this.watchWindow = watchWindow;
        this.slice = slice;

        try {
            this.wrt_fd = new File(String.format(write_file));
            this.wrt_fd.createNewFile();
            this.buf_wrt = new BufferedWriter(new FileWriter(this.wrt_fd));
        } catch (Exception e) {
            e.printStackTrace();
        }

        OCM = OCM_Monitor_Collector_Ctrl.getOCMSock();
        inStrm_OCM = OCM_Monitor_Collector_Ctrl.getInStrm_OCM();
        inStrmRd_OCM = OCM_Monitor_Collector_Ctrl.getInStrmRd_OCM();
        bufInput_OCM = OCM_Monitor_Collector_Ctrl.getBufInput_OCM();
        bufRd_OCM = OCM_Monitor_Collector_Ctrl.getBufRd_OCM();
        outStrm_OCM = OCM_Monitor_Collector_Ctrl.getOutStrm_OCM();
        prtwt_OCM = OCM_Monitor_Collector_Ctrl.getPrtwt_OCM();

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
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                buf_wrt.write(df.format(new Date()) + "\t");
                for (int i = 0, j = 0; i < len; i = i + DOUBLE_FIELD_SIZE, j++) {
                    ocm_data[j] = OCM_util.bytes2Double(receive, i);
//                    System.out.println("slice[" + j + "]: " + ocm_data[j]);
                    buf_wrt.write(ocm_data[j] + "\t");
                }
                buf_wrt.write("\n");
                buf_wrt.flush();

                Arrays.fill(ocm_data, 0);
                Arrays.fill(receive, (byte) 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}