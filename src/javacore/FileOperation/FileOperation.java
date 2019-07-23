package src.javacore.FileOperation;

import java.io.*;
import java.util.Arrays;


/**
 * @author tsf
 * @date 19-7-23
 * @desp
 */
public class FileOperation {

    /* convert string array to int array.
    *
    * */
    public int [] strArrayToIntArray(String[] strArr) {
       int [] intArr = new int[strArr.length];

       for (int i = 0; i < strArr.length; i++) {
           intArr[i] = Integer.parseInt(strArr[i], 10);
       }

       return intArr;
    }


    /* read csv file, then encrypted the data, finally write to a new file. */
    public void readCSV() {

        String path = "/home/tsf/ideaProject/JavaProject/src/javacore/FileOperation/";
        String readPrefix = "all_exception_tsf_kjw_del_repeat_train";
        String readFileName = path + readPrefix + ".csv";
        String writePrefix = "all_exception_tsf_kjw_del_repeat_train_encrypted";
        String writeFileName = path + writePrefix + ".csv";

        BufferedReader reader = null;
        BufferedWriter writer = null;
        String line = "";
        String seperator = ",";

        String[] recordLine;
        int[] readRecord;
        int[] writeRecord;

        try {
            reader = new BufferedReader(new FileReader(readFileName));
            writer = new BufferedWriter(new FileWriter(writeFileName));
            while ((line = reader.readLine()) != null) {
                recordLine = line.split(seperator);
                readRecord = strArrayToIntArray(recordLine);
                System.out.println(readRecord[0] + " " + readRecord[1] + " " + readRecord[2] + " " + readRecord[3] +
                        readRecord[4] + " " + readRecord[5] + " " + readRecord[6]);

                /* write to file. */
                for (int i = 0; i < readRecord.length; i++) {
                    writer.write(String.valueOf(readRecord[i]));
                    writer.write(",");
                }
                writer.newLine();
            }

            writer.close();

        } catch (IOException io) {
            io.printStackTrace();
        }
    }


    public static void main(String[] args) {
        FileOperation fileOperation = new FileOperation();
        fileOperation.readCSV();
    }


}
