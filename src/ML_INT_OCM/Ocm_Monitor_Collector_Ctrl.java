package src.ML_INT_OCM;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author tsf
 * @date 20-7-7
 * @desp dynamically adjustable periodical request OCM data.
 *       besides, we leverage int-collector's data to adjust the parameters,
 *       i.e., <request_frequency, request_slice_precision>
 */

public class Ocm_Monitor_Collector_Ctrl {
    /* ocm server socket info <ip_addr, port> */
    private static final String SER_OCM_ADDR = "192.168.108.126";
    private static final int SER_OCM_PORT = 9999;

    /* collector server socket info  <ip_addr, port>, also known as DA (data analyzer) */
    private static final String SER_DA_ADDR = "192.168.108.221";
    private static final int SER_DA_PORT = 2018;

    private static final int DOUBLE_FIELD_SIZE = 8;

    public static final double WATCH_WINDOW = 0.05;
    public static final double MIN_SLICE = 0.0003125;
    public static final int SLICE_NUM = (int) Math.ceil(WATCH_WINDOW / MIN_SLICE);

    private static HashMap<Integer, Double> BER_map_slice = init_BER_map_to_slice_table();
    private static HashMap<Integer, Integer> BER_map_request_ms_period = init_BER_map_to_request_ms_period_table();

    /* ocm client socket for communicating with OCM. */
    private static Socket OCMSock;
    private static InputStream inStrm_OCM;
    private static InputStreamReader inStrmRd_OCM;
    private static BufferedInputStream bufInput_OCM;
    private static BufferedReader bufRd_OCM;
    private static OutputStream outStrm_OCM;
    private static PrintWriter prtwt_OCM;

    /* collector client socket for communicating with collector. */
    private static Socket DASock;
    private static InputStream inStrm_DA;
    private static InputStreamReader inStrmRd_DA;
    private static BufferedInputStream bufInput_DA;
    private static BufferedReader bufRd_DA;
    private static OutputStream outStrm_DA;
    private static PrintWriter prtwt_DA;

    /* file writer to write ber. */
    private static File wrt_ber_fd;
    private static BufferedWriter buf_ber_wrt;

    public File getWrt_ber_fd() {
        return wrt_ber_fd;
    }

    public BufferedWriter getBuf_ber_wrt() {
        return buf_ber_wrt;
    }

    public static HashMap<Integer, Double> getBER_map_slice() {
        return BER_map_slice;
    }

    public static String getSerOcmAddr() {
        return SER_OCM_ADDR;
    }

    public static int getSerOcmPort() {
        return SER_OCM_PORT;
    }

