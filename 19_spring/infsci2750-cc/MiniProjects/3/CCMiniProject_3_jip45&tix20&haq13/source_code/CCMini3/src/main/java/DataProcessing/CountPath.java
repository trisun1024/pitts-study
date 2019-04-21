package DataProcessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CountPath {
    public static void main(String[] args) throws IOException {
        //
        HashMap<String, Integer> pathList = new HashMap<>();
        // file path
        String file = "data//in//access_log";
        // read file
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            String path = "";
            String[] strQ = line.split("\"");
            if (strQ.length > 1) {
                String strPath = strQ[1];
                String[] strSS = strPath.split(" ");
                if (strSS.length > 1) {
                    path = strSS[1];
                }
            }
            int value;
            if (pathList.containsKey(path)) {
                value = pathList.getOrDefault(path, 1);
                value++;
            } else {
                value = 1;
            }
            pathList.put(path, value);
        }

        String outPath = "data//out/pathcount.csv";
        FileWriter fw = new FileWriter(outPath);
        fw.write("path count" + "\n");
        Iterator<Map.Entry<String, Integer>> itr = pathList.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, Integer> entry = itr.next();
            fw.write(entry.getKey() + " " + entry.getValue().toString() + "\n");
        }
    }
}
