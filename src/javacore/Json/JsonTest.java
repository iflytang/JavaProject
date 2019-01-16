package src.javacore.Json;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;


/**
 * @author tsf
 * @date 18-10-11.
 * @desc
 */
public class JsonTest {

    // create json
    public void jsonStr() {
        System.out.println("********* jsonStr *********");
        JsonObject object = Json.object().add("name", "iflytang")
                                        .add("gender", "male")
                                        .add("age", 11);
        JsonObject object1 = Json.object().add("index", 1)
                                        .add("person", object);

        System.out.println(object.toString());
        System.out.println(object1.toString());
    }

    // read json file
    public void readJson() {
        System.out.println("********* readJson *********");
        try {
            JsonObject object = Json.parse(new FileReader("weather.json")).asObject();
            System.out.println("resultcode: " + object.get("resultcode").asInt());
            System.out.println("reason: " + object.get("reason").asString());

            JsonObject result = object.get("result").asObject();
            System.out.println("result.sk: " + result.get("sk").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // read json array
    public void readJsonArr() {
        System.out.println("********* readJsonArr *********");
        try {
            JsonObject object = Json.parse(new FileReader("catArr.json")).asObject();
            JsonArray array = object.get("info").asArray();
            JsonObject object1 = array.get(0).asObject();
            System.out.println(array.toString() + "\n" + object1);
            if (object1.get("id").asInt() == 1) {
                JsonObject stu1 = Json.object().add("id", 12)
                        .add("name", "Tom").add("master", "Mark");
                array.set(0, stu1);
                System.out.println(array.toString() + "\n" + stu1);
            }
            System.out.println(array.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // write json output to file
    public void wirteJsonStr(String fileName) {
        System.out.println("********* wirteJsonStr *********");
        try {
            // delete if file exist
//            File file = new File(fileName);
//            if (file.exists()) {
//                file.delete();
//            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));
            JsonObject object = Json.parse(new FileReader("catArr.json")).asObject();
            JsonArray array = object.get("info").asArray();

            writer.append(' ');  // clear file content

            for (int i=0; i<100; i++) {
                writer.append(i + ": " + array.toString() + "\n");
//                if (i % 50 == 0) {
//                    writer.close();
//                    writer = new BufferedWriter(new FileWriter(fileName, false));
//                }
            }

            // write json array
            JsonObject stu1 = Json.object().add("name", "Alice")
                                            .add("id", 0x01);
            JsonObject stu2 = Json.object().add("name", "Bob")
                                            .add("id", 0x02);
            JsonObject stu3 = Json.object().add("name", "Candy")
                                            .add("id", 0x03);

            JsonArray stu = new JsonArray();
            stu.add(stu1).add(stu2).add(stu3);
            JsonObject students = Json.object().add("students",stu);
            writer.append(students.toString() + "\n");

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createJsonArr() {
        System.out.println("********* createJsonArr *********");
        JsonArray stuArr = new JsonArray();
        JsonObject students = Json.object().add("students",stuArr);
        JsonObject stu1 = Json.object().add("name", "Alice")
                .add("id", 0x01);
        JsonObject stu2 = Json.object().add("name", "Bob")
                .add("id", 0x02);
        JsonObject stu3 = Json.object().add("name", "Candy")
                .add("id", 0x03);
        JsonObject stu4 = Json.object().add("name", "Dan")
                .add("id", 0x04);
//        JsonArray stuArr = new JsonArray();
        stuArr.add(stu1).add(stu2).add(stu3);
//        JsonObject students = Json.object().add("students",stuArr);
        stuArr.add(stu4);
//        students.add("students", stuArr);
        System.out.println(students.toString());
    }

    public void testTime() {
        long currTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currTime);
        System.out.println("timestamp: " + timestamp);
        System.out.println(timestamp.getTime());
        System.out.println("currTime: " + currTime);

        for (int i=0; i < 20; i++) {
            timestamp = new Timestamp(System.currentTimeMillis());
            timestamp.toString();
        }
        System.out.println("timestamp: " + timestamp);
        System.out.println(timestamp.getTime());
        System.out.println("currTime: " + currTime);
    }

    public void testMap() {
        Map<String, String> sel_int_hash = new HashMap<>();
        String hash = "12";
        if (!sel_int_hash.containsKey("sel_int_hash") ||
                (hash != sel_int_hash.get("sel_int_hash"))) {
            System.out.println("no keys in testMap");
            sel_int_hash.put("sel_int_hash", "testMap");
        }

        if (!sel_int_hash.containsKey("sel_int_hash") ||
                (hash != sel_int_hash.get("sel_int_hash"))) {
            System.out.println("have keys in testMap");
        }
    }

    public String byte2HexStr(byte byteNum) {
        String hex = Integer.toHexString(   byteNum & 0xff);
        if (hex.length() == 1) {
            hex = '0' + hex;
        }
        System.out.println(hex);
        return hex;
    }

    public String funcByteHexStr() {
        String deviceId = "pof:0000000000000012";
        String device = deviceId.substring(18, 20);
        byte dpid = Integer.valueOf(device).byteValue();

        int k = 2, b = 1;
        byte y = (byte) (k * dpid + b);
        System.out.println("deviceId: " + deviceId + ", dpid = " + dpid + ", y = " + y);
        return byte2HexStr(y);
    }

    public static void main(String[] args) {

        JsonTest test = new JsonTest();

        File file = new File("weather.json");
        System.out.println(file.getAbsolutePath());

//        test.jsonStr();
//        test.readJson();
//        test.readJsonArr();

//        test.wirteJsonStr("result.txt");
//        test.createJsonArr();

//        test.testTime();
//        test.testMap();

//        byte mapInfo = 0x1;
//        test.byte2HexStr(mapInfo);

        test.funcByteHexStr();



    }
}
