package DataProcessing;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CountIP {

    public static void main(String[] args) throws IOException {
        //
        HashMap<String, Integer> ipList = new HashMap<>();
        // file path
        String file = "data//in//access_log";
        // read file
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            String ip = "";
            String[] strS = line.split(" ");
            if (strS.length > 1) {
                ip = strS[0];
            }
            int value;
            if (ipList.containsKey(ip)) {
                value = ipList.getOrDefault(ip, 1);
                value++;
            } else {
                value = 1;
            }
            ipList.put(ip, value);
        }

        String outPath = "data//out/ipcount.csv";
        FileWriter fw = new FileWriter(outPath);
        fw.write("ip count" + "\n");
        Iterator<Map.Entry<String, Integer>> itr = ipList.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, Integer> entry = itr.next();
            fw.write(entry.getKey() + " " + entry.getValue().toString() + "\n");
        }
    }
}
