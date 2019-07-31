package src.homomorphicencryption.EncryptData;

import src.homomorphicencryption.Paillier.DefaultPaillier;

import java.io.*;
import java.math.BigInteger;


/**
 * @author tsf
 * @date 19-7-23
 * @desp
 */
public class EncryptDataInPaillier {

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

    /* use the paillier's self-blinding property to encrypt data 'str' in integer. */
    public BigInteger paillierEncrypt(DefaultPaillier paillier, String strM, int rndX, int rndR, boolean isSeed) {
        BigInteger m = new BigInteger(strM);

        // self-blinding: D(E(x)g^nx) = nx+m mod n
//        BigInteger c1 = paillier.Encrypt(m);
//        BigInteger c2 = paillier.gxPowMod();             // random generate 'x' in g^nx

        /* generate small random. */
        BigInteger c1 = paillier.Encrypt(m, rndR, isSeed);
        BigInteger c2 = paillier.gxPowMod(rndX, isSeed);   // adjust the g^nx range, if isSeed, generate bigX in [1, rndX]

        return paillier.CiperMultiply(c1, c2);
    }


    /* read csv file, then encrypted the data, finally write to a new file. */
    public void readCSV() {

        String path = "/home/tsf/ideaProject/JavaProject/src/homomorphicencryption/EncryptData/";
        String readPrefix = "all_exception_tsf_kjw_del_repeat_train";
        String readFileName = path + readPrefix + ".csv";
        String writePrefix = "all_exception_tsf_kjw_del_repeat_train_encrypted_in_paillier";
        String writeFileName = path + writePrefix + ".csv";

        BufferedReader reader = null;
        BufferedWriter writer = null;
        String line = "";
        String seperator = ",";

        String[] recordLine;
        int[] readRecord;
        String[] writeRecord = new String[7];

        /* to encrypt data in paillier algorithm. */
        DefaultPaillier paillier = new DefaultPaillier();
        BigInteger key = paillier.KeyGen(32, 64);

        try {
            reader = new BufferedReader(new FileReader(readFileName));
            writer = new BufferedWriter(new FileWriter(writeFileName));

            while ((line = reader.readLine()) != null) {
                recordLine = line.split(seperator);
                readRecord = strArrayToIntArray(recordLine);
                System.out.println(readRecord[0] + " " + readRecord[1] + " " + readRecord[2] + " " + readRecord[3] +
                        readRecord[4] + " " + readRecord[5] + " " + readRecord[6]);

                /* process(encrypt) the data.
                 * col:description = 0:device; 1:latency; 2:power; 3:osnr;
                 *                   4:in_bd;  5:out_bd;  6:flag
                 * */
                writeRecord[0] = paillierEncrypt(paillier, String.valueOf(readRecord[0]), 3,4,true).toString();
                writeRecord[1] = paillierEncrypt(paillier, String.valueOf(readRecord[1]), 3,4,true).toString();
                writeRecord[2] = paillierEncrypt(paillier, String.valueOf(readRecord[2]), 3,4,true).toString();
                writeRecord[3] = paillierEncrypt(paillier, String.valueOf(readRecord[3]), 3,4,true).toString();
                writeRecord[4] = paillierEncrypt(paillier, String.valueOf(readRecord[4]), 3,4,true).toString();
                writeRecord[5] = paillierEncrypt(paillier, String.valueOf(readRecord[5]), 3,4,true).toString();
                writeRecord[6] = String.valueOf(readRecord[6]);

                /* write to file. */
                for (int i = 0; i < readRecord.length; i++) {
                    writer.write(String.valueOf(writeRecord[i]));
                    writer.write(",");
                }
                writer.newLine();
            }

            writer.write("p: " + paillier.getP() + ", q: " + paillier.getQ() + ", n: " + paillier.getN() +
                    ", bigX: " + paillier.getBigX() + ", g: " + paillier.getG() + ", lambda: " + paillier.getLambda()
                    + ", u: " + paillier.getU() + ", nSquare: " + paillier.getnSquare());
            writer.newLine();

            writer.close();
            System.out.println("p: " + paillier.getP() + ", q: " + paillier.getQ() + ", n: " + paillier.getN() +
                    ", bigX: " + paillier.getBigX() + ", g: " + paillier.getG() + ", lambda: " + paillier.getLambda()
                    + ", u: " + paillier.getU() + ", nSquare: " + paillier.getnSquare());

        } catch (IOException io) {
            io.printStackTrace();
        }
    }


    public static void main(String[] args) {
        EncryptDataInPaillier encryptData = new EncryptDataInPaillier();
        encryptData.readCSV();
    }


}
