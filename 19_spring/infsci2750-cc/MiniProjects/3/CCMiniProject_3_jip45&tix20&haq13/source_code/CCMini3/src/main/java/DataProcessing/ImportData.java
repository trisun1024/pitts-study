package DataProcessing;

import com.datastax.driver.core.Cluster;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ImportData {

    private static ArrayList<String> temp;

    public static void main(String[] args) throws IOException {

        // Create ArrayList
        temp = new ArrayList<String>();

        // file path
        String file = "data//in//access_log";
        // read file
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        int i = 1;
        int count = 1;
        while ((line = br.readLine()) != null) {
            String ip = "";
            String path = "";
            String status = "";
            String size = "";
            String[] strS = line.split(" ");
            if (strS.length > 1) {
                ip = strS[0];
            }
            String[] strQ = line.split("\"");
            if (strQ.length > 1) {
                String strPath = strQ[1];
                String[] strSS = strPath.split(" ");
                if (strSS.length > 1) {
                    path = strSS[1];
                }
                String strPath1 = strQ[2];
                String[] strSSS = strPath1.split(" ");
                if (strSSS.length == 3) {
                    status = strSSS[1];
                    size = strSSS[2];
                }
            }
            if (ip.equals("")) ip = "unknown";
            if (path.equals("")) path = "unknown";
            if (status.equals("")) ip = "unknown";
            if (size.equals("")) path = "unknown";
            temp.add("" + i + " " + ip + " " + path + " " + status + " " + size + " " + 1);
            count++;
            if (count % 1000000 == 0) i++;
        }

        String outPath = "data//out/access_log.csv";
        FileWriter fw = new FileWriter(outPath + "\n");
        fw.write("key ip path status size count");
        Iterator itr = temp.iterator();
        while (itr.hasNext()) {
            fw.write(itr.next() + "\n");
        }
    }

}