    public static Socket getOCMSock() {
        return OCMSock;
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

    public static String getSerDaAddr() {
        return SER_DA_ADDR;
    }

    public static int getSerDaPort() {
        return SER_DA_PORT;
    }

    public static Socket getDASock() {
        return DASock;
    }

    public static InputStream getInStrm_DA() {
        return inStrm_DA;
    }

    public static InputStreamReader getInStrmRd_DA() {
        return inStrmRd_DA;
    }

    public static BufferedInputStream getBufInput_DA() {
        return bufInput_DA;
    }

    public static BufferedReader getBufRd_DA() {
        return bufRd_DA;
    }

    public static OutputStream getOutStrm_DA() {
        return outStrm_DA;
    }

    public static PrintWriter getPrtwt_DA() {
        return prtwt_DA;
    }

    /* create ber files, and write the ber info into files. */
    public static void init_ber_file_writer(String write_file) {
        try {
            wrt_ber_fd = new File(String.format(write_file));
            if (!wrt_ber_fd.exists()) {
                wrt_ber_fd.createNewFile();
            }
            buf_ber_wrt = new BufferedWriter(new FileWriter(wrt_ber_fd));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* close file writer. */
    public static void close_ber_file_writer() {
        try {
            buf_ber_wrt.flush();
            buf_ber_wrt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* set tcp socket connection to OCMSock. */
    public static void setupSocketToOcm(){
        try{
            OCMSock = new Socket(SER_OCM_ADDR, SER_OCM_PORT);  // build socket object and setup connection
            inStrm_OCM = OCMSock.getInputStream();
            inStrmRd_OCM = new InputStreamReader(inStrm_OCM);
            bufInput_OCM = new BufferedInputStream(inStrm_OCM);
            bufRd_OCM = new BufferedReader(inStrmRd_OCM);
            outStrm_OCM = OCMSock.getOutputStream();
            prtwt_OCM = new PrintWriter(outStrm_OCM);
            System.out.println("OCMSock: " + OCMSock);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /* set tcp socket connection to DASock. */
    public static void setupSocketToDA(){
        try{
            DASock = new Socket(SER_DA_ADDR, SER_DA_PORT);  // build socket object and setup connection
            inStrm_DA = DASock.getInputStream();
            inStrmRd_DA = new InputStreamReader(inStrm_DA);
            bufInput_DA = new BufferedInputStream(inStrm_DA);
            bufRd_DA = new BufferedReader(inStrmRd_DA);
            outStrm_DA = DASock.getOutputStream();
            prtwt_DA = new PrintWriter(outStrm_DA);
            System.out.println("DASock: " + DASock);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void teardownOcmSocket() {
        try {
            bufRd_OCM.close();
            inStrm_OCM.close();
            prtwt_OCM.close();
            OCMSock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void teardownDaSocket() {
        try {
            bufRd_DA.close();
            inStrm_DA.close();
            prtwt_DA.close();
            DASock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* <order, slice> */
    public static HashMap<Integer, Double> init_BER_map_to_slice_table() {
        HashMap<Integer, Double> BER_map_slice = new HashMap<>();

        /* for different severe degree level, we have different slice granularity.
        *  slice_num = watch_win / slice_unit.
        * */
        double severe_level_slice_unit = 0.0003125;  // 0.05 / 0.0003125 = 160 points
        double medium_level_slice_uint = severe_level_slice_unit * 2;    // 80 points, * 2
        double normal_level_slice_uint = severe_level_slice_unit * 8;    // 20 points, * 8

        int order_1 = 1;
        int order_2 = 2;
        int order_3 = 3;
        int order_4 = 4;
        int order_5 = 5;
        int order_6 = 6;
        int order_7 = 7;
        int order_8 = 8;
        int order_9 = 9;

        /* severe degraded link state. */
//        double slice_1 = severe_level_slice_unit;
//        double slice_2 = severe_level_slice_unit;
//        double slice_3 = severe_level_slice_unit;

        /* medium degraded link state. */
//        double slice_4 = medium_level_slice_uint;
//        double slice_5 = medium_level_slice_uint;

        /* normal link state. */
//        double slice_6 = normal_level_slice_uint;
//        double slice_7 = normal_level_slice_uint;
//        double slice_8 = normal_level_slice_uint;
//        double slice_9 = normal_level_slice_uint;

//        BER_map_slice.put(order_1, slice_1);
//        BER_map_slice.put(order_2, slice_2);
//        BER_map_slice.put(order_3, slice_3);
//        BER_map_slice.put(order_4, slice_4);
//        BER_map_slice.put(order_5, slice_5);
//
//        BER_map_slice.put(order_6, slice_6);
//        BER_map_slice.put(order_7, slice_7);
//        BER_map_slice.put(order_8, slice_8);
//        BER_map_slice.put(order_9, slice_9);

        BER_map_slice.put(order_1, severe_level_slice_unit);
        BER_map_slice.put(order_2, severe_level_slice_unit);
        BER_map_slice.put(order_3, severe_level_slice_unit);
        BER_map_slice.put(order_4, severe_level_slice_unit);
        BER_map_slice.put(order_5, severe_level_slice_unit);
        BER_map_slice.put(order_6, severe_level_slice_unit);   // uca 1,1,6

        BER_map_slice.put(order_7, medium_level_slice_uint);   // uca 1,1.4

        BER_map_slice.put(order_8, normal_level_slice_uint);   // uca, 1,1,0
        BER_map_slice.put(order_9, normal_level_slice_uint);

        return BER_map_slice;
    }

    /* <order, sleep_ms> */
    public static HashMap<Integer, Integer> init_BER_map_to_request_ms_period_table() {
        HashMap<Integer, Integer> BER_map_period = new HashMap<>();

        int order_1 = 1;
        int order_2 = 2;
        int order_3 = 3;
        int order_4 = 4;
        int order_5 = 5;
        int order_6 = 6;
        int order_7 = 7;
        int order_8 = 8;
        int order_9 = 9;

        /* for different severe degree level, we have different request period, ms.
         * */
        int severe_level_request_period_unit = 1000;
        int medium_level_request_period_uint = 2000;
        int normal_level_request_period_uint = 5000;

        BER_map_period.put(order_1, severe_level_request_period_unit);
        BER_map_period.put(order_2, severe_level_request_period_unit);
        BER_map_period.put(order_3, severe_level_request_period_unit);
        BER_map_period.put(order_4, severe_level_request_period_unit);
        BER_map_period.put(order_5, severe_level_request_period_unit);
        BER_map_period.put(order_6, severe_level_request_period_unit);

        BER_map_period.put(order_7, medium_level_request_period_uint);

        BER_map_period.put(order_8, normal_level_request_period_uint);
        BER_map_period.put(order_9, normal_level_request_period_uint);

        return BER_map_period;
    }

    public static void main (String[] args) {
        double start_freq = 192.575;   // THz, central wavelength
        double watchWindow = 0.05;     // THz

        double min_slice = 0.0003125;
        double max_slice = 0.005;
        double slice = min_slice;  // expected slice

        int default_sleep_ms = 1000;  // default sleep 1s
        int expected_request_ms_period = default_sleep_ms; // expected request period
        int expected_request_s_period = 1;

        String file_prefix = "src/ML_INT_OCM/";
        String file_ocm_power_name = file_prefix + "result_ocm_power.txt";
        String file_da_ber_name = file_prefix + "result_da_ber.txt";

        String ocm_conf = OCM_util.construct_ocm_conf(start_freq, watchWindow, slice);   // default
        int sleep_ms = 1000;

        try {
            init_ber_file_writer(file_da_ber_name);
            setupSocketToOcm();
            setupSocketToDA();

            /* send ocm_conf to ocm agent. */
            Ocm_Monitor_Send_Thread ocm_monitor_send_thread = new Ocm_Monitor_Send_Thread(start_freq, watchWindow, slice, default_sleep_ms);
            ocm_monitor_send_thread.start();

            /* request ocm data from ocm agent. */
            Ocm_Monitor_Recv_Thread ocm_monitor_recv_thread = new Ocm_Monitor_Recv_Thread(file_ocm_power_name, watchWindow, slice);
            ocm_monitor_recv_thread.start();

            /* receive 'ber' data and reconfigure 'ocm_conf'
            *  teardown sockets in da_thread.
            * */
//            Collector_Ctrl_Thread da_thread = new Collector_Ctrl_Thread(ocm_monitor_send_thread, ocm_monitor_recv_thread);
//            da_thread.start();

            int cnt = 0, i = 0, j = 0;
            boolean run_once = true;

            double ber = 0.0;  // sw1
            double[] ber_arr = new double[3];  // sw1-sw3
            int cur_ber_order = 0, old_ber_order = 0;  // sw1

            boolean ocm_conf_update_flag = false;  // ocm
            boolean should_update_right_now = false;
            int ocm_conf_no_update_cnt = 0;

            while (true) {
                byte[] receive = new byte[100];
                ocm_conf_update_flag = false;  // if ber changes ocm_conf, then turn it to be true
                int len = inStrm_DA.read(receive, 0, receive.length);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (i = 0, j = 0; i < len; i = i + DOUBLE_FIELD_SIZE, j++) {
                    ber_arr[j] = OCM_util.bytes2Double(receive, i);
                }
                ber = ber_arr[0];  // only parse sw1's ber

                /*System.out.printf("cnt: %d, ber: %.16g, ber[0]: %.16g, ber[1]: %.16g, ber[2]: %.16g\n",
                        cnt, ber, ber_arr[0], ber_arr[1], ber_arr[2]);*/


                if (run_once) {
                    ocm_monitor_send_thread.setShould_send(true);
                    run_once = false;
                }

                if (len < 0) {
                    break;
                }

//                System.out.printf("cnt: %d, ber: %.16g\n", cnt, ber);

                /* if order changes, reconfigure 'slice' precision. */
                cur_ber_order = OCM_util.get_order_of_ber(ber);
                System.out.println("cur_ber_order: " + cur_ber_order + ", old_ber_order: " + old_ber_order);
                if (cur_ber_order != old_ber_order) {  // update ocm_conf
                    slice = BER_map_slice.get(cur_ber_order);
//                    ocm_monitor_recv_thread.setSlice(slice);

                    ocm_conf = OCM_util.construct_ocm_conf(start_freq, watchWindow, slice);
//                    ocm_monitor_send_thread.setOcm_conf(ocm_conf);

//                    expected_request_period = BER_map_request_ms_period.get(cur_ber_order);
//                    ocm_monitor_send_thread.setSleep_ms(expected_request_period);

                    expected_request_s_period = BER_map_request_ms_period.get(cur_ber_order) / default_sleep_ms;

                    old_ber_order = cur_ber_order;

                    ocm_conf_update_flag = true;
                    should_update_right_now = true;

                } else {
                    ocm_conf_no_update_cnt++;

                    // try twice to update ocm_conf right now, because OCM Recv slow down one whole updated request
                    // period for ocm_conf update during my testing. if we try twice, it only slow down about 1s.
                    if (should_update_right_now  // try second time
                            || (ocm_conf_no_update_cnt >= (expected_request_s_period))) {
                        ocm_conf_update_flag = true;
                        should_update_right_now = false;
                    }
                }

                if (ocm_conf_update_flag) {
                    ocm_monitor_recv_thread.setSlice(slice);

                    ocm_monitor_send_thread.setShould_send(true);
                    ocm_monitor_send_thread.setOcm_conf(ocm_conf);

                    ocm_conf_no_update_cnt = 0;
                }

                System.out.printf("cnt: %d, update_flag: %s, period: %d, ber: %.16g, ber[0]: %.16g, ber[1]: %.16g, ber[2]: %.16g\n",
                        cnt, ocm_conf_update_flag, expected_request_s_period, ber, ber_arr[0], ber_arr[1], ber_arr[2]);

                /* format:
                * timestamp, cnt, recv_sw1_ber, ber_arr[0], ber_arr[1], ber_arr[2], start_freq, window, slice.
                */
                buf_ber_wrt.write(df.format(new Date()) + "\t" + cnt + "\t" + ber + "\t" +
                        ber_arr[0] + "\t" + ber_arr[1] + "\t" + ber_arr[2] + "\t" +
                        start_freq + "\t" + watchWindow + "\t" + slice + "\t" + ocm_conf_update_flag + "\t\n");
                buf_ber_wrt.flush();

/*
                if(cnt == 2) {
                    slice = 0.01;
                    ocm_monitor_recv_thread.setSlice(slice);
                    ocm_conf = OCM_util.construct_ocm_conf(start_freq, watchWindow, slice);
                    ocm_monitor_send_thread.setOcm_conf(ocm_conf);
                }

                if(cnt == 4) {
                    slice = 0.0003125;
                    ocm_monitor_recv_thread.setSlice(slice);
                    ocm_conf = OCM_util.construct_ocm_conf(start_freq, watchWindow, slice);
                    ocm_monitor_send_thread.setOcm_conf(ocm_conf);
                }

                if(cnt == 6) {
                    slice = 0.05;
                    ocm_monitor_recv_thread.setSlice(slice);
                    ocm_conf = OCM_util.construct_ocm_conf(start_freq, watchWindow, slice);
                    ocm_monitor_send_thread.setOcm_conf(ocm_conf);
                }

                if(cnt == 8) {
                    slice = 0.0003125;
                    ocm_monitor_recv_thread.setSlice(slice);

//                    sleep_ms = 5000;
//                    ocm_monitor_send_thread.setSleep_ms(sleep_ms);

                    ocm_conf = OCM_util.construct_ocm_conf(start_freq, watchWindow, slice);
                    ocm_monitor_send_thread.setOcm_conf(ocm_conf);
                }
*/

                cnt += 1;
            }

        } catch (Exception e) {
            teardownOcmSocket();
            teardownDaSocket();
            close_ber_file_writer();
            e.printStackTrace();
        }
    }
}
